package br.senai.sp.jandira.costurie_app.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class EstadoViewModel : ViewModel() {
    var estadoSelecionado: String by mutableStateOf("")
}
