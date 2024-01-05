package com.example.demo.controller;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ApiService apiService;

    @GetMapping("/api")
    @ResponseBody
    public List<ResponseDto> getApi(SearchDto searchDto) throws UnsupportedEncodingException, URISyntaxException, ParseException {

        return apiService.getApi(searchDto);

    }

    @GetMapping("/")
    public String page(){
        return "index";
    }


//    @GetMapping("/api2")
//    public List getApi2() throws UnsupportedEncodingException, URISyntaxException, ParseException {
//
//        return apiService.searchData(apiService.getApi(),"");
//
//    }
}
