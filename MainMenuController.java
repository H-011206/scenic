package com.zpy.controller;

import com.zpy.pojo.CountNumber;
import com.zpy.pojo.MainMenu;
import com.zpy.service.SorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("main")
public class MainMenuController {

    @Autowired
    private SorderService sorderService;

    @RequestMapping("mainMenu")
    public List<MainMenu> queryNum(){
        List<CountNumber>list=sorderService.queryNum();
        List<MainMenu>list1=new ArrayList<>();
        for (CountNumber countNumber : list) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setType(countNumber.getName());
            mainMenu.setMount(Integer.valueOf(countNumber.getCount()));
            list1.add(mainMenu);
        }

        return list1;
    }
}
