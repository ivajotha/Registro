package mx.ivajotha.desarrollo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.ivajotha.desarrollo.ActivityDetail;
import mx.ivajotha.desarrollo.ActivityItem;
import mx.ivajotha.desarrollo.R;
import mx.ivajotha.desarrollo.adapter.AdapterItemList;
import mx.ivajotha.desarrollo.model.ModelItem;
import mx.ivajotha.desarrollo.model.ModelItemList;
import mx.ivajotha.desarrollo.sql.ItemDataSource;

/**
 * Created by jonathan on 20/06/16.
 */

public class FragmentList extends Fragment {
    private ListView listView;
    private int counter;
    private boolean isWifi;
    private ItemDataSource itemDS;

    public void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        itemDS = new ItemDataSource(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list,container,false);
        listView = (ListView) view.findViewById(R.id.listItems);

        listView.setAdapter(new AdapterItemList(getActivity(),itemDS.getallItems()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter = (AdapterItemList) parent.getAdapter();
                ModelItemList modelItemList = adapter.getItem(position);
                Toast.makeText(getActivity(),modelItemList.item,Toast.LENGTH_SHORT).show();
            }
        });


        final EditText mItemsText = (EditText) view.findViewById(R.id.mItemText);
        view.findViewById(R.id.btnAddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemData = mItemsText.getText().toString();
                String msg_desc = getResources().getString(R.string.msg_desc);
                String msg_txt_empty = getResources().getString(R.string.msg_txt_empty);


                if(!TextUtils.isEmpty(itemData)){
                    ModelItemList modelItemList = new ModelItemList();
                    modelItemList.item = itemData;

                    modelItemList.description  = msg_desc + counter;
                    modelItemList.resourceId = isWifi? R.drawable.ic_device_signal_wifi_4_bar: R.drawable.ic_action_settings_voice;
                    itemDS.saveItemList(modelItemList);
                    listView.setAdapter(new AdapterItemList(getActivity(),itemDS.getallItems()));
                    isWifi=!isWifi;
                    counter++;
                    mItemsText.setText("");
                }else{
                    Toast.makeText(getActivity(),msg_txt_empty,Toast.LENGTH_SHORT).show();
                }
            }
        });



        return view;
    }

}