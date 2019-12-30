package com.example.movieapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    private TextView textView;
    private ImageView imageView;
    private Picasso picasso;

    RetroPhoto photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        photo = new RetroPhoto(1,1,"ff","das","dsf");

        setContentView(R.layout.activity_child);
        textView=(TextView)findViewById(R.id.text_child);
        imageView=(ImageView)findViewById(R.id.image_url);
        progressDialog = new ProgressDialog(ChildActivity.this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        picasso = Picasso.with(this);

        GetDataService service= RetrofitClientInstance.getRetrofit().create(GetDataService.class);
        Call<RetroPhoto> call=service.getSinglePhoto(getTaskId());
        call.enqueue(new Callback<RetroPhoto>() {
            @Override
            public void onResponse(Call<RetroPhoto> call, Response<RetroPhoto> response) {
                progressDialog.dismiss();
                generateSinglePhotoList(response.body());
            }

            @Override
            public void onFailure(Call<RetroPhoto> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ChildActivity.this,"something wrong",Toast.LENGTH_LONG).show();
            }
        });


    }

    private void generateSinglePhotoList(RetroPhoto photo) {
//        String myPhotoTitle = photo.getTitle();
//        Log.d("ChildActivity",myPhotoTitle);
        textView.setText(photo.getTitle());

        picasso.with(this).load(photo.getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error((R.drawable.ic_launcher_background))
                .into(imageView);
      // textView=(TextView)findViewById(R.id.text_child);
        //imageView=(ImageView)findViewById(R.id.image_view);
      //String text = getIntent().getStringExtra("text");
    }
}
