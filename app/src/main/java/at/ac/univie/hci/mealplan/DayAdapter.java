package at.ac.univie.hci.mealplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    private Context context;
    private List<Day> dayList;

    public DayAdapter(Context context, List<Day> dayList) {
        this.context = context;
        this.dayList = dayList;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = dayList.get(position);

        holder.dayTextView.setText(day.getName());
        holder.listLayout.setVisibility(day.isExpanded() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }
    public class DayViewHolder extends RecyclerView.ViewHolder {

        private TextView dayTextView;
        private ViewGroup listLayout;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
            listLayout = itemView.findViewById(R.id.listLayout);

            dayTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Day day = dayList.get(position);
                        day.setExpanded(!day.isExpanded());
                        notifyItemChanged(position);
                    }
                }
            });
        }
    }
}
