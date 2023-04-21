package com.example.table;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {
    private static final String ARG_EXAMPLE_ITEM = "example_item";
    private ExampleItem mExampleItem;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(ExampleItem exampleItem) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_EXAMPLE_ITEM, (Parcelable) exampleItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExampleItem = getArguments().getParcelable(ARG_EXAMPLE_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView textView = view.findViewById(R.id.textview_detail_title);
        if (mExampleItem != null) {
            textView.setText(mExampleItem.getLine1());
        }
        return view;
    }
}

