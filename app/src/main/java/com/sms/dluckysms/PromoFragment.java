package com.sms.dluckysms;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class PromoFragment extends Fragment {

    private ListView simpleListView;
    private String type;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.promo_fragment, container, false);
        simpleListView = (ListView) view.findViewById(R.id.simpleListView);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            type = bundle.getString("type");

          //  Log.d("code", wtlCode);
        }
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("PromoCodes");
            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("LoadPackage"));
                String formula_value = jo_inside.getString("LoadPackage");
                String url_value = jo_inside.getString("Denom");
                String url_value1 = jo_inside.getString("Description");

                String promoKeyword = jo_inside.getString("WTLKeyword");
                if(url_value1.equalsIgnoreCase("null")){
                    url_value1 = "";
                }
                //Add your values in your `ArrayList` as below:
                m_li = new HashMap<String, String>();
                m_li.put("LoadPackage", formula_value);
                m_li.put("Denom", "P " + url_value+ ".00");
                m_li.put("des",  url_value1);
                m_li.put("des",  url_value1);
                m_li.put("keyword",  promoKeyword);
                formList.add(m_li);


                String[] from = {"LoadPackage", "Denom","des"};//string array
                int[] to = {R.id.id_text, R.id.amount,R.id.description};//int array of views id's

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                CustomListAdapter simpleAdapter = new CustomListAdapter(getActivity(),transaction , formList, R.layout.item_list_content, from, to);//Create object and set the parameters for simpleAdapter
                simpleListView.setAdapter(simpleAdapter);//sets the adapter for listView
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return view;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(PromoFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });
    }


    public String loadJSONFromAsset() {
        String json = null;
        InputStream is = null;
        try {
            if(type.equalsIgnoreCase("1")){
                 is = getActivity().getAssets().open("data.json");
            }else if(type.equalsIgnoreCase("2")){
                is = getActivity().getAssets().open("smartbro.json");
            }
            else if(type.equalsIgnoreCase("3")){
                is = getActivity().getAssets().open("tnt.json");
            }
            else if(type.equalsIgnoreCase("4")){
                is = getActivity().getAssets().open("sun.json");
            }
            else if(type.equalsIgnoreCase("5")){
                is = getActivity().getAssets().open("merlacomain.json");
            }
            else if(type.equalsIgnoreCase("6")){
                is = getActivity().getAssets().open("cignal.json");
            }
            else if(type.equalsIgnoreCase("7")){
                is = getActivity().getAssets().open("pldt.json");
            }

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}