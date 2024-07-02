package org.eu.luolei.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    private String nickname;
    private String email;
    private String userPic;
    private Timestamp create_time;
    private Timestamp update_time;
}
