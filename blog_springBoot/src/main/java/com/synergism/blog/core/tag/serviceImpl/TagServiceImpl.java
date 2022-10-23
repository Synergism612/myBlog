package com.synergism.blog.core.tag.serviceImpl;

import com.synergism.blog.core.tag.entity.Tag;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.tag.mapper.TagMapper;
import com.synergism.blog.core.tag.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TagInformation> getAllTagInformationList() {
        return mapper.getAllTagInformationList();
    }

    @Override
    public List<TagInformation> getTagInformationListByUsername(String username) {
        return this.getAllTagInformationList()
                .stream()
                .filter(tagInformation -> tagInformation
                        .getUsername()
                        .equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tag> getListByArticleID(long articleID) {
        return mapper.getListByArticleID(articleID);
    }
}
