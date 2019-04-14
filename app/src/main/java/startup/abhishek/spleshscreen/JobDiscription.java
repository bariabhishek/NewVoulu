package startup.abhishek.spleshscreen;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import startup.abhishek.spleshscreen.Adeptor.CoustomSwipeAdeptor;
import startup.abhishek.spleshscreen.fragments.BottomSheetFragmentui;

public class JobDiscription extends AppCompatActivity implements BottomSheetFragmentui.BottomSheetListener {

    Button accept,reject;
    int[] image = {R.drawable.sch,R.drawable.sch};
    ViewPager viewPager ;
    CoustomSwipeAdeptor coustomSwipeAdeptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.new_job_dec );
        String id=getIntent().getExtras().getString("id");
        Toast.makeText(this, ""+id, Toast.LENGTH_LONG).show();
        /*viewPager = findViewById( R.id.viewPager );
        coustomSwipeAdeptor = new CoustomSwipeAdeptor( this,image );
        viewPager.setAdapter( coustomSwipeAdeptor );*/

//        accept = findViewById( R.id.btnConnect );
//
//        click();
    }

    private void click() {
        accept.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragmentui fragment = new BottomSheetFragmentui();
                fragment.show( getSupportFragmentManager(),"bottomsheet" );
            }
        } );
    }

    @Override
    public void onButtonClicked(String text) {

    }
}
