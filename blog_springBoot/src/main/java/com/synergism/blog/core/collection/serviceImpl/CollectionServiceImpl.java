package com.synergism.blog.core.collection.serviceImpl;

import com.synergism.blog.core.collection.entity.Collection;
import com.synergism.blog.core.collection.mapper.CollectionMapper;
import com.synergism.blog.core.collection.service.CollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收藏信息表 服务实现类
 * </p>
 *
 * @author Synergism
 * @since 2022-10-31 09:33:18
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements CollectionService {

}
