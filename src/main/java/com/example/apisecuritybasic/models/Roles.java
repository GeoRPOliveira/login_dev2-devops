package com.example.apisecuritybasic.models;

import jakarta.persistence.*;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String roleName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Roles(long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
    public Roles(){};

    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", role_name='" + roleName + '\'' +
                '}';
    }
}
