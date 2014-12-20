package br.com.tairoroberto.testeactionbar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Cria um TextView
		TextView textView = new TextView(getActivity());
		textView.setText("Fagment 2 em ação");
		return (textView);
	}
}
