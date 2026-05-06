package services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
public class VehicleValidator {

    private final RestClient bikesClient;
    private final RestClient carsClient;

    public VehicleValidator(@Qualifier("bikesClient") RestClient bikesClient,
            @Qualifier("carsClient") RestClient carsClient) {

        this.bikesClient = bikesClient;
        this.carsClient = carsClient;

    }

    public boolean exists(String type, Long externalId) {
        RestClient client = switch (type.toUpperCase()) {
            case "BIKE" -> bikesClient;
            case "CAR" -> carsClient;
            default -> throw new IllegalArgumentException("Invalid vehicle type: " + type);
        };
        String path = type.equalsIgnoreCase("BIKE") ? "/api/bikes/" : "/api/cars/";
        try {
            client.get().uri(path + externalId).retrieve().toBodilessEntity();
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        }
    }

}
