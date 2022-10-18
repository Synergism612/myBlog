package com.synergism.blog.api.publicAPI.serviceImpl;

import com.synergism.blog.api.publicAPI.service.PublicAPIService;
import com.synergism.blog.result.Result;
import com.synergism.blog.security.cryptography.service.CryptographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicAPIServiceImpl implements PublicAPIService {

    private final CryptographyService cryptographyService;

    @Autowired
    PublicAPIServiceImpl(CryptographyService cryptographyService){
        this.cryptographyService = cryptographyService;
    }

    @Override
    public Result<String> getPublicKey() {
        return Result.success(cryptographyService.getRSAPublicKey());
    }
}
