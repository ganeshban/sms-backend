package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.ClientInfoDTO;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.model.ClientInfoModel;
import com.ganeshban.smsserver.repository.ClientInfoRepository;
import com.ganeshban.smsserver.repository.UserRepository;
import com.ganeshban.smsserver.service.ClientInfoService;
import com.ganeshban.smsserver.transformer.ClientInfoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.ganeshban.smsserver.utils.Constants.ErrorMessage.USER_NOT_FOUND;
import static com.ganeshban.smsserver.utils.Constants.IncludePatternStrategy.NUMBER;
import static com.ganeshban.smsserver.utils.Constants.IncludePatternStrategy.UPPERCASE;
import static com.ganeshban.smsserver.utils.Constants.generatePassword;

@Service
@RequiredArgsConstructor
public class ClientInfoServiceImpl implements ClientInfoService {

    private final ClientInfoRepository repository;
    private final UserRepository userRepository;
    private final ClientInfoTransformer transformer;


    @Override
    public ClientInfoEntity getClientInfoByClientCode(String code) throws NotFound {
        return repository.findByClientCode(code).orElseThrow(() -> new NotFound(USER_NOT_FOUND));

    }

    public ClientInfoModel getClientInfoForCurrentlyLogedInUser() throws NotFound {
        UserDetails currentlyLoggedUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserEntity userName = userRepository.findByUserName(currentlyLoggedUser.getUsername()).orElseThrow(() -> new NotFound(USER_NOT_FOUND));
        String code = userName.getClientCode().getClientCode();
        ClientInfoEntity entity = getClientInfoByClientCode(code);
        return transformer.toModel(entity);
    }

    @Override
    public ClientInfoModel save(ClientInfoDTO clientInfoDTO) {
        ClientInfoEntity entity = transformer.dtoToEntity(clientInfoDTO);
        entity.setClientCode(generateClientCode());


        return transformer.toModel(repository.save(entity));
    }

    private String generateClientCode() {
        return "SMS" + generatePassword(7, UPPERCASE, NUMBER);

    }
}
