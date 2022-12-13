package com.codingmom.server.repository;

import com.codingmom.server.domain.LectureTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository extends JpaRepository<LectureTbl,Long> {
}
