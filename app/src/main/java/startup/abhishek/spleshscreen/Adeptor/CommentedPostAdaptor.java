package startup.abhishek.spleshscreen.Adeptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import startup.abhishek.spleshscreen.JobDiscription;
import startup.abhishek.spleshscreen.R;
import startup.abhishek.spleshscreen.SessionManger;

public class CommentedPostAdaptor extends RecyclerView.Adapter<CommentedPostAdaptor.ViewHolder> {

    Context context;
    List <ModelList> list;
    SessionManger sessionManger;
    String job_giver_mobile;
   // String Url="https://voulu.in/api/addToFavorite.php";
    public CommentedPostAdaptor(Context context, List <ModelList> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from( context );
        View view = layoutInflater.inflate( R.layout.commented_activity_data,parent,false );

        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        if(list.get(i).getImage().equals("NO"))
        {
            viewHolder.mainImage.setVisibility(View.GONE);
        }
        else
        {
            Glide.with(context).load(list.get(i).getImage()).into(viewHolder.mainImage);
        }

        Glide.with(context).load(list.get(i).getProfilePic()).into(viewHolder.profile);
        viewHolder.title.setText( list.get( i ).getTitle());
        viewHolder.dis.setText( list.get( i ).getDis() );
        viewHolder.pese.setText("₹ "+list.get( i ).getPese() );
        viewHolder.username.setText( list.get( i ).getUsername() );
        viewHolder.time.setText( list.get( i ).getTime() );

        viewHolder.postCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(context, JobDiscription. class);
                view.putExtra("id",list.get( i ).getId());
                view.putExtra("title",list.get( i ).getTitle());
                view.putExtra("des",list.get( i ).getDis());
                view.putExtra("username",list.get( i ).getUsername());
                view.putExtra("profile",list.get( i ).getProfilePic());
                view.putExtra("paise",list.get( i ).getPese());
                view.putExtra("img",list.get( i ).getImage());
                view.putExtra("img2",list.get( i ).getImg2());
                view.putExtra("img3",list.get( i ).getImg3());
                context.startActivity(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mainImage, option, share, like;
        TextView title, pese, dis, username, time;
        CardView postCard;
        CircleImageView profile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mainImage = itemView.findViewById(R.id.photoCard);
            profile = itemView.findViewById(R.id.userProfileCard);
            title = itemView.findViewById(R.id.titleCard);
            dis = itemView.findViewById(R.id.disCard);
            pese = itemView.findViewById(R.id.peseCard);
            postCard = itemView.findViewById(R.id.postCardMain);
            option = itemView.findViewById(R.id.option);
            share = itemView.findViewById(R.id.shareButton);
            like = itemView.findViewById(R.id.likeBtn);
            username = itemView.findViewById(R.id.userNameCard);
            time = itemView.findViewById(R.id.timeCard);
        }
    }
}
