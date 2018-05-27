package com.mainuser.budgetapp.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mainuser.budgetapp.R;
import com.mainuser.budgetapp.database.LineItem;
import com.mainuser.budgetapp.detail.LineItemActivity;
import com.mainuser.budgetapp.util.IntentStrings;
import com.mainuser.budgetapp.util.StringFormats;

import java.util.Date;
import java.util.List;
import java.util.Locale;


public class LineItemListAdapter extends RecyclerView.Adapter<LineItemListAdapter.ViewHolder> {
    private final String TAG = LineItemListAdapter.class.getSimpleName();

    private Context context;
    private List<LineItem> lineItemList;

    LineItemListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LineItemListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_line_item, parent, false);
        return new LineItemListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LineItemListAdapter.ViewHolder holder, int position) {
        LineItem currentItem = lineItemList.get(position);

        holder.dateView.setText(formatDate(currentItem.getDate()));
        holder.descView.setText(currentItem.getDescription());
        holder.amountView.setText(formatAmount(currentItem.getAmount()));
        holder.categoryView.setText(currentItem.getCategory());
    }

    @Override
    public int getItemCount() {
        return (lineItemList == null ? 0 : lineItemList.size());
    }

    public LineItem get(int position) {
        return lineItemList.get(position);
    }

    void setItemsList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    private String formatDate(long dateVal) {
        Date date = new Date(dateVal);
        return String.format(Locale.ENGLISH, StringFormats.DATE, date);
    }

    private String formatAmount(Double value) {
        return String.format(Locale.ENGLISH, StringFormats.AMOUNT, value);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateView;
        TextView descView;
        TextView categoryView;
        TextView amountView;

        ViewHolder(View v) {
            super(v);
            this.dateView = v.findViewById(R.id.line_item_date);
            this.descView = v.findViewById(R.id.line_item_description);
            this.categoryView = v.findViewById(R.id.line_item_category);
            this.amountView = v.findViewById(R.id.line_item_amount);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = ViewHolder.this.getAdapterPosition();
                    if (position == RecyclerView.NO_POSITION) {
                        Log.d(TAG, "Could not find position.");
                        return;
                    }
                    Intent intent = new Intent(context, LineItemActivity.class);
                    intent.putExtra(IntentStrings.LINE_ITEM_ID, position);
                    context.startActivity(intent);
                }
            });
        }
    }

}
