package at.ac.univie.hci.mealplan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    private List<CartItem> itemList;
    private RecyclerView recyclerView;
    private CartItemAdapter adapter;
    private FloatingActionButton addIngredientButton;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    // idk what I should change here
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.cartItemRecyclerView);
        itemList = new ArrayList<>();

        // hardcoded list of items
        itemList.add(new CartItem("1","Carrot"));
        itemList.add(new CartItem("1","Onion"));
        itemList.add(new CartItem("500g","Spaghetti Noodles"));
        itemList.add(new CartItem("200g","Brown Mushrooms"));
        itemList.add(new CartItem("400g","Canned Tomatoes"));
        itemList.add(new CartItem("120g","Mozzarella Cheese"));
        itemList.add(new CartItem("120g","Flour"));
        itemList.add(new CartItem("300ml","Milk"));

        adapter = new CartItemAdapter(itemList, this);
        recyclerView.setAdapter(adapter);

        // Set the layout manager for the RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);


        addIngredientButton = getView().findViewById(R.id.addIngredientButton);
        addIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddItemDialogFragment dialogFragment = new AddItemDialogFragment();
                dialogFragment.setTargetFragment(CartFragment.this, 1);
                dialogFragment.show(getFragmentManager(), "AddItemDialogFragment");

            }
        });

    }

    // Method to add a new item to the RecyclerView
    public void addCartItem(CartItem item) {
        itemList.add(item);
        adapter.notifyItemInserted(itemList.size() - 1);
    }
    public void addCartItem(String measurements, String name) {
        CartItem item = new CartItem(measurements, name);
        itemList.add(item);
        adapter.notifyItemInserted(itemList.size() - 1);
        reorderItems();
    }

    public void reorderItems() {
        List<CartItem> checkedItems = new ArrayList<>();
        List<CartItem> uncheckedItems = new ArrayList<>();

        // Separate checked and unchecked items
        for (CartItem item : itemList) {
            if (item.isChecked()) {
                checkedItems.add(item);
            } else {
                uncheckedItems.add(item);
            }
        }

        // Clear the itemList
        itemList.clear();

        // Add unchecked items first, then checked items
        itemList.addAll(uncheckedItems);
        itemList.addAll(checkedItems);

        // Notify the adapter about the data change
        adapter.notifyDataSetChanged();
    }

}