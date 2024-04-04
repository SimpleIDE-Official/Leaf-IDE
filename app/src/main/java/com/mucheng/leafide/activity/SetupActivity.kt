package com.mucheng.leafide.activity

import android.os.Bundle
import com.mucheng.leafide.databinding.ActivitySetupBinding

class SetupActivity : BaseActivity() {

    private lateinit var viewBinding: ActivitySetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

}