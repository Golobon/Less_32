package com.example.less_46;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterHelper {
    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "phoneName";
    String[] groups = new String[] {"HTC", "Samsung", "LG" };
    // названия телефонов
    String[] phonesHTC = new String[] {"Sansation", "Desire", "Wildfire" };
    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave" };
    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black",
            "Optimus One"};
    ArrayList<Map<String, String>> groupData;
    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;
    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    //Список атрибутов группы и элемента
    Map<String, String> m;
    SimpleExpandableListAdapter adapter;
    Context ctx;
    AdapterHelper(Context _ctx) { ctx = _ctx; }
    SimpleExpandableListAdapter getAdapter() {
        groupData = new ArrayList<>();

        for (String group : groups) {
            m = new HashMap<>();
            m.put(ATTR_GROUP_NAME, group);
            groupData.add(m);
        }

        String[] groupFrom = new String[]{ ATTR_GROUP_NAME };
        int[] groupTo = new int[]{ android.R.id.text1 };

        childData = new ArrayList<>();

        childDataItem = new ArrayList<>();
        for (String phone : phonesHTC) {
            m = new HashMap<>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<>();
        for (String phone : phonesSams) {
            m = new HashMap<>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<>();
        for (String phone : phonesLG) {
            m = new HashMap<>();
            m.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        String[] childFrom = new String[]{ ATTR_PHONE_NAME };
        int[] childTo = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                ctx, groupData,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, childData, android.R.layout.simple_list_item_1,
                childFrom, childTo
        );
        return adapter;
    }
    String getGroupText(int groupPos) {
        return ((Map<String, String>) (adapter.getGroup(groupPos)))
                .get(ATTR_GROUP_NAME);
    }
    String getChildText(int groupPos, int childPos) {
        return ((Map<String, String>) (adapter.getChild(groupPos, childPos)))
                .get(ATTR_PHONE_NAME);
    }
    String getGroupChildText(int groupPos, int childPos) {
        return getGroupText(groupPos) + " " +
                getChildText(groupPos,childPos);
    }
}
