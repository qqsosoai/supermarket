package cn.bdqn.controller;

import cn.bdqn.bean.User;
import cn.bdqn.service.UserService;
import cn.bdqn.util.md5.MyMd5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hasee on 2017/8/8.
 */
@Controller
public class LoginController {
    @Resource(name = "userService")
    private UserService service;
    @RequestMapping(value="/login.html",method= RequestMethod.GET)//请求登录页面
    public String login(){
        return "login";
    }
    @RequestMapping(value="/login.html",method = RequestMethod.POST)//处理提交登录
    public String login(User user, HttpSession session, Model model){
        User loginUser = service.findByName(user.getUserCode());
        if (loginUser!=null){//判断是否有该账号
            if (MyMd5.isMd5String(user.getUserPassword(),loginUser.getUserPassword())){//判断密码是否正确
                session.setAttribute("user",loginUser);
                return "redirect:/welcome.html";
            }else{//密码输入不正确
                model.addAttribute("message","pass");
                return "login";
            }
        }else{//用户账号输入不正确
            model.addAttribute("message","name");
            return "login";
        }

    }
    @RequestMapping("/welcome.html")//跳转用户主页面
    public String welcome(){
        return "user/welcome";
    }
    @RequestMapping("/login.html/out")//退出登录
    public String outLogin(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }

    @RequestMapping("/validate.html")//验证原密码是否正确
    public void ajax(HttpSession session, String oldPassword, HttpServletResponse response){
        User user = (User) session.getAttribute("user");
        try {
            PrintWriter writer = response.getWriter();
            boolean flag=false;
            if (MyMd5.isMd5String(oldPassword,user.getUserPassword())){//判断用户输入原密码是否正确
                flag=true;
            }
            writer.print("{\"flag\":\""+flag+"\"}");
            writer.flush();writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/submit.html")//处理用户修改密码请求
    @ResponseBody
    public String ajax(String oldPassword,String newPassword,HttpSession session){
        User user = (User) session.getAttribute("user");
        if (MyMd5.isMd5String(oldPassword,user.getUserPassword())){//判断旧的密码是否正确
            try {
                newPassword= MyMd5.toMd5String(newPassword);//将用户输入的新密码加密
                if (service.updatePassword(user.getUserId(),newPassword)){
                    user.setUserPassword(newPassword);
                    return "success";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "eor";
            }
        }else{//用户跳过前台js验证
            return "out";
        }
        return "eor";
    }
}
