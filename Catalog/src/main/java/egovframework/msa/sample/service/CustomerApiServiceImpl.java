package egovframework.msa.sample.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerApiServiceImpl implements CustomerApiService {

    private final RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getCustomerDetailFallback")
    public String getCustomerDetail(String customerId) {

        // ribbon 의 ServerList 에 localhost:8082(customer) 를 추가함
        return restTemplate.getForObject("http://customer/customers/" + customerId, String.class);
    }

    public String getCustomerDetailFallback(String customerId, Throwable ex) {
        System.out.println("Error : " + ex.getMessage());
        return "고객 정보 조회가 지연되고 있습니다.";
    }
}
