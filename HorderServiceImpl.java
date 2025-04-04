package com.zpy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpy.mapper.HorderMapper;
import com.zpy.pojo.Horder;
import com.zpy.service.HorderService;
import org.springframework.stereotype.Service;

@Service
public class HorderServiceImpl extends ServiceImpl<HorderMapper, Horder>implements HorderService {
}
