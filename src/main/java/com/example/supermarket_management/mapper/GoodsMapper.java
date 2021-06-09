package com.example.supermarket_management.mapper;

import com.example.supermarket_management.pojo.Good;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface GoodsMapper {
    @Select("select * from goods")
    @Results({
            @Result(column = "date_start",property = "dateStart"),
            @Result(column = "date_end",property = "dateEnd")
    })
    ArrayList<Good> getGoods();
}
