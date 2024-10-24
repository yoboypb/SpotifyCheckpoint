package com.example.checkpointmobile.bancodedados

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import kotlin.time.Duration

class DatabaseHelper(context:Context) : SQLiteOpenHelper(

    context,"biblioteca",null,1
) {
    companion object{
        const val TABELA_MUSICAS = "musicas"
        const val ID_MUSICA = "id_musica"
        const val TITULO = "titulo"
        const val ARTISTA = "artista"
        const val GRAVADORA = "gravadora"
        const val DURACAO = "duracao"
        const val GENERO = "genero"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        //É executado um única vez, quando o app é instalado
        val sql = "CREATE TABLE IF NOT EXISTS $TABELA_MUSICAS(" +
                "$ID_MUSICA INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "$TITULO VARCHAR(100)," +
                "$ARTISTA TEXT," +
                "$GRAVADORA TEXT," +
                "$DURACAO NUMBER(100)," +
                "$GENERO TEXT" +
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
    fun getAllMusics(): List<Musica> {
        val musicasList = mutableListOf<Musica>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABELA_MUSICAS"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(ID_MUSICA))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(TITULO))
                val artist = cursor.getString(cursor.getColumnIndexOrThrow(ARTISTA))
                val recorder = cursor.getString(cursor.getColumnIndexOrThrow(GRAVADORA))
                val duration = cursor.getInt(cursor.getColumnIndexOrThrow(DURACAO))
                val gender = cursor.getString(cursor.getColumnIndexOrThrow(GENERO))


                val musica = Musica(id, title, artist, recorder, duration, gender)
                musicasList.add(musica)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return musicasList
    }
}
data class Musica(
    val id: Int,
    val title: String,
    val artist: String,
    val recorder: String,
    val duration: Int,
    val gender: String
)