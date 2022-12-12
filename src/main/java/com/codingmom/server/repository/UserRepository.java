package com.codingmom.server.repository;

import com.codingmom.server.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    User findByKakaoid(String k_id);

    //    Map<String, Object> findByK_id(Long id);
//    @Update("update Users set k_img_url=#{k_img_url} where IFNULL(k_id, 0)=#{k_id}")
//    void updateKImg(String k_img_url, long k_id);
}
