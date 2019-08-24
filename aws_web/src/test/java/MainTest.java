import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class MainTest {
    @Test
    public void test1(){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String,Object> map = new LinkedMultiValueMap();
        String file = "F:\\colton_test\\src\\main\\java\\com\\colton\\service\\AwsService.java";
        map.add("uploadFile",new FileSystemResource(new File(file)));
        map.add("bucketName","coltoncheng");

        HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity<>(map);
        restTemplate.exchange("http://localhost:8080/aws/upload", HttpMethod.POST,httpEntity,String.class);
    }
}
