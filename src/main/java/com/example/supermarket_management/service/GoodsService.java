package com.example.supermarket_management.service;

import com.example.supermarket_management.mapper.GoodsMapper;
import com.example.supermarket_management.pojo.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    public ArrayList<Good> getGoods() {
        return goodsMapper.getGoods();
    }
}
