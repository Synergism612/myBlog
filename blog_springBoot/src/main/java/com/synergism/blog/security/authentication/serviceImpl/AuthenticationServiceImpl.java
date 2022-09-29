package com.synergism.blog.security.authentication.serviceImpl;

import com.synergism.blog.blog.role.entity.Role;
import com.synergism.blog.blog.role.service.RoleService;

import com.synergism.blog.security.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private List<Role> roleList;

    private RoleService roleService;

    @Autowired
    AuthenticationServiceImpl(RoleService roleService){
        this.roleService = roleService;
    }

    @Override
    public void init() {
        this.roleList = roleService.list();
        roleList.forEach(System.out::println);
    }
}
