package com.hsgumussoy.javaodev2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String userName;
    private String fullName;
    private String password;
    private String tckn;
    private String birthPlace;
    private Date birthDate;
    private String telNo;

    public User(Long id, String userName, String fullName, String password, String tckn, String birthPlace, Date birthDate, String telNo) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.tckn = tckn;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.telNo = telNo;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTckn() {
        return tckn;
    }
    public void setTckn(String tckn) {
        this.tckn = tckn;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}

