package com.example.supermarket_management.mapper;


import com.example.supermarket_management.pojo.Record;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface RecordMapper {

    @Select("select inventory_records.*,goods.id as goods_id,goods.name from inventory_records,goods where inventory_records.goods_no = goods.no")
    @Results({
            @Result(column = "name",property = "goodsName"),
            @Result(column = "goods_no",property = "goodsNo"),
            @Result(column = "goods_id",property = "goodsId")
    })
    ArrayList<Record> getRecordList();

    @Insert("insert  into inventory_records (goods_no,count,time,type) values (#{goodsNo} ,#{count} ,#{time} ,#{type} )")
    int insertRecord(Record record);

    @Update("update inventory_records set type='已退款' where id = #{id}")
    int refund(int id);
    @Select("select name,goods_no,sum(count)*1 as all_count,price*sum(count) as all_price,(price-purchase)*sum(count) as profits\n" +
            "from inventory_records,goods where goods_no = goods.no and type = '销售' and  time = #{currDate} group by name")
    ArrayList<HashMap<String, String>> getDailyReport(Date currDate);
}
