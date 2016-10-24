package schaffer.logindemo.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SchafferW on 2016/10/20.
 */

public class RetrofitUtils {

    public static final String BASE_URL = "http://git.ychlink.com/";
    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public static RetrofitService getStringService() {
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    }


}
