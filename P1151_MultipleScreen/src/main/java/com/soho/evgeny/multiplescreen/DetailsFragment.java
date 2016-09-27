package com.soho.evgeny.multiplescreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by evgeny on 26.09.16.
 */
public class DetailsFragment extends Fragment {

    public static DetailsFragment newInstance(int pos){
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("position",pos);
        detailsFragment.setArguments(arguments);
        return detailsFragment;
    }

    int getPosition() {
        return getArguments().getInt("position", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v  = inflater.inflate(R.layout.details,container,false);
        TextView tv = (TextView)v.findViewById(R.id.tvText);
        tv.setText(getResources().getStringArray(R.array.content)[getPosition()]);
        return v;
    }


}
