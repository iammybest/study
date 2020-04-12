package com.iammybest.ehcache;

/**
 * Created by MrDeng on 2017/2/20.
 */
public class UserEntity {
    private Long id;
    private String name;

    public UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
