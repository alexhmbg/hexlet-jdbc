package io.hexlet;

public class User {
    private Long id;
    private String name;
    private String phone;
    User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    Long getId() {
        return this.id;
    }

    void setId(Long id) {
        this.id = id;
    }

    String getName() {
        return name;
    }

    String getPhone() {
        return phone;
    }
}
