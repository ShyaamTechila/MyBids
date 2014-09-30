package info.android.customlistviewvolley.adater;

import info.android.customlistviewvolley.app.AppController;
import com.example.mybids.Product;
import com.example.mybids.R;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
	private Activity activity;
	private LayoutInflater inflater;
	private List<Product> movieItems;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

	public CustomListAdapter(Activity activity, List<Product> movieItems) {
		this.activity = activity;
		this.movieItems = movieItems;
		Log.e("count","adapter -> CustomListAdapter");
	}

	@Override
	public int getCount() {
		Log.e("inside","" + movieItems.size());
		return movieItems.size();
	}

	@Override
	public Object getItem(int location) {
		return movieItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		Log.e("inside","" + position);
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Log.e("inside","adapter -> get view");

		if (inflater == null)
			inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (convertView == null)
			convertView = inflater.inflate(R.layout.listrow, null);

		if (imageLoader == null)
			imageLoader = AppController.getInstance().getImageLoader();
		NetworkImageView thumbNail = (NetworkImageView) convertView
				.findViewById(R.id.thumbnail);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		TextView rating = (TextView) convertView.findViewById(R.id.rating);
		TextView genre = (TextView) convertView.findViewById(R.id.genre);
		TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

		// getting movie data for the row
		Product m = movieItems.get(position);

		// thumbnail image
		thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
		
		// title
		title.setText("Name: " +m.getTitle());
		
		// rating
		rating.setText("Company: " + m.getRating());
		
		// release year
		year.setText("Rs:" +String.valueOf(m.getYear()));
		
		
		
		/*// genre
		String genreStr = "";
		for (String str : m.getGenre()) {
			genreStr += str + ", ";
		}
		genreStr = genreStr.length() > 0 ? genreStr.substring(0,
				genreStr.length() - 2) : genreStr;
		genre.setText(genreStr);
		
		// release year
		year.setText(String.valueOf(m.getYear()));*/
		
		

		return convertView;
	}

	private String setText(String rating) {
		// TODO Auto-generated method stub
		return null;
	}

}