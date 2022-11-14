package com.synergism.blog.core.classify.serviceImpl;

import com.synergism.blog.core.classify.entity.Classify;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.classify.mapper.ClassifyMapper;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<ClassifyInformation> result = mapper.selectAllClassifyInformationList();
        return result.size()==0?null:result;
    }

    @Override
    public List<ClassifyInformation> getClassifyInformationListByUsername(String username) {
        List<ClassifyInformation> result = mapper.selectClassifyInformationListByUsername(username);
        return result.size()==0?null:result;
    }

    @Override
    public Classify getOneByArticleID(long articleID) {
        return mapper.selectOneByArticleID(articleID);
    }

    @Override
    public List<Classify> getUsedListByUsername(String username) {
        List<Classify> result = mapper.selectUsedListByUsername(username);
        return result.size()==0?null:result;
    }

    @Override
    @Transactional
    public void save(long userID, String name, String annotation) {
        Classify classify = new Classify(name,annotation);
        mapper.insert(classify);
        mapper.bundle(classify.getId(),userID);
    }
}
