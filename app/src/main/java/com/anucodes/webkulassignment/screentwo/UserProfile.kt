package com.anucodes.webkulassignment.screentwo

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anucodes.webkulassignment.viewmodel.UserViewModel


@Composable
fun UserProfile(modifier: Modifier,
                userViewModel: UserViewModel,
                userId: Int
){
    val user by userViewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        userViewModel.fetchUserById(userId)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        if(user!=null) {
            Text(text = "Name: ${user!!.name}")
            Text(text = "Email: ${user!!.email}")
            Text(text = "Phone: ${user!!.phone}")
            Text(text = "Website: ${user!!.website}")

            Spacer(modifier = Modifier.size(15.dp))
            Text(text = "Address: \n" +
                    "Suite: ${user!!.address.suite}\n" +
                    "Street: ${user!!.address.street}\n" +
                    "City: ${user!!.address.city}\n" +
                    "Pincode: ${user!!.address.zipcode}")

            Spacer(modifier = Modifier.size(15.dp))
            Text(text = "Company: \n" +
                    "Name: ${user!!.company.name}\n" +
                    "Catch Phrase: ${user!!.company.catchPhrase}\n" +
                    "bs: ${user!!.company.bs}\n")
        }else{
            Text("Please check the internet connection")
        }

    }

}