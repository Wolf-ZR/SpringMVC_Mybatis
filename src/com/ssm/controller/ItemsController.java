package com.ssm.controller;

import com.ssm.controller.validate.ValidateGroup1;
import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;
import com.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *Controller
 */
@Controller
@RequestMapping("/items")
public class ItemsController {
    //商品分类
    //itemtypes表示最终将方法返回值放进request的key中
    @ModelAttribute("itemtypes")
    public Map<String,String> getItemTypes(){
        Map<String,String> itemTypes=new HashMap<>();
        itemTypes.put("101","数码");
        itemTypes.put("102","母婴");
        return itemTypes;
    }
    @Autowired
    private ItemsService itemsService;
    @RequestMapping("/queryItems")
    public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo)throws Exception{
        //System.out.println(request.getParameter("id"));
        //商品列表
        List<ItemsCustom> itemslist=itemsService.findCustoms(itemsQueryVo);

        //返回modelandView
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemslist",itemslist);
        //指定试图
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }
    /**
     * 商品信息修改页面展示
     */
    /*@RequestMapping("/editItems")*/
    @RequestMapping(value = "/editItems",method={RequestMethod.POST,RequestMethod.GET})
    public String editItems(Model model, @RequestParam(value = "id",required = false,defaultValue = "1") Integer items_id)throws Exception{
        ItemsCustom itemsCustom=itemsService.findItemsById(items_id);
        //抛异常,和业务相关的异常一般在service中抛出
       /* if(itemsCustom==null){
            throw new CustomException("该商品信息不存在！");
        }*/
        //调用service根据商品信息查询商品信息

        //将商品信息放到model
        //ModelAndView modelAndView=new ModelAndView();
        //modelAndView.addObject("itemsCustom",itemsCustom);\
        //商品修改页面
        //modelAndView.setViewName("items/editItems");
        model.addAttribute("items",itemsCustom);
        return "items/editItems";
    }
    /**
     * 商品修改提交
     */
    @RequestMapping("/editItemsSubmit")
    //@Validated和BindingResult配对使用，一前一后
    public String editItemsSubmit(Model model,Integer id,
                                  @ModelAttribute(value = "items")
                                  @Validated(value = {ValidateGroup1.class}) ItemsCustom itemsCustom,
                                  BindingResult bindingResult,MultipartFile
                                  items_pic)throws Exception{

        if (bindingResult.hasErrors()) {
            List<ObjectError> errors=bindingResult.getAllErrors();
            for (ObjectError error:errors){
                System.out.println(error.getDefaultMessage());
            }
            model.addAttribute("errors",errors);
            //还可以使用model进行数据回显,简单类型只能用model回显
            model.addAttribute("items",itemsCustom);
            return "items/editItems";
        }
        //获得原始名称
        String orignalName=items_pic.getOriginalFilename();
        if(items_pic!=null&&orignalName!=null&&orignalName.length()>0){
            //存储图片的路径
            String pic_path="D:/BaiduNetdiskDownload/我的图片/";
            //新图片的名称
            String newFileName= UUID.randomUUID()+orignalName.substring(orignalName.lastIndexOf('.'));
            //新图片
            File picture=new File(pic_path+newFileName);
            //将图片从内存写入磁盘
            items_pic.transferTo(picture);
            //将新图片名称写入ItemsCustom中
            itemsCustom.setPic(newFileName);

        }
        //调用service更新商品信息，页面需要将商品信息传到此方法
        itemsService.updateItemsById(id,itemsCustom);
        //返回ModelAndView
        /*ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("success");*/
        //重定向到商品查询列表
        //return "redirect:queryItems.action";
        //页面转发,地址url不变，可以共享request
        /*return "forward:queryItems.action";*/


        return "success";
    }
    /**
     * 批量删除商品
     */
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id)throws Exception{
        itemsService.deleteItems(items_id);
        return "success";
    }
    /**
     * 批量修改商品
     */
    @RequestMapping("/alterItems")
    public ModelAndView alterItems(ItemsQueryVo itemsQueryVo)throws Exception{
        //商品列表
        List<ItemsCustom> itemslist=itemsService.findCustoms(itemsQueryVo);

        //返回modelandView
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemsList",itemslist);
        //指定试图
        modelAndView.setViewName("items/editQueryItems");
        return modelAndView;
    }
    /**
     * 批量修改提交
     */
    @RequestMapping("alterItemsSubmit")
    public String alterItemsSubmit(ItemsQueryVo itemsQueryVo)throws Exception{
        itemsService.alterItems(itemsQueryVo);
        return "success";
    }
    /**
     * 查询商品信息，输出json
     */
    @RequestMapping("/itemsView/{id}")
    public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id)throws Exception{
        //调用service查询商品信息
        ItemsCustom itemsCustom=itemsService.findItemsById(id);
        return itemsCustom;
    }
}
