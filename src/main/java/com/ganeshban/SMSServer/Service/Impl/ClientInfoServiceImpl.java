package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.DTO.ClientInfoDTO;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.model.ClientInfoModel;
import com.ganeshban.smsserver.repository.ClientInfoRepository;
import com.ganeshban.smsserver.repository.UserRepository;
import com.ganeshban.smsserver.service.ClientInfoService;
import com.ganeshban.smsserver.transformer.ClientInfoTransformer;
import com.ganeshban.smsserver.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.ganeshban.smsserver.utils.Constants.IncludePatternStrategy.NUMBER;
import static com.ganeshban.smsserver.utils.Constants.IncludePatternStrategy.UPPERCASE;
import static com.ganeshban.smsserver.utils.Constants.generatePassword;

@Service
@RequiredArgsConstructor
public class ClientInfoServiceImpl implements ClientInfoService {

    private final ClientInfoRepository repository;
    private final UserRepository userRepository;
    private final ClientInfoTransformer transformer;
    private final JwtUtils jwtUtils;



    @Override
    @Cacheable(cacheNames = "clientInfo")
    public ClientInfoModel getClientInfoByClientCode(String code) throws NotFound {
        ClientInfoEntity entity = repository.findByClientCode(code).orElseThrow(() -> new NotFound("User not found with id code."));
        return transformer.toModel(entity);
    }

    public ClientInfoModel getClientInfoEntityByClientCode() throws NotFound {
        UserDetails currentlyLoggedUser= (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<UserEntity> userName = userRepository.findByUserName(currentlyLoggedUser.getUsername());
        String code = userName.get().getClientCode().getClientCode();
        ClientInfoEntity entity = repository.findByClientCode(code).orElseThrow(() -> new NotFound("User not found with id code."));
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
