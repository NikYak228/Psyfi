package com.example.table;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import java.util.Calendar;
import java.util.Date;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity {
    EditText emailEdittext, PasswordEdittext, confirmPasswordEdittext;
    Button createaccountBtn;
    ProgressBar progressBar;
    TextView loginbtntextview, Greetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int rightNow = Calendar.getInstance().getTime().getHours();
        setContentView(R.layout.activity_create_account);

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
        confirmPasswordEdittext = findViewById(R.id.confirm_password_edittext);
        createaccountBtn = findViewById(R.id.create_acc_btn);
        progressBar = findViewById(R.id.progress_bar);
        loginbtntextview = findViewById(R.id.login_text_botton);
        Greetings = findViewById(R.id.Greetings);
        if(rightNow>=7&&rightNow<12){
            Greetings.setText("Доброго утра ☀️");
        }
        if(rightNow>=12&&rightNow<18){
            Greetings.setText("Доброго дня \uD83D\uDE42");
        }
        if(rightNow>=18&&rightNow<24){
            Greetings.setText("Доброй ночи \uD83C\uDF12");
        }
        if(rightNow>=0&&rightNow<7){
            Greetings.setText("Доброй ночи \uD83C\uDF12");
        }
        createaccountBtn.setOnClickListener(v-> createAcc());
        loginbtntextview.setOnClickListener(v-> finish());

    }
    void createAcc(){
        String email = emailEdittext.getText().toString();
        String password = PasswordEdittext.getText().toString();
        String confirmpassword = confirmPasswordEdittext.getText().toString();

        boolean isValidated = validateData(email, password, confirmpassword);
        if(!isValidated){
            return;
        }
        createAccointInFirebase(email, password);

    }
    void createAccointInFirebase(String email, String password){
        changeInProgress(true);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                changeInProgress(false);
                if(task.isSuccessful()){
                    Utility.showToast(CreateAccountActivity.this, "Успех! \n Проверьте почту для верификации");
                    firebaseAuth.getCurrentUser().sendEmailVerification();
                    Toast.makeText(CreateAccountActivity.this, firebaseAuth.getCurrentUser().toString(), Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                }else{
                    Utility.showToast(CreateAccountActivity.this, task.getException().getLocalizedMessage());
                }
            }
        });
    }

    void changeInProgress(boolean inProgress){
        if(inProgress){
            progressBar.setVisibility(View.VISIBLE);
            createaccountBtn.setVisibility(View.INVISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
            createaccountBtn.setVisibility(View.VISIBLE);
        }
    }
    boolean validateData(String email, String password, String confirmPassword){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEdittext.setError("Email is invalid");
            return false;
        }
        if(password.length()<6){
            PasswordEdittext.setError("Пароль слищком короткий");
            return false;
        }
        if(!password.equals(confirmPassword)){
            confirmPasswordEdittext.setError("Пароли не совпадают");
            return false;
        }
        return true;
    }
}