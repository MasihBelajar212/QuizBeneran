package com.example.quizbeneran;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private static final int PROFILE_PAGE = 2;
    private static final int STORE_PAGE = 1;
    private Intent Login;
    private TransactionAdapter adapter;
    private TextView recycleViewAlert;
    private RecyclerView transactionRV;
    private ArrayList<Transaction> transactions;
    private User user;
    private TextView userGreetTV;
    private TextView walletTV;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0090R.layout.activity_home_page);
        getSupportActionBar().setTitle((CharSequence) "Home Page");
        this.transactionRV = (RecyclerView) findViewById(C0090R.C0093id.transactionRV);
        this.userGreetTV = (TextView) findViewById(C0090R.C0093id.userGreetTV);
        this.walletTV = (TextView) findViewById(C0090R.C0093id.walletTV);
        this.recycleViewAlert = (TextView) findViewById(C0090R.C0093id.recycleViewAlert);
        Intent intent = getIntent();
        this.Login = intent;
        this.user = (User) intent.getParcelableExtra("loggedUser");
        this.transactions = this.Login.getParcelableArrayListExtra("transactions");
        TextView textView = this.userGreetTV;
        textView.setText("Welcome back, " + this.user.getUsername() + "!");
        TextView textView2 = this.walletTV;
        textView2.setText("Rp." + this.user.getWallet());
        TransactionAdapter transactionAdapter = new TransactionAdapter(this);
        this.adapter = transactionAdapter;
        transactionAdapter.setTransactions(this.user.getId(), this.transactions);
        this.transactionRV.setAdapter(this.adapter);
        this.transactionRV.setLayoutManager(new LinearLayoutManager(this, 1, false));
        if (this.adapter.getItemCount() == 0) {
            showEmptyAlert();
        }
    }

    public void showEmptyAlert() {
        this.recycleViewAlert.setText(C0090R.string.No_Transaction_Alert);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0090R.C0095menu.home_page_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case C0090R.C0093id.homeMenu:
                Toast.makeText(this, "Ke home page", 0).show();
                return true;
            case C0090R.C0093id.logOutMenu:
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("transactions", this.transactions);
                setResult(-1, intent);
                finish();
                return true;
            case C0090R.C0093id.profileMenu:
                Intent intent2 = new Intent(this, ProfilePage.class);
                intent2.putExtra("user", this.user);
                startActivityForResult(intent2, 2);
                return true;
            case C0090R.C0093id.storeMenu:
                Intent intent3 = new Intent(this, StorePage.class);
                intent3.putParcelableArrayListExtra("transactions", this.transactions);
                intent3.putExtra("user", this.user);
                startActivityForResult(intent3, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == -1) {
            this.adapter = new TransactionAdapter(this);
            this.user = (User) data.getParcelableExtra("user");
            this.transactions = data.getParcelableArrayListExtra("transactions");
            this.adapter.setTransactions(this.user.getId(), this.transactions);
            this.transactionRV.setAdapter(this.adapter);
            if (this.adapter.getItemCount() > 0) {
                this.recycleViewAlert.setText((CharSequence) null);
            }
            TextView textView = this.walletTV;
            textView.setText("Rp." + this.user.getWallet());
        }
        if (requestCode == 2 && resultCode == -1) {
            this.user = (User) data.getParcelableExtra("user");
            TextView textView2 = this.walletTV;
            textView2.setText("Rp." + this.user.getWallet());
        }
    }
}
