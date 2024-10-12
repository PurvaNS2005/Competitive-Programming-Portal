package com.example.competitveprogrammingportal;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    TextView SignUp, email, password;
    Button signIn;
    ProgressBar progressBar_login;
    private FirebaseAuth mAuth;
    private boolean isPasswordVisible = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Initialize views
        SignUp = findViewById(R.id.Sign_Up);
        signIn = findViewById(R.id.sign_in_button);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_pw);
        progressBar_login = findViewById(R.id.progressbar_login);
        ImageView eyeIcon = findViewById(R.id.eye_show);
        // handle password visibility
        eyeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    eyeIcon.setImageResource(R.drawable.img_7);
                    isPasswordVisible = false;
                } else {
                    // If password is hidden, show it
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    eyeIcon.setImageResource(R.drawable.img_9);
                    isPasswordVisible = true;
                }


            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LogIn.this, MainActivity.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar_login.setVisibility(View.VISIBLE);
                if(!TextUtils.isEmpty(email.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){
                    checkLogIn(email.getText().toString(), password.getText().toString());
                }else{
                    Toast.makeText(LogIn.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    progressBar_login.setVisibility(View.GONE);
                }
            }
        });
    }
    // check if entered credentials are right
    public void checkLogIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar_login.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LogIn.this, HomePage.class);
                            i.putExtra("username", user.getDisplayName());
                            i.putExtra("email",user.getEmail());
                            startActivity(i);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LogIn.this, "Incorrect password/email",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}