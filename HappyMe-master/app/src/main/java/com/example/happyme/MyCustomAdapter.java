package com.example.happyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyCustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;


    public MyCustomAdapter( Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_item,countryModelList);
        this.context=context;
        this.countryModelList=countryModelList;
        this.countryModelListFiltered= countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);
        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        tvCountryName.setText(countryModelListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imageView);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return super.getFilter();
    }

}
