package com.synergism.blog.api.publicAPI.service;

import com.synergism.blog.result.Result;

public interface PublicAPIService {

    /**
     * 获取公钥
     * @return 结果[公钥]
     */
    Result<String> getPublicKey();
}
