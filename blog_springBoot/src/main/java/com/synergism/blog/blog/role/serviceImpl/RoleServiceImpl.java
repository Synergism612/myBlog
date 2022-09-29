package com.synergism.blog.blog.role.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.blog.role.entity.Role;
import com.synergism.blog.blog.role.mapper.RoleMapper;
import com.synergism.blog.blog.role.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
