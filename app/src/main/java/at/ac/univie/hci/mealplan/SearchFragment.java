package at.ac.univie.hci.mealplan;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    private List<CartItem> itemList;
    private RecyclerView recyclerView;
    private CartItemAdapter adapter;
    private Toolbar toolbar;
    private SearchView searchView;
    public static Button toggleButton;
    private FragmentManager fragmentManager;
    private FilterFragment filterFragment;
    private SearchItemsView searchFragment;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchView = view.findViewById(R.id.searchView);
        searchFragment = new SearchItemsView();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());

        toggleButton = getView().findViewById(R.id.filter_button);
        fragmentManager = getChildFragmentManager();
        filterFragment = new FilterFragment();

        recyclerView = getView().findViewById(R.id.search_recycle_view);
        itemList = new ArrayList<>();
        adapter = new CartItemAdapter(itemList, new CartFragment());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setVisibility(View.VISIBLE);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check the current visibility state of the RecyclerView
                // Hide the keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

                int recyclerViewVisibility = recyclerView.getVisibility();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragmentContainerView2);

                if (recyclerViewVisibility == View.VISIBLE || currentFragment instanceof SearchItemsView) {
                    replaceFragment(filterFragment);
                    // If the RecyclerView is visible, hide it and show the Fragment view
                    recyclerView.setVisibility(View.GONE);
                } else {
                    replaceFragment(searchFragment);
                    // If the RecyclerView is visible, hide it and show the Fragment view
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });


        // Set up the search view
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView.setVisibility(View.VISIBLE);
                replaceFragment(searchFragment);
                recyclerView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                int recyclerViewVisibility = recyclerView.getVisibility();

                if (recyclerViewVisibility != View.VISIBLE) {
                    // If the RecyclerView is hidden, show it and hide the Fragment view
                    recyclerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

    }

    private  void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView2, fragment);
        fragmentTransaction.commit();
        recyclerView.setVisibility(View.VISIBLE);
    }

}

