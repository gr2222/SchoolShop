package com.gr.web.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.dto.ImageHolder;
import com.gr.dto.UserExecution;
import com.gr.entity.LocalAuth;
import com.gr.entity.PersonInfo;
import com.gr.enums.UserStateEnum;
import com.gr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 15:28
 */
@Controller()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "registerUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerUser(@RequestParam("personInfo") String personinfoStr,
                                            @RequestParam("loaclAuth") String localAuthStr,
                                            HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        ObjectMapper mapper = new ObjectMapper();
        PersonInfo personInfo;
        LocalAuth localAuth;
        try {
            personInfo = mapper.readValue(personinfoStr, PersonInfo.class);
            localAuth = mapper.readValue(localAuthStr, LocalAuth.class);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errMsg", "数据错误");
            return map;
        }
        CommonsMultipartFile headimg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        ImageHolder imageHolder = null;
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            headimg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("headimg");
            try {
                imageHolder = new ImageHolder(headimg.getOriginalFilename(), headimg.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UserExecution userExecution = userService.addUser(localAuth, personInfo, imageHolder);
        if (userExecution.getState() == UserStateEnum.SUCCESS.getState()) {
            map.put("success", true);
            return map;
        } else {
            map.put("success", false);
            map.put("errMsg", "注册错误");
            return map;
        }
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loginUser(@RequestParam("username") String username,
                                         @RequestParam("password") String password,
                                         HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        UserExecution userExecution = userService.loginUser(username, password);
        if (userExecution.getState() == UserStateEnum.SUCCESS.getState()) {
            LocalAuth localAuth = userExecution.getLocalAuth();
            PersonInfo personInfo = localAuth.getPersonInfo();
            if (personInfo.getEnableStatus() == 1) {
                HttpSession session = request.getSession();
                session.setAttribute("personInfoSession", personInfo);
                map.put("success", true);
            } else {
                map.put("success", false);
                map.put("errMsg", "该账号被禁用");
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "登录失败");
        }
        return map;
    }
}
