package com.example.quizbeneran;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StorePage extends AppCompatActivity {
    private static final int ITEM_DETAIL = 1;
    private StoreItemAdapter adapter;
    private Intent intent;
    private RecyclerView storeItemRV;
    private ArrayList<StoreItem> storeItems = new ArrayList<>();
    private ArrayList<Transaction> transactions;
    private User user;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0090R.layout.activity_store_page);
        this.storeItemRV = (RecyclerView) findViewById(C0090R.C0093id.storeItemRV);
        Intent intent2 = getIntent();
        this.intent = intent2;
        this.transactions = intent2.getParcelableArrayListExtra("transactions");
        this.user = (User) this.intent.getParcelableExtra("user");
        this.storeItems.add(new StoreItem("Exploding Kitten", 2, 5, 250000));
        this.storeItems.add(new StoreItem("Card Against Humanity", 2, 4, 182500));
        StoreItemAdapter storeItemAdapter = new StoreItemAdapter(this);
        this.adapter = storeItemAdapter;
        storeItemAdapter.setStoreItems(this.user, this.transactions, this.storeItems);
        this.storeItemRV.setAdapter(this.adapter);
        this.storeItemRV.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void toItemDetail(StoreItem item) {
        Intent toDetail = new Intent(this, ItemDetail.class);
        toDetail.putExtra("user", this.user);
        toDetail.putExtra("item", item);
        toDetail.putParcelableArrayListExtra("transactions", this.transactions);
        startActivityForResult(toDetail, 1);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1) {
            this.user = (User) data.getParcelableExtra("user");
            this.transactions = data.getParcelableArrayListExtra("transactions");
            Intent intent2 = new Intent();
            intent2.putExtra("user", this.user);
            intent2.putParcelableArrayListExtra("transactions", this.transactions);
            setResult(-1, intent2);
            finish();
        }
    }
}
