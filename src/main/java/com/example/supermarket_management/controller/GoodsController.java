package com.example.supermarket_management.controller;

import com.example.supermarket_management.pojo.Goods;
import com.example.supermarket_management.util.DateUtil;
import com.example.supermarket_management.util.Result;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
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

    @PostMapping("/upload")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> uploadGoods(){
        Goods goods = new Goods();
        int goodsNo = Integer.parseInt(request.getParameter("no"));
        goods.setNo(goodsNo);
        goods.setId(request.getParameter("id"));
        goods.setName(request.getParameter("name"));
        goods.setCategory(request.getParameter("category"));
        goods.setPurchase(Integer.parseInt(request.getParameter("purchase")));
        goods.setPrice(Integer.parseInt(request.getParameter("price")));
        goods.setDateStart(DateUtil.strToDate(request.getParameter("dateStart")));
        goods.setDateEnd(DateUtil.strToDate(request.getParameter("dateEnd")));
        goods.setInventory(Integer.parseInt(request.getParameter("inventory")));
        int flag;
        if(goodsNo!=0){
            //update操作
            System.out.println("开始更新操作");
            flag = goodsService.updateGoods(goods);
        }else{
            //add操作
            System.out.println("开始插入操作");
            flag = goodsService.insertGoods(goods);
        }
        System.out.println("插入操作完毕");
        System.out.println(goodsNo);
        Result<String> result = new Result<>();
        result.setCode(HttpStatus.OK.value());
        result.setMsg("物品上传成功");
        result.setData("物品上传成功");
        return result;
    }

    @GetMapping("/sell")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> sellGoods(int no,int count){
        System.out.println(no);
        System.out.println(count);
//        System.out.println(request.getParameter("no"));
//        System.out.println(request.getParameter("count"));
        //销售修改goods表中的inventory
        //将操作记录插入inventory_records表中操作人固定写死，类型销售
        Result<String> result = new Result<>();
        result.setCode(200);
        result.setMsg("物品销售成功");
        return result;
    }

    @GetMapping("delete")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> deleteGoods(int no){
        //将goods表中对应的goods的status改为=1
        Result<String> result = new Result<>();
        result.setCode(200);
        result.setMsg("物品删除成功");
        return result;
    }
}
