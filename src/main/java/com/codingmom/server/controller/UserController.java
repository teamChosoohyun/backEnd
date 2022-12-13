package com.codingmom.server.controller;

import com.codingmom.server.domain.UserTbl;
import com.codingmom.server.repository.UserRepository;
//import com.codingmom.server.service.JwtService;
import com.codingmom.server.service.KaKaoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final KaKaoService kakaoService;
//    private final JwtService jwtService;

    public UserController(UserRepository userRepository, KaKaoService kakaoService) {
        this.userRepository = userRepository;
        this.kakaoService = kakaoService;
//        this.jwtService = jwtService;
    }


    // kakao user info 받기
    @ResponseBody
    @RequestMapping(value = "/user/getKakaoUserInfo", method = RequestMethod.GET)
    public Map<String, Object> GetKakaoUserInfo(@RequestParam(value = "code", required = true) String code) throws Exception {
        String access_code = kakaoService.getAccessToken(code);
        return kakaoService.getUserInfo(access_code);
    }

//     user가 회원가입 되어 있는지 체크 후 true라면 로그인 처리 + JWT token 발급 false라면 회원가입 절차
    @ResponseBody
    @RequestMapping(value = "/user/kakaoLogin", method = RequestMethod.POST)
    public String KakaoLogin(@RequestParam(value = "k_id", required = true) String k_id) throws Exception {
        UserTbl exist = userRepository.findByKakaoid(k_id); // user table에 존재하면 존재하는 정보, 존재하지 않으면
//        String current_profile_img = userInfo.get("k_img_url").toString().replace("\"", "");
        if (exist == null) { // 회원 가입이 안 되어 있다면
            return "guest";
        } else { // 회원 가입이 되어있다면 JWT token 보내줘야 함
//            if (!current_profile_img.equals(exist.get("k_img_url").toString())) {
//                userRepository.updateKImg(userInfo.get("k_img_url").toString().replace("\"", ""), k_id);
//            }
            Map<String, Object> userInfo = kakaoService.getUserInfoById(Long.parseLong(k_id));

//            User user = exist;
//
//            return jwtService.createJWT(user);
            return "auth exist";
        }
    }

    // user 정보를 받아 회원가입
    @ResponseBody
    @RequestMapping(value = "/user/join", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public String Join(UserTbl userTbl) throws Exception {
        userRepository.save(userTbl);
//        return jwtService.createJWT(user);
        return "success";
    }


    //token 확인
//    @ResponseBody
//    @RequestMapping(value = "/checkJWT", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
//    public Map<String, Object> CheckJWT(@RequestParam String token) throws Exception {
//        Map<String, Object> check = jwtService.checkJWT(token);
//        if (check == null) {
//            return check;
//        } else {
//            return check;
//        }
//    }

//    @RequestMapping(value = "/getUserId", method = RequestMethod.GET)
//    public long GetUserId(@RequestParam long k_id) throws Exception {
//        Map<String, Object> user = userRepository.findByKakaoid(k_id);
//        return (long) user.get("user_id");
//    }


//    @ResponseBody
//    @RequestMapping(value = "/user/update", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
//    public String userUpdate(@Param("user_id") long user_id, @Param("nickname") String nickname, @Param("lat") double lat, @Param("lon") double lon, @Param("address") String address) throws Exception {
//        userRepository.save();
//        return "success";
//    }
}

