package com.example.todomusica.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomusica.R;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> implements Filterable {


    Context mContext;
    List<ArtistItem> mData ;
    List<ArtistItem> mDataFiltered ;
    boolean isDark = false;


    public ArtistAdapter(Context mContext, List<ArtistItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;
    }

    public ArtistAdapter(Context mContext, List<ArtistItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;

    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_artist,viewGroup,false);
        return new ArtistViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder ArtistViewHolder, int position) {

        ArtistViewHolder.tv_title.setText(mDataFiltered.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {

                    mDataFiltered = mData ;

                }
                else {
                    List<ArtistItem> lstFiltered = new ArrayList<>();
                    for (ArtistItem row : mData) {

                        if (row.getName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }

                    }

                    mDataFiltered = lstFiltered;

                }


                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDataFiltered = (List<ArtistItem>) results.values;
                notifyDataSetChanged();

            }
        };




    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {



        TextView tv_title,tv_content,tv_date;
        ImageView img_user;
        RelativeLayout container;





        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.cardContainer);
            tv_title = itemView.findViewById(R.id.cardArtistName);
            /*tv_content = itemView.findViewById(R.id.tv_description);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_user = itemView.findViewById(R.id.img_user);*/

            if (isDark) {
                setDarkTheme();
            }
        }


        private void setDarkTheme() {

            //container.setBackgroundResource(R.drawable.card_bg_dark);

        }



    }
}