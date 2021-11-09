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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchClient#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchClient extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchClient() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchClient.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchClient newInstance(String param1, String param2) {
        SearchClient fragment = new SearchClient();
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
    Spinner searchbySpinner;
    String searchType,searchField;
    EditText searchFieldEditText;
    Button SearchBtn;
    ListView myListView;
    TableLayout clientstable;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_search_client, container, false);
        searchbySpinner=view.findViewById(R.id.searchbySpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getActivity(),R.array.modes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchbySpinner.setAdapter(adapter);
        searchbySpinner.setOnItemSelectedListener(this);
        searchFieldEditText=(EditText) view.findViewById(R.id.searchFieldEditText);
        SearchBtn=view.findViewById(R.id.Searchbtn);
        clientstable=view.findViewById(R.id.ViewAllClientTable);
        myListView =(ListView) view.findViewById(R.id.mydataListView);

        //Log.i("lets see if searchField is here","hello"+searchField);
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.i("lets see if spinner selected is here",searchType);
                searchField=searchFieldEditText.getText().toString();


                if(searchField!=null) {


                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2:8081/api/v1/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    AreebaApi areebaApi = retrofit.create(AreebaApi.class);
                    Call<List<Post>> call = areebaApi.getPostsByField(searchType, searchField);
                    call.enqueue(new Callback<List<Post>>() {
                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                            if (!response.isSuccessful()) {
                                Log.i("inresponse", "Responseunsuccessful");
                            }
                            if (response.code() == 200) {
                                List<Post> posts = response.body();
                                Log.i("inresponse", "post" + posts.get(0).getName());

                                ItemAdapter itemAdapter = new ItemAdapter(getActivity(), posts);
                                myListView.setAdapter(itemAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Post>> call, Throwable t) {
                            if (t instanceof IOException) {

                                Log.i("inresponse", t.getMessage());
                            } else {
                                Log.i("inresponse", "Failure conversion issue");
                            }
                        }
                    });
                }









                clientstable.setVisibility(View.VISIBLE);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        searchType= adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}