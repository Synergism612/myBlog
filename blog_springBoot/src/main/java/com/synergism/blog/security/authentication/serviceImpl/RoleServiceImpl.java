package com.synergism.blog.security.authentication.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.security.authentication.entity.Role;
import com.synergism.blog.security.authentication.mapper.RoleMapper;
import com.synergism.blog.security.authentication.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
