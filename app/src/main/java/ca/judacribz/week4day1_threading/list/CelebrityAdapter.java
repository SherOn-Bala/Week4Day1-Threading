package ca.judacribz.week4day1_threading.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

import ca.judacribz.week4day1_threading.R;
import ca.judacribz.week4day1_threading.models.Celebrity;

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.CelebrityHolder> {

    ItemClickedListener itemClickedListener;

    interface ItemClickedListener {
        void onItemClicked(Celebrity celebrity);
    }

    private ArrayList<Celebrity> celebrities;
    private Context context;

    public CelebrityAdapter(Context context,
                            ArrayList<Celebrity> celebrities) {
        this.context = context;
        this.itemClickedListener = (ItemClickedListener) context;
        this.celebrities = celebrities;
    }


    public void setCelebrityList(ArrayList<Celebrity> celebrities) {
        this.celebrities = celebrities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CelebrityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CelebrityHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_celebrity,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull CelebrityHolder holder, int position) {
        holder.setViews(celebrities.get(position));
    }

    @Override
    public int getItemCount() {
        return celebrities.size();
    }

    class CelebrityHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAge;

        Celebrity celebrity;
        long id;

        CelebrityHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
        }

        void setViews(Celebrity celebrity) {
            this.celebrity = celebrity;

            id = celebrity.getId();

            tvName.setText(String.format(
                    Locale.US,
                    context.getString(R.string.name),
                    celebrity.getLastName(),
                    celebrity.getFirstName()
            ));

            tvAge.setText(String.format(
                    Locale.US,
                    context.getString(R.string.age_d),
                    celebrity.getAge()
            ));
        }
    }
}
