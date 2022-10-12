package com.example.retrofitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
      Button btn;
      private final String url="https://dummyjson.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= findViewById(R.id.button);
        btn.setOnClickListener(this::display);
    }

    public void display(View view){
        //Creating retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TestAPI testAPI = retrofit.create(TestAPI.class);
        int id=4;

        //calling the method to get the data
        Call<PojoClass> call= testAPI.getData(id);
        call.enqueue(new Callback<PojoClass>() {
            @Override
            public void onResponse(Call<PojoClass> call, Response<PojoClass> response) {
                Toast.makeText(MainActivity.this, "Data Recieved", Toast.LENGTH_SHORT).show();

                //Result
                PojoClass data= response.body();
                Log.d("Brand: ",data.getBrand());
            }

            @Override
            public void onFailure(Call<PojoClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Could not fetch data", Toast.LENGTH_SHORT ).show();
            }
        });
    }
}