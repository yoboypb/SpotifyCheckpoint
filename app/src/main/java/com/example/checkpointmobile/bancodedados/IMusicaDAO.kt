package com.example.checkpointmobile.bancodedados

import com.example.checkpointmobile.model.Musica

interface IMusicaDAO {
    fun salvar(musica: Musica):Boolean
    fun atualizar(musica: Musica):Boolean
    fun remover(idMusica: Int):Boolean
    fun listar():List<Musica>
}