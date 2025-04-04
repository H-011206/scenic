package com.zpy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpy.mapper.RouteMapper;
import com.zpy.pojo.Route;
import com.zpy.service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route>implements RouteService {
}
