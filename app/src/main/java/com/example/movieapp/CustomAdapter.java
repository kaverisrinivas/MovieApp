package com.example.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private Context context;
    private List<RetroPhoto> photoList;
    private Picasso picasso;


    public CustomAdapter(Context context, List<RetroPhoto> photoList, Picasso picasso,OnNoteClickListener onNoteClickListener) {
        this.picasso = picasso;
        this.context = context;
        this.photoList = photoList;
        this.onNoteClickListener=onNoteClickListener;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView imageView;
        TextView txtTitle;

        public CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.text_view);
            imageView = mView.findViewById(R.id.image_view);
        }
    }

    OnNoteClickListener onNoteClickListener;

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_scrolling, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder,int position) {
        holder.txtTitle.setText(photoList.get(position).getTitle());
       // Picasso.Builder builder = new Picasso.Builder(context);
        //builder.downloader(new OkHttp3Downloader(context));
      //  Picasso picasso = builder.build();

        picasso.with(context).load(photoList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error((R.drawable.ic_launcher_background))
                .into(holder.imageView);
        holder.txtTitle.setOnClickListener(v ->
                onNoteClickListener.onNoteClick(position));


    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }
}

 /*  // private var episodeAdapter: EpisodeAdapter = EpisodeAdapter()
private var recyclerView: RecyclerView? = null
private var textView: TextView? = null


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_title_screen)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.adapter = episodeAdapter
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        val model = ViewModelProviders.of(this)[MyViewModel::class.java]
        model.loadUsers()

        model.users.observe(this,
        Observer<EpisodeData> { list ->

        episodeAdapter?.setList(list)
        Log.d("users", "value posted")
        }
        )*/