package com.example.quizbeneran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StoreItemAdapter extends RecyclerView.Adapter<StoreItemAdapter.MyViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<StoreItem> storeItems;
    private ArrayList<Transaction> transactions;
    private User user;

    public StoreItemAdapter(Context context2) {
        this.context = context2;
    }

    public void setStoreItems(User user2, ArrayList<Transaction> transactions2, ArrayList<StoreItem> storeItems2) {
        this.storeItems = storeItems2;
        this.user = user2;
        this.transactions = transactions2;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(C0090R.layout.store_item, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.productItemName.setText(this.storeItems.get(position).getProductName());
        if (this.storeItems.get(position).getMaxPlayer() == this.storeItems.get(position).getMinPlayer()) {
            TextView access$100 = holder.playerMinMax;
            access$100.setText(this.storeItems.get(position).getMinPlayer() + " Players");
        } else {
            TextView access$1002 = holder.playerMinMax;
            access$1002.setText(this.storeItems.get(position).getMinPlayer() + " - " + this.storeItems.get(position).getMaxPlayer() + " Players");
        }
        holder.productItemPrice.setText(this.storeItems.get(position).getPrice().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (StoreItemAdapter.this.context instanceof StorePage) {
                    ((StorePage) StoreItemAdapter.this.context).toItemDetail((StoreItem) StoreItemAdapter.this.storeItems.get(position));
                }
            }
        });
    }

    public int getItemCount() {
        return this.storeItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView playerMinMax;
        /* access modifiers changed from: private */
        public TextView productItemName;
        /* access modifiers changed from: private */
        public TextView productItemPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.productItemName = (TextView) itemView.findViewById(C0090R.C0093id.productItemName);
            this.playerMinMax = (TextView) itemView.findViewById(C0090R.C0093id.playerMinMax);
            this.productItemPrice = (TextView) itemView.findViewById(C0090R.C0093id.productItemPrice);
        }
    }
}
