package com.synergism.blog.CORS.config;

import com.synergism.blog.security.keyManagement.service.KeyManagementService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CORSConfig {
    private final String Allow_Credentials;
    private final String Allow_Methods;
    private final String Allow_Headers;
    private final String Expose_Headers;

    @Autowired
    public CORSConfig() {
        Allow_Credentials = "true";
        Allow_Methods = "GET,POST,DELETE,PUT,PATCH";
        Allow_Headers = "content-type,Accept,Referer,User-Agent" + KeyManagementService.AllToString();
        Expose_Headers = KeyManagementService.AllToString();
    }
}
