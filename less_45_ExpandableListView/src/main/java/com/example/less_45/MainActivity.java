package com.example.less_45;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    // название компаний (групп)
    String[] groups = new String[] {"HTC", "Samsung", "LG" };
    // названия телефонов
    String[] phonesHTC = new String[] {"Sansation", "Desire", "Wildfire" };
    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave" };
    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black",
    "Optimus One"};
    // коллекция для групп
    ArrayList<Map<String, String>> groupData;
    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;
    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    //Список атрибутов группы и элемента
    Map<String, String> m;

    ExpandableListView elvMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupData = new ArrayList<>();

        for (String group : groups) {
            m = new HashMap<>();
            m.put("groupName", group);
            groupData.add(m);
        }

        String[] groupFrom = new String[] { "groupName" };
        int[] groupTo = new int[] { android.R.id.text1 };

        childData = new ArrayList<>();

        childDataItem = new ArrayList<>();
        for (String phone : phonesHTC) {
            m = new HashMap<>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<>();
        for (String phone : phonesSams) {
            m = new HashMap<>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<>();
        for (String phone : phonesLG) {
            m = new HashMap<>();
            m.put("phoneName", phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String[] childFrom = new String[] { "phoneName" };
        int[] childTo = new int[] { android.R.id.text1 };

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupData,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, childData, android.R.layout.simple_list_item_1,
                childFrom, childTo
        );

        elvMain = findViewById(R.id.elv_main);
        elvMain.setAdapter(adapter);
    }
}