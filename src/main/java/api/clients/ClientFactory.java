package api.clients;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class ClientFactory {

    public static <T> T getClient(Class<T> tClass) {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(tClass, "https://restful-booker.herokuapp.com");
    }
}
