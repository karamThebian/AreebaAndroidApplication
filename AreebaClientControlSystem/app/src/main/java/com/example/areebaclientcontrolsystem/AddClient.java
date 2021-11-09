package com.example.areebaclientcontrolsystem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddClient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddClient extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddClient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClient.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClient newInstance(String param1, String param2) {
        AddClient fragment = new AddClient();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    EditText fullNameEditText,addressEditText,phoneNumberEditText;
    Button addClientBtn;
    String fullName,address,phoneNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_client,container,false);
        fullNameEditText=view.findViewById(R.id.fullNameEditText);
        addressEditText=view.findViewById(R.id.addressEditText);
        phoneNumberEditText=view.findViewById(R.id.phoneNumberEditText);
addClientBtn=view.findViewById(R.id.addClientBtn);
addClientBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        fullName=fullNameEditText.getText().toString();
        address=addressEditText.getText().toString();
        phoneNumber=phoneNumberEditText.getText().toString();
        Post post=new Post(fullName,address,phoneNumber);

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AreebaApi areebaApi= retrofit.create(AreebaApi.class);
        Call<Void> call = areebaApi.addClient(post);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()){
                    Log.i("inresponse","Responseunsuccessful");
                }
                if (response.code()==200){
                    fullNameEditText.getText().clear();
                    addressEditText.getText().clear();
                    phoneNumberEditText.getText().clear();
                    Toast.makeText(getActivity(),"Client successfully added",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });




    }
});







        return view;
    }
}