package mg.itu.lazanomentsoa.itutptparis.backendnodejs.service;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NodeJsService {
   // private static final  String BASE_URL_NODEJS = "http://10.163.1.166:3000/api/";
    //private static final  String BASE_URL_NODEJS = "http://192.168.1.135:3000/api/";
    //private static final  String BASE_URL_NODEJS = "http://192.168.43.165:3000/api/";
    private static final  String BASE_URL_NODEJS = "https://mbdsp7-node-tpt-pari.herokuapp.com/api/";

    private static Retrofit retrofitNodeJs = new Retrofit.Builder()
            .baseUrl(BASE_URL_NODEJS)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static <S> S createServiceNodeJs(Class<S> serviceClass){
        return retrofitNodeJs.create(serviceClass);
    }
}
