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
import com.mainuser.budgetapp.util.IntentActions;
import com.mainuser.budgetapp.util.StringFormats;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder> {
        private final String TAG = CustomListAdapter.class.getSimpleName();

        private Context context;
        private List<LineItem> lineItemList;

        CustomListAdapter(Context context) {
            this.context = context;
        }

        @Override
        public CustomListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.list_line_item, parent, false);
            return new CustomListAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomListAdapter.ViewHolder holder, int position) {
            Log.d(TAG, "calling onBindViewHolder");
            LineItem currentItem = lineItemList.get(position);

            holder.dateTextView.setText(formatDate(currentItem.getDate()));
            holder.descTextView.setText(currentItem.getDescription());
            holder.amountTextView.setText(formatAmount(currentItem.getAmount()));
            holder.categoryTextView.setText(currentItem.getCategory());
        }

        @Override
        public int getItemCount() {
            return (lineItemList == null ? 0 : lineItemList.size());
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView dateTextView;
            TextView descTextView;
            TextView categoryTextView;
            TextView amountTextView;

            ViewHolder(View v) {
                super(v);
                this.dateTextView = v.findViewById(R.id.line_item_date);
                this.descTextView = v.findViewById(R.id.line_item_description);
                this.categoryTextView = v.findViewById(R.id.line_item_category);
                this.amountTextView = v.findViewById(R.id.line_item_amount);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = ViewHolder.this.getAdapterPosition();
                        Intent intent = new Intent(context, LineItemActivity.class);
                        intent.putExtra(IntentActions.LINE_ITEM_ID, position);
                        context.startActivity(intent);
                    }
                });
            }
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

}
