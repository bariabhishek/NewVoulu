package com.wikav.voulu.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import com.wikav.voulu.Adeptor.CommentedPostAdaptor;
import com.wikav.voulu.Adeptor.ModelList;
import com.wikav.voulu.R;
import com.wikav.voulu.SessionManger;

public class FavoriteFragment extends Fragment {

    RecyclerView recyclerView;
    List<ModelList> arrayList;
    SessionManger sessionManger;
    TextView noDataFavPost;
    String Url="https://voulu.in/api/getFavoriteJobPost.php";

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate( R.layout.fragment_followers, container, false );
        recyclerView = view.findViewById( R.id.recycleviewfollower );
        noDataFavPost = view.findViewById( R.id.noDataFavPost );
        sessionManger=new SessionManger(getActivity());
        HashMap<String,String> getUser=sessionManger.getUserDetail();
       String  userMobile=getUser.get(sessionManger.MOBILE);
        arraydata(userMobile);


        arrayList =new ArrayList<>(  );

        return view;
    }


    private void arraydata(final String Mobile) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //    progressDialog.dismiss();
                        Log.i("RESPO", response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("allPost");
                            if (success.equals("1")){
                                Log.d("Response",response);
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
                                    String share = object.getString("status").trim();
                                    arrayList.add( new ModelList(img,title,des,rate,id,time,mobile,like,profile,username,share,img2,img3) );
                                }
                                setupRecycle(arrayList);

                            }
                            else

                            {
                                recyclerView.setVisibility(View.GONE);
                                noDataFavPost.setVisibility(View.VISIBLE);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            //  Toast.makeText(getActivity(), "Something went wrong..."+e, Toast.LENGTH_LONG).show();


                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ///    Toast.makeText(getActivity(), "Something went wrong..."+error, Toast.LENGTH_LONG).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                params.put("key", "9195A3CDB388F894B3EE3BD665DFD");
                params.put("mobile",Mobile);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        requestQueue.getCache().clear();






    }
    public  void setupRecycle(List list)
    {

        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
        CommentedPostAdaptor follower = new CommentedPostAdaptor(getContext(),list);
        recyclerView.setAdapter( follower );
    }
}
