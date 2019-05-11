package com.ssm.mapper;

import com.ssm.po.Items;
import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;

import java.util.List;

public interface ItemsCustomMapper {
    /**
     * 查找购买商品的用户
     * @param items
     * @return
     * @throws Exception
     */
    ItemsQueryVo findItemsofUser(Items items)throws Exception;

    /**
     * 查询商品列表
     * @param itemsQueryVo
     * @return
     * @throws Exception
     */
    List<ItemsCustom> findItems(ItemsQueryVo itemsQueryVo)throws Exception;
}
