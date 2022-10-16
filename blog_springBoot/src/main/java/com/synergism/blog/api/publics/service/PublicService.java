package com.synergism.blog.api.publics.service;

import com.synergism.blog.result.entity.Result;

public interface PublicService {

    /**
     * 获取公钥
     * @return 结果[公钥]
     */
    Result<String> getPublicKey();
}
