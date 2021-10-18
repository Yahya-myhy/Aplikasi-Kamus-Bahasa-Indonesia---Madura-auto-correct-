package com.example.skripsi.cobain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class kamus extends AppCompatActivity {

    private TextView adb;
    private EditText in;
    private DataHelper dbHelper;
    private Cursor cursor,c1;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus);

        dbHelper = new DataHelper(getApplicationContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        in = (EditText) findViewById(R.id.edtxt_input);
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //levenstain
                String input= in.getText().toString();
                //untuk mengambil data di data base
                cursor = db.rawQuery("SELECT * FROM datakamus", null);
                //untuk mengambil data pertama
                cursor.moveToFirst();
                //mengambil total baris
                int jumlah_data = cursor.getCount();
                //isi nilai similary
                float[]a = new float[jumlah_data];
                for (int i=0; i<jumlah_data; i++){
                    String uji = cursor.getString(1);
                    algorithm algo = new algorithm(uji, input);
                    a[i]=algo.editDistance(uji, input);
                    //untuk mengambil data di bawahnya moveToFirst
                    cursor.moveToNext();
                }

                //mencari similary terbesar
                float maksimal = 0;
                int id = 0;
                for (int i=0; i<jumlah_data;i++){
                    //jika masimal kurang dari array ke i. maka nilai maksimal dirubah menjadi nilai array ke i
                    if (maksimal<a[i]){
                        maksimal=a[i];
                    }
                }
                    //untuk mengambil letak max pada array, untuk di jdkan sleksi pada database (mengambil id)
                for (int i=0; i<jumlah_data;i++){
                    if (a[i]==maksimal){
                        id=i+1;
                    }
                }

                //pencocokan (pengambilan data berdasarkan id)
                c1= db.query("datakamus",null, "id=="+id, null, null, null, null);
                c1.moveToFirst();
                String hasil=c1.getString(2).toString();
                in.setText(c1.getString(1));
                adb= (TextView) findViewById(R.id.hasil);
                adb.setText(hasil);
                c1.close();
            }
        });


    }
}
