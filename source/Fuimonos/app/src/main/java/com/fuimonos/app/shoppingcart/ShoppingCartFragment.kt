package com.fuimonos.app.shoppingcart

import com.fuimonos.app.R
import com.fuimonos.app.commons.BaseViewModelFragment
import com.fuimonos.app.databinding.FrgShoppingCartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShoppingCartFragment : BaseViewModelFragment<ShoppingCartViewModel, FrgShoppingCartBinding>() {

    override val mViewModel: ShoppingCartViewModel by viewModel()
    override val contentViewLayoutRes = R.layout.frg_shopping_cart

}