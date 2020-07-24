package com.fuimonos.app.restaurantmenu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuimonos.app.R
import com.fuimonos.app.commons.picasso.MaskTransformation
import com.fuimonos.app.databinding.HolderItemFoodBinding
import com.fuimonos.app.models.Food
import com.squareup.picasso.Picasso

class FoodsAdapter(private val viewModel: RestaurantMenuViewModel) : RecyclerView.Adapter<FoodsAdapter.FoodViewHolder>()  {

    var items = listOf<Food>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderItemFoodBinding.inflate(inflater, parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }

    class FoodViewHolder(private val itemBinding: HolderItemFoodBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Food, viewModel: RestaurantMenuViewModel) = with(itemBinding) {
            this.viewModel = viewModel
            this.food = item
            Picasso.get()
                .load(item.image)
                .transform(MaskTransformation(this.ivFoodImage.context, R.drawable.rounded_corners_image_transformation))
                .into(this.ivFoodImage)
            executePendingBindings()
        }

    }

}
