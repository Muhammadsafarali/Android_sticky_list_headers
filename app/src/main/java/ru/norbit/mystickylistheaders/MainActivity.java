package ru.norbit.mystickylistheaders;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StickyListHeadersListView stickyList = (StickyListHeadersListView) findViewById(R.id.list);
        MyAdapter adapter = new MyAdapter(this);
        stickyList.setAdapter(adapter);
    }
}
