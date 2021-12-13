package com.sms.dluckysms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import id.ionbit.ionalert.IonAlert;

public class SendPromoFragment extends Fragment implements SmsTestListener {

    private ListView simpleListView;
    private String load;
    private String wtlCode;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "balKey";
    public static final String Amount = "amountKey";

    SharedPreferences sharedpreferences;
    private String bal;
    private TextView tvWallet;
    private Button btnSend;
    private EditText etMobileNumber;


    private void fireNextCommand(String message)
    {
        // Tell the Activity to send the next message,
        // and pass it a reference to the Fragment that
        // is running the test
 //((MainActivity) getActivity()).sendSmsTest("7670", message, this,2);
//        getActivity().onBackPressed();

        ((MainActivity) getActivity()).sendSmsTest("3966", message, this,2);





        new IonAlert(getActivity(), IonAlert.SUCCESS_TYPE)
                .setTitleText("Your Request is Being Process...")
                .setConfirmText("Click Here to Continue!")
                .setConfirmClickListener(new IonAlert.ClickListener() {
                    @Override
                    public void onClick(IonAlert sDialog) {

                        sDialog.dismissWithAnimation();

                        Fragment someFragment = new FirstFragment();
                        Bundle args = new Bundle();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();

                        //  FragmentTransaction transaction = transaction1;
                        // someFragment.setArguments(args);
                        transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                        // transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                        transaction.commit();


                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                    }
                })
                .show();

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_send_promo, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            wtlCode = bundle.getString("code");
             load = bundle.getString("load");
            Log.d("code", wtlCode);
        }


        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        bal = sharedpreferences.getString(Amount, null);

        tvWallet = view.findViewById(R.id.wallet);
        tvWallet.setText(bal);
        TextView tvLoad = view.findViewById(R.id.label_promo);
        tvLoad.setText("Promo Code : " + load);
        btnSend = view.findViewById(R.id.btnSubmit);
        etMobileNumber = view.findViewById(R.id.input_mobile_number_edit);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String dluck3Text = "D3CLOAD "+ wtlCode + " " + etMobileNumber.getText().toString();

                Log.d("tet", dluck3Text);


                String strUserName = etMobileNumber.getText().toString();

                if(TextUtils.isEmpty(strUserName)) {
                    new IonAlert(getActivity(), IonAlert.ERROR_TYPE)
                            .setTitleText("Please Inputted Wrong or Empty Number")
                            .setConfirmText("Click Here to Try Again.")
                            .setConfirmClickListener(new IonAlert.ClickListener() {
                                @Override
                                public void onClick(IonAlert sDialog) {

                                    sDialog.dismissWithAnimation();


                                }
                            })
                            .show();

                }else{
                    fireNextCommand(dluck3Text);

                }

               // SmsManager.getDefault().sendTextMessage("7670", null, dluck3Text, null, null);

            }
        });


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
        try {
            InputStream is = getActivity().getAssets().open("data.json");
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

    @Override
    public void onSmsTestResponse(String message) {

    }
}