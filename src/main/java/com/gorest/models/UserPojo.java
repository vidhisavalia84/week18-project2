package com.gorest.models;

public class UserPojo {

    /*"name": "jim23ypwtenric",
        "email": "jim23ypwtenricwerrtretr@gmail.com",
        "gender": "male",
        "status": "active"*/
    private String name;
    private String email;
    private String status;
    private String gender;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
