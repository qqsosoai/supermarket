package cn.bdqn.controller;

import cn.bdqn.bean.User;
import cn.bdqn.service.UserService;
import cn.bdqn.util.PageUtil;
import cn.bdqn.util.md5.MyMd5;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by hasee on 2017/8/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    private UserService service;
    private Logger log=Logger.getLogger(UserController.class);
    @RequestMapping("/welcome.html")//跳转用户主页面
    public String welcome(){
        return "user/welcome";
    }
    @RequestMapping("/main.html")//跳转用户主页面
    public String main(Model model){
        PageUtil util=new PageUtil();
        try {
            util.setSqlCount(service.findByNameSqlCount(null));
            List<User> users = service.findByNameLimit(null, util.getPageIndex(), util.getPageSize());
            model.addAttribute("page",util);
            model.addAttribute("users",users);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/userList";
    }
    @RequestMapping("/view.html/{id}")//用户详情页面
    public String view(@PathVariable Integer id,Model model){
        try {
            model.addAttribute("user",service.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/userView";
    }
    @RequestMapping(value = "/add.html",method = RequestMethod.GET)//跳转添加用户页面
    public String add(){
        return "user/userAdd";
    }
    @RequestMapping(value = "/add.html",method = RequestMethod.POST)//提交用户添加
    public String add(User user,HttpSession session){
        User loginUser = (User)session.getAttribute("user");
        user.setCreatedBy(loginUser.getUserId());
        user.setUserPassword(MyMd5.toMd5String(user.getUserPassword()));
        boolean flag=false;
        try {
            flag = service.addService(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            return "redirect:/user/main.html";
        }else{
            return "redirect:/user/main.html?flag=false";
        }
    }
    @RequestMapping(value = "/update.html/{id}",method = RequestMethod.GET)//跳转修改页面
    public String update(@PathVariable Integer id, HttpSession session, Model model){
        boolean flag=false;
        try {
            User user= (User) session.getAttribute("user");
            User data = service.findById(id);
            if (user.getUserType()<data.getUserType()) {//判断用户是否要修改比自己权限大的用户信息
                model.addAttribute("user",data);
                flag=true;
            }else if(user.getUserId()==data.getUserId()){//判断用户是否修改自己
                model.addAttribute("user",data);
                flag=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag) {
            return "user/userUpdate";
        }else{
            return "redirect:/user/main.html?flag=per";
        }
    }
    @RequestMapping(value = "/update.html",method = RequestMethod.POST)//提交用户修改页面
    public String update(User user,HttpSession session){
        User loginUser = (User) session.getAttribute("user");
        user.setModifyBy(loginUser.getUserId());
        boolean flag=false;
        try {
            flag = service.updateService(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag)
            return "redirect:/user/main.html";
        else
            return "redirect:/user/main.html?flag=false";
    }
    @RequestMapping(value = "/delete.html",method = RequestMethod.POST)
    public void delete(Integer id,HttpSession session,HttpServletResponse response){
        User user = (User) session.getAttribute("user");
        try {
            PrintWriter print = response.getWriter();
            boolean flag=false;
            User delUser=service.findById(id);//查询删除用户的权限
            if (user.getUserType()<delUser.getUserType()){//有权限删除
                flag=service.delService(delUser);
            }
            print.print("{\"flag\":\""+flag+"\"}");
            print.flush();print.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/ajax.html",method = RequestMethod.POST)//处理ajax分页请求
    public void ajax(PageUtil util, String name,Integer proId,Integer isPay, HttpServletResponse response){
        try {
            util.setSqlCount(service.findByNameSqlCount(name));
            List<User> users = service.findByNameLimit(name, util.getPageIndex(), util.getPageSize());
            String usersJson = JSON.toJSONString(users);
            PrintWriter print = response.getWriter();
            if (!usersJson.equals("[]")){
                String out=usersJson.replace("]",","+JSON.toJSONString(util)+"]");
                log.debug(out);
                print.print(out);
            }else{
                print.print("{\"flag\":\"false\"}");
            }
            print.flush();
            print.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/validate.html",method = RequestMethod.POST)//验证用户账号是否存在存在返回true不存在返回false
    public void ajax(String userCode,HttpServletResponse response){
        boolean flag=true;
        User name = service.findByName(userCode);
        if (name==null){
            flag=false;
        }
        String out="{\"flag\":\""+flag+"\"}";
        try {
            PrintWriter print = response.getWriter();
            print.print(out);print.flush();print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
