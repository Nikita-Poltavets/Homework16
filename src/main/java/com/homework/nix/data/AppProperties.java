package com.homework.nix.data;

import com.homework.nix.annotation.PropertyKey;

public class AppProperties {

    @PropertyKey("name")
    private String name;

    @PropertyKey("age")
    public int age;

    @PropertyKey("gender")
    private String gender;

    @PropertyKey("occupation")
    private String occupation;

    private double height;

}
