package net.softwarevillage.moneydragon.presentation.ui.screens.auth

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import net.softwarevillage.moneydragon.R
import net.softwarevillage.moneydragon.presentation.ui.components.MainButton
import net.softwarevillage.moneydragon.presentation.ui.components.MainTextInput
import net.softwarevillage.moneydragon.presentation.ui.components.NavigationButton
import net.softwarevillage.moneydragon.presentation.ui.theme.Black
import net.softwarevillage.moneydragon.presentation.ui.theme.Blue
import net.softwarevillage.moneydragon.presentation.ui.theme.GreyText

@Composable
fun CreateUserScreen(
    navigateBack: () -> Unit
) {

    val name = remember {
        mutableStateOf("")
    }

    val surname = remember {
        mutableStateOf("")
    }

    val imageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            imageUri.value = it
        })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.weight(0.45f)) {
            Spacer(modifier = Modifier.height(32.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                NavigationButton(
                    navigate = navigateBack,
                    modifier = Modifier.padding(start = 32.dp)
                )
                Text(
                    text = stringResource(id = R.string.profile),
                    color = Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = stringResource(id = R.string.set_profile),
                color = GreyText,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(28.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Blue
                ),
                shape = RoundedCornerShape(100),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clickable {
                            photoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }, contentAlignment = Alignment.Center
                ) {
                    if (imageUri.value == null) {
                        Icon(
                            painter = painterResource(id = R.drawable.upload),
                            contentDescription = "uploadIcon", tint = Color.White,
                        )
                    } else {
                        AsyncImage(
                            model = imageUri.value,
                            contentDescription = "profilePhoto",
                            contentScale = ContentScale.FillBounds
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .weight(0.26f)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            MainTextInput(
                text = name,
                label = R.string.enter_name,
                action = ImeAction.Next
            )
            MainTextInput(
                text = surname,
                label = R.string.enter_surname,
                action = ImeAction.Done
            )
        }
        Column(
            modifier = Modifier
                .weight(0.29f)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainButton(title = R.string.set) {

            }

        }

    }
}