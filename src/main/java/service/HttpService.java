package service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("httpService")
public class HttpService {
	
	@Autowired(required = false)  
    private CloseableHttpClient httpClient;  
  
    // ������Ϣ������  
    @Autowired(required = false)  
    private RequestConfig requestConfig;
    
    /** 
     * ִ��Get���� 
     *  
     * @param url 
     * @return ���󵽵����� 
     * @throws URISyntaxException 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public String doGet(String url) throws URISyntaxException,  
            ClientProtocolException, IOException {
        return doGet(url, null); 
    }  
  
    /** 
     * ִ��Get���� 
     *  
     * @param url 
     * @param params 
     *            �����еĲ��� 
     * @return ���󵽵����� 
     * @throws URISyntaxException 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public String doGet(String url, Map<String, Object> params)  
            throws URISyntaxException, ClientProtocolException, IOException {  
        // ��������Ĳ���  
        URI uri = null;  
        if (params != null) {  
            URIBuilder builder = new URIBuilder(url);  
            for (Map.Entry<String, Object> entry : params.entrySet()) {  
                builder.addParameter(entry.getKey(),  
                        String.valueOf(entry.getValue()));  
            }  
            uri = builder.build();  
        }  
  
        // ����http GET����  
        HttpGet httpGet = null;  
        if (uri != null) {  
            httpGet = new HttpGet(uri);  
        } else {  
            httpGet = new HttpGet(url);  
        }  
        // �����������  
        httpGet.setConfig(this.requestConfig);  
  
        // ����Ľ��  
        CloseableHttpResponse response = null;  
        try {  
            // ִ������  
            response = httpClient.execute(httpGet);  
            // �жϷ���״̬�Ƿ�Ϊ200  
            if (response.getStatusLine().getStatusCode() == 200) {  
                // ��ȡ����˷��ص�����,������  
                return EntityUtils.toString(response.getEntity(), "UTF-8");  
            }  
        } finally {  
            if (response != null) {  
                response.close();  
            }  
        }  
        return null;  
    }  
  
    /** 
     *  
     * @param url 
     * @param params 
     *            �����еĲ��� 
     * @return ���󵽵����� 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     * @throws IOException 
     */  
    public String doPost(String url, Map<String, Object> params)  
            throws URISyntaxException, ClientProtocolException, IOException {  
        // ����post����  
        List<NameValuePair> parameters = null;  
        // ����һ��form��ʽ��ʵ��  
        UrlEncodedFormEntity formEntity = null;  
  
        // ��������Ĳ���  
        if (params != null) {  
            // ����post����  
            parameters = new ArrayList<NameValuePair>();  
            for (Map.Entry<String, Object> entry : params.entrySet()) {  
                // ��Ӳ���  
                parameters.add(new BasicNameValuePair(entry.getKey(), String  
                        .valueOf(entry.getValue())));  
            }  
            // ����һ��form��ʽ��ʵ��  
            formEntity = new UrlEncodedFormEntity(parameters);  
        }  
  
        // ����http GET����  
        HttpPost httpPost = null;  
        if (formEntity != null) {  
            httpPost = new HttpPost(url);  
            // ������ʵ�����õ�httpPost������  
            httpPost.setEntity(formEntity);  
            // αװ���������  
            httpPost.setHeader(  
                    "User-Agent",  
                    "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");  
        } else {  
            httpPost = new HttpPost(url);  
            // αװ���������  
            httpPost.setHeader(  
                    "User-Agent",  
                    "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36");  
        }  
        // �����������  
        httpPost.setConfig(this.requestConfig);  
  
        // ����Ľ��  
        CloseableHttpResponse response = null;  
        try {  
            // ִ������  
            response = httpClient.execute(httpPost);  
            // �жϷ���״̬�Ƿ�Ϊ200  
            if (response.getStatusLine().getStatusCode() == 200) {  
                // ��ȡ����˷��ص�����,������  
                return EntityUtils.toString(response.getEntity(), "UTF-8");  
            }  
        } finally {  
            if (response != null) {  
                response.close();  
            }  
        }  
        return null;  
    }  
  
    /** 
     *  
     * @param url 
     * @param params 
     *            �����еĲ��� 
     * @return ���󵽵����� 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     * @throws IOException 
     */  
    public String doPost(String url) throws URISyntaxException,  
            ClientProtocolException, IOException {  
        return doPost(url, null);  
    }  
} 
