package com.codingmom.server.controller;

import com.codingmom.server.repository.LectureRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import com.codingmom.server.domain.LectureTbl;

@CrossOrigin
@RestController
public class LectureController {

    private final LectureRepository lectureRepository;

    public LectureController(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    @ResponseBody
    @RequestMapping(value = "/create/lecture", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public LectureTbl CreateLecture(LectureTbl lectureTbl) throws Exception {
        return lectureRepository.save(lectureTbl);

    }
    @ResponseBody
    @RequestMapping(value = "/delete/lecture", method = RequestMethod.DELETE)
    public String DeleteLecture(@Param(value = "id")Long id) throws Exception {
        lectureRepository.deleteById(id);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/update/gowork", method = RequestMethod.PUT)
    public String GoWork(@Param(value = "id")Long id,@Param("go_work")java.sql.Timestamp go_work) throws Exception {
        LectureTbl lectureTbl = lectureRepository.findById(id)
                .orElseThrow();
        Long lecture_id = lectureTbl.getId();
        long lecturer_id = lectureTbl.getLecturer_id();
        String category = lectureTbl.getCategory();
        java.sql.Timestamp work_time = lectureTbl.getWork_time();
        java.sql.Timestamp leave_work = null;
        LectureTbl nlecture = new LectureTbl(lecture_id,lecturer_id,category,work_time,go_work,leave_work);
        lectureRepository.save(nlecture);
        return "success";
    }
    @ResponseBody
    @RequestMapping(value = "/update/leavework", method = RequestMethod.PUT)
    public String LeaveWork(@Param(value = "id")Long id,@Param("leave_work")java.sql.Timestamp leave_work) throws Exception {
        LectureTbl lectureTbl = lectureRepository.findById(id)
                .orElseThrow();
        Long lecture_id = lectureTbl.getId();
        long lecturer_id = lectureTbl.getLecturer_id();
        String category = lectureTbl.getCategory();
        java.sql.Timestamp work_time = lectureTbl.getWork_time();
        java.sql.Timestamp go_work = lectureTbl.getGo_work();
        LectureTbl nlecture = new LectureTbl(lecture_id,lecturer_id,category,work_time,go_work,leave_work);
        lectureRepository.save(nlecture);
        return "success";
    }

}
