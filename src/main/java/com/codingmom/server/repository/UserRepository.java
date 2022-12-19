package com.codingmom.server.repository;

import com.codingmom.server.domain.UserTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserTbl,Long> {

    List<UserTbl> findByType(long type);
    List<UserTbl> findByTypeAndCategory(long type,String category);
    UserTbl findByKakaoid(String k_id);

    //    Map<String, Object> findByK_id(Long id);
//    @Update("update Users set k_img_url=#{k_img_url} where IFNULL(k_id, 0)=#{k_id}")
//    void updateKImg(String k_img_url, long k_id);
}
