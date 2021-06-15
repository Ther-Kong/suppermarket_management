package com.example.supermarket_management.service;

import com.example.supermarket_management.mapper.RecordMapper;
import com.example.supermarket_management.pojo.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RecordService {

    @Autowired
    RecordMapper recordMapper;

    public ArrayList<Record> getRecordList() {
        return recordMapper.getRecordList();
    }

    public int insertRecord(Record record) {
        return recordMapper.insertRecord(record);
    }

    public int refund(int id) {
        return recordMapper.refund(id);
    }
}
