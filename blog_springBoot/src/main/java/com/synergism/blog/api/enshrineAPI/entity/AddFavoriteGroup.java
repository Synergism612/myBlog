package com.synergism.blog.api.enshrineAPI.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddFavoriteGroup {
        private String username;
        private String name;
        private String annotation;
        private int if_private;
}
