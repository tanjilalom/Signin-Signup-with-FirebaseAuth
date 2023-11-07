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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;


public class Signup extends AppCompatActivity {

    EditText inname, inemail, inphn, inpass;
    Button signupbtn2;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        inname = findViewById(R.id.editSignUpFullName);
        inemail = findViewById(R.id.editSignUpEmail);
        inphn = findViewById(R.id.editSignUpMobile);
        inpass = findViewById(R.id.editSignUpPassword);

        signupbtn2 = findViewById(R.id.signup_btn2);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();



        signupbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                performAuth();

                firebaseDatabase = FirebaseDatabase.getInstance();
                databaseReference = firebaseDatabase.getReference("user");

                String name1 = inname.getText().toString();
                String phone = inphn.getText().toString();
                String email1 = inemail.getText().toString();
                String password = inpass.getText().toString();

                signuphelper signuphelper = new signuphelper(name1, phone, email1, password);

                databaseReference.child(phone).setValue(signuphelper);
            }
        });
    }

    private void performAuth() {

        String name1 = inname.getText().toString();
        String phone = inphn.getText().toString();
        String email1 = inemail.getText().toString();
        String password = inpass.getText().toString();


        if(!Patterns.EMAIL_ADDRESS.matcher(email1).matches() || email1.isEmpty() )
        {
            inemail.setError("Enter Correct Email");
            inemail.requestFocus();
        }
        else if (password.isEmpty())
        {
            inpass.setError("Enter Proper Password");
            inpass.requestFocus();
        }
        else if (password.length()<6)
        {
            inpass.setError("Minimum length of a password should be 6");
            inpass.requestFocus();
        }

        else
        {
            progressDialog.setMessage("Please wait for verification");
            progressDialog.setTitle("Registered");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email1, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendusertonextactivity();
                        Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendusertonextactivity() {

        Intent myintent = new Intent(this, Welcome.class);
        startActivity(myintent);
    }


}