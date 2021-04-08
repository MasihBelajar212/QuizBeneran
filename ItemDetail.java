package com.example.quizbeneran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ItemDetail extends AppCompatActivity {
    private Button btnBuy;
    private Intent detail;
    private StoreItem item;
    private TextView productDetailMinMaxPlayer;
    private TextView productDetailName;
    private TextView productDetailPrice;
    private ArrayList<Transaction> transactions;
    private User user;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0090R.layout.activity_item_detail);
        Intent intent = getIntent();
        this.detail = intent;
        this.user = (User) intent.getParcelableExtra("user");
        this.transactions = this.detail.getParcelableArrayListExtra("transactions");
        this.item = (StoreItem) this.detail.getParcelableExtra("item");
        this.productDetailName = (TextView) findViewById(C0090R.C0093id.productDetailName);
        this.productDetailMinMaxPlayer = (TextView) findViewById(C0090R.C0093id.productDetailMinMaxPlayer);
        this.productDetailPrice = (TextView) findViewById(C0090R.C0093id.productDetailPrice);
        this.btnBuy = (Button) findViewById(C0090R.C0093id.btnBuy);
        this.productDetailName.setText(this.item.getProductName());
        TextView textView = this.productDetailMinMaxPlayer;
        textView.setText(this.item.getMinPlayer() + " - " + this.item.getMaxPlayer() + " Players");
        TextView textView2 = this.productDetailPrice;
        StringBuilder sb = new StringBuilder();
        sb.append("Rp.");
        sb.append(this.item.getPrice());
        textView2.setText(sb.toString());
    }

    public void buyItem(View view) {
        if (this.user.getWallet().intValue() < this.item.getPrice().intValue()) {
            Toast.makeText(this, "Insufficient funds", 0).show();
        } else if (this.transactions != null) {
            this.transactions.add(new Transaction(this.user.getId(), this.item.getProductName(), new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()), this.item.getPrice()));
            this.user.pay(this.item.getPrice());
            Intent intent = new Intent(this, HomePage.class);
            intent.putParcelableArrayListExtra("transactions", this.transactions);
            intent.putExtra("user", this.user);
            setResult(-1, intent);
            finish();
        }
    }
}
