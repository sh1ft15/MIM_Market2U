package com.example.yeong.market2u.MIM_SearchProduct;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yeong.market2u.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductMenuFragment extends Fragment {


    public ProductMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_menu, container, false);
    }

}
