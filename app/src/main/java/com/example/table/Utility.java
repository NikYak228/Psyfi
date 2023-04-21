package com.example.table;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Utility {
    static void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
    static void addtobase(int anxiety, int depression, int eating_disorders, int substance_abuse,
                          int self_harm, int attention_Deficit_Hyperactivity_Disorder_ADHD, int obsessive_Compulsive_Disorder_OCD, int post_traumatic_stres_disorder_PTSD,
                          int social_phobia_or_Social_disorder){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollection = db.collection("users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        String email = firebaseUser.getEmail();
        assert email != null;
        DocumentReference userRef = usersCollection.document(email);
        Map<String, Object> data = new HashMap<>();
        data.put("tags.Anxiety", FieldValue.increment(anxiety));
        data.put("tags.Depression", FieldValue.increment(depression));
        data.put("tags.Eating disorders", FieldValue.increment(eating_disorders));
        data.put("tags.Substance abuse", FieldValue.increment(substance_abuse));
        data.put("tags.Self-harm", FieldValue.increment(self_harm));
        data.put("tags.Attention Deficit Hyperactivity Disorder (ADHD)", FieldValue.increment(attention_Deficit_Hyperactivity_Disorder_ADHD));
        data.put("tags.Obsessive-Compulsive Disorder (OCD)", FieldValue.increment(obsessive_Compulsive_Disorder_OCD));
        data.put("tags.Post-traumatic stress disorder (PTSD)", FieldValue.increment(post_traumatic_stres_disorder_PTSD));
        data.put("tags.Social phobia or Social anxiety disorder", FieldValue.increment(social_phobia_or_Social_disorder));

        userRef.update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
    static void addtobase(Map<String, Integer> diseaseMap){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollection = db.collection("users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        String email = firebaseUser.getEmail();
        assert email != null;
        DocumentReference userRef = usersCollection.document(email);
        Map<String, Object> data = new HashMap<>();

        for (Map.Entry<String, Integer> entry : diseaseMap.entrySet()) {
            String disease = entry.getKey();
            int value = entry.getValue();
            data.put("tags." + disease, FieldValue.increment(value));
        }

        userRef.update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
    static void addcardtobase(Map<String, Integer> cardsMap){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollection = db.collection("users");
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        String email = firebaseUser.getEmail();
        assert email != null;
        DocumentReference userRef = usersCollection.document(email);
        Map<String, Object> data = new HashMap<>();

        for (Map.Entry<String, Integer> entry : cardsMap.entrySet()) {
            String disease = entry.getKey();
            int value = entry.getValue();
            data.put("cards." + disease, FieldValue.increment(value));
        }

        userRef.update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}
