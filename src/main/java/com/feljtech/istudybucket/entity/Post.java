package com.feljtech.istudybucket.entity;

import java.sql.Time;
//Post class which can either be query, resource or other post type
public class Post {
    private Long id;
    private String postType;
    private Long numberOfComment;
    private Time postTime;
    private String postContent;
    private Long userId;


}
