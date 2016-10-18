package com.tistory.massivcode.baseadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Model> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new Model("title " + i, "contents " + i));
        }

        final ModelAdapter adapter = new ModelAdapter(data);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "아이템 클릭함: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnRecyclerItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "아이템 롱클릭함!: " + position, Toast.LENGTH_SHORT).show();
                adapter.updateItem(position, new Model("123s", "dad"));
            }
        });
    }
}
