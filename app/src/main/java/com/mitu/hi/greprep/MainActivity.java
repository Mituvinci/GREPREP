package com.mitu.hi.greprep;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    String text ;
    TextView tv;
    String[] dictionary;
    private RecyclerView mRecyclerViewDictionary;
    private RecyclerView.Adapter mAdapterDictionary;
    private RecyclerView.LayoutManager mLayoutManagerDictionary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textview);
        mLayoutManagerDictionary = new LinearLayoutManager(getApplicationContext());
        try {
           text =  readFromAssets(getApplicationContext(),"copy.txt");
           // tv.setText(text);
            dictionary = text.split("\n");
            mRecyclerViewDictionary = (RecyclerView) findViewById(R.id.recycdictionary);
            mRecyclerViewDictionary.setHasFixedSize(true);
            mRecyclerViewDictionary.setLayoutManager(mLayoutManagerDictionary);
            mAdapterDictionary = new AdapterDictionary(Arrays.asList(dictionary));
            mRecyclerViewDictionary.setAdapter(mAdapterDictionary);
            mRecyclerViewDictionary.setNestedScrollingEnabled(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        int i=0;
        while (mLine != null) {
            sb.append(mLine+"\n"); // process line
            mLine = reader.readLine();
           // Log.d("mLine",mLine);

        }
        reader.close();
        return sb.toString();
    }
}
