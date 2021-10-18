package com.example.skripsi.cobain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class tentang_plikasi extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentang_aplikasi);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);
                tentang_plikasi.this.finish();
                return true;
            case R.id.item2:
                Intent b = new Intent(getApplicationContext(), tambah_data.class);
                startActivity(b);
                tentang_plikasi.this.finish();
                return true;
            case R.id.item3:
                Intent c = new Intent(getApplicationContext(), tampil_data.class);
                startActivity(c);
                tentang_plikasi.this.finish();
                return true;
            case R.id.item4:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
