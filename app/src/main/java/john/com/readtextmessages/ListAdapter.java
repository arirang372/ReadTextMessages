package john.com.readtextmessages;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * List adapter for storing SMS data
 * 
 * @author itcuties
 *
 */
public class ListAdapter extends ArrayAdapter<SMSData> {

	// List context
    private final Context context;
    // List values
    private final List<SMSData> smsList;
	
	public ListAdapter(Context context, List<SMSData> smsList) {
		super(context, R.layout.activity_main, smsList);
		this.context = context;
		this.smsList = smsList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
        View rowView = inflater.inflate(R.layout.single_text_item, parent, false);

        TextView txtNumber = rowView.findViewById(R.id.txtNumber);
		txtNumber.setText(smsList.get(position).getNumber());

		TextView txtBody = rowView.findViewById(R.id.txtBody);
		txtBody.setText(smsList.get(position).getBody());

        return rowView;
	}


}
