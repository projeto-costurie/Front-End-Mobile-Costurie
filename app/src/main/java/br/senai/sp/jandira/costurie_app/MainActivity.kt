package br.senai.sp.jandira.costurie_app

import ProfileScreen
import ProfileViewedScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.senai.sp.jandira.costurie_app.screens.chats.ChatListScreen
import br.senai.sp.jandira.costurie_app.screens.chats.ChatScreen
import br.senai.sp.jandira.costurie_app.screens.editProfile.EditProfileScreen
import br.senai.sp.jandira.costurie_app.screens.editProfile.TagsEditProfileScreen
import br.senai.sp.jandira.costurie_app.screens.editPublication.EditPublicationScreen
import br.senai.sp.jandira.costurie_app.screens.expandedComment.ExpandedCommentScreen
import br.senai.sp.jandira.costurie_app.screens.expandedPublication.ExpandedPublicationScreen
import br.senai.sp.jandira.costurie_app.screens.explore.ExploreScreen
import br.senai.sp.jandira.costurie_app.screens.home.HomeScreen
import br.senai.sp.jandira.costurie_app.screens.loading.LoadingScreen
import br.senai.sp.jandira.costurie_app.screens.login.LoginScreen
import br.senai.sp.jandira.costurie_app.screens.main.MainScreen
import br.senai.sp.jandira.costurie_app.screens.password.PasswordScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.AboutScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.ChangeEmailScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.ChangePasswordScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.DescriptionScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.LocationScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.NameScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.ProfilePicScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.TagSelectScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.TermsAndConditionsScreen
import br.senai.sp.jandira.costurie_app.screens.personalization.TypeProfileScreen
import br.senai.sp.jandira.costurie_app.screens.profile.ProfileListScreen
import br.senai.sp.jandira.costurie_app.screens.publish.PublishScreen
import br.senai.sp.jandira.costurie_app.screens.register.RegisterScreen
import br.senai.sp.jandira.costurie_app.screens.services.ServicesScreen
import br.senai.sp.jandira.costurie_app.screens.settings.SettingsScreen
import br.senai.sp.jandira.costurie_app.screens.settings.YourAccountScreen
import br.senai.sp.jandira.costurie_app.screens.tradePassword.TradePasswordScreen
import br.senai.sp.jandira.costurie_app.screens.validationCode.ValidationCodeScreen
import br.senai.sp.jandira.costurie_app.ui.theme.Costurie_appTheme
import br.senai.sp.jandira.costurie_app.viewModel.PasswordResetViewModel
import br.senai.sp.jandira.costurie_app.viewModel.TagPublicationViewModel
import br.senai.sp.jandira.costurie_app.viewModel.TagsViewModel
import br.senai.sp.jandira.costurie_app.viewModel.UserTagViewModel
import br.senai.sp.jandira.costurie_app.viewModel.UserViewModel
import br.senai.sp.jandira.costurie_app.viewModel.UserViewModel2
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Costurie_appTheme {
                val navController = rememberAnimatedNavController()
                val viewModelPassword = viewModel<PasswordResetViewModel>()
                val viewModelUser = viewModel<UserViewModel>()
                val viewModelUser2 = viewModel<UserViewModel2>()
                val viewModelTags = viewModel<TagsViewModel>()
                val viewModelUserTags = viewModel<UserTagViewModel>()
                val viewModelTagsPublication = viewModel<TagPublicationViewModel>()
                val localStorage: Storage = Storage()
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "settings")
   {
                    composable(route = "main") { MainScreen(navController = navController) }
                    composable(route = "register") { RegisterScreen(navController = navController, lifecycleScope = lifecycleScope) }
                    composable(route = "login") { LoginScreen(navController = navController, lifecycleScope = lifecycleScope) }
                    composable(route = "password") { PasswordScreen(navController = navController, lifecycleScope = lifecycleScope, viewModelPassword) }
                    composable(route = "validationCode") { ValidationCodeScreen(navController = navController, lifecycleScope = lifecycleScope, viewModelPassword) }
                    composable(route = "tradePassword") { TradePasswordScreen(navController = navController, lifecycleScope = lifecycleScope, viewModelPassword) }
                    composable(route = "loading") { LoadingScreen(navController = navController, lifecycleScope = lifecycleScope) }
                    composable(route = "home") { HomeScreen(navController = navController, lifecycleScope = lifecycleScope, viewModelUser2) }
                    composable(route = "explore") { ExploreScreen(navController = navController, localStorage = localStorage) }
//                    composable(route = "publish") { PublishScreen(navController = navController, lifecycleScope = lifecycleScope, localStorage = localStorage)}
//                    composable(route = "expandedComment") { ExpandedCommentScreen(nav) }
                    composable(route = "expandedPublication") { ExpandedPublicationScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelTagsPublication,  localStorage = localStorage) }
                    composable(route = "services") { ServicesScreen(navController = navController, lifecycleScope = lifecycleScope, categories = emptyList(), filterings = emptyList(), viewModelUserTags = viewModelUserTags, localStorage = localStorage) }
                    composable(route = "profile") { ProfileScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelUser2,  localStorage = localStorage) }
                    composable(route = "profileViewed") { ProfileViewedScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelUser2, localStorage = localStorage) }
                    composable(route = "profileList") { ProfileListScreen(navController = navController, lifecycleScope = lifecycleScope, profiles = emptyList(), viewModel = viewModelUser, viewModelUserTags = viewModelUserTags, localStorage = localStorage) }
                    composable(route = "editProfile") { EditProfileScreen(lifecycleScope = lifecycleScope, navController = navController, viewModel = viewModelUser2, localStorage = localStorage) }
                    composable(route = "tagsEditProfile") { TagsEditProfileScreen(lifecycleScope = lifecycleScope, navController = navController, viewModelUser = viewModelUser, viewModelTags = viewModelTags, localStorage = localStorage) }
                    composable(route = "editPublication") { EditPublicationScreen(lifecycleScope = lifecycleScope, navController = navController, localStorage = localStorage, viewModelTag = viewModelTagsPublication) }

                    //telas de chat
                    composable(route = "chatList") { ChatListScreen(navController = navController, lifecycleScope = lifecycleScope) }
                    composable(route = "chat") { ChatScreen(lifecycleScope = lifecycleScope, navController = navController) }

                    //telas de configuracões
                    composable(route = "settings") { SettingsScreen(lifecycleScope = lifecycleScope, navController = navController) }
                    composable(route = "yourAccount") { YourAccountScreen(lifecycleScope = lifecycleScope, navController = navController) }
                    composable(route = "changeEmail") { ChangeEmailScreen(navController = navController, localStorage = localStorage) }
                    composable(route = "changePassword") { ChangePasswordScreen(navController = navController, localStorage = localStorage) }
                    composable(route = "about") { AboutScreen(navController = navController) }
                    composable(route = "termsAndConditions") { TermsAndConditionsScreen(navController = navController) }

                    //telas de personalização
                    composable(route = "name") { NameScreen(navController = navController, localStorage) }
                    composable(route = "foto") { ProfilePicScreen(navController = navController, localStorage, lifecycleScope = lifecycleScope) }
                    composable(route = "description") { DescriptionScreen(navController = navController, localStorage, lifecycleScope = lifecycleScope) }
                    composable(route = "location") { LocationScreen(navController = navController,lifecycleScope = lifecycleScope) }
                    composable(route = "profileType") { TypeProfileScreen(navController = navController,lifecycleScope = lifecycleScope) }
                    composable(route = "tagSelection") { TagSelectScreen(lifecycleScope = lifecycleScope, navController = navController) }
                    }
                }
            }
        }
}
