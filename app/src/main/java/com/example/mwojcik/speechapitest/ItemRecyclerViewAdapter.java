package com.example.mwojcik.speechapitest;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {


    public interface ItemRecyclerViewListener {
        public void onItemSelected(int position);
    }

    private List<Item> itemList;
    private ItemListActivity mParentActivity;
    private boolean mTwoPane;
    private int selectedPosition = RecyclerView.NO_POSITION;

    private ItemRecyclerViewListener itemRecyclerViewListener;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Item item = (Item) view.getTag();
            if (mTwoPane) {
                ItemDetailFragment fragment = ItemDetailFragment.newInstance(item);

                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                //selectedPosition = -1;
                Context context = view.getContext();
                Intent intent = new Intent(context, ItemDetailActivity.class);
                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item);
                context.startActivity(intent);
            }

        }
    };

    public ItemRecyclerViewAdapter(ItemListActivity mParentActivity, List<Item> itemList, boolean mTwoPane, int positionSelected){
        this.mParentActivity = mParentActivity;
        this.mTwoPane = mTwoPane;
        this.itemList = itemList;
        this.selectedPosition = positionSelected;
        itemRecyclerViewListener = null;
    }

    public void setItemRecyclerViewListener(ItemRecyclerViewListener itemRecyclerViewListener) {
        this.itemRecyclerViewListener = itemRecyclerViewListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_list_content, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);

        return viewHolder;
    }

    //wypelniany item view przez holdera
    @Override
    public void onBindViewHolder(@NonNull final ItemRecyclerViewAdapter.ViewHolder holder, final int position) {
        Item item = itemList.get(position);
        holder.textView.setText(item.getName());
//        holder.textView2.setText(item.getImageURL());
        holder.itemView.setTag(item);

        if(mTwoPane) {
            if (selectedPosition == -1) {
                selectedPosition = 0;
                if (itemList.size() > 0){
                    ItemDetailFragment fragment = ItemDetailFragment.newInstance(itemList.get(0));
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                }
            } else {
                if (itemList.size() >= selectedPosition) {
                    ItemDetailFragment fragment = ItemDetailFragment.newInstance(itemList.get(selectedPosition));
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                }
            }
        }

        holder.itemView.setSelected(selectedPosition == position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                notifyItemChanged(selectedPosition);
                selectedPosition = holder.getLayoutPosition();
                notifyItemChanged(selectedPosition);
                itemRecyclerViewListener.onItemSelected(selectedPosition);

                Item item = (Item) view.getTag();

                if (mTwoPane) {

                    ItemDetailFragment fragment = ItemDetailFragment.newInstance(item);

                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item);
                    context.startActivity(intent);
                }



            }
        });

//        if(mTwoPane) {
//            if (selectedPosition == position) {
//                holder.itemView.setSelected(true);
//                if (mTwoPane) {
//                    ItemDetailFragment fragment = ItemDetailFragment.newInstance(item);
//
//                    mParentActivity.getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.item_detail_container, fragment)
//                            .commit();
//                }
//            } else {
//                holder.itemView.setSelected(false);
//            }
//        }

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
//        private TextView textView2;
        private LinearLayout linearLayout;
        private ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_list);
            textView = (TextView) itemView.findViewById(R.id.id_text);
//            textView2 = (TextView) itemView.findViewById(R.id.content);
            imageView = (ImageView) itemView.findViewById(R.id.item_list_image);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    if(position!=RecyclerView.NO_POSITION){
////                        Item item = itemList.get(position);
//                        Item item = (Item) itemView.getTag();
//                    }
//                }
//            });
        }


    }
}
