package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerClientTest {
    @Autowired
    CustomerClient customerClient;

    @Test
    void getCustomerByIdTest() {
        CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void saveCustomerTest() {
        CustomerDto customerDto = CustomerDto.builder()
                .name("Ion Customer")
                .build();

        URI savedCustomerURI = customerClient.saveCustomer(customerDto);
        assertNotNull(savedCustomerURI);
    }

    @Test
    void updateCustomerTest() {
        CustomerDto customerDto = CustomerDto.builder()
                .name("Updated Customer")
                .build();

        customerClient.updateCustomer(UUID.randomUUID(), customerDto);
    }

    @Test
    void deleteCustomerTest() {
        customerClient.deleteCustomer(UUID.randomUUID());
    }
}