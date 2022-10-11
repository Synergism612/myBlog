package com.synergism.blog.core.tag.serviceImpl;

import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.mapper.TagMapper;
import com.synergism.blog.core.tag.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-11 05:58:34
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
