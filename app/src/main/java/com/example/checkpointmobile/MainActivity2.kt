package com.example.checkpointmobile

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checkpointmobile.bancodedados.DatabaseHelper
import com.example.checkpointmobile.model.Musica
import com.example.checkpointmobile.bancodedados.MusicaDAO

class MainActivity2 : AppCompatActivity() {

    private val bancoDados by lazy {
        DatabaseHelper(this)
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var musicaAdapter: MusicaAdapter
    private lateinit var btnSalvar: Button
    private lateinit var btnListar: Button
    private lateinit var btnAtualizar: Button
    private lateinit var btnDeletar: Button
    private lateinit var editNomeMusica: EditText
    private lateinit var editNomeArtista: EditText
    private lateinit var editDuracao: EditText
    private lateinit var editNomeGravadora: EditText
    private lateinit var editGenero: EditText


override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Inicializando os componentes
        btnSalvar = findViewById(R.id.btnSalvar)
        btnListar = findViewById(R.id.btnListar)
        btnAtualizar = findViewById(R.id.btnAtualizar)
        btnDeletar = findViewById(R.id.btnDeletar)
        editNomeMusica = findViewById(R.id.editNomeMusica)
        editNomeArtista = findViewById(R.id.editNomeArtista)
        editGenero = findViewById(R.id.editGenero)
        editDuracao = findViewById(R.id.editDuracao)
        editNomeGravadora = findViewById(R.id.editNomeGravadora)


    // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Inicializar com uma lista vazia
        musicaAdapter = MusicaAdapter(emptyList())
        recyclerView.adapter = musicaAdapter

        // Adicionar eventos aos botões
        btnSalvar.setOnClickListener {
            salvar()
        }

        btnListar.setOnClickListener {
            listar()
        }

        btnAtualizar.setOnClickListener {
            atualizar()
        }

        btnDeletar.setOnClickListener {
            deletar()
        }
    }

    private fun salvar() {
        val nomeMusica = editNomeMusica.text.toString()
        val nomeArtista = editNomeArtista.text.toString()
        val nomeGravadora = editNomeGravadora.text.toString()
        val Duracao = editDuracao.top
        val Genero = editGenero.text.toString()


        val musicaDAO = MusicaDAO(this)
        val musica = Musica(-1, nomeMusica, nomeArtista, nomeGravadora, Duracao, Genero)
        musicaDAO.salvar(musica)
    }

    private fun listar() {
        val musicaDAO = MusicaDAO(this)

        // Obter a lista de produtos
        val listMusica = musicaDAO.listar()

        // Atualizar o adapter do RecyclerView com a nova lista de produtos
        musicaAdapter = MusicaAdapter(listMusica)
        recyclerView.adapter = musicaAdapter

        if (listMusica.isNotEmpty()) {
            listMusica.forEach { musica ->
                Log.i("db_info", "${musica.idMusica} - ${musica.titulo} - ${musica.artista}")
            }
        }
    }

    private fun atualizar() {
        val nomeMusica = editNomeMusica.text.toString()
        val nomeArtista = editNomeArtista.text.toString()
        val nomeGravadora = editNomeGravadora.text.toString()
        val Duracao = editDuracao.top
        val Genero = editGenero.text.toString()

        val musicaDAO = MusicaDAO(this)
        val musica = Musica(-1, nomeMusica, nomeArtista, nomeGravadora, Duracao, Genero)
        musicaDAO.atualizar(musica)
    }

    private fun deletar() {
        val musicaDAO = MusicaDAO(this)
        musicaDAO.remover(1)
    }
}
