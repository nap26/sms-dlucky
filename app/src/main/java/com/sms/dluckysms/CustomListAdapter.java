package com.sms.dluckysms;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomListAdapter extends SimpleAdapter {
    private final FragmentTransaction fragmentTransaction;
    LayoutInflater inflater;
    Context context;
    ArrayList<HashMap<String, String>> arrayList;

    public CustomListAdapter(Context context,FragmentTransaction fragmentTransaction, ArrayList<HashMap<String, String>> data, int resource,String[] from, int[] to) {
        super(context, data, resource, from, to);
        this.context = context;
        this.arrayList = data;
        this.fragmentTransaction = fragmentTransaction;
        inflater.from(context);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        final String promoCode  = arrayList.get(position).get("keyword");
        final String loadPackage  = arrayList.get(position).get("LoadPackage");
        Log.d("prmos",promoCode);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment someFragment = new SendPromoFragment();
                Bundle args = new Bundle();
                args.putString("code",promoCode);
                args.putString("load",loadPackage);
                FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
              //  transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });


        return view;
    }




}
