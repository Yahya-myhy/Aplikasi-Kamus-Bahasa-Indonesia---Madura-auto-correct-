package com.example.skripsi.cobain;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.MenuItem;

public class tampil_data extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tampil_data);

        dbcenter = new DataHelper(this);
        Refreshlist();
    }

    public void Refreshlist(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM datakamus",null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString() + "  =  " + cursor.getString(2).toString();
        }
        ListView01 = (ListView)findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Intent a = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(a);
                tampil_data.this.finish();
                return true;
            case R.id.item2:
                Intent b = new Intent(getApplicationContext(), tambah_data.class);
                startActivity(b);
                tampil_data.this.finish();
                return true;
            case R.id.item3:
                return true;
            case R.id.item4:
                Intent c = new Intent(getApplicationContext(), tentang_plikasi.class);
                startActivity(c);
                tampil_data.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
