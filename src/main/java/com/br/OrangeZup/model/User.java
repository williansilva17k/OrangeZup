package com.br.OrangeZup.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "User")
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", unique = true, nullable = false, length = 30)
    private String email;

    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false, updatable = false, name = "date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date;
}