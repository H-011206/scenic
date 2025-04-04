package com.zpy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpy.mapper.HotelMapper;
import com.zpy.pojo.Hotel;
import com.zpy.service.HotelService;
import org.springframework.stereotype.Service;

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper,Hotel>implements HotelService {
}
