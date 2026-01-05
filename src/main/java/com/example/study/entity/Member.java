package com.example.study.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private BigInteger id;

    @Setter
    private String userName;

    @Setter
    private int age;

    public Member(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @ManyToOne
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
