package com.example.contactsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



    public class DatabaseAdapter extends RecyclerView.Adapter<DatabaseAdapter.ViewHolder> {

        private String[] localDataSet;
        private String[] localDataSet2;
        private String[] localDataSet3;

        View.OnClickListener listener;


        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder)
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;
            private final TextView textView2;
            private final TextView textView3;


            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View



                textView = (TextView) view.findViewById(R.id.textView);
                textView.setOnClickListener(listener);
                textView.setTag(this);

                textView2 = (TextView) view.findViewById(R.id.textView2);
                textView2.setOnClickListener(listener);

                textView3 = (TextView) view.findViewById(R.id.textView3);
                textView3.setOnClickListener(listener);


            }

            public TextView getTextView() {
                return textView;
            }
            public TextView getTextView2() {
                return textView2;
            }
            public TextView getTextView3() {
                return textView3;
            }
        }

        /**
         * Initialize the dataset of the Adapter
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView
         */
        public DatabaseAdapter(String[] dataSet, String[] dataSet2,String[] dataSet3) {


            localDataSet = dataSet;
            localDataSet2 = dataSet2;
            localDataSet3 = dataSet3;
        }

        public void setOnClickListener(View.OnClickListener listenThingie) {

            listener = listenThingie;

        }


        // Create new views (invoked by the layout manager)
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.text_row_item, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView().setText(localDataSet[position]);
            viewHolder.getTextView2().setText(localDataSet2[position]);
            viewHolder.getTextView3().setText(localDataSet3[position]);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.length;
        }
    }




