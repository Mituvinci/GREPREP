package com.mitu.hi.greprep;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hi on 02-May-16.
 */
public class AdapterDictionary extends RecyclerView.Adapter<AdapterDictionary.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private List<String> mDataset;
    private static MyClickListener myClickListener;

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_layout, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.word.setText(mDataset.get(position));
        holder.meaning.setText(mDataset.get(position+1));
        holder.sentence.setText(mDataset.get(position+2));


    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        TextView word,meaning,sentence;

        public DataObjectHolder(View itemView) {
            super(itemView);
            word = (TextView) itemView.findViewById(R.id.word);
            meaning = (TextView) itemView.findViewById(R.id.meaning);
            sentence = (TextView) itemView.findViewById(R.id.sentence);


            Log.i(LOG_TAG, "Adding Listener");
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public AdapterDictionary(List<String> myDataset) {
        mDataset = myDataset;
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
