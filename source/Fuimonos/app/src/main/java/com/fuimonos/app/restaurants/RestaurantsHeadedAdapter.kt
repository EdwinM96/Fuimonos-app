package com.fuimonos.app.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuimonos.app.R
import com.fuimonos.app.commons.picasso.CircleTransform
import com.fuimonos.app.databinding.HolderItemRestaurantBinding
import com.fuimonos.app.models.Restaurant
import com.fuimonos.app.models.RestaurantsHeaded
import com.fuimonos.app.restaurants.RestaurantsHeadedAdapter.RestaurantHeadingViewType.Companion.HEADER
import com.fuimonos.app.restaurants.RestaurantsHeadedAdapter.RestaurantHeadingViewType.Companion.RESTAURANT
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.holder_item_restaurant_header.view.*

class RestaurantsHeadedAdapter(private val viewModel: RestaurantsViewModel) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = listOf<RestaurantsHeaded>()
        set(value) {
            field = value
            itemsTyped = createItemsTypedWith(value)
            notifyDataSetChanged()
        }

    private var itemsTyped = listOf<RestaurantHeadingViewType>()

    private fun createItemsTypedWith(restaurantsHeaded: List<RestaurantsHeaded>): List<RestaurantHeadingViewType> {
        val restaurantsHeadingViewTypes = mutableListOf<RestaurantHeadingViewType>()
        restaurantsHeaded.forEach { restaurantHeaded ->
            val headingViewType = HeaderType(restaurantHeaded.header)
            val restaurantsTypes = restaurantHeaded.restaurants?.map { RestaurantType(it) } ?: listOf()
            restaurantsHeadingViewTypes.add(headingViewType)
            restaurantsHeadingViewTypes.addAll(restaurantsTypes)
        }
        return restaurantsHeadingViewTypes
    }

    override fun getItemCount() = itemsTyped.size

    override fun getItemViewType(position: Int): Int {
        return itemsTyped[position].getViewType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when(viewType) {
            HEADER -> {
                val view = inflater.inflate(R.layout.holder_item_restaurant_header, parent, false)
                HeaderViewHolder(view)
            }
            RESTAURANT -> {
                val binding = HolderItemRestaurantBinding.inflate(inflater, parent, false)
                RestaurantViewHolder(binding)
            }
            else -> {
                val view = inflater.inflate(R.layout.holder_item_restaurant_header, parent, false)
                HeaderViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> {
                val headerType = itemsTyped[position] as HeaderType
                holder.bind(headerType.headerText)
            }
            is RestaurantViewHolder -> {
                val restaurantType = itemsTyped[position] as RestaurantType
                holder.bind(restaurantType.restaurant, viewModel)
            }
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(headerText: String?) = with(itemView) {
            tvRestaurantHeader.text = headerText
        }

    }

    class RestaurantViewHolder(private val itemBinding: HolderItemRestaurantBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Restaurant, viewModel: RestaurantsViewModel) = with(itemBinding) {
            this.viewModel = viewModel
            this.restaurant = item
            Picasso.get()
                .load(item.outstandingImage)
                .into(this.ivOutstandingImage)
            Picasso.get()
                .load(item.logo)
                .transform(CircleTransform())
                .into(this.ivRestaurantLogo)
            executePendingBindings()
        }

    }

    interface RestaurantHeadingViewType {
        fun getViewType() : Int
        companion object {
            const val HEADER = 0
            const val RESTAURANT = 1
        }
    }

    class HeaderType(val headerText: String?) : RestaurantHeadingViewType {
        override fun getViewType() = HEADER
    }

    class RestaurantType(val restaurant: Restaurant) : RestaurantHeadingViewType {
        override fun getViewType() = RESTAURANT
    }

}
