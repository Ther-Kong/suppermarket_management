package com.example.supermarket_management.controller;

import com.example.supermarket_management.pojo.Goods;
import com.example.supermarket_management.pojo.Record;
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
        Record record = new Record();
        if(goodsNo!=0){
            //update操作
            System.out.println("开始更新操作");
            int goodsNum = goodsService.getGoodsCountByNo(goodsNo);
            flag = goodsService.updateGoods(goods);
            if (goodsNum<goods.getInventory()){
                record.setType("进货");
                record.setCount(goods.getInventory()-goodsNum);
            }else{
                record.setType("销售");
                record.setCount(goodsNum-goods.getInventory());
            }
            record.setGoodsNo(goodsNo);
        }else{
            //add操作
            System.out.println("开始插入操作");
            flag = goodsService.insertGoods(goods);
            record.setGoodsNo(goods.getNo());
            record.setCount(goods.getInventory());
            record.setType("进货");
        }
        record.setTime(DateUtil.getCurrDate());
        flag = recordService.insertRecord(record);
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
        int num = goodsService.getGoodsCountByNo(no);
        int flag = goodsService.updateGoodsInventory(no,num-count);
        //将操作记录插入inventory_records表中操作人固定写死，类型销售
        Record record = new Record();
        record.setGoodsNo(no);
        record.setCount(count);
        record.setType("销售");
        record.setTime(DateUtil.getCurrDate());
        flag = flag & recordService.insertRecord(record);
        Result<String> result = new Result<>();
        result.setCode(200);
        result.setMsg("物品销售成功");
        return result;
    }

    @GetMapping("/purchase")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> purchaseGoods(int no,int count){
        System.out.println(no);
        System.out.println(count);
//        System.out.println(request.getParameter("no"));
//        System.out.println(request.getParameter("count"));
        //进货修改goods表中的inventory

        //将操作记录插入inventory_records表中操作人固定写死，类型进货
        Result<String> result = new Result<>();
        result.setCode(200);
        result.setMsg("物品销售成功");
        return result;
    }

    @GetMapping("delete")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> deleteGoods(int no){
        //将goods表中对应的goods的status改为=1
        int flag = goodsService.deleteGoods(no);
        Result<String> result = new Result<>();
        if (flag == 1) {
            result.setCode(HttpStatus.OK.value());
            result.setMsg("物品删除成功");
        } else {
            result.setCode(HttpStatus.NOT_ACCEPTABLE.value());
            result.setMsg("物品删除失败");
        }
        return result;
    }
}
