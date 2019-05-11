package com.ssm.service_imp;

import com.ssm.exception.CustomException;
import com.ssm.mapper.ItemsCustomMapper;
import com.ssm.mapper.ItemsMapper;
import com.ssm.po.Items;
import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;
import com.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品信息Service实现类
 */
public class ItemsServiceImp implements ItemsService {
    @Autowired
    private ItemsCustomMapper itemsCustomMapper;
    @Autowired
    private ItemsMapper itemsMapper;
    @Override
    public List<ItemsCustom> findCustoms(ItemsQueryVo itemsQueryVo) throws Exception {
        return itemsCustomMapper.findItems(itemsQueryVo);

    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {
        Items items=itemsMapper.selectByPrimaryKey(id);
        //添加扩展功能
        ItemsCustom itemsCustom=null;
        //抛异常
        if(items==null){
            throw new CustomException("该商品信息不存在！");
        }
        if(items!=null){
            itemsCustom=new ItemsCustom();
            BeanUtils.copyProperties(items,itemsCustom);
        }

        return itemsCustom;
    }

    @Override
    public int updateItemsById(Integer id, ItemsCustom itemsCustom) throws Exception {
        //添加业务校验功能
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
        return 0;
    }

    @Override
    public void deleteItems(Integer[] items_id) throws Exception {
        for(Integer id:items_id){
            itemsMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public void alterItems(ItemsQueryVo itemsQueryVo) throws Exception {
        for(ItemsCustom itemCustom:itemsQueryVo.getItemsList()){
            itemsMapper.updateByPrimaryKeyWithBLOBs(itemCustom);
        }
    }
}
