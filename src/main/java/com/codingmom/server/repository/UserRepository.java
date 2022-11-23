package com.codingmom.server.repository;

import com.codingmom.server.domain.User;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Map<String, Object> findByK_id(long k_id);
    @Update("update Users set k_img_url=#{k_img_url} where IFNULL(k_id, 0)=#{k_id}")
    void updateKImg(String k_img_url, long k_id);
}
