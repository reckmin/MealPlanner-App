package at.ac.univie.hci.mealplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import at.ac.univie.hci.mealplan.databinding.ActivityCalendarBinding;

public class calendarActivity extends AppCompatActivity {



    ActivityCalendarBinding activityCalendarBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // code below removes the action bar and title bar
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        activityCalendarBinding = ActivityCalendarBinding.inflate(getLayoutInflater());
        setContentView(activityCalendarBinding.getRoot());



        replaceFragment(new CalendarFragment());

        activityCalendarBinding.bottomNavigationView.setOnItemSelectedListener(item -> {
           switch(item.getItemId()){
               case R.id.calendar:
                   replaceFragment(new CalendarFragment());
                   break;
               case R.id.favourite:
                   replaceFragment(new FavoriteFragment());
                   break;
               case R.id.search:
                   replaceFragment(new SearchFragment());
                   break;
               case R.id.shoppingcart:
                   replaceFragment( new CartFragment());
                   break;
               case R.id.profile:
                   replaceFragment(new ProfileFragment());
                   break;
           }
            return true;
        });
    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    /*
    @Override
    public void onClick(View v) {
        if(v.getId()  == R.id.item1 ){
            select.animate().x(0).setDuration(100);
            item1.setTextColor(Color.WHITE);
            item2.setTextColor(def);
        } else if(v.getId() == R.id.item2){
            item2.setTextColor(Color.WHITE);
            item1.setTextColor(def);
            int size = item2.getWidth();
            select.animate().x(size).setDuration(100);
        }

    }

     */
}