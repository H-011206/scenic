package com.zpy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpy.pojo.CountNumber;
import com.zpy.pojo.SOrder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SorderMapper extends BaseMapper<SOrder> {
    List<SOrder> listSorder(Integer id);

    List<CountNumber> queryNum();

    List<SOrder> listWish(Integer userId);

    List<SOrder> listMySorder(Integer userId);
}
