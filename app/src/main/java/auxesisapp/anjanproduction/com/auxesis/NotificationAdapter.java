package auxesisapp.anjanproduction.com.auxesis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HP on 03-09-2018.
 */

public class NotificationAdapter extends BaseAdapter {

    private ArrayList<notoficationModel> list;
    private Context ctx;

    public NotificationAdapter(Context ctx,ArrayList<notoficationModel> list){
        this.list=list;
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.notification_view, viewGroup, false);
        }
        final notoficationModel model=(notoficationModel)this.getItem(i);
        TextView titleText=(TextView)view.findViewById(R.id.notititle);
        TextView descText=(TextView)view.findViewById(R.id.notides);
        titleText.setText(model.getTitle());
        descText.setText(model.getDescription());

     return view;
    }
}
