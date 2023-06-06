package com.example.table;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {

    EditText emailEdittext, PasswordEdittext;
    Button loginBtn;
    ProgressBar progressBar;
    TextView createaccbtntextview, Greetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int rightNow = Calendar.getInstance().getTime().getHours();
        setContentView(R.layout.activity_login);
        // Получаем ссылку на слой
        View layer = findViewById(R.id.login);
// Set the visibility of the layer to invisible
        layer.setVisibility(View.INVISIBLE);

// Get the height of the screen
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

// Create an animation for the translationY property
        ObjectAnimator animation = ObjectAnimator.ofFloat(layer, "translationY", screenHeight, 0);
        animation.setDuration(900); // duration of the animation in milliseconds

// Add a listener to set the visibility of the layer to visible before the animation starts
        animation.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                layer.setVisibility(View.VISIBLE);
            }
        });

// Start the animation
        animation.start();
        emailEdittext = findViewById(R.id.email_edittext);
        PasswordEdittext = findViewById(R.id.password_edittext);
        loginBtn = findViewById(R.id.login_acc_btn);
        progressBar = findViewById(R.id.progress_bar);
        createaccbtntextview = findViewById(R.id.create_acc_text_botton);
        Greetings = findViewById(R.id.Greetings);

        loginBtn.setOnClickListener((v)-> loginUser() );
        createaccbtntextview.setOnClickListener((v)->startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class)));

        if(rightNow>=7&&rightNow<12){
            Greetings.setText("Доброго утра ☀️");
        }
        if(rightNow>=12&&rightNow<18){
            Greetings.setText("Доброго дня \uD83D\uDE42");
        }
        if(rightNow>=18&&rightNow<24){
            Greetings.setText("Доброй ночи \uD83C\uDF12");
        }
    }
    void loginUser(){
        String email = emailEdittext.getText().toString();
        String password = PasswordEdittext.getText().toString();

        boolean isValidated = validateData(email, password);
        if(!isValidated){
            return;
        }
        loginAccInFireBase(email, password);
    }
    void loginAccInFireBase(String email, String password){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        changeInProgress(true);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if(task.isSuccessful()){
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, SplashActivity.class));
                    }else{
                        Utility.showToast(LoginActivity.this, "Email не верифицирован, пожалуйста пройдите верификацию");
                }
                }else {
                    Utility.showToast(LoginActivity.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }
    
    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            loginBtn.setVisibility(View.INVISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }
    boolean validateData(String email, String password){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            PasswordEdittext.setError("Пароль слишком короткий");
            return false;
        }
        return true;
    }
}