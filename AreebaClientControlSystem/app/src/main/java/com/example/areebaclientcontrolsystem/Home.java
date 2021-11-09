package com.example.areebaclientcontrolsystem;

import android.graphics.Color;
import android.nfc.Tag;
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
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
    TextView description;
    Button viewAllbtn;
    TableLayout clientstable;
    ListView myListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        description=view.findViewById(R.id.Description);
        viewAllbtn=view.findViewById(R.id.ViewAllbtn);
        clientstable=view.findViewById(R.id.ViewAllClientTable);
        Log.i("inOncreate","were here");
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8081/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AreebaApi areebaApi= retrofit.create(AreebaApi.class);
        Call<List<Post>> call = areebaApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()){
                    Log.i("inresponse","Responseunsuccessful");
                }
                Log.i("inresponse","Response returned");
                List<Post> posts=response.body();

                myListView =(ListView) view.findViewById(R.id.mydataListView);
                ItemAdapter itemAdapter= new ItemAdapter(getActivity(),posts);
                myListView.setAdapter(itemAdapter);
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                if (t instanceof IOException){

                    Log.i("inresponse",t.getMessage());
                }

            else{
                Log.i("inresponse","Failure conversion issue");
            }
            }
        });
        viewAllbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description.setText("List of All Clients Viewed");

                viewAllbtn.setVisibility(View.GONE);
                clientstable.setVisibility(View.VISIBLE);

            }
        });
        return view;
    }

}