package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("id")
    public int idUser;

    @JsonProperty("username")
    public String username;

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("password")
    public String password;

    @JsonProperty("phone")
    public String phone;

    @JsonProperty("userStatus")
    public int userStatus;

}
