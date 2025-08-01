package com.example.jetpackcomposetestfor26thsprint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetestfor26thsprint.ui.theme.JetpackComposeTestFor26thSprintTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val contact = Contact(
            name = "John",
            surname = "Doe",
            familyName = "Smith",
            imageRes = R.drawable.circle,
            isFavorite = true,
            phone = "+1234567890",
            address = "123 Main St, Springfield, USA",
            email = "lalala"
        )
        enableEdgeToEdge()
        setContent {
            JetpackComposeTestFor26thSprintTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContactDetails(
                        contact = contact,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.TopEnd) {
            ContactImageOrInitials(contact)

            if (contact.isFavorite) {
                Icon(
                    painter = painterResource(android.R.drawable.star_big_on),
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(24.dp),
                    tint = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "${contact.surname} ${contact.name} ${contact.familyName}",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        InfoRow(label = stringResource(id = R.string.phone), value = contact.phone)
        InfoRow(label = stringResource(id = R.string.address), value = contact.address)
        contact.email?.let {
            InfoRow(label = stringResource(id = R.string.email), value = it)
        }
    }
}

@Composable
fun ContactImageOrInitials(contact: Contact) {
    if (contact.imageRes != null) {
        Image(
            painter = painterResource(id = contact.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFFE57373)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = contact.name.take(1) + contact.surname?.take(1),
                fontSize = 48.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label:",
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            modifier = Modifier.weight(2f),
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWithInitialsEmailAndFavorite() {
    JetpackComposeTestFor26thSprintTheme {
        ContactDetails(
            contact = Contact(
                name = "Anna",
                surname = "Ivanova",
                familyName = "Petrovna",
                imageRes = null,
                isFavorite = true,
                phone = "+7 495 000 00 00",
                address = "Москва, ул. Пушкина",
                email = "anna@example.com"
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewWithPhotoNoEmail() {
    JetpackComposeTestFor26thSprintTheme {
        ContactDetails(
            contact = Contact(
                name = "Mike",
                surname = "Smith",
                familyName = "Johnson",
                imageRes = R.drawable.img_cat,
                isFavorite = false,
                phone = "+1 800 123 4567",
                address = "Los Angeles, CA",
                email = null
            )
        )
    }
}