package com.HS.Topcity.Adapters;

//public class DrawAdapter extends RecyclerView.Adapter<DrawAdapter.ViewHolder> {
//
//    private List<DrawItem> items;
//    private Map<Class<? extends DrawItem>,Integer > viewTypes;
//    private SparseArray<DrawItem> holderFacteries;
//
//    private OnItemClickListener listener;
//
//    public DrawAdapter(List<DrawItem> items){
//        this.items = items;
//        this.viewTypes = new HashMap<>();
//        this.holderFacteries = new SparseArray<>();
//        processviewType();
//    }
//    private void processviewType(){
//
//        int type = 0 ;
//        for (DrawItem item : items){
//            if(!viewTypes.containsKey( item.getClass() )){
//                viewTypes.put( item.getClass(),type );
//                type++;
//            }
//        }
//    }
//    public void setSelected(int position){
//        DrawItem newcheck = items.get( position );
//        if (!newcheck.isSelectTable()){
//            return;
//        }
//        for (int i =0 ; i<items.size();i++){
//            DrawItem item = items.get(i);
//           if (item.isChecked()){
//               item.setChecked( false );
//               notifyItemChanged(i);
//               break;
//           }
//
//        }
//        newcheck.setChecked( true );
//        notifyItemChanged( position );
//
//        if(listener != null){
//            listener.onItemSelected(position  );
//        }
//
//    }
//
//    public void setListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }
//
//    public interface OnItemClickListener{
//      void onItemSelected(int position);
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ViewHolder holder = holderFacteries.get( viewType ).createdViewHolder( parent);
//        holder.drawAdapter = this;
//
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        items.get( position ).bindViewHolder( holder );
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return viewTypes.get( items.get( position ).getClass() );
//    }
//
//    public static abstract class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
//
//        private DrawAdapter drawAdapter;
//        public ViewHolder(@NonNull View itemView) {
//            super( itemView );
//
//            itemView.setOnClickListener( this);
//        }
//
//        @Override
//        public void onClick(View v) {
//
//            drawAdapter.setSelected( getAdapterPosition() );
//        }
//    }
//
//}
