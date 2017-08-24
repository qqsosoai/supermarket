package cn.bdqn.controller;

import cn.bdqn.bean.Provider;
import cn.bdqn.bean.User;
import cn.bdqn.service.ProviderService;
import cn.bdqn.util.PageUtil;
import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by hasee on 2017/8/19.
 */
@Controller
@RequestMapping("/pro")
public class ProviderController {
    @Resource(name="providerService")
    private ProviderService service;
    private Logger log=Logger.getLogger(ProviderController.class);
    @RequestMapping("/main.html")//跳转供应商主页面
    public String main(Model model){
        PageUtil util=new PageUtil();
        try {
            util.setSqlCount(service.findByNameSqlCount(null));
            List<Provider> providers = service.findByNameLimit(null, util.getPageIndex(), util.getPageSize());
            model.addAttribute("page",util);
            model.addAttribute("providers",providers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/providerList";
    }
    @RequestMapping("/view.html/{id}")
    public String view(@PathVariable Integer id,Model model){
        try {
            model.addAttribute("pro",service.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/providerView";
    }
    @RequestMapping(value = "/add.html",method = RequestMethod.GET)//跳转添加页面
    public String add(){
        return "user/providerAdd";
    }
    @RequestMapping(value = "/add.html",method = RequestMethod.POST)//提交新增
    public String add(Provider provider,HttpSession session){
        boolean flag=false;
        User user= (User) session.getAttribute("user");
        try {
            provider.setCreatedBy(user.getUserId());
            flag= service.addService(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            return "redirect:/pro/main.html";
        }else{
           return "redirect:/pro/main.html?add=false";
        }
    }
    @RequestMapping(value = "/update.html/{id}",method = RequestMethod.GET)//跳转修改页面
    public String update(@PathVariable Integer id,Model model){
        try {
            model.addAttribute("pro",service.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/providerUpdate";
    }
    @RequestMapping(value = "/update.html",method = RequestMethod.POST)//提交修改
    public String update(Provider provider, HttpSession session){
        boolean flag=false;
        try {
            User user = (User) session.getAttribute("user");
            provider.setModifyBy(user.getUserId());
            flag=service.updateService(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            return "redirect:/pro/main.html";
        }else{
            return "redirect:/pro/main.html?update=false";
        }
    }
    @RequestMapping(value = "/delete.html/{id}",method = RequestMethod.POST)
    public void delete(@PathVariable Integer id,HttpServletResponse response){
        boolean b=false;
        PrintWriter print=null;
        try {
            b = service.deleteByBilldel(id);
            print = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (print!=null) {
            if (b){
                print.print("{\"flag\":\"true\"}");
            }else{
                print.print("{\"flag\":\"false\"}");
            }
            print.flush();
            print.close();
        }
    }
    @RequestMapping(value = "/ajax.html",method = RequestMethod.POST)//处理ajax分页请求
    public void ajax(PageUtil util, String name, Integer proId,
                     Integer isPay, HttpServletResponse response){
        try {
            util.setSqlCount(service.findByNameSqlCount(name));
            List<Provider> providers = service.findByNameLimit(name, util.getPageIndex(), util.getPageSize());
            String proString = JSON.toJSONString(providers);
            String out =  proString.replace("]",","+JSON.toJSONString(util)+"]");
            log.debug(out);
            if (proString.equals("[]")){
                out="{\"flag\":\"false\"}";
            }
            PrintWriter print = response.getWriter();
            print.print(out);
            print.flush();
            print.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
