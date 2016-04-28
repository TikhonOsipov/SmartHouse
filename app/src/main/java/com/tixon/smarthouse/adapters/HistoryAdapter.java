package com.tixon.smarthouse.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tixon.smarthouse.R;
import com.tixon.smarthouse.model.ArduinoHistory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by tikhon.osipov on 28.04.2016
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private ArrayList<ArduinoHistory> history;
    private Calendar calendar;

    public HistoryAdapter(ArrayList<ArduinoHistory> list) {
        this.history = list;
        calendar = Calendar.getInstance();
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater
                .from(parent.getContext()).inflate(R.layout.history_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        holder.tvText.setText(history.get(position).getHistoryItem());
        calendar.setTimeInMillis(history.get(position).getTime());
        holder.tvDate.setText(String.format(Locale.getDefault(),
                "%1$td.%1$tm.%1$tY %1$tH:%1$tM:%1$tS.%1$tL", calendar));
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvText, tvDate;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            tvText = (TextView) itemView.findViewById(R.id.tvHistoryText);
            tvDate = (TextView) itemView.findViewById(R.id.tvHistoryDateTime);
        }
    }
}
