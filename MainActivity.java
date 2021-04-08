package com.example.quizbeneran;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final  boolean $assertionsDisabled = false;
    private static final int LOGGED_OUT_USER = 2;
    private static final int REGISTRATION_PAGE = 1;
    private Toast alert;
    private Button btnLogin;
    private EditText inputPassword;
    private EditText inputUsername;
    private String password;
    private ArrayList<Transaction> transactions;
    private String username;
    private ArrayList<User> users = new ArrayList<>();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0090R.layout.activity_main);
        getSupportActionBar().setTitle((CharSequence) "Login Page");
        this.inputUsername = (EditText) findViewById(C0090R.C0093id.inputUsername);
        this.inputPassword = (EditText) findViewById(C0090R.C0093id.inputPassword);
        this.btnLogin = (Button) findViewById(C0090R.C0093id.btnLogin);
        this.transactions = new ArrayList<>();
    }

    public void Login(View view) {
        this.username = this.inputUsername.getText().toString();
        this.password = this.inputPassword.getText().toString();
        int LOGGED_USER = 0;
        int STATUS = -1;
        if (this.username.isEmpty() || this.password.isEmpty()) {
            if (this.username.isEmpty()) {
                this.inputUsername.setBackgroundResource(C0090R.C0092drawable.errorfield);
                this.inputUsername.setError("This field cannot be blank");
            } else {
                this.inputUsername.setBackgroundResource(0);
            }
            if (this.password.isEmpty()) {
                this.inputPassword.setBackgroundResource(C0090R.C0092drawable.errorfield);
                this.inputPassword.setError("This field cannot be blank");
                return;
            }
            this.inputPassword.setBackgroundResource(0);
            return;
        }
        this.inputPassword.setBackgroundResource(0);
        this.inputUsername.setBackgroundResource(0);
        int i = 0;
        while (true) {
            if (this.users.size() > i) {
                if (!this.users.get(i).getUsername().equals(this.username) || !this.users.get(i).getPassword().equals(this.password)) {
                    if (this.users.get(i).getUsername().equals(this.username) && !this.users.get(i).getPassword().equals(this.password)) {
                        STATUS = 0;
                        break;
                    }
                    i++;
                } else {
                    STATUS = 1;
                    LOGGED_USER = i;
                    break;
                }
            } else {
                break;
            }
        }
        if (STATUS == 0) {
            this.inputPassword.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputUsername.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputUsername.setError("Incorrect Username");
            this.inputPassword.setError("Incorrect Password");
            Toast.makeText(this, "Username and Password does not match!", 0).show();
        } else if (STATUS == -1) {
            this.inputUsername.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputUsername.setError("Username not registered!");
            Toast.makeText(this, "Username not been registered yet!", 0).show();
        } else if (STATUS == 1) {
            this.inputPassword.setBackgroundResource(0);
            this.inputUsername.setBackgroundResource(0);
            this.inputUsername.setError((CharSequence) null);
            Intent Login = new Intent(this, HomePage.class);
            Login.putExtra("transactions", this.transactions);
            Login.putExtra("loggedUser", this.users.get(LOGGED_USER));
            startActivityForResult(Login, 2);
        }
    }

    public void toRegister(View view) {
        Intent registration = new Intent(this, RegistrationPage.class);
        registration.putParcelableArrayListExtra("Users", this.users);
        startActivityForResult(registration, 1);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode != -1) {
                return;
            }
            if (data != null) {
                this.users = data.getParcelableArrayListExtra("Users");
                return;
            }
            throw new AssertionError();
        } else if (requestCode != 2 || resultCode != -1) {
        } else {
            if (data != null) {
                this.transactions = data.getParcelableArrayListExtra("transactions");
                return;
            }
            throw new AssertionError();
        }
    }
}
