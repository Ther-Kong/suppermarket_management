package com.example.supermarket_management.mapper;

import com.example.supermarket_management.pojo.Goods;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Mapper
@Repository
//@SpringBootApplication
//@RestController
//@ComponentScan(basePackages={"**.***.**"})
//@ServletComponentScan(basePackages={"**.***.**"})
//@MapperScan(basePackages={"**.***.**"}, sqlSessionFactoryRef = "sqlSessionFactory")
//@EnableScheduling //定时任务注解
public interface GoodsMapper {
    @Select("select * from goods where status = 0")
    @Results({
            @Result(column = "date_start",property = "dateStart"),
            @Result(column = "date_end",property = "dateEnd")
    })
    ArrayList<Goods> getGoods();
    @Update("update goods set id = #{id} , name = #{name},category =#{category} ," +
            "purchase = #{purchase},price = #{price},date_start = #{dateStart} ,date_end = #{dateEnd} ," +
            "inventory = #{inventory} where no = #{no}")
    int updateGoods(Goods goods);
    @Insert("insert into goods (id,name,category,purchase,price,date_start,date_end,inventory)" +
            " values(#{id},#{name},#{category} ,#{purchase} ,#{price} ,#{dateStart} ,#{dateEnd} ,#{inventory} )")
    int insertGoods(Goods goods);
}
