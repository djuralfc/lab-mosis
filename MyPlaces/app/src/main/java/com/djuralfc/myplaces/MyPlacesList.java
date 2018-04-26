package com.djuralfc.myplaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.djuralfc.myplaces.MyPlacesData.*;

public class MyPlacesList extends AppCompatActivity {

    ArrayList<String> places;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_places_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final ListView myPlacesList = (ListView) findViewById(R.id.my_places_list);
        myPlacesList.setAdapter(new ArrayAdapter<MyPlace>(this, android.R.layout.simple_list_item_1, MyPlacesData.getInstance().getMyPlaces()));


        myPlacesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle positionBundle = new Bundle();
                positionBundle.putInt("position", position);
                Intent i = new Intent(MyPlacesList.this, ViewMyPlacesActivity.class);
                i.putExtras(positionBundle);
                startActivity(i);

                myPlacesList.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener()
                {
                    @Override
                    public void onCreateContextMenu(ContextMenu contextmenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo)
                    {
                        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)contextMenuInfo;
                        MyPlace place = MyPlacesData.getInstance().getPlace(info.position);
                        contextmenu.setHeaderTitle(place.getName());
                        contextmenu.add(0,1,2, "View Place");
                        contextmenu.add(0,1,2, "Edit Place");
                    }

                });
            }
        });
    }
@Override
public boolean onContextItemSelected (MenuItem item)
{
    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
    Bundle positionBundle = new Bundle();
    positionBundle.putInt("position", info.position);
    Intent i = null;
    if (item.getItemId() == 1) {
        i = new Intent(this, ViewMyPlacesActivity.class);
        i.putExtras(positionBundle);
        startActivity(i);
    }
        else if (item.getItemId()==2)
    {
        i = new Intent(this, EditMyPlaceActivity.class);
        i.putExtras(positionBundle);
        startActivity(i);
    }
    return super.onContextItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_places_list, menu);
        return true;
    }

    static  int NEW_PLACE = 1;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.show_map_item) {
            Toast.makeText(this, "Show Map!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.new_place_item) {
            Intent i = new Intent(this, EditMyPlaceActivity.class);
            startActivityForResult(i, NEW_PLACE);
            Toast.makeText(this, "New Place!", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.about_item) {
            Intent i = new Intent(this, About.class);
            startActivity(i);
        }
        else if (id == android.R.id.home)
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected  void onActivityResult (int requestCode , int resultCode, Intent data){

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
        {
            ListView myPlacesList = (ListView) findViewById(R.id.my_places_list);
            myPlacesList.setAdapter(new ArrayAdapter<MyPlace>(this, android.R.layout.simple_list_item_1,
                    MyPlacesData.getInstance().getMyPlaces()));

        }
    }
}
