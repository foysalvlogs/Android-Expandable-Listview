package com.foysaldev.androidexpandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private CustomAdapter customAdapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private int lastExpandableListView = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListData();

        expandableListView = findViewById(R.id.ExpandableListViewid);
        customAdapter = new CustomAdapter(this,listDataHeader,listDataChild);
        expandableListView.setAdapter(customAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                String groupName = listDataHeader.get(i);
                Toast.makeText(MainActivity.this, "Group Clicked : "+groupName, Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                String groupName = listDataHeader.get(i);
                Toast.makeText(MainActivity.this, "Clicked Collapsed : "+groupName, Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                String childString = listDataChild.get(listDataHeader.get(i)).get(i1);
                Toast.makeText(MainActivity.this, ""+childString, Toast.LENGTH_SHORT).show();

                return false;
            }
        });

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                if (lastExpandableListView != -1 && lastExpandableListView != i) {
                    expandableListView.collapseGroup(lastExpandableListView);
                }
                lastExpandableListView = i;
            }
        });

    }

    private void ListData() {

        String[] headerString = getResources().getStringArray(R.array.list_header);
        String[] childString = getResources().getStringArray(R.array.list_child);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        /*listDataHeader.add("1. Barisal");
        listDataHeader.add("2. Chittagong");

        List<String> Barisal = new ArrayList<>();
        Barisal.add("1.1 Barisal");
        Barisal.add("1.2 Barguna");
        Barisal.add("1.3 Bhola");
        Barisal.add("1.4 Jhalokati");
        Barisal.add("1.5 Patuakhali");
        Barisal.add("1.6 Pirojpur");

        List<String> Chittagong = new ArrayList<>();
        Chittagong.add("1.1 Brahmanbaria");
        Chittagong.add("1.2 Comilla");
        Chittagong.add("1.3 Chandpur");
        Chittagong.add("1.4 Lakshmipur");
        Chittagong.add("1.5 Noakhali");
        Chittagong.add("1.6 Feni");

        listDataChild.put(listDataHeader.get(0),Barisal);
        listDataChild.put(listDataHeader.get(1),Chittagong);*/

        for (int i = 0; i < headerString.length; i++) {
            listDataHeader.add(headerString[i]);

            List<String> child = new ArrayList<>();
            child.add(childString[i]);

            listDataChild.put(listDataHeader.get(i), child);

        }
    }
}