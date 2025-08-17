package com.example.lab_5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab_5.ui.theme.Lab_5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab_5Theme(
                dynamicColor = true // ✅ soporta dark, light y dynamic
            ) {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    PantallaPrincipal(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaPrincipal(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔹 Barra superior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(12.dp))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Actualización disponible", fontSize = 16.sp)
            Text(
                "Descargar",
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                    )
                    context.startActivity(intent)
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Fecha y botón "Terminar jornada"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Sábado", fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("31 De Mayo", fontSize = 16.sp)
            }
            Button(onClick = { /* No pide acción */ }) {
                Text("Terminar jornada")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔹 Tarjeta del restaurante
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Pizza Hut", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text("Zona 10", fontSize = 14.sp)
                        Text("3:00 PM - 9:00 PM", fontSize = 14.sp)
                    }
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "Directions",
                        modifier = Modifier
                            .size(32.dp)
                            .clickable {
                                val gmmIntentUri =
                                    Uri.parse("geo:14.6091,-90.5240?q=Pizza+Hut+Zona+10")
                                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                                mapIntent.setPackage("com.google.android.apps.maps")
                                context.startActivity(mapIntent)
                            }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Arodi Josué Chávez Ramírez",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F61))
                    ) {
                        Text("Iniciar")
                    }

                    Text(
                        "Detalles",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            Toast.makeText(
                                context,
                                "Comida rápida\nPrecio: QQ",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            }
        }
    }
}
