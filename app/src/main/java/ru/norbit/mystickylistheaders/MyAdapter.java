package ru.norbit.mystickylistheaders;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by safarali.alisultanov on 26.10.2016.
 */

public class MyAdapter extends BaseSwipeAdapter implements StickyListHeadersAdapter {

    private List<String> countries;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<String> country) {

        inflater = LayoutInflater.from(context);
        countries = country;
//        countries = context.getResources().getStringArray(R.array.countries);
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int i) {
        return countries.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View v = inflater.inflate(R.layout.test_list_item_layout, null);
        SwipeLayout swipeLayout = (SwipeLayout)v.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeLayout.addSwipeListener(new SimpleSwipeListener() {

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                // if (xvel <= 0) don't remove
                // if (xvel > 0) remove
                Log.e("LOG", "onHandRelease xvel = " + xvel + " yvel = " + yvel);
            }

        });
        swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Log.e("LOG", "double click");
            }
        });
        ViewHolder holder = new ViewHolder();;
        holder.text = (TextView) swipeLayout.findViewById(R.id.text);
        swipeLayout.setTag(holder);
        holder.text.setText(countries.get(position));

        return v;
    }

    @Override
    public void fillValues(int position, View convertView) {
    }


    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = "" + countries.get(position).subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
//        Log.e("LOG", "tmp");
        long ch = countries.get(position).subSequence(0,1).charAt(0);
        return ch;
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
    }
}
