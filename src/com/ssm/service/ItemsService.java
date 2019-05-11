package com.ssm.service;

import com.ssm.po.Items;
import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * 商品信息Service接口
 *
 */
public interface ItemsService {
    /**
     * 商品查询信息
     */
    List<ItemsCustom> findCustoms(ItemsQueryVo itemsQueryVo)throws Exception;
    /**
     * 根据id查询商品
     */
    ItemsCustom findItemsById(Integer id)throws Exception;
    /**
     * 根据主键（id）修改商品
     */
    int updateItemsById(Integer id,ItemsCustom itemsCustom)throws Exception;
    /**
     * 批量删除商品
     */
    void deleteItems(Integer[] items_id)throws Exception;
    /**
     * 批量修改数据
     */
    void alterItems(ItemsQueryVo itemsQueryVo)throws Exception;
}
