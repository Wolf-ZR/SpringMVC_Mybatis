package test;

import com.ssm.mapper.ItemsCustomMapper;
import com.ssm.po.Items;
import com.ssm.po.ItemsCustom;
import com.ssm.po.ItemsQueryVo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ItemsCustomMapperTest {
    private ApplicationContext applicationContext;


    @Before
    public void setUp() throws Exception {
        applicationContext=new ClassPathXmlApplicationContext(
                "classpath:spring/applicationContext-dao.xml");
    }
    /**
     * 查询购买商品的用户
     */
    @Test
    public void testFindItemsOfUser() throws Exception {
        ItemsCustomMapper mapper=(ItemsCustomMapper)applicationContext.getBean("itemsCustomMapper");
        Items items=new Items();
        items.setName("笔记本");
        items.setDetail("笔记本");
        ItemsQueryVo itemsQueryVo=mapper.findItemsofUser(items);
        System.out.println(itemsQueryVo);
    }

    @Test
    public void testFindItems() throws Exception {
        ItemsCustomMapper mapper=(ItemsCustomMapper)applicationContext.getBean("itemsCustomMapper");
        ItemsQueryVo vo=new ItemsQueryVo();
        ItemsCustom itemsCustom=new ItemsCustom();
        itemsCustom.setName("笔记本");
        vo.setItemsCustom(itemsCustom);
        List<ItemsCustom> items=mapper.findItems(vo);
        System.out.println(items);
    }
}