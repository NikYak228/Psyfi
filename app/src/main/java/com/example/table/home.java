package com.example.table;

import static android.app.Activity.RESULT_OK;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class home extends Fragment implements ExampleAdapter.OnItemClickListener {

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

        exampleList.add(new ExampleItem("Преодоление проблемы злоупотребления веществами", String.valueOf(perc)+"%", "Сегодня мы поговорим о проблеме злоупотребления веществами, таких как наркотики или алкоголь. Эти вещества могут оказывать отрицательное влияние на наше здоровье, отношения и общую жизнь. Если ты или кто-то из твоих близких столкнулся с проблемой злоупотребления веществами, важно знать, как справиться с этой проблемой.\n" +
                "\n" +
                "Вот несколько советов, которые могут помочь в преодолении проблемы злоупотребления веществами:\n" +
                "\n" +
                "Признай проблему. Важно осознать, что у тебя есть проблема с злоупотреблением веществами. Будь откровенным с собой и готов признать необходимость изменений.\n" +
                "\n" +
                "Обратись за помощью. Проблемы с злоупотреблением веществами требуют профессиональной помощи. Обратись к врачу, психологу или специалисту по зависимости, чтобы получить поддержку и руководство по лечению.\n" +
                "\n" +
                "Найди здоровые замены. Ищи замены для злоупотребления веществами, которые могут приносить тебе радость и удовлетворение. Это может быть занятие спортом, искусством, музыкой или другими хобби, которые помогут тебе испытывать позитивные эмоции.\n" +
                "\n" +
                "Развивай навыки справления со стрессом. Часто злоупотребление веществами связано со стремлением справиться со стрессом или негативными эмоциями. Ищи альтернативные стратегии справления со стрессом, такие как медитация, йога, дыхательные упражнения или общение с близкими людьми.\n" +
                "\n" +
                "Получай поддержку от окружающих. Обратись к людям, которым доверяешь, и попроси их поддержки. Расскажи им о своих намерениях изменить свою жизнь и попроси их быть рядом с тобой в этом процессе.\n" +
                "\n" +
                "Помни, что преодоление проблемы злоупотребления веществами может быть сложным, но это возможно. Придерживайся профессионального лечения и окружай себя поддерживающей средой. Ты заслуживаешь здоровой и счастливой жизни!", R.drawable.wall, 3, 0, 0, 0, 1, 0 ,0 ,1, 1,0, 0));
        exampleList.add(new ExampleItem("Как преодолеть расстройства пищевого поведения", String.valueOf(perc)+"%", "Сегодня мы поговорим о расстройствах пищевого поведения, таких как анорексия, булимия и компульсивное переедание. Эти проблемы могут повлиять на наше физическое и эмоциональное здоровье. Если у тебя или у кого-то из твоих близких есть подозрения на расстройства пищевого поведения, важно знать, как справиться с этими проблемами.\n" +
                "\n" +
                "Вот несколько советов, которые могут помочь тебе преодолеть расстройства пищевого поведения:\n" +
                "\n" +
                "Обратись за помощью. Расстройства пищевого поведения требуют профессионального вмешательства. Обратись к врачу, психологу или диетологу, чтобы получить необходимую поддержку и лечение.\n" +
                "\n" +
                "Изучи свои эмоции. Часто расстройства пищевого поведения связаны с эмоциональным состоянием. Постарайся понять, какие эмоции могут приводить к неправильному пищевому поведению. Ведение дневника эмоций может помочь тебе разобраться в своих чувствах.\n" +
                "\n" +
                "Развивай здоровые стратегии справления. Ищи альтернативные способы справиться со стрессом и негативными эмоциями. Это могут быть физические упражнения, медитация, творческие занятия или разговор с друзьями. Найди то, что помогает тебе расслабиться и контролировать свои эмоции без прибегания к неправильному пищевому поведению.\n" +
                "\n" +
                "Поддерживай здоровый образ жизни. Регулярное физическое упражнение и правильное питание могут помочь улучшить самочувствие и установить здоровые привычки. Обратись к диетологу, чтобы разработать план питания, отвечающий твоим потребностям.\n" +
                "\n" +
                "Ищи поддержку в окружении. Расскажи близким людям о своих борьбах и попроси их поддержки. Разделение своих переживаний с любящими людьми может помочь тебе чувствовать себя менее одиноким и более мотивированным для лечения.\n" +
                "\n" +
                "Помни, что преодоление расстройств пищевого поведения требует времени и терпения. Будь терпеливым с самим собой и доверься профессионалам, которые помогут тебе на этом пути.", R.drawable.wall, 2, 1, 6, 0, 0, 0 ,0 ,0, 0,0, 0));
        exampleList.add(new ExampleItem("Повышенная тревожность и депрессия", String.valueOf(perc)+"%", "Сегодня я хочу поговорить с тобой о повышенной тревожности и депрессии. Иногда мы можем ощущать грусть, потерю интереса к жизни и непреодолимую усталость. Если ты часто испытываешь эти чувства, то возможно, у тебя есть депрессия.\n" +
                "\n" +
                "Депрессия – это серьезное состояние, которое может затронуть различные аспекты твоей жизни. Она может вызывать чувство пустоты, отчаяния и лишать энергии для повседневных дел. Если у тебя есть подозрения на депрессию, важно обратиться за помощью.\n" +
                "\n" +
                "Вот несколько советов, которые могут помочь тебе справиться с повышенной тревожностью и депрессией:\n" +
                "\n" +
                "Обратись к профессионалу. Психолог или психиатр помогут тебе разобраться в твоих чувствах и найти эффективные способы лечения. Не бойся обратиться за помощью, ведь это совершенно нормально.\n" +
                "\n" +
                "Регулярно занимайся физической активностью. Физическая активность помогает выделять эндорфины – гормоны счастья, которые улучшают наше настроение и снижают тревожность. Попробуй заниматься спортом, танцами или йогой.\n" +
                "\n" +
                "Общайся с близкими людьми. Расскажи своим друзьям или членам семьи о своих чувствах. Иметь поддержку окружающих людей очень важно в борьбе с депрессией.\n" +
                "\n" +
                "Заботься о себе. Помни, что ты заслуживаешь заботы и любви. Удели время для самоухода: занимайся хобби, читай интересные книги, слушай любимую музыку или просто проведи время себе на радость.\n" +
                "\n" +
                "Установи режим сна и питания. Здоровый сон и правильное питание имеют большое значение для нашего физического и эмоционального благополучия. Постарайся спать достаточное количество часов и употреблять питательные продукты.\n" +
                "\n" +
                "Не забывай, что депрессия – это состояние, которое можно преодолеть. Следуй советам и помни, что ты не одинок в своей борьбе. Обратись за помощью и верь в свою силу.", R.drawable.wall, 4, 3, 0, 0, 0, 0 ,0 ,0, 0,0, 0));
        exampleList.add(new ExampleItem("Как преодолеть тревогу", String.valueOf(perc)+"%", "Мы все время чувствуемся немного беспокойными или тревожными, и это совершенно нормально. Но если тревога становится слишком сильной и начинает мешать вашей повседневной жизни, то может быть полезно найти способы справиться с ней.\n" +
                "\n" +
                "Расслабление и глубокое дыхание:\n" +
                "Попробуйте различные техники расслабления, такие как медитация, глубокое дыхание или йога. Эти методы помогут вам снизить уровень тревоги и успокоиться.\n" +
                "\n" +
                "Установите приоритеты:\n" +
                "Иногда тревога возникает из-за ощущения, что у вас слишком много дел. Попробуйте установить приоритеты и разделить задачи на более управляемые части. Это поможет вам чувствовать себя более организованными и уменьшит тревогу.\n" +
                "\n" +
                "Поддержка близких людей:\n" +
                "Расскажите о своей тревоге доверенному другу, члену семьи или учителю. Иметь кого-то, кто вас поддерживает, может сделать большую разницу. Вы не должны справляться с этим один на один.\n" +
                "\n" +
                "Здоровый образ жизни:\n" +
                "Правильное питание, регулярное физическое упражнение и достаточный сон имеют огромное значение для вашего физического и эмоционального благополучия. Уделите время заботе о своем теле, и вы заметите, что тревога станет менее интенсивной.\n" +
                "\n" +
                "Избегайте излишней информации:\n" +
                "Иногда постоянный поток новостей и социальных медиа может усиливать нашу тревогу. Попробуйте ограничить время, проводимое в интернете, и сконцентрируйтесь на позитивных вещах в вашей жизни.\n" +
                "\n" +
                "Узнайте о тревоге:\n" +
                "Познакомьтесь с основными принципами тревоги, чтобы лучше понять свои эмоции. Изучение этого состояния может помочь вам контролировать его и справиться с ним.\n" +
                "\n" +
                "Важно помнить, что каждый человек уникален, и то, что может сработать для одного, не обязательно подойдет другому. Если ваша тревога продолжает беспокоить вас и мешать вашей жизни, обратитесь за помощью к психологу или психиатру. Они смогут предложить вам индивидуальный подход и поддержку.\n" +
                "\n" +
                "Не забывайте, что вы не одни в своих борьбах, и всегда есть надежда на улучшение.", R.drawable.wall, 8, 0, 0, 0, 0, 1 ,0 ,0, 0,0, 0));
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
                "Когда мы думаем негативно, наша тревожность усиливается. Попробуйте изменить свой подход к проблемам и думать позитивно. Остановитесь, подумайте и поищите в своей жизни положительные моменты, которые могут помочь вам преодолеть беспокойство.", R.drawable.wall, 1, 0, 0, 0, 0, 0 ,0 ,0, 0,0, 0));FirebaseFirestore db = FirebaseFirestore.getInstance();
        exampleList.add(new ExampleItem("Преодоление социальной фобии: советы психотерапевта", String.valueOf(perc)+"%", "Социальная фобия, также известная как социальное расстройство, является распространенным психологическим заболеванием, которое влияет на миллионы людей по всему миру. Это состояние характеризуется чрезмерным страхом оценки окружающих, страхом общения с людьми, особенно незнакомыми, и переживанием сильного дискомфорта в обществе. Несмотря на то, что социальная фобия может вызывать серьезные проблемы в повседневной жизни, существуют многочисленные методы, которые могут помочь в ее преодолении.\n" +
                "\n" +
                "Психотерапевты рекомендуют несколько методов для борьбы с социальной фобией. Первый шаг - осознание того, что вы страдаете от этого состояния и что вы не одиноки. Многие люди страдают от социальной фобии, и поиск поддержки может помочь вам почувствовать себя лучше и получить поддержку в борьбе с этой проблемой. Поэтому важно не стесняться и обратиться к психотерапевту, другу или близкому человеку.\n" +
                "\n" +
                "Одним из основных методов борьбы с социальной фобией является систематическая десенситизация. Этот метод заключается в том, что пациент постепенно выстраивает контакт с ситуациями, которые вызывают у него беспокойство и тревогу, и привыкает к ним. Этот метод помогает пациентам постепенно преодолевать свои страхи и тревоги, начиная с менее страшных ситуаций и переходя к более сложным сценариям.", R.drawable.wall, 1, 1, 0, 0, 0, 0 ,0 ,0, 5,0, 0));

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