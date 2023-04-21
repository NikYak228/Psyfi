package com.example.table;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {

    public ImageView imgsun;
    public ImageView imgicon;

    public String docname;

    public int time;

    public boolean user_created = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logined_anim);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser!=null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            CollectionReference usersCollection = db.collection("users");
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String uid = firebaseUser.getUid();
            String email = firebaseUser.getEmail();
            time = 2000;
            imgicon = findViewById(R.id.icon);
// Set the visibility of the layer to invisible
            imgicon.setVisibility(View.INVISIBLE);

// Get the height of the screen
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int screenHeight = displayMetrics.heightPixels;

// Create an animation for the translationY property
            ObjectAnimator animation = ObjectAnimator.ofFloat(imgicon, "translationY", screenHeight, 0);
            animation.setDuration(1000); // duration of the animation in milliseconds

// Add a listener to set the visibility of the layer to visible before the animation starts
            animation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animation) {
                    imgicon.setVisibility(View.VISIBLE);
                }
                @Override
                public void onAnimationEnd(Animator animation) {
                    imgicon.animate().scaleX(3f).scaleY(3f).setDuration(500).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            imgicon.animate().scaleX(0.4f).scaleY(0.4f).setDuration(300).withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    ObjectAnimator animation22 = ObjectAnimator.ofFloat(imgicon, "translationY", 0, -screenHeight);
                                    animation22.setDuration(1000); // duration of the animation in milliseconds
                                    animation22.start();
                                }
                            });

                        }

                    });

                }
            });

// Start the animation
            animation.start();

        }else{
            time=1015;
            setContentView(R.layout.activity_splash);
            imgsun = findViewById(R.id.sun);
            imgsun.animate().scaleX(26f).scaleY(26f).setDuration(980);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if(currentUser==null){
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }else{
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    CollectionReference usersCollection = db.collection("users");
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = firebaseUser.getUid();
                    String email = firebaseUser.getEmail();
                    assert email != null;
                    DocumentReference userRef = usersCollection.document(email);

                    // Create a new user with a first and last name



                    User user = new User(uid, email);
// Add a new document with a generated ID// replace USER_ID with the actual user ID
                    userRef.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if ((documentSnapshot.getLong("tags.questionIndex")) == null) {
                                        userRef.set(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        user_created = true;
                                                        startActivity(new Intent(SplashActivity.this,PsychQuestionsActivity.class));
                                                    }

                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Toast.makeText(SplashActivity.this, "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_SHORT).show();
                                                        docname = documentReference.getId();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(SplashActivity.this, "Error adding document" + e, Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }else{
                                        userRef.get()
                                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                        if ((documentSnapshot.getLong("tags.questionIndex")) != null) {
                                                            int questionIndex = (documentSnapshot.getLong("tags.questionIndex")).intValue();
                                                            if (questionIndex > 9) {
                                                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                                            }
                                                            if ((documentSnapshot.getLong("tags.questionIndex")).intValue() <= 9) {
                                                                startActivity(new Intent(SplashActivity.this,PsychQuestionsActivity.class));
                                                            }
                                                        }
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });



                }


            }
        },time);

    }
}