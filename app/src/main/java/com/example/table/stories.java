package com.example.table;

import static com.example.table.MainActivity.EXTRA_LAYOUT_ID;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class stories extends Fragment implements ExampleAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleList;
    private RecyclerView.LayoutManager mLayoutManager;

    public int perc = 12;

    public int speed = 120;

    private int anxiety;

    private int depression;

    private int eating_disorders;

    private int substance_abuse;

    private int self_harm;

    private int attention_Deficit_Hyperactivity_Disorder_ADHD;

    private int obsessive_Compulsive_Disorder_OCD;

    private int post_traumatic_stres_disorder_PTSD;

    private int social_phobia_or_Social_disorder;

    public int numclick;

    public void HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        initRecyclerView(rootView);


        return rootView;
    }

    private void initRecyclerView(View rootView) {
        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(" \"Побеждая депрессию: методы психотерапии и рекомендации психологов\"", "Line 2", "Депрессия - это серьезное психическое расстройство, которое существенно влияет на качество жизни и настроение человека. Однако, существует множество методов и подходов психотерапии, которые могут помочь победить депрессию и снова наслаждаться жизнью.\n" +
                "\n" +
                "Один из методов психотерапии, который может помочь справиться с депрессией, - это когнитивно-поведенческая терапия (КПТ). КПТ предполагает изменение негативных мыслей и поведенческих реакций, которые могут приводить к ухудшению настроения и углублению депрессии. Пациент обучается новым навыкам и стратегиям, которые помогают ему лучше справляться с эмоциональным дискомфортом.\n" +
                "\n" +
                "Другим эффективным методом психотерапии, используемым при депрессии, является интерперсональная терапия (ИПТ). ИПТ предполагает работу с межличностными конфликтами и проблемами в отношениях с другими людьми, которые могут приводить к ухудшению настроения и депрессии. Пациент обучается новым навыкам взаимодействия и решению конфликтов.\n" +
                "\n" +
                "Также, рекомендуется следить за своим физическим здоровьем. Регулярная физическая активность, здоровое питание и достаточный сон могут значительно улучшить настроение и помочь бороться с депрессией.\n" +
                "\n" +
                "Важно понимать, что депрессия - это патологическое состояние, которое необходимо лечить под наблюдением опытного психотерапевта. Лечение депрессии требует участия специалиста и терпения со стороны пациента.\n" +
                "\n" +
                "Борьба с депрессией может быть сложной задачей, но важно понимать, что это возможно. Существует множество методов психотерапии и рекомендаций от психологов, которые могут помочь победить депрессию и наслаждаться жизнью.", R.drawable.wall, 1, 4, 0, 0, 0, 0 ,0 ,0, 0,0, 0));
        exampleList.add(new ExampleItem("\"Преодоление тревожности: методы борьбы с повседневными и экстремальными ситуациями\"", String.valueOf(perc)+"%", "Беспокойство (Anxiety) является очень распространенной проблемой среди людей, и может приводить к серьезным последствиям, если не управлять ими. Как психотерапевт, я хотел бы поделиться некоторыми методами, которые могут помочь в борьбе с беспокойством.\n" +
                "\n" +
                "Расслабление\n" +
                "Один из наиболее эффективных способов справиться с беспокойством - это расслабление. Дыхательные упражнения, йога, медитация и просто глубокое дыхание могут помочь уменьшить напряжение и тревожность.\n" +
                "\n" +
                "Активность\n" +
                "Другой хороший способ борьбы с беспокойством - это увлечения, которые приводят к полезной активности и отвлекают от проблем. Физические упражнения, игры, занятия танцами, и даже чтение могут помочь вам забыть о беспокойстве.\n" +
                "\n" +
                "Планирование\n" +
                "Часто беспокойство проистекает из неопределенности. Поэтому планирование может помочь в борьбе с беспокойством. Например, составление плана на день или неделю может помочь вам чувствовать себя более уверенно и контролировать свою жизнь.\n" +
                "\n" +
                "Общение\n" +
                "Разговор с кем-то близким или с психотерапевтом может помочь вам расслабиться и преодолеть беспокойство. Вы можете получить поддержку и новый взгляд на проблемы, которые вас беспокоят.\n" +
                "\n" +
                "Изменение мышления\n" +
                "Когда мы думаем негативно, наша тревожность усиливается. Попробуйте изменить свой подход к проблемам и думать позитивно. Остановитесь, подумайте и поищите в своей жизни положительные моменты, которые могут помочь вам преодолеть беспокойство.", R.drawable.wall, 1, 0, 0, 0, 0, 0 ,0 ,0, 0,0, 0));
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        mRecyclerView = rootView.findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mExampleList = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mExampleList);

        mExampleList.setOnItemClickListener(this);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        String email = firebaseUser.getEmail();
        DocumentReference userRef = db.collection("users").document(email); // replace USER_ID with the actual user ID
        userRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if ((documentSnapshot.getLong("tags.Anxiety")) != null) {
                            anxiety=(documentSnapshot.getLong("tags.Anxiety")).intValue();
                            depression=(documentSnapshot.getLong("tags.Depression")).intValue();
                            eating_disorders=(documentSnapshot.getLong("tags.Eating disorders")).intValue();
                            substance_abuse=(documentSnapshot.getLong("tags.Substance abuse")).intValue();
                            self_harm=(documentSnapshot.getLong("tags.Self-harm")).intValue();
                            attention_Deficit_Hyperactivity_Disorder_ADHD=(documentSnapshot.getLong("tags.Attention Deficit Hyperactivity Disorder (ADHD)")).intValue();
                            obsessive_Compulsive_Disorder_OCD=(documentSnapshot.getLong("tags.Obsessive-Compulsive Disorder (OCD)")).intValue();
                            post_traumatic_stres_disorder_PTSD=(documentSnapshot.getLong("tags.Post-traumatic stress disorder (PTSD)")).intValue();
                            social_phobia_or_Social_disorder=(documentSnapshot.getLong("tags.Social phobia or Social anxiety disorder")).intValue();


                            for (ExampleItem exampleItem : exampleList) {
                                int totalTagValue = 0;
                                totalTagValue+=exampleItem.Anxiety*anxiety;
                                totalTagValue+=exampleItem.Depression*depression;
                                totalTagValue+=exampleItem.Eating_disorders*eating_disorders;
                                totalTagValue+=exampleItem.Substance_abuse*substance_abuse;
                                totalTagValue+=exampleItem.Self_harm*self_harm;
                                totalTagValue+=exampleItem.Attention_Deficit_Hyperactivity_Disorder_ADHD*attention_Deficit_Hyperactivity_Disorder_ADHD;
                                totalTagValue+=exampleItem.Obsessive_Compulsive_Disorder_OCD*obsessive_Compulsive_Disorder_OCD;
                                totalTagValue+=exampleItem.Post_traumatic_stres_disorder_PTSD*post_traumatic_stres_disorder_PTSD;
                                totalTagValue+=exampleItem.Social_phobia_or_Social_disorder*social_phobia_or_Social_disorder;
                                if ((documentSnapshot.getLong("cards."+exampleItem.getLine1())) == null) {
                                    exampleItem.setTotalTagValue(totalTagValue);
                                }else{
                                    exampleItem.setTotalTagValue(-1);
                                }
                            }
                            Collections.sort(exampleList, new Comparator<ExampleItem>() {
                                @Override
                                public int compare(ExampleItem o1, ExampleItem o2) {
                                    return o2.getTotalTagValue() - o1.getTotalTagValue();
                                }
                            });
                            mExampleList.notifyDataSetChanged();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        mExampleList.notifyDataSetChanged();

    }


    @Override
    public void onItemClick(int position) {
        ExampleItem clickedItem = mExampleList.getItem(position);

        ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                mRecyclerView.findViewHolderForAdapterPosition(position).itemView,
                PropertyValuesHolder.ofFloat(View.SCALE_X, 0.95f),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.95f)
        );
        scaleDown.setDuration(speed/2);
        scaleDown.setInterpolator(new DecelerateInterpolator());
        scaleDown.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                ObjectAnimator scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                        mRecyclerView.findViewHolderForAdapterPosition(position).itemView,
                        PropertyValuesHolder.ofFloat(View.SCALE_X, 1f),
                        PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f)
                );
                scaleUp.setDuration(speed);
                scaleUp.setInterpolator(new AccelerateInterpolator());
                scaleUp.start();


            }
        });
        scaleDown.start();

    }

    public interface AnimationListener {
        void onAnimationEnd();
    }

}