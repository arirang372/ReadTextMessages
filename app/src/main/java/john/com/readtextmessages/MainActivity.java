package john.com.readtextmessages;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Activity. Displays a list of numbers.
 * 
 * @author itcuties
 *
 */
public class MainActivity extends AppCompatActivity
{

	private static final String TAG = "MainActivty";

	@BindView(R.id.lv_texts)
	ListView lv_texts;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		if(getIntent() != null)
		{
			String message = getIntent().getStringExtra("text_message");
			if(!TextUtils.isEmpty(message))
			{
				Toast.makeText(this, message, Toast.LENGTH_LONG).show();
			}
		}
		setupUI();
		
	}

	private void setupUI()
	{
		List<SMSData> smsList = new ArrayList<>();

		Uri uri = Uri.parse("content://sms/inbox");
		Cursor c= getContentResolver().query(uri, null, null ,null,null);
		startManagingCursor(c);

		// Read the sms data and store it in the list
		if(c.moveToFirst()) {
			for(int i=0; i < c.getCount(); i++) {
				SMSData sms = new SMSData();
				sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
				sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
				smsList.add(sms);

				c.moveToNext();
			}
		}
		c.close();

		// Set smsList in the ListAdapter
		lv_texts.setAdapter(new ListAdapter(this, smsList));
	}

	@Override
	public void onStart()
	{
		super.onStart();


	}



}
