package com.coda.oncescreens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.coda.oncescreens.features.screens.ChatScreen
import com.coda.oncescreens.features.screens.ChattingScreen
import com.coda.oncescreens.features.screens.InstagramProfileScreen
import com.coda.oncescreens.features.screens.ShoesScreen
import com.coda.oncescreens.features.screens.TaskManagementScreen
import com.coda.oncescreens.features.screens.TimerScreen
import com.coda.oncescreens.features.screens.TwitterProfileScreen
import com.coda.oncescreens.ui.theme.ComposeProjectsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectsTheme{
                // ShoesScreen()
               // TimerScreen()
               // ChatScreen()
               // TaskManagementScreen()
               // ChattingScreen()
//                TwitterProfileScreen()
                InstagramProfileScreen()
            }
        }
    }
}