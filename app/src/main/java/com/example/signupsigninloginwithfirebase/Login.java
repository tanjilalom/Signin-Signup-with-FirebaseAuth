package com.example.signupsigninloginwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText inemail, inpass;
    TextView text;
    Button loginbtn, signupbtn1;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    int counter = 3;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inemail = findViewById(R.id.editloginemail);
        inpass = findViewById(R.id.editloginpass);
        loginbtn = findViewById(R.id.login_btn);
        signupbtn1 = findViewById(R.id.signup_btn);
        text = findViewById(R.id.attempt);

        loginbtn.setOnClickListener(this);
        signupbtn1.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    //without firebase auth

    /*private void performlogin() {
        sendusertonextactivity();
        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
    }*/


    private void performlogin() {

        String email1 = inemail.getText().toString();
        String password = inpass.getText().toString();


        if (!Patterns.EMAIL_ADDRESS.matcher(email1).matches())
        {
            inemail.setError("Enter Correct Email");
            inemail.requestFocus();
        }
        else if (password.isEmpty() || password.length() < 6)
        {
            inpass.setError("Enter Proper Password");
            inpass.requestFocus();
        }
        else {
            progressDialog.setMessage("Please wait for Login");
            progressDialog.setTitle("Sign In");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email1, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendusertonextactivity();
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();

                        counter--;
                        text.setText("Number of attempts remaining : " + counter);
                        if (counter == 0) {
                            loginbtn.setEnabled(false);
                        }
                    }
                }
            });
        }
    }

    private void sendusertonextactivity() {

        Intent myintent = new Intent(this, Welcome.class);
        startActivity(myintent);
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.login_btn) {
            performlogin();
        }
        if(v.getId() == R.id.signup_btn){
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }

    }
}