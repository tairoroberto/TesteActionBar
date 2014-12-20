package br.com.tairoroberto.testeactionbar;




import android.R.integer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

@SuppressWarnings("deprecation")
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg));
		
		//TABS
		/*Define o modo de navegação na ActionBar*/
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//define as tabs
		Tab tab1 = actionBar.newTab();
		tab1.setText("tab 1");
		tab1.setIcon(android.R.drawable.ic_menu_today);
		tab1.setTabListener(new NavegacaoTabs(new Fragment1()));
		
		Tab tab2 = actionBar.newTab();
		tab2.setText("tab 2");
		tab2.setIcon(android.R.drawable.ic_menu_upload);
		tab2.setTabListener(new NavegacaoTabs(new Fragment2()));
		
		Tab tab3 = actionBar.newTab();
		tab3.setText("tab 3");
		tab3.setIcon(android.R.drawable.ic_menu_search);
		tab3.setTabListener(new NavegacaoTabs(new Fragment3()));
		
		//Adiciona as tabs ao ActionBar
		actionBar.addTab(tab1,false);
		actionBar.addTab(tab2,false);
		actionBar.addTab(tab3,false);
		
		//verifica se há um instancia salva
		if (savedInstanceState != null) {
			int indiceTab = savedInstanceState.getInt("indiceTab");
			getSupportActionBar().setSelectedNavigationItem(indiceTab);
		}else{
			getSupportActionBar().setSelectedNavigationItem(0);
		}
	}
	
	private  class NavegacaoTabs implements ActionBar.TabListener{
		private Fragment fragment;
			
		public NavegacaoTabs(Fragment fragment) {
			this.fragment = fragment;
		}
		
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction fragTran) {
			// Quando a tab é selecionada novamente, quando ele ja está visivél
			Log.i("Script", "Tab: " + tab + "selecionada novamente");
			
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction fragTran) {
			// Quando a tab é selecionada 
			Log.i("Script", "Tab: " + tab + "selecionada");
			FragmentTransaction frTransaction = getSupportFragmentManager().beginTransaction();
			frTransaction.replace(R.id.fragmentContainer, this.fragment);
			frTransaction.commit();
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction fragTran) {
			// Quando outra tab for selecionada devemos remover este fragment 
			Log.i("Script", "Tab: " + tab + "removida");
			FragmentTransaction frTransaction = getSupportFragmentManager().beginTransaction();
			frTransaction.remove(this.fragment);
			frTransaction.commit();
			
		}


	}
	
	//Salva o estado dos objetos
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putInt("indiceTab", getSupportActionBar().getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
