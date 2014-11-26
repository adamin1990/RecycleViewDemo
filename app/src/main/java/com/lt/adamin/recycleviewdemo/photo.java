package com.lt.adamin.recycleviewdemo;

import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;


public class photo extends ActionBarActivity {
  private RecyclerView recyclerView;
  private DemoAdapter adapter;
    private String[] urls;
    private ImageView imageView;
    private View mCurrentView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        recyclerView=(RecyclerView)findViewById(R.id.id_recyclerview_horizontal);
        imageView=(ImageView)findViewById(R.id.id_content);
        urls=Constants.picUrls;
        LinearLayoutManager magager=new LinearLayoutManager(this);
        magager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(magager);
        adapter=new DemoAdapter(urls);
        recyclerView.setAdapter(adapter);
        mCurrentView=recyclerView.getChildAt(0);
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View newView = recyclerView.getChildAt(0);
                int position=recyclerView.getChildPosition(newView);
                ImageLoader.getInstance().displayImage(urls[position],imageView);
//                if (newView != null && newView != mCurrentView){
//
//                }
            }
        });
        adapter.setmOnItemClickLitener(new DemoAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                ImageLoader.getInstance().displayImage(urls[position],imageView);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
