package com.sms.dluckysms;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment  implements SmsTestListener{

    private String number = "3966";
    private String[] commands;
    private int commandIndex;
    private TextView tvWallet;

    private void startSmsTest()
    {
        // Initialize commands here
        // and fire the first one
        commands = new String[] { "command1", "command2" };
        commandIndex = 0;
        fireNextCommand();
    }

    private void fireNextCommand()
    {
        // Tell the Activity to send the next message,
        // and pass it a reference to the Fragment that
        // is running the test
        ((MainActivity) getActivity()).sendSmsTest(number, commands[commandIndex], this,1);
    }


    @SuppressLint("WrongConstant")
    @Override
    public void onSmsTestResponse(String message)
    {
        Log.d("pass","das");
        tvWallet.setText(message);
        //Toast.makeText(getActivity(), "Test finished" + message).show();
//        Toast.makeText(getActivity(),"tedt"+message, 5);
        // Increment our command counter and if
        // we're not finished, fire the next one
//        commandIndex++;
//        if (commandIndex < commands.length)
//        {
//            fireNextCommand();
//        }
//        else
//        {
//            //Toast.makeText(getActivity(), "Test finished" + message).show();
//            Toast.makeText(getActivity(),"tedt"+message, 2);
//
//        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = null;
        if(view == null){
             view = inflater.inflate(R.layout.activity_item_list, container, false);
        }

        // Inflate the layout for this fragment
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        tvWallet = view.findViewById(R.id.wallet);

        startSmsTest();

//        SmsManager mySmsManager = SmsManager.getDefault();
//        mySmsManager.sendTextMessage("7670",null, tvWallet.getText().toString(), null, null);



        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.imageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);



                Fragment someFragment = new PromoFragment();
                Fragment first = new FirstFragment();

                Bundle args = new Bundle();
                args.putString("type","1");
//                args.putString("code",promoCode);
//                args.putString("load",loadPackage);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

               // FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
               // transaction.addToBackStack(first.getClass().getName());  // if written, this transaction will be added to backstack
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        view.findViewById(R.id.imageButtonTalkNText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);



                Fragment someFragment = new PromoFragment();
                Fragment first = new FirstFragment();
                Bundle args = new Bundle();
                args.putString("type","3");
//                args.putString("load",loadPackage);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
                //transaction.addToBackStack(first.getClass().getName());  // if written, this transaction will be added to backstack
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });



        view.findViewById(R.id.imageButtonSmartBro).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);



                Fragment someFragment = new PromoFragment();
                Fragment first = new FirstFragment();
                Bundle args = new Bundle();
                args.putString("type","2");
//                args.putString("load",loadPackage);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
              //  transaction.addToBackStack(first.getClass().getName());  // if written, this transaction will be added to backstack
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        view.findViewById(R.id.imageButtonSun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);



                Fragment someFragment = new PromoFragment();
                Fragment first = new FirstFragment();
                Bundle args = new Bundle();
                args.putString("type","4");
//                args.putString("load",loadPackage);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
              //  transaction.addToBackStack(first.getClass().getName());  // if written, this transaction will be added to backstack
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        view.findViewById(R.id.imageButtonMeralco).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);



                Fragment someFragment = new PromoFragment();
                Fragment first = new FirstFragment();
                Bundle args = new Bundle();
                args.putString("type","5");
//                args.putString("load",loadPackage);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
               // transaction.addToBackStack(first.getClass().getName());  // if written, this transaction will be added to backstack
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        view.findViewById(R.id.imageButtonCignal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);



                Fragment someFragment = new PromoFragment();
                Fragment first = new FirstFragment();
                Bundle args = new Bundle();
                args.putString("type","6");
//                args.putString("load",loadPackage);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
               // transaction.addToBackStack(first.getClass().getName());  // if written, this transaction will be added to backstack
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        view.findViewById(R.id.imageButtonPldt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);



                Fragment someFragment = new PromoFragment();
                Fragment first = new FirstFragment();
                Bundle args = new Bundle();
                args.putString("type","7");
//                args.putString("load",loadPackage);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();

                // FragmentTransaction transaction = fragmentTransaction;
                someFragment.setArguments(args);
                transaction.replace(R.id.nav_host_fragment, someFragment ); // give your fragment container id in first parameter
               transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();
            }
        });
    }
}