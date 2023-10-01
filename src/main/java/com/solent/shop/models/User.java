package com.solent.shop.models;

public class User {

    int id;

    String name;

    String email;

    String password;

    String confirm_pass;

    int user_role;

    int user_status;

    String created_at;

    String updated_at;

    public User() {
    }

    public User(int id, String name, String email, String password, int user_role, int user_status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.user_role = user_role;
        this.user_status = user_status;
    }

    public User(int id, String name, String email, String password, String confirm_pass, int user_role, int user_status, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirm_pass = confirm_pass;
        this.user_role = user_role;
        this.user_status = user_status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_pass() {
        return confirm_pass;
    }

    public void setConfirm_pass(String confirm_pass) {
        this.confirm_pass = confirm_pass;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", confirm_pass=" + confirm_pass + ", user_role=" + user_role + ", user_status=" + user_status + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
}
