package com.example.aplikasipergipergi.adapter; //Baris ini mendefinisikan paket (package) dari kelas HotelAdapter. Kelas ini akan termasuk dalam paket com.example.aplikasipergipergi.adapter//


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasipergipergi.R;
import com.example.aplikasipergipergi.model.ModelHotel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

//Baris ini mengimpor kelas dan paket yang akan digunakan dalam kelas HotelAdapter.
// Beberapa kelas yang diimpor termasuk Context, LayoutInflater, View, ViewGroup, ImageView,
// RelativeLayout, TextView, RecyclerView, dan kelas-kelas lain yang terkait dengan Glide
// (library untuk memuat gambar) serta kelas ModelHotel dari paket
// com.example.aplikasipergipergi.model.//

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {
    //Baris ini mendeklarasikan kelas HotelAdapter yang merupakan subkelas dari RecyclerView.Adapter
    // dan menggunakan kelas ViewHolder yang didefinisikan di dalamnya.//

    private List<ModelHotel> items;
    private HotelAdapter.onSelectData onSelectData;
    private Context mContext;
    //Baris ini mendeklarasikan beberapa variabel member:
    //
    //items: List dari model ModelHotel yang akan digunakan sebagai data
    // untuk ditampilkan di RecyclerView.
    //onSelectData: Interface untuk menangani pemilihan data dari RecyclerView.
    //mContext: Objek Context yang digunakan dalam adapter.

    public interface onSelectData {
        void onSelected(ModelHotel modelNews);
        //Baris ini mendeklarasikan interface onSelectData yang memiliki
        // satu metode onSelected untuk menangani pemilihan data.
    }

    public HotelAdapter(Context context, List<ModelHotel> items, HotelAdapter.onSelectData xSelectData) {
        this.mContext = context;
        this.items = items;
        this.onSelectData = xSelectData;
        //Baris ini merupakan konstruktor dari kelas HotelAdapter yang menerima objek Context,
        // list items, dan objek onSelectData sebagai parameter. Konstruktor ini digunakan
        // untuk menginisialisasi variabel member.
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_hotel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ModelHotel data = items.get(position);

        //Get Image
        Glide.with(mContext)
                .load(data.getGambarHotel())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imgHotel);

        holder.tvNamaHotel.setText(data.getTxtNamaHotel());
        holder.rlListHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectData.onSelected(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder { //widget buat memanggil

        public TextView tvNamaHotel;
        public RelativeLayout rlListHotel;
        public ImageView imgHotel;

        public ViewHolder(View itemView) {
            super(itemView);
            rlListHotel = itemView.findViewById(R.id.rlListHotel);
            tvNamaHotel = itemView.findViewById(R.id.tvNamaHotel);
            imgHotel = itemView.findViewById(R.id.imgHotel);
        }
    }
}
