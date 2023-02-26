package com.exam.controller;

import com.exam.entity.*;
import com.exam.serviceimpl.JwtUserDetailsService;
import com.exam.serviceimpl.LoginServiceImpl;
import com.exam.util.ApiResultHandler;
import com.exam.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ApiResult login(@RequestBody Login login) {

        String username = login.getUsername();
        String password = login.getPassword();
        if(login.getType().equals("Admin")){
            Admin adminRes = loginService.adminLogin(username, password);
            if (adminRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", adminRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Publisher")){
            Teacher teacherRes = loginService.teacherLogin(username,password);
            if (teacherRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", teacherRes);
            } else {
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Subscriber")){
            Student studentRes = loginService.studentLogin(username,password);
            if (studentRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", studentRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }
        return ApiResultHandler.buildApiResult(400, "Request Fail", null);
    }
    @PostMapping("/updatelogin")
    public ApiResult loginupdate(@RequestBody Login login){
        String username = login.getUsername();
        String password = login.getPassword();

        if(login.getType().equals("Admin")){
            Admin adminRes = loginService.adminLogin(username, password);
            if (adminRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", adminRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Publisher")){
            Teacher teacherRes = loginService.teacherLogin(username,password);
            if (teacherRes != null) {
                return ApiResultHandler.buildApiResult(200, "Request Success", teacherRes);
            } else {
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }else if(login.getType().equals("Subscriber")){
            //   BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
       //     password = bCryptPasswordEncoder.encode(password);
            int studentRes = loginService.updatestudentLogin(username,password);
            if (studentRes == 1) {
                return ApiResultHandler.buildApiResult(200, "Request Success", studentRes);
            }else{
                return ApiResultHandler.buildApiResult(400, "Request Fail", null);
            }
        }
        return ApiResultHandler.buildApiResult(400, "Request Fail", null);
    }



}
