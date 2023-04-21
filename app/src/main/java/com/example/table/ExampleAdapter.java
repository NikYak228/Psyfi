package com.example.table;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import static com.example.table.Utility.addcardtobase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    private ExampleAdapter mAdapter;
    private static final int REQUEST_CODE_DETAILS = 1;


    private Context mContext;

    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;

        public TextView mTextView3;



        private stories.AnimationListener mListener_str;


        private ImageView mImageView;


        public ExampleViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mTextView1 = itemView.findViewById(R.id.textview_line1);


            mImageView = itemView.findViewById(R.id.image_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                            ExampleItem clickedItem = mExampleList.get(position);
                            Map<String, Integer> cardMap = new HashMap<>();
                            cardMap.put(clickedItem.getLine1(), 0);
                            addcardtobase(cardMap);
                            Intent intent = new Intent(v.getContext(), ItemDetailsActivity.class);
                            intent.putExtra("line1", clickedItem.getLine1());
                            intent.putExtra("line2", clickedItem.getLine2());
                            intent.putExtra("img", clickedItem.getImageResourceId());
                            intent.putExtra("Anxiety", clickedItem.getAnxiety());
                            intent.putExtra("Depression", clickedItem.getDepression());
                            intent.putExtra("Eating_disorders", clickedItem.getEating_disorders());
                            intent.putExtra("Substance_abuse", clickedItem.getSubstance_abuse());
                            intent.putExtra("Self_harm", clickedItem.getSelf_harm());
                            intent.putExtra("Attention_Deficit_Hyperactivity_Disorder_ADHD", clickedItem.getAttention_Deficit_Hyperactivity_Disorder_ADHD());
                            intent.putExtra("Obsessive_Compulsive_Disorder_OCD", clickedItem.getObsessive_Compulsive_Disorder_OCD());
                            intent.putExtra("Post_traumatic_stres_disorder_PTSD", clickedItem.getPost_traumatic_stres_disorder_PTSD());
                            intent.putExtra("Social_phobia_or_Social_disorder", clickedItem.getSocial_phobia_or_Social_disorder());
                            v.getContext().startActivity(intent);

                        }

                    }

                }

            });


        }
    }

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleList.get(position);

        holder.mTextView1.setText(currentItem.getLine1());

        holder.mImageView.setImageResource(currentItem.getImageResourceId());

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public ExampleItem getItem(int position) {
        return mExampleList.get(position);
    }

}