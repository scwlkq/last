package com.iweb.sp.controller;


import com.iweb.sp.pojo.AdminInfo;
import com.iweb.sp.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lukecheng
 * @since 2022-08-14
 */
@RestController
public class AdminInfoController {
    @Resource
    AdminService adminService;

    @RequestMapping("/code_confirm/by_name_password")
    public AdminInfo login(String jobNumber, String password) {
        AdminInfo adminInfo = adminService.login(jobNumber, password);
        return adminInfo;
    }

}

