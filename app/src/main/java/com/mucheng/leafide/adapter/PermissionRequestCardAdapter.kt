package com.mucheng.leafide.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mucheng.leafide.bean.ui.PermissionRequestCard
import com.mucheng.leafide.databinding.LayoutPermissionRequestCardBinding

class PermissionRequestCardAdapter(
    private val context: Context,
    private val permissionRequestCards: List<PermissionRequestCard>,
) : RecyclerView.Adapter<PermissionRequestCardAdapter.ViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }

    inner class ViewHolder(val viewBinding: LayoutPermissionRequestCardBinding) :
        RecyclerView.ViewHolder(viewBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutPermissionRequestCardBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return permissionRequestCards.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewBinding = holder.viewBinding
        val permissionRequestCard = permissionRequestCards[position]
        viewBinding.shapeableImageView.setImageResource(permissionRequestCard.iconRes)
        viewBinding.title.setText(permissionRequestCard.titleRes)
        viewBinding.description.setText(permissionRequestCard.descriptionRes)
    }
}
