package com.example.table;
import static com.example.table.Utility.addtobase;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PsychQuestionsActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView questionText;
    private int questionIndex = 0;

    public boolean check = false;


    private String[] questions = {
            "Вы часто беспокоитесь и переживаете о разных событиях в жизни?",
            "Вы чувствуете, что потеряли интерес к повседневным делам и ничто не доставляет вам удовольствие?",
            "Вы слишком много думаете о своем весе и/или питании?",
            "Вы регулярно употребляете алкоголь или наркотики?",
            "Вы когда-либо наносили себе физический вред?",
            "Вы часто меняете настроение, от сильного подъема на сильный спад?",
            "Вы испытываете трудности в концентрации на уроке или при выполнении задач?",
            "Вы испытываете навязчивые мысли или действия, которые не уходят из головы?",
            "Вы переживаете флэшбэки или кошмары после травматического события?",
            "Вы чувствуете сильный страх и тревогу в социальных ситуациях или при общении с людьми?",
            "Вы чувствуете себя оторванным или отдаленным от своих друзей или членов семьи?",
            "У вас есть проблемы со сном или засыпанием по ночам?",
            "Вы чувствуете себя подавленным или перегруженным стрессом или давлением?",
            "Вы испытывали внезапные, необъяснимые изменения настроения или поведения?"
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(questionIndex<=13|| !check) {
            setContentView(R.layout.activity_psych_questions);

            questionText = findViewById(R.id.question_text);


            FirebaseFirestore db = FirebaseFirestore.getInstance();
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String uid = firebaseUser.getUid();
            String email = firebaseUser.getEmail();
            DocumentReference userRef = db.collection("users").document(email); // replace USER_ID with the actual user ID
            userRef.get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if ((documentSnapshot.getLong("tags.questionIndex")) != null) {
                                questionIndex = (documentSnapshot.getLong("tags.questionIndex")).intValue();
                                if (questionIndex > 9) {
                                    startActivity(new Intent(PsychQuestionsActivity.this, MainActivity.class));
                                }
                                if ((documentSnapshot.getLong("tags.questionIndex")).intValue() <= 9) {
                                    questionText.setText(questions[questionIndex]);
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

            questionText.setText(questions[questionIndex]);
            CardView cardViewYes = findViewById(R.id.card_view_yes);

            cardViewYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = firebaseUser.getUid();
                    String email = firebaseUser.getEmail();
                    DocumentReference userRef = db.collection("users").document(email); // replace USER_ID with the actual user ID
                    userRef.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if ((documentSnapshot.getLong("tags.questionIndex")) != null) {
                                        questionIndex = (documentSnapshot.getLong("tags.questionIndex")).intValue();
                                        if (questionIndex > 13) {
                                            startActivity(new Intent(PsychQuestionsActivity.this, MainActivity.class));
                                        }
                                        if ((documentSnapshot.getLong("tags.questionIndex")).intValue() <= 9) {
                                            questionText.setText(questions[questionIndex]);
                                        }
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });

                    switch (questionIndex) {
                        case 0:
                        case 1:
                        case 11:
                            // Anxiety
                            Map<String, Integer> anxietyMap = new HashMap<>();
                            anxietyMap.put("Anxiety", 1);
                            addtobase(anxietyMap);
                            break;
                        case 2:
                        case 9:
                            // Eating disorders
                            Map<String, Integer> eatingDisordersMap = new HashMap<>();
                            eatingDisordersMap.put("Eating disorders", 1);
                            addtobase(eatingDisordersMap);
                            break;
                        case 3:
                            // Substance abuse
                            Map<String, Integer> substanceAbuseMap = new HashMap<>();
                            substanceAbuseMap.put("Substance abuse", 1);
                            addtobase(substanceAbuseMap);
                            break;
                        case 4:
                            // Self-harm
                            Map<String, Integer> selfHarmMap = new HashMap<>();
                            selfHarmMap.put("Self-harm", 1);
                            addtobase(selfHarmMap);
                            break;
                        case 5:
                            // Bipolar disorder
                            Map<String, Integer> bipolarMap = new HashMap<>();
                            bipolarMap.put("Bipolar disorder", 1);
                            addtobase(bipolarMap);
                            break;
                        case 6:
                        case 13:
                            // ADHD
                            Map<String, Integer> adhdMap = new HashMap<>();
                            adhdMap.put("Attention Deficit Hyperactivity Disorder ADHD", 1);
                            addtobase(adhdMap);
                            break;
                        case 7:
                            // OCD
                            Map<String, Integer> ocdMap = new HashMap<>();
                            ocdMap.put("Obsessive Compulsive Disorder OCD", 1);
                            addtobase(ocdMap);
                            break;
                        case 12:
                            Map<String, Integer> deprMap = new HashMap<>();
                            deprMap.put("Depression", 1);
                            addtobase(deprMap);
                        case 8:
                            // PTSD
                            Map<String, Integer> ptsdMap = new HashMap<>();
                            ptsdMap.put("Post traumatic stres disorder PTSD", 1);
                            addtobase(ptsdMap);
                            break;
                        case (14):
                        case (15):
                            startActivity(new Intent(PsychQuestionsActivity.this, MainActivity.class));
                        case 10:
                            // Social phobia or Social anxiety disorder
                            Map<String, Integer> socialPhobiaMap = new HashMap<>();
                            socialPhobiaMap.put("Social phobia or Social anxiety disorder", 1);
                            addtobase(socialPhobiaMap);
                            break;
                    }

                    questionIndex++;
                    Map<String, Integer> diseaseMap0 = new HashMap<>();
                    diseaseMap0.put("questionIndex", 1);
                    addtobase(diseaseMap0);
                    questionText = findViewById(R.id.question_text);
                    if (questionIndex <= 13) {
                        questionText.setText(questions[questionIndex]);
                    }
                }

            });


            CardView cardViewNo = findViewById(R.id.card_view_no);
            cardViewNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    String uid = firebaseUser.getUid();
                    String email = firebaseUser.getEmail();
                    DocumentReference userRef = db.collection("users").document(email); // replace USER_ID with the actual user ID
                    userRef.get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if ((documentSnapshot.getLong("tags.questionIndex")) != null) {
                                        questionIndex = (documentSnapshot.getLong("tags.questionIndex")).intValue();
                                        if (questionIndex > 13) {
                                            startActivity(new Intent(PsychQuestionsActivity.this, MainActivity.class));
                                        }
                                        if ((documentSnapshot.getLong("tags.questionIndex")).intValue() <= 9) {
                                            questionText.setText(questions[questionIndex]);
                                        }
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                    questionIndex++;
                    questionText = findViewById(R.id.question_text);
                    if (questionIndex <= 9) {
                        questionText.setText(questions[questionIndex]);
                    }
                    if (questionIndex > 9) {
                        startActivity(new Intent(PsychQuestionsActivity.this, MainActivity.class));
                    }
                    Map<String, Integer> diseaseMap0 = new HashMap<>();
                    diseaseMap0.put("questionIndex", 1);
                    addtobase(diseaseMap0);
                    // user answered no to the question
                    // add code to save the answer
                    // move to the next question or show the result
                }
            });
        }else{
            startActivity(new Intent(PsychQuestionsActivity.this,MainActivity.class));
        }
        if(questionIndex>9){
            startActivity(new Intent(PsychQuestionsActivity.this,MainActivity.class));
            check=true;
        }


    }
}

