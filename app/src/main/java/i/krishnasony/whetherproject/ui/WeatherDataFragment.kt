package i.krishnasony.whetherproject.ui


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import i.krishnasony.whetherproject.MainActivity

import i.krishnasony.whetherproject.R
import i.krishnasony.whetherproject.databinding.FragmentWeatherDataBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherDataFragment : Fragment() {

    private lateinit var dataBinding: FragmentWeatherDataBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather_data,container,false)
        dataBinding.onCityNextClickListener = onEditCityClickListener
        return dataBinding.root
    }

    private var onEditCityClickListener = View.OnClickListener {
            MainActivity.start(context!!,"Bhopal")
    }
    companion object {
        const val CITY ="city"
        fun newInstance(cityName:String?):WeatherDataFragment{
            val bundle = Bundle()
            bundle.putString(CITY,cityName)
            val fragment = WeatherDataFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


}
