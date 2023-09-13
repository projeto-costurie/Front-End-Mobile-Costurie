package br.senai.sp.jandira.costurie_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.costurie_app.components.CaixaDeTexto
import br.senai.sp.jandira.costurie_app.components.GradientButton
import br.senai.sp.jandira.costurie_app.ui.theme.Contraste
import br.senai.sp.jandira.costurie_app.ui.theme.Costurie_appTheme
import br.senai.sp.jandira.costurie_app.ui.theme.Destaque1
import br.senai.sp.jandira.costurie_app.ui.theme.Destaque2
import br.senai.sp.jandira.costurie_app.ui.theme.Principal1
import br.senai.sp.jandira.costurie_app.ui.theme.Principal2

class PasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Costurie_appTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PasswordScreen()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordScreen() {
    var textstate2 by remember { mutableStateOf("") }

    Surface (
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ){
        Column (
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Box (
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Image(
                    painter = painterResource(id = R.drawable.forma_topo_recuperar_a_senha),
                    contentDescription = "",
                    modifier = Modifier
                        .width(177.dp)
                        .height(272.dp),
                    alignment = Alignment.TopStart

                )
                Image(
                    painter = painterResource(id = R.drawable.forma_baixo_recuperar_a_senha),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.BottomEnd
                )
                Box (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(id = R.drawable.modal_redefinir_senha),
                        contentDescription = "",
                        alignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(100.dp))
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent
                            )
                        ) {
                            Column(Modifier.padding(end = 250.dp)) {
                                Image(
                                    painter = painterResource(id = R.drawable.arrow_back),
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(35.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Column(
                            modifier = Modifier.height(500.dp),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                color = Color.White,
                                text = stringResource(R.string.recuperar_senha).uppercase(),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
//                            Image(
//                                painter = painterResource(id = R.drawable.recuperar_senha),
//                                contentDescription = "",
//                                modifier = Modifier.width(178.dp).height(12.dp)
//                            )
                            Image(
                                painter = painterResource(id = R.drawable.costureira2),
                                contentDescription = "",
                                alignment = Alignment.Center,
                                modifier = Modifier.width(200.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.verificacao_email),
                                modifier = Modifier
                                    .width(247.dp),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp
                            )
                            OutlinedTextField(
                                value = textstate2,
                                onValueChange = { textstate2 = it },
                                label = { Text(stringResource(id = R.string.email_label), fontSize = 15.sp)},
                                colors = TextFieldDefaults.textFieldColors(
                                    unfocusedLabelColor = Color.Black,
                                    cursorColor = Color.Black,
                                    focusedLabelColor = Color.Black,
                                    textColor = Color.Black,
                                    containerColor = Principal2,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    focusedIndicatorColor = Color.Transparent
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            GradientButton(
                                onClick = {  },
                                text = stringResource(id = R.string.texto_botao_enviar).uppercase(),
                                color1 = Destaque1,
                                color2 = Destaque2
                            )
                        }
                    }
                }
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PasswordScreenPreview() {
    PasswordScreen()
}