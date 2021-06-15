package com.example.supermarket_management.service;

import com.example.supermarket_management.mapper.GoodsMapper;
import com.example.supermarket_management.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    public ArrayList<Goods> getGoods() {
        return goodsMapper.getGoods();
    }

    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    public Goods insertGoods(Goods goods) {
        return goodsMapper.insertGoods(goods);
    }


    public int getGoodsCountByNo(int goodsNo) {
        return goodsMapper.getGoodsCountByNo(goodsNo);
    }

    public int updateGoodsInventory(int no, int count) {
        return goodsMapper.updateGoodsInventory(no,count);
    }
}
