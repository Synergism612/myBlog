package com.synergism.blog.core.role.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.synergism.blog.core.role.entity.Role;
import com.synergism.blog.core.role.mapper.RoleMapper;
import com.synergism.blog.core.role.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
