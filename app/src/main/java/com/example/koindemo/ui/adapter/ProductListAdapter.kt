package com.example.koindemo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.koindemo.R
import com.example.koindemo.databinding.ItemProductListBinding
import com.example.koindemo.models.Products

class ProductListAdapter(private val context: Context) : RecyclerView.Adapter<ProductListAdapter.UserViewHolder>() {
    private var listItems = ArrayList<Products>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemProductListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_product_list, parent, false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(listItems: List<Products>) {
        this.listItems = listItems as ArrayList<Products>
        notifyDataSetChanged()
    }

    class UserViewHolder(private val itemBinding: ItemProductListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Products) {
            itemBinding.product=item
            itemBinding.executePendingBindings()

        }
    }
    interface UserInteraction {
        fun onUserClicked(user: Products)
    }
}