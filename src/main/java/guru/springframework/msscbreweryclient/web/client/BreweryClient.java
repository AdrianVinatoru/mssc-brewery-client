package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
@Slf4j
public class BreweryClient {
    public final String BEER_PATH_V1 = "/api/v2/beer/";
    private String apihost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto getBeerById(UUID beerId) {
        BeerDto beerDto = restTemplate.getForObject(apihost + BEER_PATH_V1 + beerId, BeerDto.class);
        log.info("CLIENT: breweryClient.getBeerById called!");
        return beerDto;
    }

    public URI saveBeer(BeerDto beerDto) {
        URI savedBeerURI = restTemplate.postForLocation(apihost + BEER_PATH_V1, beerDto, BeerDto.class);
        log.info("CLIENT: Beer saved!");
        return savedBeerURI;
    }

    public void updateBeer(UUID beerUuid, BeerDto beerDto) {
        restTemplate.put(apihost + BEER_PATH_V1 + beerUuid, beerDto);
        log.info("CLIENT: Beer updated!");
    }

    public void deleteBeer(UUID beerUuid) {
        restTemplate.delete(apihost + BEER_PATH_V1 + beerUuid);
        log.info("CLIENT: beer deleted!");
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }
}
