package com.starkindustries.permissionhadnling.Frontend.Activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.starkindustries.permissionhadnling.Frontend.Component.PermissionsCompose
import com.starkindustries.permissionhadnling.ui.theme.PermissionHadnlingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PermissionHadnlingTheme {
                PermissionsCompose()
            }
        }
    }
}




