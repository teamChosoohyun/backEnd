package com.codingmom.server.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String k_id;
    private String k_img_url;
    private long type;

    public User(Long id, String name, String k_id, String k_img_url, long type) {
        this.id = id;
        this.name = name;
        this.k_id = k_id;
        this.k_img_url = k_img_url;
        this.type = type;
    }
}


