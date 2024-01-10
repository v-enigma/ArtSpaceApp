package com.example.artspaceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceAppTheme {
                // A surface container using the 'background' color from the theme
                 ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp(){
    val item = rememberSaveable{ mutableStateOf((1..6).random())}
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(35.dp)
        .verticalScroll(rememberScrollState()),
        color = MaterialTheme.colorScheme.primary
            ){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement =  Arrangement.Center) {
            ImageCard(modifier = Modifier.weight(1f),item.value)
            TextCard(description = R.string.Photo_description, authorWithDate =  R.string.Author)
            ImageControls( previous = { if(item.value > 1 ){  item.value -- }else { item.value = 6} } , next ={if(item.value <5){ item.value++ }else{item.value = 1} })
        }
    }
}
@Composable
fun TextCard(modifier: Modifier= Modifier, @StringRes description :Int, authorWithDate :Int){
    Surface(modifier = modifier
        .padding(top = 50.dp, bottom = 20.dp,)
        .fillMaxWidth(),
            tonalElevation = 10.dp ){
        Column(Modifier.padding(  25.dp)) {
            Text(stringResource(id = description))
            Text(text = stringResource(id = authorWithDate), fontWeight = FontWeight.Bold , fontSize = 13.sp)
        }
    }
}
@Composable
fun ImageControls(modifier: Modifier = Modifier.fillMaxWidth(), previous :()-> Unit , next :() -> Unit){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween){
        ButtonControl(modifier = modifier, text = "Previous" , onClick =previous)
        ButtonControl(modifier = modifier, text = "Next", onClick =  next)
    }
}
private fun getImageResource( item: Int):Int{
   return  when(item){
        1 -> R.drawable.scenary
        2 -> R.drawable.dawn
        3 -> R.drawable.sunny
        4 -> R.drawable.water
        5 -> R.drawable.imagination
        else -> R.drawable.dice
    }

}
@Composable
fun ImageCard(modifier: Modifier,imageResource : Int){
    Surface(modifier = modifier
        .fillMaxWidth()
        , shadowElevation = 3.dp, tonalElevation = 500.dp,color = MaterialTheme.colorScheme.onPrimary  ){
        Image(
            painter = painterResource(getImageResource(imageResource)),
            null,
            modifier = Modifier
                .padding(horizontal =34.dp, vertical = 20.dp)
                , contentScale = ContentScale.FillBounds
        )
    }
}
@Composable
fun ButtonControl(modifier: Modifier, text :String, onClick :() -> Unit){
    Button(onClick = onClick){
        Text(text)
    }

}
