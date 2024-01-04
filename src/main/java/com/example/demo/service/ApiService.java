package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    private String BASEURL = "http://api.odcloud.kr";
    private String PATH = "/api/15109950/v1/uddi:1f78fe49-78b4-4784-a5f0-e2c8a60515b4";
    private final String SERVICEKEY = "yjHOBHOQT8MqXtWjX8LdAEYMqNe53bpvUQ%2BOmTq55fwa0NCwX5z0%2FX8Rp7gLSQ6ACyHCOGAd0cHV4cck1wksIQ%3D%3D";
    private final RestTemplate restTemplate;

    public List<Map> getApi() throws UnsupportedEncodingException, URISyntaxException {
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
                    "&page=1" +
                    "&perPage=10";
        URI uri2 = new URI(apiUrl);

        ResponseEntity<Map> response = restTemplate.getForEntity(uri2, Map.class);
        Map jsonResponse = response.getBody();
        List<Map> dataArray = (List<Map>) jsonResponse.get("data");

        for (Map data : dataArray) {
           String result = data.get(String.valueOf("시군구명")).toString();
           if(Objects.equals(result, "달서구"))
           {
               log.info("지역명 : "+data.toString());
           }
        }


        return  dataArray;
    }

//    public List searchData(JSONObject dataList, String keyword) throws ParseException {
//
//        dataList.g
//        return list;
//    }
}
