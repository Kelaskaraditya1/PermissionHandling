package com.starkindustries.permissionhadnling.Frontend.Component

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionsCompose(){
    var permissionList= rememberMultiplePermissionsState(permissions = listOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.RECORD_AUDIO
    ))
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver{_,event->
            if(event==Lifecycle.Event.ON_RESUME)
                permissionList.launchMultiplePermissionRequest()
    }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        permissionList.permissions.forEach{
            perm->
            when(perm.permission){
                Manifest.permission.CAMERA->{
                    when{
                        perm.status.isGranted->{
                            Text(text = "Camera Permission Granted")
                        }
                        perm.status.shouldShowRationale->{
                            Text(text = "Camera Permission is Required")
                        }
                        (!perm.status.isGranted&&!perm.status.shouldShowRationale)->{
                            Text(text = "Camera Permission Denied!!Enable it manually from settings")
                        }
                    }
                }
                Manifest.permission.RECORD_AUDIO->{
                    when{
                        perm.status.isGranted->{
                            Text(text = "Record Audio Permission Granted")
                        }
                        perm.status.shouldShowRationale->{
                            Text(text = "Camera Permission is Required")
                        }
                        (!perm.status.isGranted&&!perm.status.shouldShowRationale)->{
                            Text(text = "Camera Permission Denied!!Enable it manually from settings")
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun PreviewCompose(){
    PermissionsCompose()
}