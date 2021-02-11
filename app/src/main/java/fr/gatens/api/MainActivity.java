package fr.gatens.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView data;

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), HomePage.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.data);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6007f1a4309f8b0017ee5022.mockapi.io/api/m1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
                APIRequest apiRequest = retrofit.create(APIRequest.class);
                Call<List<Post>> call = apiRequest.getPosts();
                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful()){
                            data.setText(response.code());
                            return;
                        }
                        List<Post> accounts = response.body();
                        /*
                        // AFFICHE UN SEUL ELEMNT CHOISI DANS LA METHODE GET
                        Post post=accounts.get(2);
                        String content="";
                        content+= "ID :"+ post.getId() +"\n";
                        content += "Account name : " + post.getAccountName() + "\n";
                        content += "Amount : " + post.getAmount() + "\n";
                        content += "Iban : " + post.getIban() + "\n\n";
                        data.append(content);
                        */
                        //AFFICHE TOUTES LES DONNEES
                        for(Post post : accounts) {
                            String content = "";
                            content+= "ID :"+ post.getId() +"\n";
                            content += "Account name : " + post.getAccountName() + "\n";
                            content += "Amount : " + post.getAmount() + "\n";
                            content += "Iban : " + post.getIban() + "\n\n";
                            data.append(content);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        data.setText(t.getMessage());
                    }
                });
    }
}