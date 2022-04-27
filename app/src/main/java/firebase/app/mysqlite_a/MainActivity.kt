package firebase.app.mysqlite_a

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_save).setOnClickListener{
            val db = DBHelper(this, null)
            val nombre = findViewById<EditText?>(R.id.edt_nombre).text.toString()
            val edad  = findViewById<EditText?>(R.id.edt_edad).text.toString()

            db.addName(nombre, edad)

            // Toast to message on the screen
            Toast.makeText(this, nombre + " Se inserto con exito", Toast.LENGTH_LONG).show()
            findViewById<EditText?>(R.id.edt_nombre).text.clear()
            findViewById<EditText?>(R.id.edt_edad).text.clear()

        }

        findViewById<Button>(R.id.btn_show).setOnClickListener{

            val db = DBHelper(this,null)
            val cursor = db.getName()
            cursor!!.moveToFirst()
            val nombre = findViewById<TextView>(R.id.txv_nombre)
            val edad = findViewById<TextView>(R.id.txv_edad)
            nombre.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL))+ "\n")
            edad.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))+ "\n")


            while (cursor.moveToNext()){
                nombre.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COL))+ "\n")
                edad.append(cursor.getString(cursor.getColumnIndex(DBHelper.AGE_COL))+ "\n")

            }

            cursor.close()

        }


    }
}