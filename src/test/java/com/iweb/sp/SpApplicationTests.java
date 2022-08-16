package com.iweb.sp;

import com.iweb.sp.pojo.AdminInfo;
import com.iweb.sp.pojo.OrderItem;
import com.iweb.sp.pojo.UserInfo;
import com.iweb.sp.pojo.vo.SkuAndCategory;
import com.iweb.sp.service.impl.AdminServiceImpl;
import com.iweb.sp.service.impl.OrderServiceImpl;
import com.iweb.sp.service.impl.SellerServiceImpl;
import com.iweb.sp.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpApplicationTests {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private SellerServiceImpl sellerService;


    @Test
    public void userServiceImplTest(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1);
        userInfo.setNickName("hello");
        userInfo.setPhone("110120130");
        userInfo.setAvatarImage("https://www.baidu.com");
        userInfo.setPassword("123456");
        userInfo.setUserStatus("是");
        userInfo.setCreateTime("2022-08-15");
        userInfo.setUpdateTime("2022-08-16");
        boolean isflag = userService.register(userInfo);
        System.out.println(isflag);
    }

    @Test
    public void AdminSercviceTest(){
        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdminId(1);
        adminInfo.setNickName("张兴悦");
        adminInfo.setJobNumber("1001");
        adminInfo.setPassword("123456");
        adminInfo.setJobTitle("小白");
        adminInfo.setCreateTime(new Date().toString());
        adminInfo.setUpdateTime(new Date().toString());
        boolean isflag = adminService.register(adminInfo);
        System.out.println(isflag);
    }

    @Test
    public void AdminSercviceTest2(){
        String jobNumber = "1001";
        String password = "123456";
        AdminInfo adminInfo = adminService.login(jobNumber, password);
        System.out.println(adminInfo);
    }


    @Test
    public void UserServiceImplTest2(){
        String code = userService.loginByMessage("18061495569");
        System.out.println("用户收到的验证码为:" + code);
    }


    @Test
    public void OrderServiceTest(){
        List<OrderItem> orderItems = orderService.selectDetailByUser(1, 1);
        System.out.println(orderItems);
    }

    @Test
    public void OrderServiceTest2(){
        List<OrderItem> orderItems = orderService.selectDetailBySeller(2, 1);
        System.out.println(orderItems);
    }

    @Test
    public void SellerServiceTest(){
        String code = sellerService.loginByMessage("18061495569");

        System.out.println(code);
    }


    @Test
    public void UserServiceTest3(){
        sellerService.loginByMessage("18061495569");
    }


    @Test
    public void UserServiceTest4(){
        System.out.println(userService.loginByCode("18061495569", "3191"));
    }

    @Test
    public void SellerServiceTest2(){
        List<SkuAndCategory> skuAndCategories = sellerService.selectAllSku(1,1);
        System.out.println(skuAndCategories);
    }

    @Test
    public void SellerServiceTest3(){
        List<SkuAndCategory> skuAndCategories = sellerService.selectAllSkuDESC(1,1);
        System.out.println(skuAndCategories);
    }

    @Test
    public void SellerServiceTest4(){
        List<SkuAndCategory> skuAndCategories = sellerService.selectAllSkuASC(1,1);
        System.out.println(skuAndCategories);
    }

}
