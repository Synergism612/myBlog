package com.synergism.blog.api.dbankAPI.controller;

import com.synergism.blog.api.dbankAPI.service.DBankAPIService;
import com.synergism.blog.result.CodeMsg;
import com.synergism.blog.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 共有接口控制器
 */
@RestController
@RequestMapping("/api/public")
public class DBankAPIController {

    DBankAPIService service;

    @Autowired
    DBankAPIController(DBankAPIService service){
        this.service = service;
    }

}
