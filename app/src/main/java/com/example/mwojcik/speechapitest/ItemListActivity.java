package com.example.mwojcik.speechapitest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private List<Item> itemsList;
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;

    private int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }



//        itemsList = new ArrayList<>();
//        itemsList.add(new Item("maks1", "makswww1"));
//        itemsList.add(new Item("maks2", "makswww2"));
//        itemsList.add(new Item("maks3", "makswww3"));
        itemsList = Item.getItemsFromRest(this);

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;


        if (savedInstanceState != null) {
            selectedIndex = savedInstanceState.getInt("selectedIndex");
            Log.d("ItemList", "Selected index restored: " + selectedIndex);
        } else {
            Log.d("ItemList", "Selected index is not restored");
        }

        setupRecyclerView((RecyclerView) recyclerView, itemsList);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Item> itemsList) {
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(this, itemsList, mTwoPane, selectedIndex);
        recyclerView.setAdapter(itemRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemRecyclerViewAdapter.setItemRecyclerViewListener(new ItemRecyclerViewAdapter.ItemRecyclerViewListener() {
            @Override
            public void onItemSelected(int position) {
                Log.d("ItemList", "Received callback to save: " + position);
                selectedIndex = position;
            }
        });
    }

    private void fetchDataFromResponse(){
//        updateRecycerlView();
    }

    private void updateRecycerlView(List<Item> newItemsList){
        itemsList.clear();
        itemsList.addAll(newItemsList);
        itemRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("selectedIndex", selectedIndex);
        super.onSaveInstanceState(outState);
    }
}
