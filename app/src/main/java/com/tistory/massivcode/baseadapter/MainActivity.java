package com.tistory.massivcode.baseadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Model> data = new ArrayList<>();
        // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        for (int i = 0; i < 10; i++) {
            data.add(new Model("title " + i, "contents " + i));
        }

        final ModelAdapter adapter = new ModelAdapter(data, BaseRecyclerAdapter.ViewType.HEADER_FOOTER);
        adapter.setHeaderItem(new HeaderItem("헤더 텍스트1", "헤더 텍스트2"));
        adapter.setFooterItem(new FooterItem("푸터 텍스트1", "푸터 텍스트2"));
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);


    }
}
