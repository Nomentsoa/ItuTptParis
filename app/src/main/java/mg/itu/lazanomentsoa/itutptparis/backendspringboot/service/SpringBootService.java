package mg.itu.lazanomentsoa.itutptparis.backendspringboot.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpringBootService {

    private static final String BASE_URL_SPRING_BOOT = "http://localhost:9091/tptParis/";

    private static Retrofit retrofitSpringBootService = new Retrofit.Builder()
            .baseUrl(BASE_URL_SPRING_BOOT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createServiceSpringBoot(Class<S> serviceClass){
        return retrofitSpringBootService.create(serviceClass);
    }
}
