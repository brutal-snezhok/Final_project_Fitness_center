package com.tsyrulik.dmitry.model.entity;

public enum UserType {
    ADMIN("admin"), CLIENT("user"), TRAINER("trainer"), GUEST("guest");

    private String typeName;

    UserType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
