package com.zhaoyang.vert.module.system.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhaoyang.vert.core.common.constant.enums.BizExceptionEnum;
import com.zhaoyang.vert.core.common.exception.VertException;
import com.zhaoyang.vert.core.support.StrKit;
import com.zhaoyang.vert.module.system.dao.DictMapper;
import com.zhaoyang.vert.module.system.model.Dict;
import com.zhaoyang.vert.module.system.service.DictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author : zhaoyang.li
 * @date : 2018/5/10
 */
@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Override
    public void addDict(String dictName, String dictValues) {
        //判断有没有该字典
        List<Dict> dicts = dictMapper.selectList(new EntityWrapper<Dict>().eq("name", dictName).and().eq("pid", 0));
        if (dicts != null && dicts.size() > 0) {
            throw new VertException(BizExceptionEnum.DICT_EXISTED);
        }

        //解析dictValues
        List<Map<String, String>> items = StrKit.parseKeyValue(dictValues);

        //添加字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setNum(0);
        dict.setPId(0);
        this.dictMapper.insert(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String num = item.get(StrKit.STR_KEY);
            String name = item.get(StrKit.STR_VALUE);
            Dict itemDict = new Dict();
            itemDict.setPId(dict.getId());
            itemDict.setName(name);
            try {
                itemDict.setNum(Integer.valueOf(num));
            } catch (NumberFormatException e) {
                throw new VertException(BizExceptionEnum.DICT_MUST_BE_NUMBER);
            }
            this.dictMapper.insert(itemDict);
        }
    }

    @Override
    public void editDict(Integer dictId, String dictName, String dicts) {
        //删除之前的字典
        this.delteDict(dictId);

        //重新添加新的字典
        this.addDict(dictName, dicts);
    }

    @Override
    public void delteDict(Integer dictId) {
        //删除这个字典的子词典
        Wrapper<Dict> dictEntityWrapper = new EntityWrapper<Dict>();
        dictEntityWrapper = dictEntityWrapper.eq("pid", dictId);
        dictMapper.delete(dictEntityWrapper);

        //删除这个词典
        dictMapper.deleteById(dictId);
    }

    @Override
    public List<Dict> selectByCode(String code) {
        return this.baseMapper.selectByCode(code);
    }

    @Override
    public List<Map<String, Object>> list(String conditiion) {
        return this.baseMapper.list(conditiion);
    }
}
