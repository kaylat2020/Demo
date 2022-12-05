package com.example.vaulttechdemo.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.braintreepayments.cardform.view.CardForm;
import com.example.vaulttechdemo.MainActivity;
import com.example.vaulttechdemo.R;
import com.example.vaulttechdemo.databinding.FragmentProfileBinding;
import com.example.vaulttechdemo.ui.home.HomeFragment;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private Intent toTransactions = new Intent(String.valueOf(HomeFragment.class));
    public static boolean cardAdded = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //test text
        final TextView textView = binding.textProfile;
        profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //card stuff ig
        final CardForm cardForm = binding.cardForm;
        //cardForm = requireView().findViewById(R.id.card_form);
        cardForm.cardRequired(true);
        cardForm.expirationRequired(true);
        cardForm.cvvRequired(true);
        cardForm.cardholderName(CardForm.FIELD_REQUIRED);
        cardForm.postalCodeRequired(true);
        cardForm.mobileNumberRequired(true);
        cardForm.mobileNumberExplanation("SMS is required on this number");
        cardForm.actionLabel("Purchase");
        cardForm.setup(this.getActivity());

        return root;
    }

    public static boolean getCardStatus()
    {
        return cardAdded;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}