package com.example.table;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String uid;
    private String email;
    private Map<String, Integer> tags;

    public User() {}

    public User(String uid, String email) {
        this.uid = uid;
        this.email = email;
        this.tags = new HashMap<>();
        this.tags.put("Anxiety", 0);
        this.tags.put("Depression", 0);
        this.tags.put("Eating disorders", 0);
        this.tags.put("Substance abuse", 0);
        this.tags.put("Self-harm", 0);
        this.tags.put("Attention Deficit Hyperactivity Disorder (ADHD)", 0);
        this.tags.put("Obsessive-Compulsive Disorder (OCD)", 0);
        this.tags.put("Post-traumatic stress disorder (PTSD)", 0);
        this.tags.put("Social phobia or Social anxiety disorder", 0);
        this.tags.put("questionIndex", 0);

        // add more tags here as needed
    }

    public String getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Integer> getTags() {
        return tags;
    }

    public void setTags(Map<String, Integer> tags) {
        this.tags = tags;
    }
}
