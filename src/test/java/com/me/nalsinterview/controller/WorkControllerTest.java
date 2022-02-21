package com.me.nalsinterview.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import com.me.nalsinterview.repository.WorkRepository;
import com.me.nalsinterview.service.WorkService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WorkService service;

    @Mock
    private WorkService serviceMock;

    @Autowired
    private WorkRepository repository;

    @Mock
    private WorkRepository repositoryMock;

    @Test
    public void testFindAll_Error_01() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ReflectionTestUtils.setField(service, "repository", repositoryMock);
        when(repositoryMock.findAll(Mockito.any(Pageable.class))).thenThrow(new RuntimeException(""));

        // Call API
        ResponseEntity<Object> actualResponse = restTemplate.exchange(
                "/api/work", HttpMethod.GET, entity,
                new ParameterizedTypeReference<Object>() {
                });

        // Check result
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, actualResponse.getStatusCode());
    }

    @Test
    public void testFindAll_Normal_02() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ReflectionTestUtils.setField(service, "repository", repository);

        // Call API
        ResponseEntity<Object> actualResponse = restTemplate.exchange(
                "/api/work", HttpMethod.GET, entity,
                new ParameterizedTypeReference<Object>() {
                });

        // Check result
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }
}
