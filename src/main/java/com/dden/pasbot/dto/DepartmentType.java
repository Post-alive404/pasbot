package com.dden.pasbot.dto;

public enum DepartmentType {
    MAIN_DEP("Main"),
    TECHNICAL_DEP("Technical"),
    LEGAL_DEP("Legal"),
    OTHER("Other");

    public final String name;

    DepartmentType(String name){
        this.name = name;
    }

}
