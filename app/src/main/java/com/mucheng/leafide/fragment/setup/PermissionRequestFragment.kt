package com.mucheng.leafide.fragment.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mucheng.leafide.R
import com.mucheng.leafide.databinding.FragmentPermissionRequestBinding

class PermissionRequestFragment : Fragment() {

    private lateinit var viewBinding: FragmentPermissionRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentPermissionRequestBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        viewBinding.prevButton.setOnClickListener {
            navController.navigateUp()
        }
        viewBinding.nextButton.setOnClickListener {
            navController.navigate(R.id.action_permissionRequestFragment_to_launcherOptionsFragment)
        }
    }

}