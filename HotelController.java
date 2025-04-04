package com.zpy.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpy.pojo.Hotel;
import com.zpy.pojo.Scenic;
import com.zpy.service.HotelService;
import com.zpy.service.ScenicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.RegEx;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private ScenicService scenicService;
    @Value("${location}")
    public String location;
    @RequestMapping("listHotel")
    public String listHotel(@RequestParam(value = "pageNum",defaultValue = "1",required = false)Integer pageNum,
                            @RequestParam(value = "pageSize",defaultValue = "6",required = false)Integer pageSize, Model model, Hotel hotel){
        if (pageNum<0||pageNum.equals("")||pageNum==null){
            pageNum=1;
        }
        if (pageSize<0||pageSize.equals("")||pageSize==null){
            pageSize=6;
        }
        PageHelper.startPage(pageNum,pageSize);
        QueryWrapper<Hotel>qw=new QueryWrapper<>();
        if (hotel.getHname()!=null){
            qw.like("hname",hotel.getHname());
        }
        List<Hotel> list = hotelService.list(qw);
        for (Hotel hotel1 : list) {
            Scenic scenic = scenicService.getById(hotel1.getSid());
            hotel1.setSname(scenic.getSname());
        }
        PageInfo<Hotel>pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "admin-hotel-list";
    }

    @RequestMapping("preSaveHotel")
    public String preSaveHotel(Model model){
        List<Scenic> list = scenicService.list(null);
        model.addAttribute("scenicList",list);
        return "admin-hotel-save";
    }

    @RequestMapping("saveHotel")
    public String saveHotel(Hotel hotel, MultipartFile file){
        if (!file.isEmpty()){
            transfile(hotel,file);
        }
        boolean save = hotelService.save(hotel);
        return "redirect:/hotel/listHotel";
    }

    private void transfile(Hotel hotel, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(index);
        String prefix=System.nanoTime()+"";
        String path=prefix+suffix;
        File file1 = new File(location);
        if (!file1.exists()){
            file1.mkdirs();
        }
        File file2 = new File(file1, path);
        try {
            file.transferTo(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        hotel.setHimage(path);
    }

    @RequestMapping("preUpdateHotel/{id}")
    public String preUpdateHotel(@PathVariable Integer id,Model model){
        Hotel byId = hotelService.getById(id);
        model.addAttribute("hotel",byId);
        List<Scenic> list = scenicService.list(null);
        model.addAttribute("scenicList",list);
        return "admin-hotel-update";
    }

    @RequestMapping("updateHotel")
    public String updateHotel(Hotel hotel,MultipartFile file){
        if (!file.isEmpty()){
            transfile(hotel,file);
        }
        boolean b = hotelService.updateById(hotel);
        return "redirect:/hotel/listHotel";
    }

    @RequestMapping("delHotel/{id}")
    public String delHotel(@PathVariable Integer id){
        boolean b = hotelService.removeById(id);
        return "redirect:/hotel/listHotel";
    }

    @ResponseBody
    @RequestMapping("batchDeleteHotel")
    public String batchDeleteHotel(String idList){
        String[] split = StrUtil.split(idList, ",");
        List<Integer>list=new ArrayList<>();
        for (String s : split) {
            if (!s.isEmpty()){
                list.add(Integer.valueOf(s));
            }
        }

        boolean b = hotelService.removeByIds(list);
        if (b){
            return "OK";
        }else {
            return "error";
        }
    }

}
