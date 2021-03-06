package fragments;

import helper.DHelper;
import helper.DbHelper;
import helper.DbQuery;

import java.util.ArrayList;
import java.util.Collections;

import uwi.dcit.AgriExpenseTT.R;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FragmentOtherResourceList  extends ListFragment{
	SQLiteDatabase db;
	DbHelper dbh;
	ArrayList<String>list;
	View view;
	ArrayAdapter<String> listAdapt;
	EditText et_search;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbh=new DbHelper(this.getActivity().getBaseContext());
		db=dbh.getReadableDatabase();
		populateList();
		Collections.sort(list);
		listAdapt = new ArrayAdapter<String>(this.getActivity().getBaseContext(),android.R.layout.simple_list_item_1,list);
		setListAdapter(listAdapt);
		
	}
		

	private void populateList() {
		list=new ArrayList<String>();
		DbQuery.getResources(db, dbh, DHelper.cat_other, list);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
		//returns the inflated layout which contains the listview
		view= inflater.inflate(R.layout.fragment_purchaselist_other, container, false);
		et_search=(EditText)view.findViewById(R.id.et_search_other);
		TWatch tw=new TWatch(listAdapt);
		et_search.addTextChangedListener(tw);
		setupButton(view);
		return view;
	}
	 private void setupButton(View v) {
		Button btn_ntFnd=(Button)v.findViewById(R.id.btn_purchaseOther_notFound);
		Click c=new Click();
		btn_ntFnd.setOnClickListener(c);
	 }
	 public class Click implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(v.getId()==R.id.btn_purchaseOther_notFound){
				Fragment f=new FragmentNewPurchaseOther();
				Bundle b=new Bundle();
				b.putString("category", DHelper.cat_other);
				b.putString("found","no");
				f.setArguments(b);
				FragmentManager fm=getFragmentManager();
				FragmentTransaction ft=fm.beginTransaction();
				ft.replace(R.id.NewCycleListContainer, f);
				ft.addToBackStack(null);
				ft.commit();
			}
		}
		 
	 }


	@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			Toast.makeText(getActivity(), getListView().getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
			Fragment f=new FragmentNewPurchaseOther();
			Bundle b=new Bundle();
			b.putString("category", DHelper.cat_other);
			b.putString("resource", list.get(position));
			b.putString("found","yes");
			f.setArguments(b);
			FragmentManager fm=getFragmentManager();
			FragmentTransaction ft=fm.beginTransaction();
			ft.replace(R.id.NewCycleListContainer, f);
			ft.addToBackStack(null);
			ft.commit();
	 }
	 public class TWatch implements TextWatcher{
		 ArrayAdapter<String> adpt;
		 public TWatch(ArrayAdapter<String> adpt){
			 super();
			 this.adpt=adpt;
		 }
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			adpt.getFilter().filter(s);
			// TODO Auto-generated method stub
			
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			
		}
		 
	 }
}
