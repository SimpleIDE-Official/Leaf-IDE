package com.mucheng.leafide.util

import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun FragmentManager.findNavController(id: Int): NavController {
    return (findFragmentById(id) as NavHostFragment).navController
}