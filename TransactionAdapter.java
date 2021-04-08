package com.example.quizbeneran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public ArrayList<Transaction> filteredTransaction;
    /* access modifiers changed from: private */
    public ArrayList<Transaction> transactions;

    public TransactionAdapter(Context context2) {
        this.context = context2;
    }

    public void setTransactions(String userId, ArrayList<Transaction> transactions2) {
        this.transactions = transactions2;
        this.filteredTransaction = new ArrayList<>();
        filterFromUser(userId);
    }

    public void filterFromUser(String userId) {
        for (int i = 0; i < this.transactions.size(); i++) {
            if (this.transactions.get(i).getTransUserId().equals(userId)) {
                this.filteredTransaction.add(this.transactions.get(i));
            }
        }
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(this.context).inflate(C0090R.layout.transaction_item, parent, false));
    }

    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.productName.setText(this.filteredTransaction.get(position).getProductName());
        holder.transDate.setText(this.filteredTransaction.get(position).getTransDate());
        holder.productPrice.setText(this.filteredTransaction.get(position).getProductPrice().toString());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TransactionAdapter.this.transactions.remove(position);
                TransactionAdapter.this.filteredTransaction.remove(position);
                TransactionAdapter.this.notifyItemRemoved(position);
                TransactionAdapter transactionAdapter = TransactionAdapter.this;
                transactionAdapter.notifyItemRangeChanged(position, transactionAdapter.filteredTransaction.size());
                if ((TransactionAdapter.this.context instanceof HomePage) && TransactionAdapter.this.filteredTransaction.size() == 0) {
                    ((HomePage) TransactionAdapter.this.context).showEmptyAlert();
                }
            }
        });
    }

    public int getItemCount() {
        return this.filteredTransaction.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Button deleteBtn;
        TextView productName;
        TextView productPrice;
        TextView transDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.productName = (TextView) itemView.findViewById(C0090R.C0093id.productName);
            this.transDate = (TextView) itemView.findViewById(C0090R.C0093id.transDate);
            this.productPrice = (TextView) itemView.findViewById(C0090R.C0093id.productPrice);
            this.deleteBtn = (Button) itemView.findViewById(C0090R.C0093id.deleteBtn);
        }
    }
}
