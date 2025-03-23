package ctu.forum.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @NoArgsConstructor  //auto create no args contructor
    @AllArgsConstructor //auto create full args constructor
    @Data //auto create getters, setters, toString(), equals(), and hashCode()
    public class UserDTO {
        private String student_id;
        private String name;
        private String email;
        private String password_hash;
    }

