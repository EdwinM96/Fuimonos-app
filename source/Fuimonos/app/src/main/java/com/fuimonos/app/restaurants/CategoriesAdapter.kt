package com.fuimonos.app.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuimonos.app.databinding.HolderItemCategoryBinding
import com.fuimonos.app.models.FoodCategory
import com.squareup.picasso.Picasso

class CategoriesAdapter(val viewModel: RestaurantsViewModel) : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    var items = listOf<FoodCategory>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderItemCategoryBinding.inflate(inflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

    class CategoryViewHolder(private val itemBinding: HolderItemCategoryBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: FoodCategory, viewModel: RestaurantsViewModel) = with(itemBinding) {
            this.viewModel = viewModel
            this.category = item
            Picasso.get()
                .load(item.icon)
                .into(this.ivCategoryIcon)
            executePendingBindings()
        }

    }

}
