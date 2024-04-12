package com.mucheng.leafide.fragment.setup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mucheng.leafide.R
import com.mucheng.leafide.adapter.PermissionRequestCardAdapter
import com.mucheng.leafide.bean.ui.PermissionRequestCard
import com.mucheng.leafide.databinding.FragmentPermissionRequestBinding

class PermissionRequestFragment : Fragment() {

    private lateinit var viewBinding: FragmentPermissionRequestBinding

    private val permissionRequestCardAdapter by lazy {
        PermissionRequestCardAdapter(
            requireContext(),
            listOf(
                PermissionRequestCard(
                    iconRes = R.drawable.baseline_storage_24,
                    titleRes = R.string.storage_permission,
                    descriptionRes = R.string.storage_permission_description
                ),
                PermissionRequestCard(
                    iconRes = R.drawable.baseline_wifi_24,
                    titleRes = R.string.network_permission,
                    descriptionRes = R.string.network_permission_description
                )
            )
        )
    }

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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        viewBinding.recyclerView.setHasFixedSize(true)
        viewBinding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewBinding.recyclerView.adapter = permissionRequestCardAdapter
    }

}