package at.ac.univie.hci.mealplan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;


public class DailyFragment extends Fragment {
    private boolean isVisible = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //------------------------Breakfast Layouts--------------------------------------
        View v = inflater.inflate(R.layout.fragment_daily, container, false);

        LinearLayout contentLayout1 = v.findViewById(R.id.linlayout1);
        TableLayout tableLayout1 = v.findViewById(R.id.table1);
        TextView textView11 = v.findViewById(R.id.dish1TextView1);
        TextView textView12 = v.findViewById(R.id.dish1DirectionsTextView);

        contentLayout1.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        tableLayout1.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        textView11.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        textView12.setVisibility(isVisible ? View.VISIBLE : View.GONE);

        Button showMoreButton1 = v.findViewById(R.id.showMoreButton1);

        showMoreButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isVisible = !isVisible;
                contentLayout1.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                tableLayout1.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                textView11.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                textView12.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                showMoreButton1.setText(isVisible ? "Show Less" : "Show More");
            }
        });

        //------------------------Lunch Layouts--------------------------------------
        LinearLayout contentLayout2 = v.findViewById(R.id.linlayout2);
        TableLayout tableLayout2 = v.findViewById(R.id.table2);
        TextView textView21 = v.findViewById(R.id.dish1TextView2);
        TextView textView22 = v.findViewById(R.id.dish2DirectionsTextView);

        contentLayout2.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        tableLayout2.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        textView21.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        textView22.setVisibility(isVisible ? View.VISIBLE : View.GONE);

        Button showMoreButton2 = v.findViewById(R.id.showMoreButton2);
        showMoreButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isVisible = !isVisible;
                contentLayout2.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                tableLayout2.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                textView21.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                textView22.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                showMoreButton2.setText(isVisible ? "Show Less" : "Show More");
            }
        });

        //------------------------Dinner Layouts--------------------------------------
        LinearLayout contentLayout3 = v.findViewById(R.id.linlayout3);
        TableLayout tableLayout3 = v.findViewById(R.id.table3);
        TextView textView31 = v.findViewById(R.id.dish3DirectionsTextView);
        TextView textView32 = v.findViewById(R.id.dish3TextView1);

        contentLayout3.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        tableLayout3.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        textView31.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        textView32.setVisibility(isVisible ? View.VISIBLE : View.GONE);

        Button showMoreButton3 = v.findViewById(R.id.showMoreButton3);
        showMoreButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isVisible = !isVisible;
                contentLayout3.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                tableLayout3.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                textView31.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                textView32.setVisibility(isVisible ? View.VISIBLE : View.GONE);
                showMoreButton3.setText(isVisible ? "Show Less" : "Show More");
            }
        });

        return v;
    }
}