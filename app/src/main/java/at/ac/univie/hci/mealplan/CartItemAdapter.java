package at.ac.univie.hci.mealplan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemViewHolder> {


    private static List<CartItem> itemList;
    private static CartFragment cartFragment; // Reference to the CartFragment

    public CartItemAdapter(List<CartItem> itemList, CartFragment cartFragment) {
        this.itemList = itemList;
        this.cartFragment = cartFragment;
    }
    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem item = itemList.get(position);
        holder.itemText.setText(item.getCartItemName());
        holder.itemMeasurement.setText(item.getCartItemMeasurement());
        holder.bind(item, position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        TextView itemMeasurement;

        CheckBox checkBox;

        public CartItemViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.cartItemName);
            itemMeasurement = itemView.findViewById(R.id.cartItemMeasurement);
            checkBox = itemView.findViewById(R.id.cartItemCheckBox);

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (int) v.getTag(); // Get the stored position from the tag
                    if (position != RecyclerView.NO_POSITION) {
                        CartItem cartItem = itemList.get(position);
                        cartItem.setChecked(checkBox.isChecked());

                        // Call the reorderItems method in CartFragment
                        if (cartFragment != null) {
                            cartFragment.reorderItems();
                        }
                    }
                }
            });
        }

        // Bind the view holder with the data
        public void bind(CartItem cartItem, int position) {
            // Set the stored position as a tag to the checkbox
            checkBox.setTag(position);

            // Bind other views with data
            itemMeasurement.setText(cartItem.getCartItemMeasurement());
            itemText.setText(cartItem.getCartItemName());
            checkBox.setChecked(cartItem.isChecked());
        }

    }
}
