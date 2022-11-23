package com.codingmom.server.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@ToString
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Leacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long lecturer_id;
    private String category;
    private java.sql.Timestamp work_time;
    private java.sql.Timestamp go_work;
    private java.sql.Timestamp leave_work;

    public Leacture(Long id, long lecturer_id, String category, Timestamp work_time, Timestamp go_work, Timestamp leave_work) {
        this.id = id;
        this.lecturer_id = lecturer_id;
        this.category = category;
        this.work_time = work_time;
        this.go_work = go_work;
        this.leave_work = leave_work;
    }

}
