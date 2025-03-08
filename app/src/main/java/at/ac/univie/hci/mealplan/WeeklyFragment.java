package at.ac.univie.hci.mealplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WeeklyFragment extends Fragment {
    private RecyclerView dayRecyclerView;
    private DayAdapter dayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weekly, container, false);
        dayRecyclerView = view.findViewById(R.id.dayRecyclerView);
        dayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Day> dayList = createDayList();

        dayAdapter = new DayAdapter(getContext(), dayList);
        dayRecyclerView.setAdapter(dayAdapter);

        return view;
    }

    private List<Day> createDayList() {
        List<Day> dayList = new ArrayList<>();
        dayList.add(new Day("Monday"));
        dayList.add(new Day("Tuesday"));
        dayList.add(new Day("Wednesday"));
        dayList.add(new Day("Thursday"));
        dayList.add(new Day("Friday"));
        dayList.add(new Day("Saturday"));
        dayList.add(new Day("Sunday"));
        return dayList;
    }
}