package com.studyMybatis.enums;

public enum Enabled {



    enabled(1), //启用
    disabled(0);//禁用

    public final int value;

    Enabled(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}