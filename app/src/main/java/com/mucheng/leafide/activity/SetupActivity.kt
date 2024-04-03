package com.mucheng.leafide.activity

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.mucheng.leafide.R
import com.mucheng.leafide.databinding.ActivitySetupBinding
import com.mucheng.leafide.util.findNavController
import com.mucheng.leafide.viewmodel.SetupPage
import com.mucheng.leafide.viewmodel.SetupViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SetupActivity : BaseActivity() {

    private lateinit var viewBinding: ActivitySetupBinding

    private val viewModel: SetupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val navController = supportFragmentManager.findNavController(R.id.fragmentContainerView)
        lifecycleScope.launch {
            viewModel.pageStateFlow.collectLatest {
                when (it) {
                    SetupPage.WelcomePage -> {
                        navController.navigate(R.id.action_global_welcomeFragment)
                        viewBinding.linearProgressIndicator.progress = 1
                    }

                    SetupPage.PermissionReuqestPage -> {
                        navController.navigate(R.id.action_global_permissionRequestFragment)
                        viewBinding.linearProgressIndicator.progress = 2
                    }

                    SetupPage.LauncherOptionsPage -> {
                        navController.navigate(R.id.launcherOptionsFragment)
                        viewBinding.linearProgressIndicator.progress = 3
                    }
                }
                when (it) {
                    SetupPage.WelcomePage -> {
                        viewBinding.prevButton.visibility = View.GONE
                    }

                    SetupPage.LauncherOptionsPage -> {
                        viewBinding.nextButton.setIconResource(R.drawable.baseline_done_24)
                    }

                    else -> {
                        viewBinding.prevButton.visibility = View.VISIBLE
                        viewBinding.nextButton.setIconResource(R.drawable.baseline_arrow_forward_24)
                    }
                }
            }
        }

        onBackPressedDispatcher.addCallback {
            if (viewModel.canGotoPrev()) {
                viewModel.gotoPrev()
            } else {
                finish()
            }
        }

        viewBinding.nextButton.setOnClickListener {
            viewModel.gotoNext()
        }

        viewBinding.prevButton.setOnClickListener {
            viewModel.gotoPrev()
        }
    }

}