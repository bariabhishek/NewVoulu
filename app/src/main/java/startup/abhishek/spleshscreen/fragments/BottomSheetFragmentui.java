package startup.abhishek.spleshscreen.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import startup.abhishek.spleshscreen.R;

public class BottomSheetFragmentui extends BottomSheetDialogFragment {
    ImageView call,message ;
    String Otp,name,number;
    TextView OTP,tvName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.bottom_sheet_layout,container,false );

        call = view.findViewById( R.id.civcall );
        tvName = view.findViewById( R.id.jobSeekerBottomsheet );
        OTP = view.findViewById( R.id.OtpBottom);
        message = view.findViewById( R.id.civmessage );
        Otp=getArguments().getString("otp");
        name=getArguments().getString("seekerName");
        number=getArguments().getString("seekerMobile");

        tvName.setText("Contect to "+name);
        OTP.setText(Otp);

        return view;
    }




}

