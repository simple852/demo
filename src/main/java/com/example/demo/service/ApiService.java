package com.example.demo.service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.response.ResponseDto;
import com.example.demo.mapper.DataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApiService {
    @Autowired
    private  DataMapper dataMapper;
    private String BASEURL = "http://api.odcloud.kr";
    private String PATH = "/api/15109950/v1/uddi:1f78fe49-78b4-4784-a5f0-e2c8a60515b4";
    private final String SERVICEKEY = "yjHOBHOQT8MqXtWjX8LdAEYMqNe53bpvUQ%2BOmTq55fwa0NCwX5z0%2FX8Rp7gLSQ6ACyHCOGAd0cHV4cck1wksIQ%3D%3D";
    @Autowired
    private  RestTemplate restTemplate;

    public List<ResponseDto> getApi(SearchDto searchDto) throws UnsupportedEncodingException, URISyntaxException, ParseException {
        String encodedQuery = URLDecoder.decode(SERVICEKEY, StandardCharsets.UTF_16);

//        //인증키 인코딩 에러남
//        URI uri = UriComponentsBuilder
//                .fromUriString(BASEURL)
//                .path(PATH)
//                .queryParam("page","1")
//                .queryParam("perPage","10")
//                .queryParam("serviceKey",SERVICEKEY)
//                .build()
//                .toUri();

        String apiUrl = BASEURL +PATH + "?serviceKey="+ SERVICEKEY +
                    "&page="+ searchDto.getPage() +
                    "&perPage=" + searchDto.getPerPage();
        URI uri2 = new URI(apiUrl);

        ResponseEntity<Map> response = restTemplate.getForEntity(uri2, Map.class);
        Map jsonResponse = response.getBody();
        List<Map> dataArray = (List<Map>) jsonResponse.get("data");
        List<ResponseDto> list = new ArrayList<>();
        for (Map data : dataArray) {
           String result = data.get(String.valueOf("시군구명")).toString();
           if(Objects.equals(result, searchDto.getLocation()))
           {
             ResponseDto responseDto  =  ResponseDto.builder()
                     .business(String.valueOf(data.get("사업자번호")))
                     .location(result)
                     .name(String.valueOf(data.get("가맹점명")))
                     .count(0)
                     .build();
             if(searchData(responseDto.getBusiness()) == null){
              dataMapper.dataSave(responseDto);
             }else{
                 dataMapper.dataUpdate(responseDto);
             }
               list.add(responseDto);
             log.info(String.valueOf(responseDto));
           }
        }
        return  list;
    }

    public ResponseDto searchData( String keyword) throws ParseException {
       return dataMapper.searchData(keyword);
    }
}
