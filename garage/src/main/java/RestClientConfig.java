import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RestClientConfig {

    @Value("${service.bikes.url}")
    private String bikesUrl;

    @Value("${service.cars.url}")
    private String carsUrl;

    @Bean(name = "bikesClient")
    public RestClient bikesClient() {
        return RestClient.builder().baseUrl(bikesUrl).build();
    }

    @Bean(name = "carsClient")
    public RestClient carsClient() {
        return RestClient.builder().baseUrl(carsUrl).build();
    }

}