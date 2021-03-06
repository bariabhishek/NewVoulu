package com.wikav.voulu;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.wikav.voulu.Adeptor.CoustomSwipeAdeptor;
import com.wikav.voulu.fragments.FullScreenDialog;
import com.wikav.voulu.fragments.FullScreenDialogForNoInternet;

public class JobDiscription extends AppCompatActivity  {
    BroadcastReceiver broadcastReceiver;

    ViewPager viewPager;
    CoustomSwipeAdeptor coustomSwipeAdeptor;
    List<String> imageArry;
    Button showComment;
    CircleImageView profile;
    ImageView mainImage;
    TextView username,jobtTitle,jobdes,paise;
    String id,title,jobdis,usernames,image,profileImage,pese;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.new_job_dec );
            imageArry=new ArrayList<>();
        viewPager = findViewById( R.id.viewpagerjob );



        checkIntenet();


        id=getIntent().getExtras().getString("id");
           jobdis=getIntent().getExtras().getString("des");
           usernames=getIntent().getExtras().getString("username");
           profileImage=getIntent().getExtras().getString("profile");
           image=getIntent().getExtras().getString("img");
          String img2=getIntent().getExtras().getString("img2"),
         img3=getIntent().getExtras().getString("img3");
           pese=getIntent().getExtras().getString("paise");
         title=getIntent().getExtras().getString("title");
         if(!image.equals("NO"))
         {
             imageArry.add(image);
             if(!img2.equals("NO"))
             {
                 imageArry.add(img2);
                 if (!img3.equals("NO")) {
                     imageArry.add(img3);
                 }
             }
         }



        coustomSwipeAdeptor = new CoustomSwipeAdeptor( this,imageArry);
        viewPager.setAdapter( coustomSwipeAdeptor );

       profile = findViewById( R.id.postProfile );
       mainImage = findViewById( R.id.postMainImage );
       username = findViewById( R.id.postUsername );
       jobtTitle = findViewById( R.id.postJobTitle );
       showComment = findViewById( R.id.showComment );
       jobdes = findViewById( R.id.postJobdes );
       paise = findViewById( R.id.paisePost );
       showComment = findViewById( R.id.showComment );

       Glide.with(this).load(profileImage).into(profile);
      /*if (!image.equals("NO")) {
          Glide.with(this).load(image).into(mainImage);
      }*/
      username.setText(usernames);
      jobtTitle.setText(title);
      jobdes.setText(jobdis);
      paise.setText("₹ "+pese);

       getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        showComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });

    }

    public void click() {

        FullScreenDialog dialog =new FullScreenDialog();
        Bundle b=new Bundle();
        b.putString("id",id);
        b.putString("title",title);
        dialog.setArguments(b);
        dialog.show(getSupportFragmentManager(),"TAG");

    }

    public void checkIntenet()
    {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int [] type={ ConnectivityManager.TYPE_MOBILE, ConnectivityManager.TYPE_WIFI};
                if(ConnectivityReceiver.isNetworkAvailable(context,type))
                {
                    return;
                }
                else {
                    FullScreenDialogForNoInternet full=new FullScreenDialogForNoInternet();
                    full.show(getSupportFragmentManager(),"show");
                }
            }
        };
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (broadcastReceiver!= null)
            unregisterReceiver(broadcastReceiver);

    }


}
