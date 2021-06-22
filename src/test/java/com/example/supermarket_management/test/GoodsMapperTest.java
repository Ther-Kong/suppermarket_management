package com.example.supermarket_management.test;

import com.example.supermarket_management.mapper.GoodsMapper;
import com.example.supermarket_management.pojo.Goods;
import com.example.supermarket_management.util.DateUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@ContextConfiguration(classes = {

})
@TestPropertySource(locations = {"classpath:application.properties"})
public class GoodsMapperTest {
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Test
    public void updateGoods() {
        System.out.println("update接口测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            Goods goods = goodsMapper.getGoods().get(0);
            Assertions.assertEquals("小凤爪2.0", goods.getName());
            goods.setName("凤爪2.0");
            goods.setInventory(100);
            goods.setPrice(12345);
            int result = goodsMapper.updateGoods(goods);
            Assertions.assertEquals(1, result);
            System.out.println("已更新");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }

    @Test
    public void insertGoods() {
        System.out.println("insertGoods接口测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            Goods goods = new Goods();
            goods.setId("1234567890125");
            goods.setName("苹果3.0");
            goods.setCategory("水果");
            goods.setPrice(12);
            goods.setPurchase(123);
            goods.setDateStart(DateUtil.strToDate("2021-05-01"));
            goods.setDateEnd(DateUtil.strToDate("2021-05-02"));
            goods.setInventory(100);
            int result = goodsMapper.insertGoods(goods);
            Assertions.assertEquals(1, result);
            System.out.println("已插入");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }

    @Test
    public void deleteGoods() {
        System.out.println("deleteGoods接口测试");
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);
            Goods goods = new Goods();
            goods.setNo(11124);
            int result = goodsMapper.deleteGoods(goods.getNo());
            Assertions.assertEquals(1, result);
            System.out.println("已删除");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.rollback();
            System.out.println("回滚");
            sqlSession.close();
        }
    }
}