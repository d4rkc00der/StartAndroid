package com.soho.evgeny.expandablelistevents;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by evgeny on 08.08.16.
 */
public class AdapterHelper {
    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "phoneName";

    String[] groups = new String[] {"HTC","Samsung","LG"};

    String[] phoneHTC = new String[] {"Sensation","Desire","Hero"};
    String[] phoneSam = new String[] {"Galaxy","Wave","Note"};
    String[] phoneLG = new String[] {"Optimus","Optimus Link","Optimus Black"};

    ArrayList<Map<String,String>> groupData;
    ArrayList<Map<String,String>> childDataItem;
    ArrayList<ArrayList<Map<String,String>>> childData;

    Map<String,String> map;

    Context ctx;

    AdapterHelper(Context _ctx) {
        ctx = _ctx;
    }

    SimpleExpandableListAdapter adapter;

    SimpleExpandableListAdapter getAdapter() {
        groupData = new ArrayList<Map<String,String>>();
        for(String group : groups) {
            map = new HashMap<String,String>();

            map.put(ATTR_GROUP_NAME,group);
            groupData.add(map);
        }

        String[] groupFrom = new String[] {ATTR_GROUP_NAME};
        int[] groupTo = new int[] {android.R.id.text1};

        childData = new ArrayList<ArrayList<Map<String,String>>>();
        childDataItem = new ArrayList<Map<String,String>>();
        for(String phone : phoneHTC) {
            map = new HashMap<String,String>();
            map.put(ATTR_PHONE_NAME,phone);
            childDataItem.add(map);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String,String>>();
        for(String phone : phoneLG) {
            map = new HashMap<String,String>();
            map.put(ATTR_PHONE_NAME,phone);
            childDataItem.add(map);
        }
        childData.add(childDataItem);

        childDataItem = new ArrayList<Map<String,String>>();
        for(String phone : phoneSam) {
            map = new HashMap<String,String>();
            map.put(ATTR_PHONE_NAME,phone);
            childDataItem.add(map);
        }
        childData.add(childDataItem);

        String[] childFrom = new String[] {ATTR_PHONE_NAME};
        int[] childTo = new int[] {android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                ctx,groupData,android.R.layout.simple_expandable_list_item_1,groupFrom,groupTo,childData,android.R.layout.simple_list_item_1,childFrom,childTo
        );
        return adapter;
    }

    String getGroupText(int groupPos) {
        return ((Map<String,String>)(adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }

    String getChildText(int groupPos, int childPos) {
        return ((Map<String,String>)(adapter.getChild(groupPos,childPos))).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText(int groupPos,int childPos) {
        return getGroupText(groupPos) + " " + getChildText(groupPos,childPos);
    }
}
