package com.fuimonos.app.options

import androidx.lifecycle.MutableLiveData
import com.fuimonos.app.R
import com.fuimonos.app.commons.BaseViewModel
import com.fuimonos.app.commons.SingleLiveEvent
import com.fuimonos.app.models.Option

class OptionsViewModel : BaseViewModel() {

    val onShowOptions = SingleLiveEvent<List<Option>>()
    val onShowSelectedOption = SingleLiveEvent<Option>()
    val onLogoutSelected = SingleLiveEvent<Nothing>()
    private val options = getOptions()

    fun start() {
        onShowOptions.value = options
    }

    fun onSelectOption(option: Option) {

        if(option.drawableId == R.drawable.ic_log_out_option) {
            //TODO: ELIMINAR DATOS DE SESIÓN
            onLogoutSelected.call()
            return
        }

        onShowSelectedOption.value = option
    }

    private fun getOptions(): List<Option> {
        return listOf(
            Option(1, R.drawable.ic_profile_option, R.string.my_profile_option),
            Option(2, R.drawable.ic_wallet_option, R.string.wallet_option),
            Option(3, R.drawable.ic_addresses_option, R.string.adresses_option),
            Option(4, R.drawable.ic_orders_option, R.string.my_orders_option),
            Option(5, R.drawable.ic_favorites_option, R.string.favorites_option),
            Option(6, R.drawable.ic_support_option, R.string.support_option),
            Option(7, R.drawable.ic_contact_option, R.string.contact_us_option),
            Option(8, R.drawable.ic_track_order_option, R.string.track_your_order_option),
            Option(9, R.drawable.ic_log_out_option, R.string.log_out_option)
        )
    }

}
