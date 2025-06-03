package com.mucheng.leafide.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.mucheng.leafide.R
import com.mucheng.leafide.databinding.ActivitySetupBinding
import com.mucheng.leafide.fragment.setup.LauncherOptionsFragment
import com.mucheng.leafide.fragment.setup.PermissionRequestFragment
import com.mucheng.leafide.fragment.setup.WelcomeFragment
import com.mucheng.leafide.mvi.SetupIntent
import com.mucheng.leafide.mvi.SetupViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SetupActivity : BaseActivity() {

    private lateinit var viewBinding: ActivitySetupBinding

    private val viewModel: SetupViewModel by viewModels()

    private lateinit var fragments: List<Fragment>

    private val activityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (!it.values.contains(false)) {
                lifecycleScope.launch { viewModel.intent.send(SetupIntent.Next) }
            } else {
                Snackbar.make(
                        viewBinding.root,
                        R.string.core_permission_denied,
                        Snackbar.LENGTH_SHORT,
                    )
                    .setAnchorView(viewBinding.nextButton)
                    .show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        if (supportFragmentManager.fragments.isEmpty()) {
            fragments =
                listOf(WelcomeFragment(), PermissionRequestFragment(), LauncherOptionsFragment())

            supportFragmentManager
                .beginTransaction()
                .also {
                    for (fragment in fragments) {
                        it.add(R.id.fragmentContainerView, fragment)
                    }
                }
                .commit()
        } else {
            fragments = supportFragmentManager.fragments
        }

        lifecycleScope.launch {
            viewModel.state.collectLatest {
                if (it.shouldFinishActivity) {
                    finish()
                    return@collectLatest
                }
                if (it.shouldStartActivity) {
                    finish()
                    startActivity(Intent(this@SetupActivity, MainActivity::class.java))
                    return@collectLatest
                }

                if (it.selectedIndex == 0) {
                    viewBinding.prevButton.visibility = View.GONE
                } else {
                    viewBinding.prevButton.visibility = View.VISIBLE
                }

                if (it.selectedIndex + 1 == fragments.size) {
                    viewBinding.nextButton.setIconResource(R.drawable.baseline_done_24)
                } else {
                    viewBinding.nextButton.setIconResource(R.drawable.baseline_arrow_forward_24)
                }

                supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim)
                    .also { transaction ->
                        for ((index, fragment) in fragments.withIndex()) {
                            if (index != it.selectedIndex) {
                                transaction.hide(fragment)
                            } else {
                                transaction.show(fragment)
                            }
                        }
                    }
                    .commit()

                viewBinding.linearProgressIndicator.progress = it.selectedIndex + 1
            }
        }

        viewBinding.prevButton.setOnClickListener {
            lifecycleScope.launch { viewModel.intent.send(SetupIntent.Prev) }
        }

        viewBinding.nextButton.setOnClickListener {
            lifecycleScope.launch {
                if (viewModel.state.value.selectedIndex == 1) {
                    activityResultLauncher.launch(
                        arrayOf(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        )
                    )
                } else {
                    viewModel.intent.send(SetupIntent.Next)
                }
            }
        }

        onBackPressedDispatcher.addCallback {
            lifecycleScope.launch { viewModel.intent.send(SetupIntent.Prev) }
        }
    }
}
