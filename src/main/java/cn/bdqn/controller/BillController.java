package cn.bdqn.controller;

import cn.bdqn.bean.Bill;
import cn.bdqn.bean.Provider;
import cn.bdqn.bean.User;
import cn.bdqn.service.BillService;
import cn.bdqn.service.ProviderService;
import cn.bdqn.util.PageUtil;
import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by hasee on 2017/8/11.
 */
@Controller
@RequestMapping("/bill")
public class BillController {
    private Logger log=Logger.getLogger(BillController.class);
    @Resource(name = "billService")
    private BillService service;
    @Resource(name="providerService")
    private ProviderService providerService;
    @RequestMapping("/main.html")//请求list主页面
    public String main(Model model){
        List<Bill> bills=null;
        List<Provider> providers=null;
        try {
            PageUtil util=new PageUtil();
            util.setSqlCount(service.findBySqlCount(null,null,null));
            bills = service.findByLimit(null, null, null, util.getPageIndex(), util.getPageSize());
            providers = providerService.findByProviderToBill();
            model.addAttribute("bills",bills);
            model.addAttribute("providers",providers);
            model.addAttribute("page",util);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/user/billList";
    }
    @RequestMapping("/view.html/{id}")//显示详情页面
    public String view(@PathVariable Integer id,Model model){
        Bill bill=null;
        try {
            bill = service.findById(id);
            model.addAttribute("bill",bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/billView";
    }
    @RequestMapping(value = "/update.html/{id}",method = RequestMethod.GET)//请求修改页面
    public String updateBill(@PathVariable Integer id,Model model){
        Bill bill=null;
        List<Provider> providers=null;
        try {
            bill = service.findById(id);
            providers = providerService.findByProviderToBill();
            model.addAttribute("bill",bill);
            model.addAttribute("providers",providers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/billUpdate";
    }
    @RequestMapping(value = "/update.html",method = RequestMethod.POST)//提交修改
    public String updateBill(Bill bill, HttpSession session){
        boolean flag=false;
        User user=(User) session.getAttribute("user");
        try {
            bill.setModifyBy(user.getUserId());
            flag=service.updateService(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            return "redirect:/bill/main.html";
        }else{
            return "redirect:/bill/main.html?update=false";
        }
    }
    @RequestMapping(value = "/add.html",method = RequestMethod.GET)//请求新增页面
    public String addBill(Model model){
        List<Provider> providers=null;
        try {
            providers = providerService.findByProviderToBill();
            model.addAttribute("providers",providers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/billAdd";
    }
    @RequestMapping(value = "/add.html",method = RequestMethod.POST)//提交新增
    public String addBill(Bill bill, HttpSession session){
        boolean flag=false;
        try {
            User user = (User) session.getAttribute("user");
            System.out.println("当前用户ID"+user.getUserId());
            bill.setCreatedBy(user.getUserId());
            flag=service.addService(bill);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            return "redirect:/bill/main.html";
        }else{
            return "redirect:/bill/main.html?add=false";
        }
    }
    @RequestMapping("/delete.html/{id}")
    public String delete(@PathVariable Integer id){
        boolean flag=false;
        try {
            flag=service.delService(new Bill(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag){
            return "redirect:/bill/main.html";
        }else{
            return "redirect:/bill/main.html?del=false";
        }
    }
    @RequestMapping(value = "/ajax.html",method = RequestMethod.POST)//处理ajax分页请求
    public void limti(PageUtil util, String name, Integer proId,
                            Integer isPay, HttpServletResponse response){
        List<Bill> list=null;
        if (proId==0){
            proId=null;
        }
        if (isPay==2){
            isPay=null;
        }
        List<Bill> bills=null;
        try {
            util.setSqlCount(service.findBySqlCount(name,proId,isPay));
            bills = service.findByLimit(name, proId, isPay, util.getPageIndex(), util.getPageSize());
            String billString = JSON.toJSONString(bills);
            String out =  billString.replace("]",","+JSON.toJSONString(util)+"]");
            log.debug(out);
            if (billString.equals("[]")){
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
