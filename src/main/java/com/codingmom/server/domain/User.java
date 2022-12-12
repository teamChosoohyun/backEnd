package com.codingmom.server.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String kakaoid;
    private String k_img_url;
    private long type;

    public User(Long id, String name, String kakaoid, String k_img_url, long type) {
        this.id = id;
        this.name = name;
        this.kakaoid = kakaoid;
        this.k_img_url = k_img_url;
        this.type = type;
    }
}


