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

/**
 * Created by jonathan on 20/06/16.
 */

public class FragmentList extends Fragment {
    private ListView listView;
    private List<ModelItem> array = new ArrayList<>();
    private int counter;
    private boolean isWifi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,container,false);
        listView = (ListView) view.findViewById(R.id.listItems);

        /**  Click para mostra detalle del elemento de la lista **/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdapterItemList adapter= (AdapterItemList) parent.getAdapter();
                ModelItem modelItem =adapter.getItem(position);
                ModelItem modelItem2 = array.get(position);
                Intent intentD= new Intent(getActivity(), ActivityItem.class);
                intentD.putExtra("key_user",modelItem2.item);
                startActivity(intentD);
            }
        });


        final EditText mItemsText = (EditText) view.findViewById(R.id.mItemText);
        /**  Agregar un Elemento de la lista **/
        view.findViewById(R.id.btnAddItem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemData = mItemsText.getText().toString();
                Pattern pat = Pattern.compile("^[a-m|A-M].*");
                if(!TextUtils.isEmpty(itemData))
                {
                    ModelItem item =new ModelItem();
                    item.item=itemData;
                    item.description  = "No.:" +counter;
                    Matcher mat = pat.matcher(item.item);
                    item.resourceId = mat.matches()?R.drawable.ic_thumb_down_black_24dp: R.drawable.ic_thumb_up_black_24dp;
                    array.add(item);
                    listView.setAdapter(new AdapterItemList(getActivity(),array));
                    counter++;
                    mItemsText.setText("");
                }

            }
        });
        return view;
    }
}