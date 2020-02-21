package com.example.lightnovel.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lightnovel.R;


public class FakeSearchFragment extends Fragment {

    private Button button;
    private EditText editText;
    private Intent intent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_fake,container,false);
        initView(view);
        return view;
    }
    private void initView(View view){
        button=view.findViewById(R.id.search_all_button);
        editText=view.findViewById(R.id.search_all);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.search_all_button:
                        System.out.println("咳咳咳");
                        Toast.makeText(getActivity(),"由于技术力原因，无法实现原生搜索功能",Toast.LENGTH_SHORT).show();
//                      String message=editText.getText().toString();
//                      intent.setClass(getActivity(),);
//                      intent.putExtra("search_url",message);
//                      startActivity(intent);
                        break;
                }
            }
        });
    }
}
