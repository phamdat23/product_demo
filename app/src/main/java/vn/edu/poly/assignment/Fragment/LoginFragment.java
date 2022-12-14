package vn.edu.poly.assignment.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import vn.edu.poly.assignment.DAO.DAO_AC;
import vn.edu.poly.assignment.DTO.DTOAC;
import vn.edu.poly.assignment.MainActivity;
import vn.edu.poly.assignment.ManHinhChinhActivity;
import vn.edu.poly.assignment.R;


public class LoginFragment extends Fragment {
    private TextInputLayout etUser;
    private TextInputLayout etPass;
    private Button btnLogin;
    private TextView tvQuenMk;
    private TextView tvCreateAc;
    ArrayList<DTOAC> list_ac;
    DAO_AC daoAc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_dn, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        tvQuenMk = (TextView) view.findViewById(R.id.tv_quen_mk);
        tvCreateAc = (TextView) view.findViewById(R.id.tv_create_ac);
        etUser = view.findViewById(R.id.et_user);
        etPass = view.findViewById(R.id.et_pass);
        daoAc = new DAO_AC(getContext());
        daoAc.opend();
        list_ac = daoAc.getALLAC();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list_ac.size(); i++) {
                    if (etUser.getEditText().getText().toString().trim().equals(list_ac.get(i).getUserName()) && etPass.getEditText().getText().toString().equals(list_ac.get(i).getPassWord())) {
                        etUser.setError("");
                        etPass.setError("");
                        Intent intent = new Intent(getContext(), ManHinhChinhActivity.class);
                        startActivity(intent);
                        break;
                    } else {
                        check(i);
                    }
                }
            }
        });
        tvCreateAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_content1, new SinginFragment());
                transaction.commit();
            }
        });
        tvQuenMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_content1, new Fragment_Update_MK());
                transaction.commit();
            }
        });

    }
    public  boolean check( int i){
        boolean a= true;
        if(etUser.getEditText().getText().toString().equals("")){
            etUser.setError("H??y nh???p t??n t??i kho???n");
            a=false;
        }else if(!etUser.getEditText().getText().toString().equals(list_ac.get(i).getUserName())){
            etUser.setError("T??n t??i kho???n kh??ng ????ng");
            a=false;
        }else{
            etUser.setError("");
            a=true;
        }
        if(etPass.getEditText().getText().toString().equals("")){
            etPass.setError("M???t kh???u kh??ng ???????c ????? tr???ng");
            a=false;
        }else if(!etPass.getEditText().getText().toString().equals(list_ac.get(i).getPassWord())){
            etPass.setError("M???t kh???u kh??ng ????ng");
            a=false;
        }else{
            etPass.setError("");
            a=true;
        }
        return a;
    }



}