package com.fuimonos.app.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fuimonos.app.R
import com.fuimonos.app.models.FoodCategory
import com.fuimonos.app.models.RestaurantsHeaded
import kotlinx.android.synthetic.main.holder_vertical_recycler_view.view.*

class CategoriesRestaurantsAdapter(val viewModel: RestaurantsViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var categoriesAdapter = CategoriesAdapter(viewModel)
    private var restaurantsAdapter = RestaurantsHeadedAdapter(viewModel)

    var itemCategories = listOf<FoodCategory>()
        set(value) {
            field = value
            categoriesAdapter.items = value
        }

    var itemRestaurantsHeaded = listOf<RestaurantsHeaded>()
        set(value) {
            field = value
            restaurantsAdapter.items = value
        }

    override fun getItemCount() = 2

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
             HORIZONTAL -> {
                val view = inflater.inflate(R.layout.holder_horizontal_recycler_view, parent, false)
                HorizontalViewHolder(view)
            }
            VERTICAL -> {
                val view = inflater.inflate(R.layout.holder_vertical_recycler_view, parent, false)
                VerticalViewHolder(view)
            }
            else -> {
                val view = inflater.inflate(R.layout.holder_vertical_recycler_view, parent, false)
                VerticalViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HorizontalViewHolder -> holder.bind(categoriesAdapter)
            is VerticalViewHolder   -> holder.bind(restaurantsAdapter)
        }
    }

    class HorizontalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(categoriesAdapter: CategoriesAdapter) = with(itemView) {
            innerRecyclerView.layoutManager = LinearLayoutManager(context,
                                                                  LinearLayoutManager.HORIZONTAL,
                                                     false)
            innerRecyclerView.adapter = categoriesAdapter
        }

    }

    class VerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(restaurantsHeadedAdapter: RestaurantsHeadedAdapter) = with(itemView) {
            innerRecyclerView.layoutManager = LinearLayoutManager(context)
            innerRecyclerView.adapter = restaurantsHeadedAdapter
        }

    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }

}
