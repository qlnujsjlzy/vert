package com.zhaoyang.vert.module.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhaoyang.vert.module.system.model.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 字典表 Mapper 接口
 *
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
public interface DictMapper extends BaseMapper<Dict> {

    /**
     * 根据编码获取词典列表
     */
    List<Dict> selectByCode(@Param("code") String code);

    /**
     * 查询字典列表
     */
    List<Map<String, Object>> list(@Param("condition") String conditiion);
}