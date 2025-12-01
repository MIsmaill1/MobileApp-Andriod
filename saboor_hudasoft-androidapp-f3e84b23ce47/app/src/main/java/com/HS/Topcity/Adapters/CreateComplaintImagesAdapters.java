package com.HS.Topcity.Adapters;

import static com.HS.Topcity.Activity.ComplaintCreated.box;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.HS.Topcity.R;

import java.util.ArrayList;

public class CreateComplaintImagesAdapters extends RecyclerView.Adapter<CreateComplaintImagesAdapters.viewHolder> {
    Context context;
    boolean isImageFitToScreen;
    ArrayList<Uri> Images;
    ArrayList<String> image;


    public CreateComplaintImagesAdapters(Context context, ArrayList<Uri> Images, int newsannoce) {
        this.context = context;
        this.Images = Images;

    }

    @NonNull
    @Override
    public CreateComplaintImagesAdapters.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( context ).inflate( R.layout.created_complaint_layout, parent, false );
        return new CreateComplaintImagesAdapters.viewHolder( v );
    }

    @Override
    public void onBindViewHolder(@NonNull CreateComplaintImagesAdapters.viewHolder holder, int position) {


                holder.image.setImageURI( Images.get( position ) );




        holder.close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getRootView().getContext());
                builder1.setMessage("Are you sure you want delete this image?.");
                builder1.setTitle( "Alert" );
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Images.remove( holder.getPosition() );
                                notifyDataSetChanged();
                                if (Images.size() == 0){
                                    box.setVisibility( View.GONE );
                                }
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                builder1.show();

            }
        } );


    }

    @Override
    public int getItemCount() {
        return Images.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView image,close;

        public viewHolder(@NonNull View itemView) {
            super( itemView );
            image = itemView.findViewById( R.id.comp_Image );
            close = itemView.findViewById( R.id.close );

        }

    }

}

