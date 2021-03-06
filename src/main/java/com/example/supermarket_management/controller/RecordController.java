package com.example.supermarket_management.controller;

import com.example.supermarket_management.pojo.Goods;
import com.example.supermarket_management.pojo.Record;
import com.example.supermarket_management.util.DateUtil;
import com.example.supermarket_management.util.Result;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("record")
public class RecordController extends BaseController{
    @GetMapping("list")
    public Result<ArrayList<Record>> getRecordList(){
        Result<ArrayList<Record>> result = new Result<>();
        ArrayList<Record> recordlist = recordService.getRecordList();
        System.out.println("暂停");
        result.setData(recordlist);
        result.setCode(HttpStatus.OK.value());
        result.setMsg("获取记录成功");
        return result;
    }

    @GetMapping("refund")
    @Transactional(rollbackFor = {SQLException.class})
    public Result<String> refund(int id,int no,int count){
        Result<String> result = new Result<>();
        System.out.println("goodsNo"+no);
        int flag;
        // 修改record
        flag = recordService.refund(id);
        //获取商品当前数量
        int num = goodsService.getGoodsCountByNo(no);
        //修改goods
        flag = flag & goodsService.updateGoodsInventory(no,num+count);
        if(flag==1){
            result.setMsg("退货成功");
            result.setCode(HttpStatus.OK.value());
            result.setData("退货成功");
        }else{
            result.setMsg("退货失败");
            result.setCode(HttpStatus.NOT_FOUND.value());
        }
        return result;
    }

    @GetMapping("report/daily")
    public Result<ArrayList <String[]>>getDailyReport(){
        Result<ArrayList <String[]>> result = new Result<>();
        ArrayList<HashMap<String,String>> mapList = recordService.getDailyReport(DateUtil.getCurrDate());
        ArrayList <String[]> data = new ArrayList<>();
        data.add(new String []{"商品","销售总量","销售总额","净利润"});
        for (HashMap<String,String> map:mapList) {
            data.add(new String[]{map.get("name"),String.valueOf(map.get("all_count")),String.valueOf(map.get("all_price")),String.valueOf(map.get("profits"))});
        }
        result.setData(data);
        result.setCode(HttpStatus.OK.value());
        return result;
    }

}
