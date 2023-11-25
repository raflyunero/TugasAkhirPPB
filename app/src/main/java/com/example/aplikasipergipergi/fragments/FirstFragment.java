package com.example.aplikasipergipergi.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aplikasipergipergi.HotelActivity;
import com.example.aplikasipergipergi.KulinerActivity;
import com.example.aplikasipergipergi.R;
import com.example.aplikasipergipergi.WisataActivity;
import com.example.aplikasipergipergi.adapter.MainAdapter;
import com.example.aplikasipergipergi.decoration.LayoutMarginDecoration;
import com.example.aplikasipergipergi.model.ModelMain;
import com.example.aplikasipergipergi.utils.Tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FirstFragment extends Fragment {

    private View view;

    RecyclerView rvMainMenu;
    MainAdapter mainAdapter;
    LayoutMarginDecoration gridMargin;
    ModelMain mdlMainMenu;
    List<ModelMain> lsMainMenu = new ArrayList<>();
    TextView tvToday;
    String hariIni;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_first, container, false);

        tvToday = view.findViewById(R.id.tvDate);
        rvMainMenu = view.findViewById(R.id.rvMainMenu);
        GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2, RecyclerView.VERTICAL, false);
        rvMainMenu.setLayoutManager(mLayoutManager);
        gridMargin = new LayoutMarginDecoration(2, Tools.dp2px(getActivity(), 4));
        rvMainMenu.addItemDecoration(gridMargin);
        rvMainMenu.setHasFixedSize(true);

        mainAdapter = new MainAdapter(lsMainMenu, new MainAdapter.onSelectData() {
            @Override
            public void onSelected(ModelMain mdlMain) {
                switch (mdlMain.getTxtName()) {
                    case "Hotel":
                        startActivityForResult(new Intent(getActivity(), HotelActivity.class), 1);
                        break;
                    case "Kuliner":
                        startActivityForResult(new Intent(getActivity(), KulinerActivity.class), 1);
                        break;
                    case "Wisata":
                        startActivityForResult(new Intent(getActivity(), WisataActivity.class), 1);
                        break;
                }
            }
        });
        //get Time Now
        Date dateNow = Calendar.getInstance().getTime();
        hariIni = (String) DateFormat.format("EEEE", dateNow);
        getToday();
        setMenu();
        return view;
    }

    private void getToday() {
        Date date = Calendar.getInstance().getTime();
        String tanggal = (String) DateFormat.format("d MMMM yyyy", date);
        String formatFix = hariIni + ", " + tanggal;
        tvToday.setText(formatFix);
    }

    private void setMenu() {
        mdlMainMenu = new ModelMain("Hotel", R.drawable.ic_hotel);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("Kuliner", R.drawable.ic_cafe);
        lsMainMenu.add(mdlMainMenu);
        mdlMainMenu = new ModelMain("Wisata", R.drawable.ic_destination);
        lsMainMenu.add(mdlMainMenu);



        rvMainMenu.setAdapter(mainAdapter);

    }

}

