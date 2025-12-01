package com.HS.Topcity.CustomClasses.MenuBar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.HS.Topcity.Activity.Contact;
import com.HS.Topcity.Activity.Development;
import com.HS.Topcity.Activity.Download;
import com.HS.Topcity.Activity.FandQ;
import com.HS.Topcity.Activity.PaymentDetail;
import com.HS.Topcity.Activity.Procedures;
import com.HS.Topcity.Activity.SharedAccountUserList;
import com.HS.Topcity.R;

public class SimpleItem extends DrawerItem<SimpleItem.ViewHolder> {

    private int selectedItemIconTint;
    private int selectedItemTextTint;

    private int normalItemIconTint;
    private int normalItemTextTint;

    private int icon;
    private String title;

    public SimpleItem(int icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    @Override
    public ViewHolder createViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate( R.layout.nenu_item_list_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void bindViewHolder(ViewHolder holder) {
        holder.title.setText(title);
        holder.icon.setImageResource(icon);

        holder.title.setTextColor(isChecked ? selectedItemTextTint : normalItemTextTint);
        holder.icon.setColorFilter(isChecked ? selectedItemIconTint : normalItemIconTint);

       holder.boxs.setOnClickListener( new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(holder.title.getText().toString().equals( "Development Activities" ))
               {
                   Intent a = new Intent(v.getContext(), Development.class );
                   v.getContext().startActivity( a );
               }
            else    if(holder.title.getText().toString().equals( "Payment Detail" ))
               {
                   Intent a = new Intent(v.getContext(), PaymentDetail.class );
                   v.getContext().startActivity( a );
               }
               else    if(holder.title.getText().toString().equals( "Procedure" ))
               {
                   Intent a = new Intent(v.getContext(), Procedures.class );
                   v.getContext().startActivity( a );
               }
               else    if(holder.title.getText().toString().equals( "Shared Account" ))
               {
                   Intent a = new Intent(v.getContext(), SharedAccountUserList.class );
                   v.getContext().startActivity( a );
               }
               else    if(holder.title.getText().toString().equals( "Downloads" ))
               {
                   Intent a = new Intent(v.getContext(), Download.class );
                   v.getContext().startActivity( a );
               }
               else    if(holder.title.getText().toString().equals( "FAQS" ))
               {
                   Intent a = new Intent(v.getContext(), FandQ.class );
                   v.getContext().startActivity( a );
               }
               else    if(holder.title.getText().toString().equals( "Contact" ))
               {
                   Intent a = new Intent(v.getContext(), Contact.class );
                   v.getContext().startActivity( a );
               }

           }
       } );
    }

    public SimpleItem withSelectedIconTint(int selectedItemIconTint) {
        this.selectedItemIconTint = selectedItemIconTint;
        return this;
    }

    public SimpleItem withSelectedTextTint(int selectedItemTextTint) {
        this.selectedItemTextTint = selectedItemTextTint;
        return this;
    }

    public SimpleItem withIconTint(int normalItemIconTint) {
        this.normalItemIconTint = normalItemIconTint;
        return this;
    }

    public SimpleItem withTextTint(int normalItemTextTint) {
        this.normalItemTextTint = normalItemTextTint;
        return this;
    }

    static class ViewHolder extends DrawerAdapter.ViewHolder {

        private ImageView icon;
        private TextView title;
        LinearLayout boxs;

        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            title = (TextView) itemView.findViewById(R.id.title);
            boxs = (LinearLayout) itemView.findViewById(R.id.menu_item_box);
        }
    }
}
