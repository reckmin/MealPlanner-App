package at.ac.univie.hci.mealplan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddItemDialogFragment extends DialogFragment {

    private EditText measurementsEditText;
    private EditText nameEditText;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_item, null);

        measurementsEditText = dialogView.findViewById(R.id.measurementsEditText);
        nameEditText = dialogView.findViewById(R.id.nameEditText);

        builder.setView(dialogView)
                .setTitle("Add Item")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String measurements = measurementsEditText.getText().toString();
                        String name = nameEditText.getText().toString();

                        // Pass the item details back to the target fragment (SearchItemsView)
                        if (measurements.isEmpty() || name.isEmpty()) {
                            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG).show();
                            measurementsEditText.setBackgroundResource(R.color.greenbright);

                        } else if (!Character.isDigit(measurements.charAt(0))) {
                            Toast.makeText(requireContext(), "Measurements must start with a number", Toast.LENGTH_LONG).show();
                            measurementsEditText.setBackgroundResource(R.color.greenbright);

                        } else {
                            CartFragment targetFragment = (CartFragment) getTargetFragment();
                            if (targetFragment != null) {
                                targetFragment.addCartItem(measurements, name);
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });

        return builder.create();
    }
}
