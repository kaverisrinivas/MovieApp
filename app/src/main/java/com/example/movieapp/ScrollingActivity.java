package com.example.movieapp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScrollingActivity extends AppCompatActivity implements OnNoteClickListener{
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_scrolling);
        progressDialog = new ProgressDialog(ScrollingActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        GetDataService service = RetrofitClientInstance.getRetrofit().create(GetDataService.class);
        Call<List<RetroPhoto>> call = service.getAllPhotos();
      //  service.getSinglePhoto(1);
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                progressDialog.dismiss();
                generatePhotoList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ScrollingActivity.this,"something went wrong...please try again",Toast.LENGTH_LONG).show();

            }
        });



    }


    private void generatePhotoList(List<RetroPhoto> photoList) {

        recyclerView=findViewById(R.id.customRecyclerView);
       adapter=new CustomAdapter(this,photoList,Picasso.with(getBaseContext()),this);
     //   recyclerView.setHasFixedSize(true);
      //  RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ScrollingActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onNoteClick(int position) {
        Toast.makeText(this,"hello busy people",Toast.LENGTH_LONG).show();
        Intent intent=new Intent(this,ChildActivity.class);
        //intent.putExtra("all",1);
        startActivity(intent);

    }
}
