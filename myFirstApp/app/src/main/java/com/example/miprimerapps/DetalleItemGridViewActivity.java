package com.example.miprimerapps;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleItemGridViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_item_gridview);

        this.getSupportActionBar().setTitle(getIntent().getExtras().get("nombre").toString());

    }
}
