package com.example.supermarket_management.controller;

import com.example.supermarket_management.pojo.Goods;
import com.example.supermarket_management.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController{

    @GetMapping("/list")
    public Result<ArrayList<Goods>> getGoodsList() throws Exception{
        Result<ArrayList<Goods>> result = new Result<>();
        ArrayList<Goods> goods = new ArrayList<>();
        goods = goodsService.getGoods();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取成功！");
        result.setData(goods);
        return result;
    }

}
