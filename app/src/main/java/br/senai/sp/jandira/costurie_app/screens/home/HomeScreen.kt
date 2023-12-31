package br.senai.sp.jandira.costurie_app.screens.home

import ProfileScreen
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.senai.sp.jandira.costurie_app.R
import br.senai.sp.jandira.costurie_app.Storage
import br.senai.sp.jandira.costurie_app.components.TextMenuBar
import br.senai.sp.jandira.costurie_app.models_private.User
import br.senai.sp.jandira.costurie_app.screens.chats.ChatListScreen
import br.senai.sp.jandira.costurie_app.screens.explore.ExploreScreen
import br.senai.sp.jandira.costurie_app.screens.publish.PublishScreen
import br.senai.sp.jandira.costurie_app.screens.services.ServicesScreen
import br.senai.sp.jandira.costurie_app.service.chat.ChatClient
import br.senai.sp.jandira.costurie_app.service.chat.view_model.ChatViewModel
import br.senai.sp.jandira.costurie_app.sqlite_repository.UserRepositorySqlite
import br.senai.sp.jandira.costurie_app.ui.theme.Costurie_appTheme
import br.senai.sp.jandira.costurie_app.viewModel.UserTagViewModel
import br.senai.sp.jandira.costurie_app.viewModel.UserViewModel
import br.senai.sp.jandira.costurie_app.viewModel.UserViewModel2
import io.socket.client.Socket
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModelUserViewModel: UserViewModel2,
    chatViewModel: ChatViewModel,
    client: ChatClient,
    socket: Socket,
    idUsuario: Int,
) {

    val localStorage: Storage = Storage()

    val context = LocalContext.current

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    val screen = localStorage.lerValor(context, "currentScreen")

    Log.i("current", "screen ${screen}")

    var currentScreen by remember {
        mutableStateOf(screen)
    }

    val items = listOf(
        BottomnavigationBarItem(
            route = "explore",
            selected = "Home",
            unselected = painterResource(id = R.drawable.home_icon),
            hasNews = false
        ),
        BottomnavigationBarItem(
            route = "services",
            selected = "Serviços",
            unselected = painterResource(id = R.drawable.services_icon),
            hasNews = false
        ),
        BottomnavigationBarItem(
            route = "publicar",
            selected = "Publicar",
            unselected = painterResource(id = R.drawable.center_button_bar),
            hasNews = false
        ),
        BottomnavigationBarItem(
            route = "chats",
            selected = "Conversas",
            unselected = painterResource(id = R.drawable.messages_icon),
            hasNews = false
        ),
        BottomnavigationBarItem(
            route = "profile",
            selected = "Perfil",
            unselected = painterResource(id = R.drawable.profile_icon),
            hasNews = false
        )
    )

    var selectedIndexItem by rememberSaveable {
        mutableStateOf(currentScreen)
    }


    Costurie_appTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.White
        ) {
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                sheetShape = RoundedCornerShape(20.dp),
                sheetElevation = 8.dp,
                sheetContent = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(750.dp)
                    ) {
                        PublishScreen(
                            navController = navController,
                            lifecycleScope = lifecycleScope,
                            localStorage = localStorage
                        )
                    }
                },
                sheetBackgroundColor = Color.White,
                sheetPeekHeight = 0.dp
            ) {
                Scaffold(
                    containerColor = Color.Transparent,
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier
                                .height(80.dp)
                                .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                                .shadow(elevation = 15.dp)
                                .clip(shape = RoundedCornerShape(15.dp)),
                            containerColor = Color.White,
                            contentColor = Color.Transparent
                        ) {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    colors = NavigationBarItemDefaults.colors(
                                        indicatorColor = Color.White
                                    ),
                                    selected = selectedIndexItem!!.toInt() == index,
                                    onClick = {
                                        selectedIndexItem = index.toString()
                                        if (selectedIndexItem!!.toInt() == 2) {
                                            scope.launch {
                                                if (sheetState.isCollapsed) {
                                                    sheetState.expand()
                                                    selectedIndexItem = currentScreen
                                                } else {
                                                    sheetState.collapse()
                                                    selectedIndexItem = currentScreen
                                                }
                                            }
                                        }
                                    },
                                    icon = {
                                        BadgedBox(
                                            badge = {

                                            }
                                        ) {
                                            if (selectedIndexItem!!.toInt() == index) {
                                                TextMenuBar(text = item.selected.uppercase())
                                            } else {
                                                if (index == 2) {
                                                    Image(
                                                        painter = item.unselected,
                                                        modifier = Modifier.size(70.dp),
                                                        contentDescription = item.route
                                                    )
                                                } else {
                                                    Image(
                                                        painter = item.unselected,
                                                        modifier = Modifier.size(22.dp),
                                                        contentDescription = item.route
                                                    )
                                                }
                                            }
                                        }
                                    })
                            }
                        }
                    }
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        if (selectedIndexItem!!.toInt() == 0) {
                            ExploreScreen(
                                navController = navController,
                                localStorage = localStorage
                            )
                            currentScreen = selectedIndexItem.toString()
                            localStorage.salvarValor(
                                context,
                                currentScreen.toString(),
                                "currentScreen"
                            )
                        } else if (selectedIndexItem!!.toInt() == 1) {
                            localStorage.salvarValor(
                                context,
                                currentScreen.toString(),
                                "currentScreen"
                            )
                            ServicesScreen(
                                navController = navController,
                                lifecycleScope = lifecycleScope,
                                filterings = emptyList(),
                                categories = emptyList(),
                                viewModelUserTags = UserTagViewModel(),
                                localStorage = localStorage
                            )
                            currentScreen = selectedIndexItem.toString()
                        } else if (selectedIndexItem!!.toInt() == 2) {
                            //PublishScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = localStorage)
                        } else if (selectedIndexItem!!.toInt() == 3) {

                            val client = ChatClient()

                            val socket = client.getSocket()

                            val context = LocalContext.current

                            //val dadaUser = UserRepository(context).findUsers()
                            val dadaUser = UserRepositorySqlite(context).findUsers()

                            var array = User()

                            var data = ""

                            if (dadaUser.isNotEmpty()) {
                                array = dadaUser[0]

                                data = array.id.toString()
                            }
                            client.connect(data.toInt())
                            ChatListScreen(
                                navController = navController,
                                lifecycleScope = lifecycleScope,
                                localStorage = localStorage,
                                client = client,
                                socket = socket,
                                chatViewModel = chatViewModel,
                                idUsuario = data.toInt()
                            )
                            currentScreen = selectedIndexItem.toString()
                            localStorage.salvarValor(
                                context,
                                currentScreen.toString(),
                                "currentScreen"
                            )
                        } else {
                            ProfileScreen(
                                navController = navController,
                                lifecycleScope = lifecycleScope,
                                viewModel = viewModelUserViewModel,
                                localStorage = localStorage
                            )
                            currentScreen = selectedIndexItem.toString()
                            localStorage.salvarValor(
                                context,
                                currentScreen.toString(),
                                "currentScreen"
                            )
                            Log.i(
                                "current",
                                "HomeScreen: ${localStorage.lerValor(context, "currentScreen")}"
                            )
                        }
                    }

                }
            }
        }
    }
}

data class BottomnavigationBarItem(
    val route: String,
    val selected: String,
    val unselected: Painter,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)