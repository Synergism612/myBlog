package com.synergism.blog.core.classify.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 标签详细信息
 */
@Getter
@Setter
public class ClassifyInformation extends Classify{
    //分类下文章数
    private int articleCount;
}
