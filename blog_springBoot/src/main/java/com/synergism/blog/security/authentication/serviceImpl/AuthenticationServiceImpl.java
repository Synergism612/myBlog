package com.synergism.blog.security.authentication.serviceImpl;

import com.synergism.blog.blog.role.service.RoleService;

import com.synergism.blog.security.authentication.entity.Authentication;
import com.synergism.blog.security.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private List<Authentication> authenticationList;

    private RoleService roleService;

    @Autowired
    AuthenticationServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void init() {
        this.authenticationList = Authentication.fromRoleList(roleService.list());
        authenticationList.forEach(U-> System.out.println(U.toString()));
    }
}
