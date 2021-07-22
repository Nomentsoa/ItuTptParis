package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NodeJsService {
    private static final  String BASE_URL_NODEJS = "http://localhost:3000/tptParis/";

    private static Retrofit retrofitNodeJs = new Retrofit.Builder()
            .baseUrl(BASE_URL_NODEJS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createServiceNodeJs(Class<S> serviceClass){
        return retrofitNodeJs.create(serviceClass);
    }
}
