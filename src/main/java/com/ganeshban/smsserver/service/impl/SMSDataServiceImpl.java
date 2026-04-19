package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.SMSDataDTO;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.model.ConfigurationModel;
import com.ganeshban.smsserver.model.SMSDataModel;
import com.ganeshban.smsserver.repository.SMSDataRepository;
import com.ganeshban.smsserver.service.ConfigurationService;
import com.ganeshban.smsserver.service.SmsDataService;
import com.ganeshban.smsserver.transformer.SMSDataTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ganeshban.smsserver.utils.Constants.ErrorMessage.INVALID_RECIPIENT;
import static com.ganeshban.smsserver.utils.Constants.ErrorMessage.SENDER_NOT_REGISTER;
import static com.ganeshban.smsserver.utils.Constants.Keyword.ACTIVE;
import static com.ganeshban.smsserver.utils.Constants.Keyword.AUTO;

@RequiredArgsConstructor
@Service
public class SMSDataServiceImpl implements SmsDataService {
    private final SMSDataRepository repo;
    private final ClientInfoServiceImpl clientInfoService;
    private final ConfigurationService configurationService;
    private final SMSDataTransformer transformer;

    private String senderPhone = "";

    @Override
    public List<SMSDataModel> newMessage(SMSDataDTO request, String code) throws NotFound {
        validateSender(code, List.of(request.getSender()));
        return saveBatchData(code, List.of(request));
    }

    @Override
    public List<SMSDataModel> newMessageInBulk(List<SMSDataDTO> requests, String code) throws NotFound {
        List<String> senders = requests.stream().map(SMSDataDTO::getSender).distinct().toList();
        validateSender(code, senders);
        return saveBatchData(code, requests);
    }

    @Override
    public List<SMSDataModel> findAllByCode(String code) {
        List<SMSDataEntity> entities = repo.findByClientCode(code);
        return entities.stream().map(transformer::toModel).toList();
    }

    @Override
    public List<SMSDataModel> findAllSendingMessage(String code, String sender, boolean hold) {
        List<SMSDataEntity> smsList = repo.findNewSMSByCodeAndSender(code, sender);

        if (hold) {
            smsList.forEach(x -> x.setSentAfterDateTime(LocalDateTime.now().plusMinutes(1)));
            repo.saveAll(smsList);
        }

        smsList.forEach(x -> {
            x.setPriority(null);
            x.setClientCode(null);
            x.setStatus(null);
            x.setCreatedAt(null);
        });
        return smsList.stream().map(transformer::toModel).toList();
    }

    @Override
    public boolean markAsSend(List<String> ids, String code) {
        List<SMSDataEntity> smsList = repo.findAllByListOfIdAndCode(ids, code);
        smsList.forEach(sms -> {
            sms.setSync(true);
            sms.setSentDateTime(LocalDateTime.now());
        });
        repo.saveAll(smsList);
        return true;
    }

    private void validateReceiver(String receiver) throws NotFound {
        boolean isCorrectLength = receiver.length() >= 10 && receiver.length() <= 15;
        if (receiver.isBlank() || !isCorrectLength) {
            throw new NotFound(INVALID_RECIPIENT + receiver);
        }
    }

    private void validateSender(String code, List<String> senders) throws NotFound {
        List<String> allowedSender = getAllowedSender(code);
        for (String sender : senders) {
            if (!allowedSender.contains(sender)) {
                throw new NotFound(SENDER_NOT_REGISTER);
            }
        }
    }

    private List<String> validateMessage(String message, List<String> messages) throws NotFound {

        List<String> list = new ArrayList<>();
        if (message != null && !message.isBlank()) {
            list.add(message);
        }
        if (messages != null && !messages.isEmpty()) {
            list.addAll(messages);
        }
        if (list.isEmpty()) {
            throw new NotFound("Please Provide the message.");
        }

        return list;
    }

    private List<String> getAllowedSender(String code) {
        List<ConfigurationModel> list = configurationService.getConfigModelByClientCode(code);
        List<String> senders = new ArrayList<>(list.stream()
                .filter(x -> x.getStatus().equalsIgnoreCase(ACTIVE))
                .map(ConfigurationModel::getSenderPhoneNumber)
                .filter(Objects::nonNull)
                .toList());
        if (!senders.isEmpty()) {
            senderPhone = senders.getFirst();
        }
        senders.add(AUTO);
        return senders;
    }

    private List<SMSDataEntity> transferToMultipleMessage(String code, SMSDataDTO... messages) throws NotFound {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<SMSDataEntity> allMessage = new ArrayList<>();
        for (SMSDataDTO sms : messages) {
            List<String> allTexts = validateMessage(sms.getMessage(), sms.getMessages());

            Set<String> receivers = Arrays.stream(sms.getReceiver().split(",")).filter(x -> !x.isBlank()).map(String::strip).collect(Collectors.toSet());
            for (String receiver : receivers) {
                validateReceiver(receiver);
                for (String texts : allTexts) {

                    int size = 160;
                    int len = texts.length() / 160;
                    int start = 0;
                    int end = size;
                    for (int i = 0; i <= len; i++) {
                        String text = texts.substring(start, Math.min(end, texts.length()));
                        start += size;
                        end += size;

                        var entity = new SMSDataEntity();
                        entity.setMessage(text);
                        entity.setReceiver(receiver);
                        entity.setPriority(sms.getPriority());
                        entity.setSender(senderPhone);
                        entity.setClientCode(code);
                        entity.setCreateBy(user.getUsername());
                        allMessage.add(entity);
                    }
                }

            }
        }
        return allMessage;
    }

    private List<SMSDataEntity> transferToMultipleMessage(String code, Collection<SMSDataDTO> messages) throws NotFound {
        List<SMSDataEntity> allMessage = new ArrayList<>();

        for (SMSDataDTO sms : messages) {
            allMessage.addAll(transferToMultipleMessage(code, sms));
        }
        return allMessage;
    }

    private List<SMSDataModel> saveBatchData(String code, List<SMSDataDTO> request) throws NotFound {
        List<SMSDataEntity> entities = transferToMultipleMessage(code, request);
        List<SMSDataEntity> savedEntity = repo.saveAll(entities);
        return savedEntity.stream().map(transformer::toModel).toList();
    }
}
