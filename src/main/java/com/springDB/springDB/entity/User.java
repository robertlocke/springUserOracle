package com.springDB.springDB.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_TABLE")
@Data
@Builder
@NoArgsConstructor
public class User {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "USER_NAME", nullable = true, length = 255)
    private String name;

    @Column(name = "USER_SALARY", nullable = true, length = 10)
    private Integer salary;

    public User(Integer id, String name, Integer salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

}
