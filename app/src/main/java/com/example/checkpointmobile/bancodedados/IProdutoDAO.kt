package com.example.checkpointmobile.bancodedados

import com.example.checkpointmobile.model.Produto

interface IProdutoDAO {
    fun salvar(produto:Produto):Boolean
    fun atualizar(produto:Produto):Boolean
    fun remover(idProduto:Int):Boolean
    fun listar():List<Produto>
}