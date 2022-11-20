package com.synergism.blog.core.repository.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FolderInformation extends Folder {

    private List<Folder> folderList;

    private List<File> fileList;
}
