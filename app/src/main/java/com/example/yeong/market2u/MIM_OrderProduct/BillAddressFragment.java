package com.example.yeong.market2u.MIM_OrderProduct;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.yeong.market2u.R;

public class BillAddressFragment extends Fragment {
    public BillAddressFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_bill_address, container, false);
        addItemInSpinnerProvince(rootView);
        return rootView;
    }

    // To initiate the spinner of province
    private void addItemInSpinnerProvince(View rootView)
    {
        Spinner spinnerProvince = (Spinner) rootView.findViewById(R.id.dlBillProvince);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (getActivity(),R.array.spinnerProvince,android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapter);
    }
}
