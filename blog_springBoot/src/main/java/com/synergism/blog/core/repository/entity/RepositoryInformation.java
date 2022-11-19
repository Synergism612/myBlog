package com.synergism.blog.core.repository.entity;

import com.synergism.blog.exception.custom.IllegalRequestException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class RepositoryInformation extends Repository {

    private String username;

    private List<Folder> folderList;

    private List<File> fileList;

    /**
     * 库级目录下是否存在博客文件夹
     * @return 存在为真，反之为假
     */
    public boolean isHaveBlog() {
        return this.folderList
                .stream()
                .anyMatch(
                        folder -> folder.getName().equals("blog")
                );
    }
}
