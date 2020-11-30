package com.suning;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lynn
 * @Date 2020/7/22 09:46
 */
public class LowLevelAPI {
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private RestClient restClient;

    @Before
    public void init(){
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("192.168.10.101", 9200, "http"),
                new HttpHost("192.168.10.102", 9200, "http"),
                new HttpHost("192.168.10.103", 9200, "http"));
        restClientBuilder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(Node node) {
                System.out.println("出错了 -> " + node);
            }
        });
        this.restClient = restClientBuilder.build();
    }

    @After
    public void after() throws IOException {
        this.restClient.close();
    }

    /**
     * 查看集群状态
     * @throws IOException
     */
    @Test
    public void testClusterState() throws IOException {
        Request request = new Request("GET", "/_cluster/state");
        request.addParameter("pretty","true");
        Response response = this.restClient.performRequest(request);
        System.out.println(response.getRequestLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    /**
     * 新增数据
     * @throws IOException
     */
    @Test public void testCreateData() throws IOException { 
        Request request = new Request("POST", "/haoke/user");
        Map<String, Object> data = new HashMap<>(); 
        data.put("id","2001");
        data.put("name","小李");
        data.put("age","25");
        data.put("sex","男");
        request.setJsonEntity(MAPPER.writeValueAsString(data)); 
        Response response = this.restClient.performRequest(request); 
        System.out.println(response.getStatusLine()); 
        System.out.println(EntityUtils.toString(response.getEntity())); }

    /**
     * 查询数据
     * @throws IOException
     */
    @Test public void testQueryData() throws IOException {
        Request request = new Request("GET", "/haoke/user/OupFdHMB9dNA7cUrf1dF");
        Response response = this.restClient.performRequest(request);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    @Test public void testSearchData() throws IOException {
        Request request = new Request("POST", "/haoke/user/_search");
        String searchJson = "{\"query\": {\"match\": {\"name\": \"张三\"}}}";
        request.setJsonEntity(searchJson);
        request.addParameter("pretty", "true");
        Response response = this.restClient.performRequest(request);
        System.out.println(response.getStatusLine());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
