package com.synergism.blog.core.classify.serviceImpl;

import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.classify.mapper.ClassifyMapper;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    private final ClassifyMapper mapper;

    @Autowired
    public ClassifyServiceImpl(ClassifyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ClassifyInformation> getAllClassifyInformationList() {
        return mapper.getAllClassifyInformationList();
    }

    @Override
    public List<ClassifyInformation> getClassifyInformationListByUsername(String username) {
        List<ClassifyInformation> result = this.getAllClassifyInformationList();
        if (result.size()==0) return null;
        return result
                .stream()
                .filter(classifyInformation -> classifyInformation
                        .getUsername()
                        .equals(username))
                .collect(Collectors.toList());
    }

    @Override
    public Classify getOneByArticleID(long articleID) {
        return mapper.getOneByArticleID(articleID);
    }
}
