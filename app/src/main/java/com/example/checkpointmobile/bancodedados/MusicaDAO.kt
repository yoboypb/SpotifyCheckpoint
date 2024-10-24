package com.example.checkpointmobile.bancodedados

import android.content.Context
import android.util.Log
import com.example.checkpointmobile.model.Musica
import java.lang.Exception

class MusicaDAO(context:Context):IMusicaDAO {
    val escrita = DatabaseHelper(context).writableDatabase
    val leitura = DatabaseHelper(context).readableDatabase

    override fun salvar(musica: Musica): Boolean {
        val nomeMusica = musica.titulo
        val artista = musica.artista
        val genero = musica.genero
        val gravadora = musica.gravadora
        val duracao = musica.duracao

        try {
            val sql = "INSERT INTO ${DatabaseHelper.TABELA_MUSICAS} VALUES(null,'$nomeMusica','$artista','$genero','$gravadora','$duracao')"
            escrita.execSQL(sql)
            Log.i("db_info", "Registro salvo")
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }

    override fun atualizar(musica: Musica): Boolean {
        val nomeMusica = musica.titulo

        try {
            val sql = "UPDATE ${DatabaseHelper.TABELA_MUSICAS} SET ${DatabaseHelper.TITULO} = '$nomeMusica' WHERE ${DatabaseHelper.ID_MUSICA}=2"
            escrita.execSQL(sql)
            Log.i("db_info", "Registro atualizado com sucesso")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("db_info", "Registro não atualizado")
            return false
        }

        return true
    }

    override fun remover(idMusica: Int): Boolean {
        val sql = "DELETE FROM ${DatabaseHelper.TABELA_MUSICAS} WHERE ${DatabaseHelper.ID_MUSICA}= $idMusica"

        try {
            escrita.execSQL(sql)
            Log.i("db_info","Sucesso ao remover")
        }catch (e:Exception){
            e.printStackTrace()
            Log.i("db_info","Error ao remover")
            return false
        }

        return true
    }

    override fun listar(): List<Musica> {
        val listaMusica = mutableListOf<Musica>()

        val sql = "SELECT * FROM ${DatabaseHelper.TABELA_MUSICAS}"
        val cursor = leitura.rawQuery(sql,null)

        val indiceMusica = cursor.getColumnIndex("${DatabaseHelper.ID_MUSICA}")
        val indiceTitulo = cursor.getColumnIndex("${DatabaseHelper.TITULO}")
        val indiceArtista = cursor.getColumnIndex("${DatabaseHelper.ARTISTA}")
        val indiceGenero = cursor.getColumnIndex("${DatabaseHelper.GENERO}")
        val indiceDuracao = cursor.getColumnIndex("${DatabaseHelper.DURACAO}")
        val indiceGravadora = cursor.getColumnIndex("${DatabaseHelper.GRAVADORA}")



        while(cursor.moveToNext()){
            val idMusica = cursor.getInt(indiceMusica)
            val titulo = cursor.getString(indiceTitulo)
            val artista = cursor.getString(indiceArtista)
            val genero = cursor.getString(indiceGenero)
            val duracao = cursor.getInt(indiceDuracao)
            val gravadora = cursor.getString(indiceGravadora)

            val musica = Musica(idMusica,titulo,artista, gravadora, duracao,genero )
            listaMusica.add(musica)

            //Log.i("db_info","Produto: $idProduto - $titulo - $descricao")
        }

        return listaMusica
    }
}