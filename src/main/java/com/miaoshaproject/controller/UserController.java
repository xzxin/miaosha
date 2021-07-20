package com.miaoshaproject.controller;


import com.miaoshaproject.controller.viewObject.UserVO;
import com.miaoshaproject.error.BussinessException;
import com.miaoshaproject.error.EnumBussinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowedHeaders="*", allowCredentials = "true")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    // 用户登录接口
    @RequestMapping(
            value = "/login",
            method = {RequestMethod.POST},
            consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "password") String password) throws BussinessException, NoSuchAlgorithmException {
        // 入参校验
        if (StringUtils.isEmpty(telephone) || StringUtils.isEmpty(password)) {
            throw new BussinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR);
        }
        // 校验用户登录是否合法
        UserModel userModel = userService.validateLogin(telephone, this.encodeByMd5(password));

        // 讲登录凭证加入到用户登录成功的session内（单点分布式）
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN", true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER", userModel);
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/getotp", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telephone") String telephone) {
        // 按照一定规则生成验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt += 10000;
        String otpCode = String.valueOf(randomInt);

        // 讲OTP验证码同对应用户手机号关联 redis关联
        httpServletRequest.getSession().setAttribute(telephone, otpCode);
        // 目前用HttpSession绑定手机号和OTPCode
        System.out.println("telephone = " +telephone + " & otpCode = " + otpCode);

        // 发送
        return CommonReturnType.create(null);
    }

    @RequestMapping(value = "/register", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(
            @RequestParam(name = "telephone") String telephone,
            @RequestParam(name = "otpCode") String otpCode,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "gender") Byte gender,
            @RequestParam(name = "age") Integer age,
            @RequestParam(name = "password") String password) throws BussinessException, NoSuchAlgorithmException {
        // 验证手机号和otpCode相符合
        String inSessonOtpCode = (String)httpServletRequest.getSession().getAttribute(telephone);
        if (!StringUtils.equals(otpCode, inSessonOtpCode)) {
            throw new BussinessException(EnumBussinessError.PARAMETER_VALIDATION_ERROR, "验证码错误");
        }
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(gender);
        userModel.setAge(age);
        userModel.setTelephone(telephone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(encodeByMd5(password));
        userService.register(userModel);
        return CommonReturnType.create(null);
    }

    public String encodeByMd5(String str) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(md5.digest(str.getBytes(StandardCharsets.UTF_8)));
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BussinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BussinessException(EnumBussinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

}
