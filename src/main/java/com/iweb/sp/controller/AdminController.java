package com.iweb.sp.controller;


import com.alibaba.fastjson.JSONObject;
import com.iweb.sp.pojo.AdminInfo;
import com.iweb.sp.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lukecheng
 * @since 2022-08-14
 */
@Api(tags = "管理员接口")
@RestController
public class AdminController {
    @Resource
    AdminService adminService;

    @RequestMapping("/test/staff_confrim")
    @ResponseBody
    public AdminInfo login(@RequestBody AdminInfo adminInfo1) {
        String number=adminInfo1.getJobNumber();
        String password=adminInfo1.getPassword();
        AdminInfo adminInfo = adminService.login(number, password);
        System.out.println(adminInfo);
        return adminInfo;
    }

}

