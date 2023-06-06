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
        exampleList.add(new ExampleItem("\"Покорение Внутреннего Хаоса", String.valueOf(perc)+"%", "С самого детства я замечал, что мне сложно сосредоточиться на одной задаче. Мои мысли рассеивались, и я испытывал постоянное чувство беспокойства. Мне было трудно сидеть на месте и сосредоточиться на учебе или выполнении заданий. Многие люди не понимали, почему я так неспокоен и не могу сосредоточиться, и это только усугубляло мои проблемы.\n" +
                "\n" +
                "Однако, со временем я понял, что у меня есть сила преодолеть свое расстройство внимания с гиперактивностью. Вот несколько советов, которые помогли мне:\n" +
                "\n" +
                "Образование: Я начал изучать свое расстройство и узнавать больше о нем. Это помогло мне понять, что ADHD - это не приговор, а просто часть моей индивидуальности. Я узнал о различных стратегиях управления временем и организации, которые могут помочь мне лучше сосредоточиться.\n" +
                "\n" +
                "Планирование: Я стал разрабатывать детальные планы и расписания для своих задач. Это помогло мне структурировать свое время и сосредоточиться на конкретных заданиях. Я использовал различные методы, такие как to-do списки, помощь от родителей или наставников, чтобы помочь мне следовать расписанию.\n" +
                "\n" +
                "Физическая активность: Я обнаружил, что физическая активность помогает мне снять излишнюю энергию и улучшает мою способность сосредоточиться. Я начал заниматься спортом, делать упражнения или даже просто прогуливаться на свежем воздухе. Это не только улучшило мое физическое состояние, но и помогло мне успокоиться и лучше сосредоточиться.\n" +
                "\n" +
                "Поддержка: Я обратился к близким людям, таким как семья и друзья, и попросил их поддержку. Они стали моей опорой, понимали мои трудности и помогали мне, когда я испытывал трудности с сосредоточенностью. Важно окружить себя людьми, которые понимают и поддерживают вас.\n" +
                "\n" +
                "Самоакцептация: Я научился принимать себя таким, какой я есть, со всеми моими особенностями и трудностями. Я понял, что ADHD не определяет мое полное бытие и что у меня есть множество других качеств, которые делают меня особенным.\n" +
                "\n" +
                "Друзья, помните, что расстройство внимания с гиперактивностью - это часть вас, но оно не определяет вас как личность. Со временем и усилиями вы можете научиться управлять своим вниманием и достичь успеха во многих аспектах жизни.\n" +
                "\n" +
                "Не забывайте, что вы не одиноки в своей борьбе, и всегда есть люди, готовые поддержать вас. Поверьте в себя и свои способности. Вместе мы можем преодолеть любые трудности и создать яркое будущее для себя.", R.drawable.deprpsy, 2, 1, 0, 0, 0, 0 ,0 ,0, 7,0, 0));
        exampleList.add(new ExampleItem("Преодоление Социальной Фобии: Мой Путь к Самоуверенности", String.valueOf(perc)+"%", "Привет, друзья! Сегодня я хотел бы рассказать вам о моем личном опыте с социальной фобией и поделиться с вами некоторыми советами о том, как преодолеть эту трудность.\n" +
                "\n" +
                "Когда я был подростком, социальная фобия стала серьезной проблемой для меня. Я постоянно чувствовал тревогу и боялся вступать во взаимодействие с другими людьми. Мне было трудно выражать свои мысли и идеи из-за страха оценки окружающих. Это сильно влияло на мою самооценку и повседневную жизнь.\n" +
                "\n" +
                "Однако, со временем я понял, что я не одинок в своей борьбе, и что существуют способы преодолеть социальную фобию. Вот несколько советов, которые помогли мне:\n" +
                "\n" +
                "Понимание: Первым шагом к преодолению социальной фобии было осознание того, что это всего лишь чувство, которое можно контролировать. Я научился распознавать свои тревожные мысли и анализировать их логически.\n" +
                "\n" +
                "Общение: Я начал активно искать возможности для социального взаимодействия. Начал с небольших шагов, например, участвовал в классных проектах или встречался с друзьями в небольших группах. Постепенно, я стал более комфортным в общении с другими людьми.\n" +
                "\n" +
                "Поддержка: Очень важно иметь поддержку окружающих. Я рассказал о своих боязнях и чувствах доверенным друзьям и семье, и они были рядом, чтобы поддержать меня. Иногда просто знание того, что ты не один в борьбе, может сделать большую разницу.\n" +
                "\n" +
                "Постепенное расширение зоны комфорта: Я старался выходить из зоны комфорта и делать то, что вызывало у меня страх. Это могло быть участие в публичных выступлениях или просто вступление в разговор с новыми людьми. Постепенно, я осознал, что мои страхи были преувеличены, и что я способен преодолеть их.\n" +
                "\n" +
                "Позитивное мышление: Я осознал, что мои мысли имеют огромное влияние на мое самочувствие. Постепенно я заменил негативные мысли позитивными. Я начал поверить в себя и свои способности. Постепенно, моя самоуверенность начала расти.\n" +
                "\n" +
                "Запомните, друзья, преодоление социальной фобии - это долгий процесс, который требует времени и терпения. Но я уверен, что каждый из вас способен преодолеть это и достичь своих целей. Вы не одиноки в своей борьбе, и всегда можно найти поддержку вокруг себя.\n" +
                "\n" +
                "Я хочу пожелать вам удачи в вашем пути к преодолению социальной фобии. Помните, что вы сильны и способны преодолеть все трудности, с которыми сталкиваетесь. Вы заслуживаете быть счастливыми и свободными от страха.\n" +
                "\n" +
                "Не позволяйте социальной фобии ограничивать вашу жизнь. Вы достойны самых лучших вещей, которые мир может вам предложить.", R.drawable.wall, 2, 1, 0, 0, 0, 0 ,0 ,0, 7,0, 0));
        exampleList.add(new ExampleItem( "Из темноты: как я преодолел депрессию и что помогло мне вернуться к жизни", "Line 2", "Депрессия - это серьезное психическое расстройство, которое может сильно повлиять на качество жизни человека. Я сам столкнулся с этой проблемой, и хочу поделиться своим опытом того, как я справился с этим состоянием. В этой статье я расскажу о методах, которые мне помогли преодолеть депрессию и снова наслаждаться жизнью.\n" +
                "\n" +
                "Тело статьи:\n" +
                "\n" +
                "Понимание депрессии: Я понял, что депрессия - это нечто большее, чем просто плохое настроение. Это состояние, которое влияет на настроение, энергию и мышление человека. Признаки депрессии могут включать в себя: постоянную усталость, потерю интереса к жизни, чувство беспомощности и безнадежности.\n" +
                "\n" +
                "Поиск помощи: Когда я осознал, что мои проблемы выходят за рамки обычного стресса, я решил обратиться за помощью. Я посетил своего врача, который рекомендовал мне обратиться к психотерапевту. Вместе с ним я начал работать над своим состоянием и нашел новые способы решения проблем.\n" +
                "\n" +
                "Терапия: Терапия стала для меня очень важной частью процесса лечения. Я работал с психотерапевтом над своими мыслями, чувствами и поведением. Мы использовали различные методы, такие как когнитивно-поведенческая терапия, медитация и групповые сессии.\n" +
                "\n" +
                "Изменение образа жизни: Кроме терапии, я начал вносить изменения в свой образ жизни. Я начал заниматься спортом, правильно питаться и улучшать качество своего сна. Я также обратил внимание на свои отношения с окружающими людьми и нашел новые способы общения и укрепления связей с друзьями и близкими.\n" +
                "\n" +
                "Поддержка и понимание: Важным фактором в моем процессе лечения была поддержка и понимание", R.drawable.clayanx, 1, 4, 0, 0, 0, 0 ,0 ,0, 0,0, 0));
        exampleList.add(new ExampleItem( "Пережить тревожность: история человека и методы борьбы", String.valueOf(perc)+"%", "Я всегда был человеком, который переживал многое. Но никогда не думал, что моя тревога может выйти из-под контроля и превратиться в болезнь. Тем не менее, это и произошло. Я начал ощущать постоянное беспокойство, неуверенность и боязнь перед самыми обыденными вещами. Моя тревожность стала мешать мне жить обычной жизнью.\n" +
                "\n" +
                "Я решил обратиться к психотерапевту, и это было одним из лучших решений в моей жизни. Он научил меня, как управлять своей тревогой, и я начал замечать, как моя жизнь становится лучше. Я хочу поделиться с вами несколькими методами, которые помогли мне преодолеть свою тревожность.\n" +
                "\n" +
                "Остановитесь и задумайтесь. Когда я начинал чувствовать беспокойство, я старался понять, что именно вызывает это чувство. Я задавал себе вопросы, например, \"Почему я чувствую себя таким беспокойным?\" или \"Что именно меня пугает?\". Это помогло мне разобраться в своих эмоциях и понять, как мне с ними справляться.\n" +
                "\n" +
                "Принимайте свои чувства. Я понял, что попытки скрыть свою тревогу или подавить ее только усугубляют проблему. Лучше всего принять свои эмоции, признать их и попытаться справиться с ними.\n" +
                "\n" +
                "Попробуйте медитацию. Медитация помогает мне сосредоточиться на моих чувствах и успокоиться. Я начинаю сидеть в тихом месте и сосредотачиваться на своем дыхании. Это помогает мне успокоиться и справиться с моей тревогой.\n" +
                "\n" +
                "Найдите хобби. Найдите занятие, которое будет приносить вам удовольствие и поможет отвлечься от своих проблем. Для меня это было чтение книг и занятие спортом.", R.drawable.wall, 1, 0, 0, 0, 0, 0 ,0 ,0, 0,0, 0));

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