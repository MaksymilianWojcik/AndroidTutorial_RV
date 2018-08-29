package com.example.mwojcik.speechapitest;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Item implements Serializable {

    private String name;
    private String imageURL;

    public Item(String name, String imageURL){
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }


    public static List<Item> getItemsFromRest(Context context){
        //RequestQueue queue = NetworkClient.getInstance(context.getApplicationContext()).getRequestQueue();
        //TUTAJ ZROBIC REQUESTA JSONARRAY ITP
        //NetworkClient.getInstance(context).addToRequestQueue(stringRequest);
        List<Item> itemsList = new ArrayList<>();
        itemsList.add(new Item("maks1", "makswww1"));
        itemsList.add(new Item("maks2", "makswww2"));
        itemsList.add(new Item("maks3", "makswww3"));
        itemsList.add(new Item("maks4", "makswww4"));
        itemsList.add(new Item("maks5", "makswww5"));
        itemsList.add(new Item("maks6", "makswww6"));
        itemsList.add(new Item("maks7", "makswww7"));
        return itemsList;
    }
}
