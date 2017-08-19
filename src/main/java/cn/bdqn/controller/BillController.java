package cn.bdqn.controller;

import cn.bdqn.bean.Bill;
import cn.bdqn.bean.Provider;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.json.Json;
import javax.servlet.http.HttpServletResponse;
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
    public String view(Model model){
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
    public String updateBill(Bill bill){
        boolean flag=false;
        try {
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
    public String addBill(Bill bill){
        boolean flag=false;
        try {
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
    @RequestMapping(value = "/ajax.html",method = RequestMethod.POST)
    public void limti(Integer pageIndex, String billName, Integer proId,
                            Integer isPay, HttpServletResponse response){
        List<Bill> list=null;
        if (proId==0){
            proId=null;
        }
        if (isPay==2){
            isPay=null;
        }
        PageUtil util=new PageUtil();
        if (pageIndex==null){
            util.setPageIndex(1);
        }else{
            util.setPageIndex(pageIndex);
        }
        List<Bill> bills=null;
        try {
            util.setSqlCount(service.findBySqlCount(billName,proId,isPay));
            bills = service.findByLimit(billName, proId, isPay, util.getPageIndex(), util.getPageSize());
            String billString = JSON.toJSONString(bills);
            String out =  billString.replace("]",","+JSON.toJSONString(util)+"]");
            log.debug(out);
            if (out.equals("[]")){
                out="{flag:}";
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
