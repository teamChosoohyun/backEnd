package com.codingmom.server.repository;

import com.codingmom.server.domain.LectureTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<LectureTbl,Long> {
//    List<LectureTbl> findByIdAndWork_timeBetween(Long id,Timestamp begin,Timestamp end);
    @Query(value = "select m from LectureTbl m where m.lecturer_id=:id and m.work_time between :begin and :end")
    List<LectureTbl> betweenWorkTime(long id,Timestamp begin,Timestamp end);
}
