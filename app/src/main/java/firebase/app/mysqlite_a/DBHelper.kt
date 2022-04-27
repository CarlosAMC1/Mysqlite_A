package firebase.app.mysqlite_a

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLData

class DBHelper (context: Context,factory:SQLiteDatabase.CursorFactory?):
SQLiteOpenHelper(context,DATABASE_NAME, factory,DATABASE_VERSION)

{
    companion object{
        private val DATABASE_NAME = "SQLITE_EJEMPLO"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "edades_tabla"
        val ID_COL = "id"
        val NAME_COL = "name"
        val AGE_COL = "age"

    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query = ("CREATE TABLE "+ TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, "+
                NAME_COL + " TEXT,"+
                AGE_COL + " TEXT)"
                )
        if (p0 != null) {
            p0.execSQL(query)
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        if (p0 != null){
            p0.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
            onCreate(p0)
        }
    }

    fun addName(name:String,age:String){

        val values = ContentValues()
        values.put(NAME_COL,name)
        values.put(AGE_COL,age)
        val db = this.writableDatabase
        db.insert(TABLE_NAME, null,values)
        db.close()
    }
    fun getName():Cursor?{
        val db = this.readableDatabase
        return db.query(TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)
    }

}