package com.tsyrulik.dmitry.model.entity;

public enum UserType {
    CLIENT("user"), ADMIN("admin"), GUEST("guest"), TRAINER("trainer");

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
