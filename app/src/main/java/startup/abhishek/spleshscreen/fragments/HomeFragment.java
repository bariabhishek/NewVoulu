package startup.abhishek.spleshscreen.fragments;


import android.content.Intent;
import android.os.Bundle;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import startup.abhishek.spleshscreen.AboutUs;
import startup.abhishek.spleshscreen.Adeptor.Adeptor;
import startup.abhishek.spleshscreen.Adeptor.CoustomSwipeAdeptor;
import startup.abhishek.spleshscreen.Adeptor.CoustomSwipeAdeptorForHome;
import startup.abhishek.spleshscreen.Adeptor.ModelList;
import startup.abhishek.spleshscreen.R;
import startup.abhishek.spleshscreen.UploadYourPost;
import startup.abhishek.spleshscreen.VolleyRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager viewPager;
    CoustomSwipeAdeptorForHome coustomSwipeAdeptorForHome;
    int [] imageArry ={R.drawable.logo,R.drawable.boy};

    RecyclerView recyclerView ;
    List<ModelList> list;
    ImageButton imageButton;
    String Url="https://voulu.in/api/getJobPost.php",response;
    View view;
    TextView noData;
    VolleyRequest volleyRequest;
    Map<String, String> params;
    private TextView mTextMessage;
    private RequestQueue requestQueue;
    private ShimmerFrameLayout mShimmerViewContainer;


    public HomeFragment() {
        // Required empty public constructor
    }
    private void arraydata() {


        mShimmerViewContainer.startShimmerAnimation();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("allPost");
                            if (success.equals("1")){
                                Log.d("Response", response);
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String title = object.getString("title").trim();
                                    String mobile = object.getString("mobile").trim();
                                    String des = object.getString("des").trim();
                                    String rate = object.getString("rate").trim();
                                    String img = object.getString("img").trim();
                                    String img2 = object.getString("img2").trim();
                                    String img3 = object.getString("img3").trim();
                                    String id = object.getString("id").trim();
                                    String time = object.getString("time").trim();
                                    String profile = object.getString("profile").trim();
                                    String username = object.getString("username").trim();
                                    String like = object.getString("like").trim();
                                    String share = object.getString("share").trim();
                                    list.add( new ModelList(img,title,des,rate,id,time,mobile,like,profile,username,share,img2,img3) );

                                }
                                setupRecycle(list);

                            }
                            else
                            {
                                noData.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Something went wrong..."+error, Toast.LENGTH_LONG).show();
                        // noData.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("key", "9195A3CDB388F894B3EE3BD665DFD");
                return params;
            }
        };
        stringRequest.setShouldCache(true);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue=Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        requestQueue.getCache().clear();







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate( R.layout.fragment_home, container, false );
            imageButton=view.findViewById(R.id.uplodButton);
            noData=view.findViewById(R.id.noData);

     //   imageArry=new ArrayList<>();
        viewPager = view.findViewById( R.id.viewPagerHome );

        coustomSwipeAdeptorForHome = new CoustomSwipeAdeptorForHome( getActivity(),imageArry);
        viewPager.setAdapter( coustomSwipeAdeptorForHome );


        mShimmerViewContainer =view.findViewById(R.id.shimmer_view_container);
        list = new ArrayList <>(  );

        params = new HashMap<>();
        params.put("key", "9195A3CDB388F894B3EE3BD665DFD");
        volleyRequest=new VolleyRequest(getContext(),params,Url);
        volleyRequest.stringRequests(true);
        response=volleyRequest.getMainResponse();
        arraydata();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById( R.id.fab );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vi = new Intent(getActivity(), UploadYourPost. class);
                startActivity(vi);
            }
        } );



        return view;
    }

    public void setupRecycle(List <ModelList> list)
    {
        Adeptor a= new Adeptor( getContext(),list );
        recyclerView = view.findViewById( R.id.recycleview );
        recyclerView.setHasFixedSize( true );
        recyclerView.setItemAnimator( new DefaultItemAnimator() );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) );
        recyclerView.setAdapter( a );
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }
}
