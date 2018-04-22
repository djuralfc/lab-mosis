package com.djuralfc.myplaces;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class    EditMyPlaceActivity extends AppCompatActivity implements  View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_place);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button finishedButton = (Button) findViewById(R.id.editmyplace_finished_button);
        finishedButton.setOnClickListener(this);
        Button cancelButton = (Button) findViewById(R.id.editmyplace_cancel_button);
        cancelButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.editmyplace_finished_button:{
            EditText etName = (EditText) findViewById(R.id.editmyplace_name_edit);
            String name = etName.getText().toString();
            EditText etDesc = (EditText) findViewById(R.id.editmyplace_desc_edit);
            String desc = etDesc.getText().toString();
            MyPlace place = new MyPlace(name, desc);
            MyPlacesData.getInstance().addNewPlace(place);
            setResult(Activity.RESULT_OK);
            finish();
            break;
            }

            case R.id.editmyplace_cancel_button:{
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
            }
        }

    }

}
