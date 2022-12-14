package com.synergism.blog.core.tag.serviceImpl;

import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.tag.mapper.TagMapper;
import com.synergism.blog.core.tag.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private final TagMapper mapper;

    @Autowired
    public TagServiceImpl(TagMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<TagInformation> getTagInformationList(int limit) {
        List<TagInformation> result = mapper.selectTagInformationList(limit);
        return result.size()==0?null:result;
    }

    @Override
    public List<Tag> getListByArticleID(long articleID) {
        List<Tag> result = mapper.selectListByArticleID(articleID);
        return result.size()==0?null:result;
    }

    @Override
    public List<Tag> getUsedListByUsername(String username) {
        List<Tag> result = mapper.selectUsedListByUsername(username);
        return result.size()==0?null:result;
    }

    @Override
    @Transactional
    public void save(long userID, String name, String annotation) {
        Tag tag = new Tag(name,annotation);
        mapper.insert(tag);
        mapper.bundle(tag.getId(),userID);
    }
}
