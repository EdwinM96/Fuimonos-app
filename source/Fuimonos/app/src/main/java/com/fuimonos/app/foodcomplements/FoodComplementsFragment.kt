package com.fuimonos.app.foodcomplements

import com.fuimonos.app.R
import com.fuimonos.app.commons.BackViewModelFragment
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.databinding.FrgFoodComplementsBinding
import com.fuimonos.app.ext.getArg
import com.fuimonos.app.models.Food
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class FoodComplementsFragment : BackViewModelFragment<FoodComplementsViewModel, FrgFoodComplementsBinding>() {

    private val food by getArg<Food>(FOOD_ARG)
    override val mViewModel: FoodComplementsViewModel by viewModel { parametersOf(food) }
    override val contentViewLayoutRes = R.layout.frg_food_complements

    companion object {
        const val FOOD_ARG = "FOOD"
    }

}
