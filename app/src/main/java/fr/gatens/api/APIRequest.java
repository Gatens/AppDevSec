package fr.gatens.api;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequest {
    //public static final String TEST = MainActivity.getEndpoint();
    @GET("accounts")
    Call<List<Post>> getPosts();
}