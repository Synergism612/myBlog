package com.synergism.blog.publics.serviceImpl;

import com.synergism.blog.publics.service.PublicService;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicServiceImpl implements PublicService {

    private final CryptographyService cryptographyService;

    @Autowired
    PublicServiceImpl(CryptographyService cryptographyService){
        this.cryptographyService = cryptographyService;
    }

    @Override
    public String getPublicKey() {
        return cryptographyService.getRSAPublicKey();
    }
}
