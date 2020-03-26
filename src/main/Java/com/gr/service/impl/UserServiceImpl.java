package com.gr.service.impl;

import com.gr.Exceptions.UserException;
import com.gr.dao.LocalAuthDao;
import com.gr.dao.PersonInfoDao;
import com.gr.dto.ImageHolder;
import com.gr.dto.UserExecution;
import com.gr.entity.LocalAuth;
import com.gr.entity.PersonInfo;
import com.gr.enums.UserStateEnum;
import com.gr.service.UserService;
import com.gr.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 14:50
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    LocalAuthDao localAuthDao;
    @Autowired
    PersonInfoDao personInfoDao;

    @Override
    @Transactional
    public UserExecution addUser(LocalAuth localAuth, PersonInfo personInfo, ImageHolder headImg) {
        if (headImg != null) {
            String headImgUrl = addImg(headImg);
            personInfo.setProfileImg(headImgUrl);
        }
        int i = 0;
        try {
            personInfo.setUserType(1);
            personInfo.setEnableStatus(1);
            personInfo.setLastEditTime(new Date());
            personInfo.setCreateTime(new Date());
            i = personInfoDao.insertPersonInfo(personInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (i <= 0) {
            throw new UserException("添加用户失败");
        }
        localAuth.setLastEditTime(new Date());
        localAuth.setCreateTime(new Date());
        localAuth.setPersonInfo(personInfo);
        int i2 = localAuthDao.insertLocalAuth(localAuth);
        if (i2 <= 0) {
            throw new UserException("添加用户失败");
        }
        UserExecution userExecution = new UserExecution(UserStateEnum.SUCCESS, personInfo);
        return userExecution;
    }

    @Override
    public UserExecution loginUser(String username, String password) {
        LocalAuth localAuth = localAuthDao.queryLocalAuthByUserNameAndPwd(username, password);
        UserExecution userExecution;
        if (localAuth == null) {
            userExecution = new UserExecution(UserStateEnum.NOT_USER);
        } else {
            userExecution = new UserExecution(UserStateEnum.SUCCESS, localAuth);
        }
        return userExecution;
    }

    @Override
    public UserExecution addIntegral(PersonInfo personInfo) {
        int i = personInfoDao.addIntegral(personInfo);
        UserExecution userExecution;
        if (i > 0) {
            userExecution = new UserExecution(UserStateEnum.SUCCESS);
        } else {
            userExecution = new UserExecution(UserStateEnum.NOT_USER);
        }
        return userExecution;
    }

    /**
     * 持久话图片
     *
     * @param headImg 图片流
     * @return 返回图片持久化之后的地址
     */
    private String addImg(ImageHolder headImg) {
        return ImageUtil.makingThumbnails(headImg, HEADIMAGEURL);
    }
}
