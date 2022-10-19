package com.synergism.blog.security.keyManagement.serviceImpl;

import com.synergism.blog.security.keyManagement.entity.Key;
import com.synergism.blog.security.keyManagement.service.KeyManagementService;
import com.synergism.blog.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class KeyManagementServiceImpl implements KeyManagementService {
    /**
     * 从请求头部中获取异世界钥匙
     * 若为空返回空字符串
     * @param request 请求头
     * @return 异世界钥匙
     */
    public String getAnotherWorldKey (HttpServletRequest request){
        String result = request.getHeader(Key.ANOTHER_WORLD_KEY());
        if (StringUtil.isEmpty(result))
            return"";
        return result;
    }
}
