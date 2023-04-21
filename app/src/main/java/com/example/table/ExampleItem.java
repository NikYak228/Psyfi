package com.example.table;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ExampleItem implements Parcelable {
    private String mLine1;
    private String mLine2;
    private String mLine3;
    private int mLayoutId;
    private int mImageResourceId;

    public int Anxiety;

    public int Depression;

    public int Eating_disorders;

    public int Substance_abuse;

    public int Self_harm;

    public int Attention_Deficit_Hyperactivity_Disorder_ADHD;

    public int Obsessive_Compulsive_Disorder_OCD;

    public int Post_traumatic_stres_disorder_PTSD;

    public int Social_phobia_or_Social_disorder;

    public int totalTagValue;

    public int cout;

    public ExampleItem(String name, String percentage, String main, int imageResourceId, int anxiety, int depression, int eating_disorders, int substance_abuse,
                       int self_harm, int attention_Deficit_Hyperactivity_Disorder_ADHD, int obsessive_Compulsive_Disorder_OCD, int post_traumatic_stres_disorder_PTSD,
                       int social_phobia_or_Social_disorder, int TotalTagValue, int numclick) {
        mLine1 = name;
        mLine3 = percentage;
        mLine2 = main;
        Anxiety = anxiety;
        Depression = depression;
        Eating_disorders = eating_disorders;
        Substance_abuse = substance_abuse;
        Self_harm = self_harm;
        Attention_Deficit_Hyperactivity_Disorder_ADHD=attention_Deficit_Hyperactivity_Disorder_ADHD;
        Obsessive_Compulsive_Disorder_OCD = obsessive_Compulsive_Disorder_OCD;
        Post_traumatic_stres_disorder_PTSD = post_traumatic_stres_disorder_PTSD;
        Social_phobia_or_Social_disorder=social_phobia_or_Social_disorder;
        mImageResourceId = imageResourceId;
        totalTagValue=TotalTagValue;
        cout = numclick;
    }

    protected ExampleItem(Parcel in) {
        mLine1 = in.readString();
        mLine2 = in.readString();
        mLine3 = in.readString();
        mLayoutId = in.readInt();
        mImageResourceId = in.readInt();
        Anxiety = in.readInt();
        Depression = in.readInt();
        Eating_disorders = in.readInt();
        Substance_abuse = in.readInt();
        Self_harm = in.readInt();
        Attention_Deficit_Hyperactivity_Disorder_ADHD = in.readInt();
        Obsessive_Compulsive_Disorder_OCD = in.readInt();
        Post_traumatic_stres_disorder_PTSD = in.readInt();
        Social_phobia_or_Social_disorder = in.readInt();
        totalTagValue = in.readInt();
        cout = in.readInt();
    }

    public static final Creator<ExampleItem> CREATOR = new Creator<ExampleItem>() {
        @Override
        public ExampleItem createFromParcel(Parcel in) {
            return new ExampleItem(in);
        }

        @Override
        public ExampleItem[] newArray(int size) {
            return new ExampleItem[size];
        }
    };

    public String getLine1() {
        return mLine1;
    }

    public String getLine2() {
        return mLine2;
    }

    public String getLine3() {
        return mLine3;
    }
    public void setnumclick(int numclick) {
        this.cout = numclick;
    }


    public int getImageResourceId() {
        return mImageResourceId;
    }


    public void setTotalTagValue(int totalTagValueset){
        totalTagValue=totalTagValueset;
    }




    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLine1);
        dest.writeString(mLine2);
        dest.writeInt(mImageResourceId);
        dest.writeInt(Anxiety);
    }

    public int getTotalTagValue() {
        return totalTagValue;
    }
    public int getAnxiety() {
        return Anxiety;
    }

    public int getDepression() {
        return Depression;
    }

    public int getEating_disorders() {
        return Eating_disorders;
    }

    public void setNumclick(int numclick) {
        this.cout = numclick;
    }


    public int getSubstance_abuse() {
        return Substance_abuse;
    }

    public int getSelf_harm() {
        return Self_harm;
    }

    public int getAttention_Deficit_Hyperactivity_Disorder_ADHD() {
        return Attention_Deficit_Hyperactivity_Disorder_ADHD;
    }

    public int getObsessive_Compulsive_Disorder_OCD() {
        return Obsessive_Compulsive_Disorder_OCD;
    }

    public int getPost_traumatic_stres_disorder_PTSD() {
        return Post_traumatic_stres_disorder_PTSD;
    }

    public int getSocial_phobia_or_Social_disorder() {
        return Social_phobia_or_Social_disorder;
    }

    public int getnumclick() {
        return cout;
    }

}




