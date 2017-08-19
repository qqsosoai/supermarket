package cn.bdqn.controller;

import cn.bdqn.bean.User;
import cn.bdqn.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
            if (loginUser.getUserPassword().equals(user.getUserPassword())){//判断密码是否正确
                session.setAttribute("user",loginUser);
                return "user/welcome";
            }else{//密码输入不正确
                model.addAttribute("message","pass");
                return "login";
            }
        }else{//用户账号输入不正确
            model.addAttribute("message","name");
            return "login";
        }

    }
    @RequestMapping("/login.html/out")//退出登录
    public String outLogin(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
