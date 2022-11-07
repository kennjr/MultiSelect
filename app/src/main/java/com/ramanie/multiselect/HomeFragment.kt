package com.ramanie.multiselect

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeFragment(){
    // for us to get updates whenever the list changes e.g the user starts selecting items we'll need to convert the list into a state
    var items by remember {
        mutableStateOf(
            (1..20).map {
                ListItem("item $it", isSelected = false)
            }
        )
    }
    
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(items.size){ index ->  
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        // with the code below we're trying to check whether the clicked index matches any of the indices
                        // we get when the list is mapped(j) if so we're gon update the item by changing its selected status else we return the item as is
                        items = items.mapIndexed { j, item ->
                            if (index == j){
                                item.copy(isSelected = !item.isSelected)
                            } else {
                                item
                            }
                        }
                    }
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = items[index].title)
                if (items[index].isSelected){
                    Icon(imageVector = Icons.Default.Check,
                        contentDescription = "${items[index].title} has been selected",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }

    // NOTE : We can get all the selected items from the list and pass their ids to another fragment
    // we'll use the code below to do so
//    items.filter { it.isSelected }
}