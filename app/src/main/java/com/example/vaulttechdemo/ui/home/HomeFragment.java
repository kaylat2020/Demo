package com.example.vaulttechdemo.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.vaulttechdemo.Transaction;
import com.example.vaulttechdemo.databinding.FragmentHomeBinding;
import com.example.vaulttechdemo.ui.profile.ProfileFragment;

import java.text.DecimalFormat;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Transaction[] allTransactions;
    private LinearLayout ll;
    private final DecimalFormat df = new DecimalFormat("#.##");

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        initialize();

        return root;
    }

    public void initialize()
    {
        //Scrollbar of Transactions
        ll = binding.ll;

        //hard coding transactions because im running low on time
        allTransactions = new Transaction[10];
        allTransactions[0] = new Transaction("-$67.85","11/29/2022","Walmart","VISA");
        allTransactions[1] = new Transaction("+$13.25","11/29/2022","Transfer from Venmo","VISA");
        allTransactions[2] = new Transaction("+$300.00","11/29/2022","Transfer","VISA");
        allTransactions[3] = new Transaction("-$9.99","11/28/2022","Discord","VISA");
        allTransactions[4] = new Transaction("-$17.60","11/22/2022","Property Insurance","VISA");
        allTransactions[5] = new Transaction("-$16.23","11/18/2022","Subway","VISA");
        allTransactions[6] = new Transaction("-$35.68","11/16/2022","Trading","VISA");
        allTransactions[7] = new Transaction("-$71.58","11/14/2022","Target","VISA");
        allTransactions[8] = new Transaction("-$14.69","11/14/2022","Subway","VISA");
        allTransactions[9] = new Transaction("-$7.99","11/7/2022","Crunchyroll","VISA");

        addToList();
    }
    public void addToList()
    {
        //this loop adds every score block from the ArrayList to the scrollable list as a text view
        ll.removeAllViews();
        if ( allTransactions != null )
        {
            for (Transaction allTransaction : allTransactions)
            {
                TextView current = new TextView( getContext() ); //initialized the text view
                current.setGravity(Gravity.START);
                current.setHeight(200);
                current.setTextColor(Color.BLACK); //sets the correct text color
                current.setTextSize(18); //sets the correct text size
                current.setText( allTransaction.toString() );

                ll.addView(current); //adds the the text view
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}