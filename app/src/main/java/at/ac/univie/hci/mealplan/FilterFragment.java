package at.ac.univie.hci.mealplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FilterFragment extends Fragment {
    private Button adjust_diet;
    private Button apply_diet;
    private Callback callback;

    public interface Callback {
        void onViewReady(View view);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        // Notify the callback that the view is ready
        if (callback != null) {
            callback.onViewReady(view);
        }

        adjust_diet = view.findViewById(R.id.adjust_diet);
        apply_diet = view.findViewById(R.id.apply_button);
        adjust_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomNavigationView bottomNavigationView = ((calendarActivity) requireActivity()).activityCalendarBinding.bottomNavigationView;

                // Find the menu item corresponding to the desired fragment (e.g., CalendarFragment)
                MenuItem menuItem = bottomNavigationView.getMenu().findItem(R.id.profile);

                // Set the menu item as checked to update the UI in the bottom navigation view
                menuItem.setChecked(true);

                // Replace the current fragment with the CalendarFragment
                ((calendarActivity) requireActivity()).replaceFragment(new ProfileFragment());
            }
        });
        apply_diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment.toggleButton.callOnClick();
            }
        });

        return view;
    }
}
