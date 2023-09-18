package net.softwarevillage.moneydragon.presentation.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainLottie
import net.softwarevillage.moneydragon.presentation.ui.components.MainPasswordTextInput
import net.softwarevillage.moneydragon.presentation.ui.components.MainTextInput
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.Grey87
import net.softwarevillage.moneydragon.presentation.ui.theme.GreyB1
import net.softwarevillage.moneydragon.presentation.ui.theme.GreyEE
import net.softwarevillage.moneydragon.presentation.ui.theme.fontFamily

@Composable
fun LoginScreen(
    navigateRegister: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle()
    val effect = viewModel.effect.collectAsStateWithLifecycle(initialValue = null)
    val context = LocalContext.current

    val email = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = state.value) {
        if (!state.value.isLoading) {
            when (effect.value) {
                is AuthEffect.ShowMessage -> {
                    val effectLogin = effect.value as AuthEffect.ShowMessage

                    if (effectLogin.isLogin) {
                        Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, effectLogin.message, Toast.LENGTH_SHORT).show()
                    }
                }

                else -> {}
            }

        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(horizontal = 32.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.logo_login),
                contentDescription = "logo"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 40.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.W700,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                MainTextInput(
                    text = email,
                    label = R.string.email,
                    type = KeyboardType.Email,
                    action = ImeAction.Next,
                    modifier = Modifier.fillMaxWidth()
                )
                MainPasswordTextInput(text = password, label = R.string.password)
            }
            Spacer(modifier = Modifier.height(40.dp))
            MainButton(
                title = R.string.login,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    viewModel.setEvent(AuthEvent.LoginUser(email.value, password.value))
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.padding(horizontal = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(thickness = 1.dp, color = GreyB1, modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(id = R.string.or),
                    color = GreyB1,
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                Divider(thickness = 1.dp, color = GreyB1, modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Unspecified,
                        containerColor = GreyEE
                    ), contentPadding = PaddingValues(8.dp), shape = RoundedCornerShape(100)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "google"
                    )
                }
                Spacer(modifier = Modifier.width(32.dp))
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.facebook),
                        contentDescription = "google",
                        tint = Color.Unspecified
                    )
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Grey87)) {
                        append(stringResource(id = R.string.have_account))
                    }
                    withStyle(style = SpanStyle(color = Blue)) {
                        append(stringResource(id = R.string.sign_up_bottom))
                    }
                },
                textAlign = TextAlign.Center,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        navigateRegister.invoke()
                    }
            )
        }
        MainLottie(showState = state.value.isLoading, res = R.raw.loading)
    }
}
