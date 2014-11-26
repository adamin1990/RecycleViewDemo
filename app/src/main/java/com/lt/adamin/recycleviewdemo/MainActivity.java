package com.lt.adamin.recycleviewdemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeLayout;
    private DemoAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        swipeLayout=(SwipeRefreshLayout)findViewById(R.id.swipeLayout);
        adapter=new DemoAdapter(Constants.picUrls);
        linearLayoutManager =new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         gridLayoutManager=new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        adapter.setmOnItemClickLitener(new DemoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Toast.makeText(MainActivity.this,String.valueOf(position),Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(adapter);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.stagered) {
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            adapter.notifyDataSetChanged();
            return true;
        }
        else if(id==R.id.gridlayout){
            recyclerView.setLayoutManager(gridLayoutManager);
            adapter.notifyDataSetChanged();
            return  true;
        }
        else if(id==R.id.action_settings)
        {
            recyclerView.setLayoutManager(linearLayoutManager);
            adapter.notifyDataSetChanged();
            return true;
        }
        else if(id==R.id.photo){
            Intent intent=new Intent();
            intent.setClass(MainActivity.this,photo.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
