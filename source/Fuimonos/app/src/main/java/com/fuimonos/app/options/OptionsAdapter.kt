package com.fuimonos.app.options

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fuimonos.app.databinding.HolderItemOptionBinding
import com.fuimonos.app.models.Option

class OptionsAdapter(private val viewModel: OptionsViewModel) : RecyclerView.Adapter<OptionsAdapter.OptionViewHolder>() {

    var items = listOf<Option>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HolderItemOptionBinding.inflate(inflater, parent, false)
        return OptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.bind(items[position], viewModel)
    }


    class OptionViewHolder(private val itemBinding: HolderItemOptionBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Option, viewModel: OptionsViewModel) = with(itemBinding) {
            this.viewModel = viewModel
            this.option = item
            this.ivOptionIcon.setImageResource(item.drawableId)
            this.tvOptionName.setText(item.nameRes)
        }

    }

}
