package com.example.crimemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class ZoneInformation extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zone_information);
        pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("Zones.pdf")
                .scrollHandle(new DefaultScrollHandle(this))
                .load();

    }
}