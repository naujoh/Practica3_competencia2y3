package web.topicos.juho.clientsapp.fragments;


import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import web.topicos.juho.clientsapp.R;

public class ProfileFragment extends Fragment implements View.OnClickListener{
    private int client_id;
    private String name, email, phone, address;
    TextInputEditText tieName, tieEmail, tieAddress, tiePhone;
    Button btnShowCoupons;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        tieName = (TextInputEditText) view.findViewById(R.id.tieName);
        tieName.setText(name);
        tieEmail = (TextInputEditText) view.findViewById(R.id.tieEmail);
        tieEmail.setText(email);
        tiePhone = (TextInputEditText) view.findViewById(R.id.tiePhone);
        tiePhone.setText(phone);
        tieAddress = (TextInputEditText) view.findViewById(R.id.tieAddress);
        tieAddress.setText(address);
        btnShowCoupons = (Button) view.findViewById(R.id.btnShowCoupons);
        btnShowCoupons.setOnClickListener(this);
        return view;
    }

    public void setClientId(int id){
        this.client_id = id;
    }

    public int getClientId() {
        return client_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnShowCoupons) {
            Bundle params = new Bundle();
            params.putInt("clientId", this.client_id);
            Intent i = new Intent(getActivity(),  web.topicos.juho.clientsapp.activities.CouponsActivity.class);
            i.putExtras(params);
            startActivity(i);
        }
    }
}
