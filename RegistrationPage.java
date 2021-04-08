package com.example.quizbeneran;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class RegistrationPage extends AppCompatActivity {
    private Intent Registration;

    public Calendar date;

    public int day;

    public DatePickerDialog dialog;

    public String gender;
    private EditText inputConfirmPass;

    public EditText inputDOB;

    public RadioGroup inputGender;
    private EditText inputPassword;
    private EditText inputPhone;
    private EditText inputUsername;
    /* access modifiers changed from: private */
    public int month;
    private CheckBox termAgree;
    private AlertDialog.Builder termAlert;
    private ArrayList<User> users;

    public int year;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0090R.layout.activity_registration_page);
        getSupportActionBar().setTitle((CharSequence) "Registration Page");
        this.inputUsername = (EditText) findViewById(C0090R.C0093id.inputUsername);
        this.inputDOB = (EditText) findViewById(C0090R.C0093id.inputDOB);
        this.inputPhone = (EditText) findViewById(C0090R.C0093id.inputPhone);
        this.inputPassword = (EditText) findViewById(C0090R.C0093id.inputPassword);
        this.inputConfirmPass = (EditText) findViewById(C0090R.C0093id.inputConfirmPass);
        this.inputGender = (RadioGroup) findViewById(C0090R.C0093id.inputGender);
        this.termAgree = (CheckBox) findViewById(C0090R.C0093id.termAgree);
        this.termAlert = new AlertDialog.Builder(this);
        Intent intent = getIntent();
        this.Registration = intent;
        this.users = intent.getParcelableArrayListExtra("Users");
        getCheckedGender();
        this.inputDOB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar unused = RegistrationPage.this.date = Calendar.getInstance();
                RegistrationPage registrationPage = RegistrationPage.this;
                int unused2 = registrationPage.year = registrationPage.date.get(1);
                RegistrationPage registrationPage2 = RegistrationPage.this;
                int unused3 = registrationPage2.month = registrationPage2.date.get(2);
                RegistrationPage registrationPage3 = RegistrationPage.this;
                int unused4 = registrationPage3.day = registrationPage3.date.get(5);
                DatePickerDialog unused5 = RegistrationPage.this.dialog = new DatePickerDialog(RegistrationPage.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        EditText access$600 = RegistrationPage.this.inputDOB;
                        access$600.setText(day + "/" + RegistrationPage.this.setMonth(month) + "/" + year);
                    }
                }, RegistrationPage.this.year, RegistrationPage.this.month, RegistrationPage.this.day);
                RegistrationPage.this.dialog.show();
            }
        });
    }

    /* access modifiers changed from: private */
    public String setMonth(int month2) {
        int month3 = month2 + 1;
        if (month3 == 1) {
            return "Jan";
        }
        if (month3 == 2) {
            return "Feb";
        }
        if (month3 == 3) {
            return "Mar";
        }
        if (month3 == 4) {
            return "Apr";
        }
        if (month3 == 5) {
            return "May";
        }
        if (month3 == 6) {
            return "Jun";
        }
        if (month3 == 7) {
            return "Jul";
        }
        if (month3 == 8) {
            return "Aug";
        }
        if (month3 == 9) {
            return "Sep";
        }
        if (month3 == 10) {
            return "Oct";
        }
        if (month3 == 11) {
            return "Nov";
        }
        if (month3 == 12) {
            return "Dec";
        }
        return null;
    }

    public void toLogin(View view) {
        finish();
    }

    public void signUp(View view) {
        boolean VALIDITY;
        String username = this.inputUsername.getText().toString();
        String password = this.inputPassword.getText().toString();
        String obj = this.inputConfirmPass.getText().toString();
        String DOB = this.inputDOB.getText().toString();
        String phone = this.inputPhone.getText().toString();
        int i = 0;
        while (true) {
            if (this.users.size() <= i) {
                VALIDITY = true;
                break;
            } else if (this.users.get(i).getUsername().equals(username)) {
                this.inputUsername.setError("Username already existed!");
                VALIDITY = false;
                break;
            } else {
                i++;
            }
        }
        if (validation() && VALIDITY) {
            this.Registration = new Intent();
            this.users.add(new User(this.users.size(), username, this.gender, DOB, phone, password));
            this.Registration.putParcelableArrayListExtra("Users", this.users);
            setResult(-1, this.Registration);
            finish();
        }
    }

    public void getCheckedGender() {
        this.inputGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (RegistrationPage.this.inputGender.getCheckedRadioButtonId()) {
                    case C0090R.C0093id.inputGenderFemale /*2131296453*/:
                        String unused = RegistrationPage.this.gender = "Female";
                        return;
                    case C0090R.C0093id.inputGenderMale /*2131296454*/:
                        String unused2 = RegistrationPage.this.gender = "Male";
                        return;
                    default:
                        String unused3 = RegistrationPage.this.gender = null;
                        return;
                }
            }
        });
    }

    public boolean validation() {
        int loginValidity = 0;
        String username = this.inputUsername.getText().toString();
        String password = this.inputPassword.getText().toString();
        String confirmPass = this.inputConfirmPass.getText().toString();
        String DOB = this.inputDOB.getText().toString();
        String phone = this.inputPhone.getText().toString();
        if (username.isEmpty()) {
            this.inputUsername.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputUsername.setError("Username cannot be empty!");
            loginValidity = 0 + 1;
        } else if (username.length() < 6 || username.length() > 12) {
            this.inputUsername.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputUsername.setError("Username must between 6 and 12 characters!");
            loginValidity = 0 + 1;
        } else {
            this.inputUsername.setBackgroundResource(0);
        }
        if (password.isEmpty()) {
            this.inputPassword.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputPassword.setError("Password cannot be empty!");
            loginValidity++;
        } else if (password.length() < 8) {
            this.inputPassword.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputPassword.setError("Password must be more than 8 characters!");
            loginValidity++;
        } else if (!password.matches("[a-zA-Z0-9]+")) {
            this.inputPassword.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputPassword.setError("Password must be alphanumeric!");
            loginValidity++;
        } else {
            this.inputPassword.setBackgroundResource(0);
        }
        if (confirmPass.isEmpty()) {
            this.inputConfirmPass.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputConfirmPass.setError("Please input password confirmation!");
            loginValidity++;
        } else if (!confirmPass.equals(password)) {
            this.inputConfirmPass.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputConfirmPass.setError("Password confirmation does not match!");
            loginValidity++;
        } else {
            this.inputConfirmPass.setBackgroundResource(0);
        }
        if (DOB.isEmpty()) {
            this.inputDOB.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputDOB.setError("Date of birth cannot be empty!");
            loginValidity++;
        } else {
            this.inputDOB.setBackgroundResource(0);
            this.inputDOB.setError((CharSequence) null);
        }
        if (phone.isEmpty()) {
            this.inputPhone.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputPhone.setError("Phone number cannot be empty!");
            loginValidity++;
        } else if (phone.length() < 10 || phone.length() > 12) {
            this.inputPhone.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputPhone.setError("Phone number must between 10 and 12 digits!");
            loginValidity++;
        } else if (phone.matches("/d")) {
            this.inputPhone.setBackgroundResource(C0090R.C0092drawable.errorfield);
            this.inputPhone.setError("Phone must be digit only!");
            loginValidity++;
        } else {
            this.inputPhone.setBackgroundResource(0);
        }
        if (this.inputGender.getCheckedRadioButtonId() == -1) {
            this.inputGender.setBackgroundResource(C0090R.C0092drawable.errorfield);
            loginValidity++;
        } else {
            this.inputGender.setBackgroundResource(0);
        }
        if (!this.termAgree.isChecked()) {
            new alertPopUp("Term of Service").show(getSupportFragmentManager(), "ToS Agreement");
            loginValidity++;
        }
        if (loginValidity > 0) {
            return false;
        }
        return true;
    }
}
