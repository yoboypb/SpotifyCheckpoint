package com.example.checkpointmobile.bancodedados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context:Context) : SQLiteOpenHelper(
    //1.Context
    //2.Nome do banco de dados
    //3.CursorFactory
    //4.versão

    context,"loja",null,1
) {
    companion object{
        const val TABELA_PRODUTOS = "produtos"
        const val ID_PRODUTO = "id_produto"
        const val TITULO = "titulo"
        const val DESCRICAO = "descricao"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        //É executado um única vez, quando o app é instalado
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_PRODUTOS(" +
                "$ID_PRODUTO INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$TITULO VARCHAR(100)," +
                "$DESCRICAO TEXT" +
                ");"

        try{
            db?.execSQL(sql)
            Log.i("db_info","Tabela criada com sucesso")
        }catch (e:Exception){
            e.printStackTrace()
            Log.i("db_info","Error ao criar tabela")
        }

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //É executado quando há mudança de versão do banco

    }
    fun getAllProducts(): List<Product> {
        val productsList = mutableListOf<Product>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABELA_PRODUTOS"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_PRODUTO))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(TITULO))
                val description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRICAO))

                val product = Product(id, title, description)
                productsList.add(product)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return productsList
    }
}
data class Product(
    val id: Int,
    val title: String,
    val description: String
)