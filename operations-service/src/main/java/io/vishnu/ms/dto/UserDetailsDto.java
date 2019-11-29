package io.vishnu.ms.dto;

import java.io.Serializable;

/**
 * spring-boot-microservice : io.vishnu.gw.dto
 *
 * @author vishnu.g
 */
public class UserDetailsDto implements Serializable {
    private long id;
    private String name;
    private String phoneNumber;
    private String email;

    public UserDetailsDto() {}

    public UserDetailsDto(long id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
