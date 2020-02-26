package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.customer", ignoreUnknownFields = false)
@Slf4j
public class CustomerClient {
    public String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    private final RestTemplate restTemplate;

    public CustomerClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public CustomerDto getCustomerById(UUID customerUuid) {
        log.info("CUSTOMER CLIENT- GET customer with id: {}", customerUuid);
        return restTemplate.getForObject(
                apihost + CUSTOMER_PATH_V1 + customerUuid, CustomerDto.class);
    }

    public URI saveCustomer(CustomerDto customerDto) {
        log.info("CUSTOMER CLIENT- Save customer: {}", customerDto);
        return restTemplate.postForLocation(apihost + CUSTOMER_PATH_V1, customerDto, CustomerDto.class);
    }

    public void updateCustomer(UUID custId, CustomerDto customerDto) {
        log.info("CUSTOMER CLIENT- Update customer: {}", customerDto);
        restTemplate.put(apihost + CUSTOMER_PATH_V1 + custId, customerDto);
    }

    public void deleteCustomer(UUID custId) {
        log.info("CUSTOMER CLIENT- Delete customer: {}");
        restTemplate.delete(apihost + CUSTOMER_PATH_V1 + custId);
    }
}
