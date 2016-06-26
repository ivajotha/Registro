package mx.ivajotha.desarrollo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.ivajotha.desarrollo.R;
import mx.ivajotha.desarrollo.model.ModelItem;

/**
 * Created by jonathan on 20/06/16.
 */

/**  Adaptador del ItemList **/
public class AdapterItemList extends ArrayAdapter<ModelItem> {
    public AdapterItemList(Context context,List<ModelItem> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list,parent,false);
        }
        TextView txtItemDescription= (TextView) convertView.findViewById(R.id.txtItemDescription);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtItemTitle);
        ImageView img = (ImageView) convertView.findViewById(R.id.row_image_view);

        ModelItem modelItem=getItem(position);
        txtTitle.setText(modelItem.item.toUpperCase());
        txtItemDescription.setText(modelItem.description);
        img.setImageResource(modelItem.resourceId);
        return convertView;
    }
}
