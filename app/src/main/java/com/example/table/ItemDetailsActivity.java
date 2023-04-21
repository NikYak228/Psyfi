package com.example.table;

import android.animation.ObjectAnimator;
import static com.example.table.Utility.addcardtobase;
import static com.example.table.Utility.addtobase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ItemDetailsActivity extends AppCompatActivity {

    public int cout = 0;
    private FirebaseFirestore db;
    private ExampleItem mCurrentItem;
    private ArrayList<ExampleItem> mExampleList;
    private int mPosition;
    private int mNumClick;
    private ExampleAdapter mAdapter;

    public int numclick;
    public int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        ImageButton thumbsUpButton = findViewById(R.id.thumbs_up_button);

        Intent intent = getIntent();
        String line1 = intent.getStringExtra("line1");
        String line2 = intent.getStringExtra("line2");
        int imageResource = intent.getIntExtra("img", 0);
        int anxiety = intent.getIntExtra("Anxiety", 0);
        int depression = intent.getIntExtra("Depression", 0);
        int eatingDisorders = intent.getIntExtra("Eating_disorders", 0);
        int substanceAbuse = intent.getIntExtra("Substance_abuse", 0);
        int selfHarm = intent.getIntExtra("Self_harm", 0);
        int adhd = intent.getIntExtra("Attention_Deficit_Hyperactivity_Disorder_ADHD", 0);
        int ocd = intent.getIntExtra("Obsessive_Compulsive_Disorder_OCD", 0);
        int ptsd = intent.getIntExtra("Post_traumatic_stres_disorder_PTSD", 0);
        int socialPhobia = intent.getIntExtra("Social_phobia_or_Social_disorder", 0);


        TextView textViewLine1 = findViewById(R.id.item_line1);
        TextView textViewLine2 = findViewById(R.id.item_line2);
        ImageView imageView = findViewById(R.id.item_image);

        textViewLine1.setText(line1);
        textViewLine2.setText(line2);
        imageView.setImageResource(imageResource);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(line1);
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollection = db.collection("users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        String email = firebaseUser.getEmail();
        assert email != null;
        DocumentReference userRef = usersCollection.document(email);
        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if ((documentSnapshot.getLong("cards." + line1)) != null) {
                            numclick = (documentSnapshot.getLong("cards." + line1)).intValue();

                            if(numclick%2==1){
                                thumbsUpButton.setAlpha(0.5f);
                            }else{
                                thumbsUpButton.setAlpha(1f);
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });



        thumbsUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Integer> cardMap = new HashMap<>();
                cardMap.put(line1, 1);
                addcardtobase(cardMap);

                // Add 1 to the values of the tags in Firestore
                Map<String, Integer> updates = new HashMap<>();
                if(thumbsUpButton.getAlpha()==1f){
                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(thumbsUpButton, "alpha", 1f, 0.5f);
                    alphaAnimator.setDuration(500);
                    alphaAnimator.start();

                    if (anxiety != 0) {
                        updates.put("Anxiety", 1);
                    }

                    if (depression != 0) {
                        updates.put("Depression", 1);
                    }

                    if (eatingDisorders != 0) {
                        updates.put("Eating disorders", 1);
                    }

                    if (substanceAbuse != 0) {
                        updates.put("Substance abuse", 1);
                    }

                    if (selfHarm != 0) {
                        updates.put("Self harm", 1);
                    }

                    if (adhd != 0) {
                        updates.put("Attention Deficit Hyperactivity Disorder (ADHD)", 1);
                    }

                    if (ocd != 0) {
                        updates.put("Obsessive Compulsive Disorder (OCD)", 1);
                    }

                    if (ptsd != 0) {
                        updates.put("Post traumatic stress disorder (PTSD)", 1);
                    }

                    if (socialPhobia != 0) {
                        updates.put("Social phobia or Social anxiety disorder", 1);
                    }
                }else {
                    ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(thumbsUpButton, "alpha", 0.5f, 1f);
                    alphaAnimator.setDuration(500);
                    alphaAnimator.start();
                    if (anxiety != 0) {
                        updates.put("Anxiety", -1);
                    }

                    if (depression != 0) {
                        updates.put("Depression", -1);
                    }

                    if (eatingDisorders != 0) {
                        updates.put("Eating disorders", -1);
                    }

                    if (substanceAbuse != 0) {
                        updates.put("Substance abuse", -1);
                    }

                    if (selfHarm != 0) {
                        updates.put("Self harm", -1);
                    }

                    if (adhd != 0) {
                        updates.put("Attention Deficit Hyperactivity Disorder (ADHD)", -1);
                    }

                    if (ocd != 0) {
                        updates.put("Obsessive Compulsive Disorder (OCD)", -1);
                    }

                    if (ptsd != 0) {
                        updates.put("Post traumatic stress disorder (PTSD)", -1);
                    }

                    if (socialPhobia != 0) {
                        updates.put("Social phobia or Social anxiety disorder", -1);
                    }
                }


                if (!updates.isEmpty()) {
                    Utility.addtobase(updates);
                }
            }
        });



    }

}





