package com.khb.hu.springcourse.hr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class HrUser {
    @Id
    private String username;
    private String password;

    public HrUser() {
    }

    public HrUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HrUser hrUser = (HrUser) o;
        return Objects.equals(username, hrUser.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
