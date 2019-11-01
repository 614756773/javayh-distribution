package com.javayh.api.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javayh.common.util.DataResult;
import com.javayh.common.util.MD5Util;
import com.javayh.conf.dto.SysUserDTO;
import com.javayh.conf.entity.SysUser;
import com.javayh.conf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * @author Dylan Yang
 * @Description: 用户相关操作
 * @ProjectName javayh-distribution
 * @date 2019-10-30 23:22
 */
@Controller
@RequestMapping("/javayh")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 来到添加用户页面
     * @return
     */
    @RequestMapping("/adduser")
    public String touser() {
        return "user/register";
    }

    /**
     * @Description 查询用户信息  后期需要在改
     * @UserModule: javayh-distribution
     * @author Dylan
     * @date 2019/10/31
     * @param pn
     * @param model
     * @return java.lang.String
     */
    @GetMapping("/alluser_admin")
    public String getAllUser(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 4);
        List<SysUser> u_list = userService.getAllPageUser();
        PageInfo<SysUser> page = new PageInfo<>(u_list, 3);
        model.addAttribute("pageInfo", page);
        return "user/users";
    }

    /**
     * @Description 新增用户
     * @UserModule: javayh-distribution
     * @author Dylan
     * @date 2019/10/31
     * @param user
     * @return com.javayh.common.util.DataResult
     */
    @PostMapping("/saveUser")
    @ResponseBody
    public DataResult addUser(SysUser user) {
        userService.saveUser(user);
        return DataResult.success();
    }

    /**
     * @Description 得到用户个人信息
     * @UserModule: javayh-distribution
     * @author Dylan
     * @date 2019/11/1
     * @param username
     * @param model
     * @return java.lang.String
     */
    @GetMapping("/user")
    public String getUser(@RequestParam("username")String username,Model model) {
        SysUserDTO user=userService.getUserByName(username);
        model.addAttribute("user", user);
        return "user/myInfo";
    }

}