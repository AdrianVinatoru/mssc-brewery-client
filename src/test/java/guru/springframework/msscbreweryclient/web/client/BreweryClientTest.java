package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    private BreweryClient breweryClient;

    @Test
    public void getBeerByIdTest() {
        BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());
        assertNotNull(beerDto);
    }

    @Test
    public void saveBeerTest() {
        BeerDto beerDto = BeerDto.builder()
                .beerName("AMZEL Beer")
                .beerStyle("PILSNER")
                .upc(100L)
                .build();

        URI savedBeerUri = breweryClient.saveBeer(beerDto);

        assertNotNull(savedBeerUri);
    }

    @Test
    public void updateBeerTest() {
        BeerDto beerDto = BeerDto.builder()
                .beerName("UPDATED Beer")
                .beerStyle("GOSE")
                .upc(120L)
                .build();

        breweryClient.updateBeer(UUID.randomUUID(), beerDto);
    }

    @Test
    public void deleteBeerTest() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }
}
