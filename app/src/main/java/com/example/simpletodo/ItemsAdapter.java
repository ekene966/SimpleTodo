package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//responsible for displaying data from the model into a row in the recycle view
public class ItemsAdapter extends  RecyclerView.Adapter<ItemsAdapter.ViewHolder> {


    public  interface  OnClickListener{
        void onItemClicked(int position);
    }

    public interface OnlongClickListener{
        void  onItemLongClicked(int position);

        void OnItemLongClicked(int adapterPosition);
    }

    List<String> items;
    OnlongClickListener longClickListener;
    OnClickListener clickListener;


    public ItemsAdapter(List<String> items, OnlongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //user layout inflator to inflate a veiw
        //wrap it inside a view holder and return it

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent,false);


        return new ViewHolder(todoView);
    }
    //responsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grap the item at the position

        String item = items.get(position);

        //bind the item into the specified view holder

        holder.bind(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access to views that represent each row of the list

    class  ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
            //update the view inside the view hilder with the data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());

                                          }
                                      });


                    tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            //remove the item from the recycler view
                            //notify the listern which postion was longed pressed
                            longClickListener.OnItemLongClicked(getAdapterPosition());

                            return true;
                        }
                    });
        }
    }
}
