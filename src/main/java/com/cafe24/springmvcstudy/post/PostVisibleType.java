package com.cafe24.springmvcstudy.post;

public enum PostVisibleType {

    PUBLIC("공개"),
    PRIVATE("비공개");

    private final String name;

    PostVisibleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
