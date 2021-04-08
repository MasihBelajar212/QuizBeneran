package com.example.quizbeneran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilePage extends AppCompatActivity {
    Button btnTopUp;
    alertPopUp notChecked;
    TextView profileDOB;
    TextView profileGender;
    TextView profileName;
    TextView profilePhone;
    TextView profileWallet;
    RadioGroup radioGroupNominal;
    Integer topUpAmount;
    EditText topUpPassword;
    User user;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0090R.layout.activity_profile_page);
        this.user = (User) getIntent().getParcelableExtra("user");
        this.profileName = (TextView) findViewById(C0090R.C0093id.profileName);
        this.profileWallet = (TextView) findViewById(C0090R.C0093id.profileWallet);
        this.profilePhone = (TextView) findViewById(C0090R.C0093id.profilePhone);
        this.profileGender = (TextView) findViewById(C0090R.C0093id.profileGender);
        this.profileDOB = (TextView) findViewById(C0090R.C0093id.profileDOB);
        this.topUpPassword = (EditText) findViewById(C0090R.C0093id.topUpPassword);
        this.btnTopUp = (Button) findViewById(C0090R.C0093id.btnTopUp);
        this.profileName.setText(this.user.getUsername());
        TextView textView = this.profileWallet;
        textView.setText("Rp." + this.user.getWallet());
        this.profilePhone.setText(this.user.getPhone());
        this.profileGender.setText(this.user.getGender());
        this.profileDOB.setText(this.user.getDateOfBirth());
        RadioGroup radioGroup = (RadioGroup) findViewById(C0090R.C0093id.radioGroupNominal);
        this.radioGroupNominal = radioGroup;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case C0090R.C0093id.tambah1000K /*2131296650*/:
                        ProfilePage.this.topUpAmount = 1000000;
                        return;
                    case C0090R.C0093id.tambah250K /*2131296651*/:
                        ProfilePage.this.topUpAmount = 250000;
                        return;
                    case C0090R.C0093id.tambah500K /*2131296652*/:
                        ProfilePage.this.topUpAmount = 500000;
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void topUp(View view) {
        if (this.radioGroupNominal.getCheckedRadioButtonId() == -1) {
            this.radioGroupNominal.setBackgroundResource(C0090R.C0092drawable.errorfield);
            Toast.makeText(this, "Please choose the top up amount!", 0).show();
            return;
        }
        this.radioGroupNominal.setBackgroundResource(0);
        if (!this.user.getPassword().equals(this.topUpPassword.getText().toString())) {
            new alertPopUp("Password not match").show(getSupportFragmentManager(), "Password not match");
            return;
        }
        this.user.addFunds(this.topUpAmount);
        Intent toHomePage = new Intent();
        toHomePage.putExtra("user", this.user);
        setResult(-1, toHomePage);
        finish();
    }
}
