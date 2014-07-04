package com.example.agriexpensett;

import fragments.ChoosePurchase;
import fragments.FragmentEmpty;
import fragments.FragmentViewCycles;
import fragments.FragmentViewResources;
import helper.DbHelper;
import helper.DbQuery;

import java.util.ArrayList;

import com.example.agriexpensett.ViewNavigation.TabListener;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.ActionBar.Tab;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class DeleteData extends ActionBarActivity {

	ArrayList<localCycle> li;
	ArrayList<localResourcePurchase> pli;
	DbHelper dbh;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//ActionBar.NavigationMode = ActionBarNavigationMode.Tabs;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_navigation);
		//for empty lists
		dbh=new DbHelper(DeleteData.this);
		db=dbh.getReadableDatabase();
		li=new ArrayList<localCycle>();
		DbQuery.getCycles(db, dbh, li);
		pli=new ArrayList<localResourcePurchase>();
		DbQuery.getResourcePurchases(db, dbh, pli, null, null);
		
		TabListener tL=new TabListener();
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.Tab t1 = getActionBar().newTab();
		t1.setText("Purchases");
    	t1.setTabListener(tL);
    	ActionBar.Tab t2 = getActionBar().newTab();
    	t2.setText("Resources");
    	t2.setTabListener(tL);
    	ActionBar.Tab t3 = getActionBar().newTab();
    	t3.setText("Cycles");
    	t3.setTabListener(tL);
    	
    	getActionBar().addTab(t3);
    	getActionBar().addTab(t1);
    	getActionBar().addTab(t2);
    	
	}
	
	public class TabListener implements ActionBar.TabListener{
		ActionBarActivity mActivity;
		Fragment currFragment;
		
		@Override
		public void onTabReselected(Tab arg0, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			ListFragment nextFrag1=null;
			Fragment n2=null;
			Bundle data=new Bundle();
			if(currFragment==null){
				if(li.isEmpty()){
					n2=new FragmentEmpty();
					data.putString("type", "cycle");
					n2.setArguments(data);
					ft.add(R.id.navContent, n2);
				}else{
					n2=new FragmentViewCycles();
					data.putString("type", "delete");
					n2.setArguments(data);
					ft.add(R.id.navContent, n2);
				}
				currFragment=n2;
				return;
			}
			if(tab.getText().toString().equals("Purchases")){
				if(pli.isEmpty()){
					n2=new FragmentEmpty();
					data.putString("type", "purchase");
				}else{
					n2=new ChoosePurchase();
					data.putString("det", "delete");
				}
			}else if(tab.getText().toString().equals("Cycles")){
				if(li.isEmpty()){
					n2=new FragmentEmpty();
					data.putString("type", "cycle");
				}else{
					n2=new FragmentViewCycles();
					data.putString("type", "delete");
				}
			}else if(tab.getText().toString().equals("Resources")){
				n2=new FragmentViewResources();
				data.putString("type", "delete");
				n2.setArguments(data);
			}
			currFragment=n2;
			n2.setArguments(data);
			ft.replace(R.id.navContent, n2);
		}

		@Override
		public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
			// TODO Auto-generated method stub
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete_data, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}