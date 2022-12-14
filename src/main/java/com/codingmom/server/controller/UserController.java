package com.codingmom.server.controller;

import com.codingmom.server.domain.UserTbl;
import com.codingmom.server.repository.UserRepository;
import com.codingmom.server.service.KaKaoService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final KaKaoService kakaoService;

    public UserController(UserRepository userRepository, KaKaoService kakaoService) {
        this.userRepository = userRepository;
        this.kakaoService = kakaoService;
    }


    @ResponseBody
    @RequestMapping(value = "/user/getKakaoUserInfo", method = RequestMethod.GET)
    public Map<String, Object> GetKakaoUserInfo(@RequestParam(value = "code", required = true) String code) throws Exception {
        String access_code = kakaoService.getAccessToken(code);
        Map<String, Object> userInfo = kakaoService.getUserInfo(access_code);
        return userInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/user/kakaoLogin", method = RequestMethod.POST)
    public Object KakaoLogin(@Param(value = "k_id") String k_id) throws Exception {
        UserTbl exist = userRepository.findByKakaoid(k_id);
        if (exist == null) {
            return "guest";
        } else {
//            Map<String, Object> userInfo = kakaoService.getUserInfoById(k_id);
//            return userInfo;
            return exist;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/user/join", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public UserTbl Join(UserTbl userTbl) throws Exception {
        userRepository.save(userTbl);
        return userRepository.findByKakaoid(userTbl.getKakaoid());
    }
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public UserTbl ShowUserInfo(@PathVariable("id")Long user_id)throws Exception{
        return userRepository.findById(user_id)
                .orElseThrow();
    }
    @ResponseBody
    @RequestMapping(value = "/lecturer/list",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<UserTbl> ShowLectererlist() throws Exception{
        return userRepository.findByType(1);
    }
    @ResponseBody
    @RequestMapping(value = "/lecturer/list/{category}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public List<UserTbl> ShowLecturerlistWithCategory(@PathVariable("category")String category)throws Exception{
        return userRepository.findByTypeAndCategory(1,category);
    }
    @ResponseBody
    @RequestMapping(value = "lecturer/{id}",method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    public UserTbl GetLecturerInfo(@PathVariable("id")Long id)throws Exception{
        UserTbl user = userRepository.findById(id)
                .orElseThrow();
        return user;
    }



//    @ResponseBody
//    @RequestMapping(value = "/user/update", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
//    public String userUpdate(@Param("user_id") long user_id, @Param("nickname") String nickname, @Param("lat") double lat, @Param("lon") double lon, @Param("address") String address) throws Exception {
//        userRepository.save();
//        return "success";
//    }
}

