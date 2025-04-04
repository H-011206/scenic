package com.zpy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpy.mapper.ScenicMapper;
import com.zpy.pojo.Scenic;
import com.zpy.service.ScenicService;
import org.springframework.stereotype.Service;

@Service
public class ScenicServiceImpl extends ServiceImpl<ScenicMapper, Scenic>implements ScenicService {
}
