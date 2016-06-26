package mx.ivajotha.desarrollo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mx.ivajotha.desarrollo.R;

/**
 * Created by jonathan on 20/06/16.
 */

public class FragmentProfile extends Fragment {

    private ImageView imgProfile;
    private boolean change=true;

    public static FragmentProfile newInstance(String name){
        FragmentProfile f = new FragmentProfile();
        Bundle b = new Bundle();
        b.putString("user_key",name);
        f.setArguments(b);
        return f;
    }

    public void changeImage()
    {
        imgProfile.setImageResource(change?R.drawable.ic_device_signal_wifi_4_bar: R.drawable.ic_action_settings_voice);
        change=!change;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Pattern pat = Pattern.compile("^[a-m|A-M].*");
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        imgProfile = (ImageView) view.findViewById(R.id.imgProfile);
        TextView txt = (TextView) view.findViewById(R.id.txtUserFragment);
        Bundle bundle=getArguments();

        String myname = (bundle!=null? bundle.getString("user_key"): "");
        txt.setText(myname);

        Matcher mat = pat.matcher(myname);
        imgProfile.setImageResource(mat.matches()?R.drawable.ic_thumb_down_black_24dp: R.drawable.ic_thumb_up_black_24dp);


        return view;
    }

}
