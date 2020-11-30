package com.suning;

import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.Strings;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lynn
 * @Date 2020/7/22 09:46
 */
public class HighLevelAPI {
    private RestHighLevelClient client;

    @Before
    public void init(){
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("192.168.10.101", 9200, "http"),
                new HttpHost("192.168.10.102", 9200, "http"),
                new HttpHost("192.168.10.103", 9200, "http"));
        this.client = new RestHighLevelClient(restClientBuilder);
    }

    @After
    public void after() throws IOException {
        this.client.close();
    }

    /**
     * 同步插入数据操作
     * @throws Exception
     */
    @Test
    public void testCreate() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("id", "2002");
        data.put("name", "小明");
        data.put("age", "20");
        data.put("sex","男");
        IndexRequest indexRequest = new IndexRequest("haoke", "user") .source(data);
        IndexResponse indexResponse = this.client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("id->" + indexResponse.getId());
        System.out.println("index->" + indexResponse.getIndex());
        System.out.println("type->" + indexResponse.getType());
        System.out.println("version->" + indexResponse.getVersion());
        System.out.println("result->" + indexResponse.getResult());
        System.out.println("shardInfo->" + indexResponse.getShardInfo());
    }

    /**
     * 异步新增数据
     * @throws Exception
     */
    @Test
    public void testCreateAsync() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("id", "1001");
        data.put("name", "王五");
        data.put("age", "20");
        data.put("sex","男");
        IndexRequest indexRequest = new IndexRequest("haoke", "user") .source(data);
        this.client.indexAsync(indexRequest, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                System.out.println("id->" + indexResponse.getId());
                System.out.println("index->" + indexResponse.getIndex());
                System.out.println("type->" + indexResponse.getType());
                System.out.println("version->" + indexResponse.getVersion());
                System.out.println("result->" + indexResponse.getResult());
                System.out.println("shardInfo->" + indexResponse.getShardInfo());
            }
            @Override
            public void onFailure(Exception e) {
            System.out.println(e);
             }
        });
        System.out.println("ok"); Thread.sleep(20000);
        }

    /**
     * 查询数据
     * @throws Exception
     */
    @Test
    public void testQuery() throws Exception {
        GetRequest getRequest = new GetRequest("haoke", "user", "OupFdHMB9dNA7cUrf1dF");
        // 指定返回的字段
        String[] includes = new String[]{"name", "id"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse response = this.client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("数据 -> " + response.getSource());
    }

    /**
     * 判断是否存在
     * @throws Exception
     */
    @Test
    public void testExists() throws Exception {
        GetRequest getRequest = new GetRequest("haoke", "user", "GkpdE2gBCKv8opxuOj12");
        // 不返回的字段
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = this.client.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println("exists -> " + exists);
    }

    /**
     * 删除数据
     * @throws Exception
     */
    @Test public void testDelete() throws Exception {
        DeleteRequest deleteRequest = new DeleteRequest("haoke", "user", "OupFdHMB9dNA7cUrf1dF");
        DeleteResponse response = this.client.delete(deleteRequest, RequestOptions.DEFAULT);
        // OK or NOT_FOUND
        System.out.println(response.status());
        }


    /**
     * 更新数据
     * @throws Exception
     */
    @Test public void testUpdate() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest("haoke", "user", "QOpddHMB9dNA7cUrd1dG");
        Map<String, Object> data = new HashMap<>();
        data.put("name", "小红");
        data.put("age", "18");
        updateRequest.doc(data);
        UpdateResponse response = this.client.update(updateRequest, RequestOptions.DEFAULT);
        System.out.println("version -> " + response.getVersion()); }

}
