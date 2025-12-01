package com.HS.Topcity.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.Models.FAQsModel;
import com.HS.Topcity.R;

import java.util.ArrayList;

public class FaqsListAdapter extends RecyclerView.Adapter<FaqsListAdapter.ViewHolder>{
    private Context context;
    private ArrayList<FAQsModel> faQsModels;

    int index;
    boolean check = false;
    private static int lastClickedPosition = -1;
    private int selectedItem = -1 ;


    public FaqsListAdapter(Context context, ArrayList<FAQsModel> faQsModels) {
        this.context = context;
        this.faQsModels = faQsModels;

    }

    @NonNull
    @Override
    public FaqsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.fandq_layout, parent, false);
        return new FaqsListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqsListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

      FAQsModel faQsModel = faQsModels.get( position );

        holder.question.setText( faQsModel.getQuestion() );
        holder.ans.setText( faQsModel.getAns() );



        if (selectedItem  != -1 && selectedItem == holder.getAdapterPosition() && faQsModel.getCheck() == false) {
            faQsModel.setCheck( true );
           holder.ans.setVisibility( View.VISIBLE );
           holder.sub.setVisibility( View.VISIBLE );
           holder.add.setVisibility( View.GONE);
        }
        else   if (selectedItem == position && faQsModel.getCheck() == true) {
            faQsModel.setCheck( false );
            holder.ans.setVisibility( View.GONE );
            holder.sub.setVisibility( View.GONE );
            holder.add.setVisibility( View.VISIBLE);
        }
        else {
           holder.ans.setVisibility( View.GONE );
            holder.sub.setVisibility( View.GONE );
            holder.add.setVisibility( View.VISIBLE);
        }

        holder.box.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousItem = selectedItem;
                selectedItem = holder.getAdapterPosition();

                notifyItemChanged(previousItem);
                notifyItemChanged(position);

            }
        } );





    }

    @Override
    public int getItemCount() {
        return faQsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView question, ans;
        LinearLayout box;
        ImageView add,sub;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            box = itemView.findViewById( R.id.ShowAns1 );
            ans = itemView.findViewById( R.id.quick_asset_Ans1 );
            question = itemView.findViewById( R.id.quick_asset_Ques1 );
            add = itemView.findViewById( R.id.plus );
            sub = itemView.findViewById( R.id.sub );

        }
    }
}

