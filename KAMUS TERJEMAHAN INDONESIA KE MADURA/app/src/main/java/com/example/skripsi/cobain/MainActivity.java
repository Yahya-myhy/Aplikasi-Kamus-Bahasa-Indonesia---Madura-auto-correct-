package com.example.skripsi.cobain;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView adb, hasilinput, perbaikan;
    EditText in;
    private DataHelper dbHelper;
    private Cursor cursor,c1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);

        dbHelper = new DataHelper(getApplicationContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        in = (EditText) findViewById(R.id.edtxt_input);
        hasilinput = (TextView) findViewById(R.id.katainput);
        perbaikan = (TextView) findViewById(R.id.perbaikan);
        btn1 = (Button) findViewById(R.id.btn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasilinput.setText(in.getText().toString());
                //levenstain
                String input= in.getText().toString();
                cursor = db.rawQuery("SELECT * FROM datakamus", null);
                cursor.moveToFirst();
                int jumlah_data = cursor.getCount();
                float[]a = new float[jumlah_data];
                for (int i=0; i<jumlah_data; i++){
                    String uji = cursor.getString(1);
                    //berhubungan dengan algorithm.java (untuk memanggil)
                    algorithm algo = new algorithm(uji, input);
                    a[i]=algo.editDistance(uji, input);
                    cursor.moveToNext();
                }

                float maksimal = 0;
                int id = 0;
                for (int i=0; i<jumlah_data;i++){
                    if (maksimal<a[i]){
                        maksimal=a[i];
                    }
                }

                for (int i=0; i<jumlah_data;i++){
                    if (a[i]==maksimal){
                        id=i+1;
                    }
                }
                //pencocokan (pencarian dikamus)
                c1= db.query("datakamus",null, "id=="+id, null, null, null, null);
                c1.moveToFirst();
                String hail=c1.getString(2).toString();
                perbaikan.setText(c1.getString(1));
                adb= (TextView) findViewById(R.id.hasil);
                adb.setText(hail);

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                return true;

            case R.id.item2:
                Intent b = new Intent(getApplicationContext(), tambah_data.class);
                startActivity(b);
                MainActivity.this.finish();
                return true;

            case R.id.item3:
                Intent c = new Intent(getApplicationContext(), tampil_data.class);
                startActivity(c);
                MainActivity.this.finish();
                return true;

            case R.id.item4:
                Intent d = new Intent(getApplicationContext(), tentang_plikasi.class);
                startActivity(d);
                MainActivity.this.finish();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
