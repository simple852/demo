package com.example.demo.service;

import com.example.demo.dto.SearchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest
public class ApiServiceTest {
    @Autowired
    private ApiService apiService;

    @Test
    public void getApiTest() throws Exception {
        // 테스트에 필요한 가상의 데이터를 생성합니다.
        SearchDto searchDto = new SearchDto();
        searchDto.setPage(1);
        searchDto.setPerPage(10);
        searchDto.setLocation("달서구");

        apiService.getApi(searchDto);


    }


}
