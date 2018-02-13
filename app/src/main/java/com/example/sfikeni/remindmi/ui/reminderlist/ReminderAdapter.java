package com.example.sfikeni.remindmi.ui.reminderlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sfikeni.remindmi.R;
import com.example.sfikeni.remindmi.database.entity.Reminder;
import com.example.sfikeni.remindmi.database.entity.ReminderEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by SFikeni on 2018/01/29.
 */

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<? extends Reminder> reminderEntities;

    ReminderAdapter() {
        reminderEntities = new ArrayList<>();
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder holder, int position) {
        ReminderEntity reminderEntity = (ReminderEntity) reminderEntities.get(position);

        if (reminderEntity != null){
            holder.titleTextView.setText(reminderEntity.getTitle());
            holder.priorityImageView.setBackgroundResource(getPriorityDrawable(reminderEntity.getPriorityLevel()));
        }
    }

    private int getPriorityDrawable(String priority){
        if (priority.equalsIgnoreCase("high")){
            return R.drawable.ic_priority_high_black_24px;

        } else if (priority.equalsIgnoreCase("low")){
            return R.drawable.ic_low_priority_black_24px;

        } else {
            return 0;
        }
    }

    @Override
    public int getItemCount() {
        return reminderEntities == null ? 0 : reminderEntities.size();
    }

    void setReminderEntities(List<? extends Reminder> reminderEntities){
        this.reminderEntities = reminderEntities;
        notifyDataSetChanged();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.reminder_priority_indicator_image)
        ImageView priorityImageView;

        @BindView(R.id.reminder_item_title)
        TextView titleTextView;

        @BindView(R.id.reminder_status_indicator_image)
        ImageView statusImageView;

        ReminderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
