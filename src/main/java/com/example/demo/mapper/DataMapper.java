package com.example.demo.mapper;

import com.example.demo.dto.response.ResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataMapper {

    void dataSave(ResponseDto responseDto);

    ResponseDto searchData(String business);

    void dataUpdate(ResponseDto responseDto);
}
