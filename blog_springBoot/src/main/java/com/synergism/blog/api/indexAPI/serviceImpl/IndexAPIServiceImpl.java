package com.synergism.blog.api.indexAPI.serviceImpl;

import com.synergism.blog.api.indexAPI.service.IndexAPIService;
import com.synergism.blog.core.classify.entity.ClassifyInformation;
import com.synergism.blog.core.classify.service.ClassifyService;
import com.synergism.blog.core.tag.entity.TagInformation;
import com.synergism.blog.core.tag.service.TagService;
import com.synergism.blog.core.user.entity.UserInformation;
import com.synergism.blog.core.user.service.UserService;
import com.synergism.blog.result.Result;
import com.synergism.blog.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexAPIServiceImpl implements IndexAPIService {

    private final UserService userService;
    private final TagService tagService;
    private final ClassifyService classifyService;

    @Autowired
    public IndexAPIServiceImpl(UserService userService, TagService tagService, ClassifyService classifyService) {
        this.userService = userService;
        this.tagService = tagService;
        this.classifyService = classifyService;
    }

    @Override
    public Result<UserInformation> getIndexUserInfo() {
        return Result.success(UserInformation.getInstance(userService.getById(1)));
    }

    @Override
    public Result<List<TagInformation>> getIndexTag(String username) {
        List<TagInformation> result;
        if (username.isEmpty())
            result = tagService.getAllTagInformationList();
        else
            result = tagService.getTagInformationListByUsername(username);
        if (result!=null){
            result = result.subList(0, Math.min(20, result.size()));
        }
        return Result.success(result);
    }

    @Override
    public Result<List<ClassifyInformation>> getIndexClassify(String username) {
        List<ClassifyInformation> result;
        if (username.isEmpty())
            result = classifyService.getAllClassifyInformationList();
        else
            result = classifyService.getClassifyInformationListByUsername(username);
        if (result != null){
            result = result.subList(0,Math.min(20,result.size()));
        }
        return Result.success(result);
    }
}
