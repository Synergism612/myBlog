package com.synergism.blog.core.classify.serviceImpl;

import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.mapper.ClassifyMapper;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:03
 */
@Service
public class ClassifyServiceImpl extends ServiceImpl<ClassifyMapper, Classify> implements ClassifyService {

}
