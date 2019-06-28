package i.krishnasony.whetherproject.ui


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import i.krishnasony.whetherproject.MainActivity
import i.krishnasony.whetherproject.R
import i.krishnasony.whetherproject.api.WhetherApi
import i.krishnasony.whetherproject.databinding.FragmentWeatherDataBinding
import i.krishnasony.whetherproject.room.repo.WeatherRepo
import i.krishnasony.whetherproject.utils.showToast
import i.krishnasony.whetherproject.viewmodel.WeatherDataViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherDataFragment : Fragment() {

    private lateinit var dataBinding: FragmentWeatherDataBinding
    val weatherApi:WhetherApi by inject()
    val mViewModel:WeatherDataViewModel by viewModel()
    private lateinit var repo: WeatherRepo
    private var cityName:String?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather_data,container,false)
        dataBinding.onCityNextClickListener = onEditCityClickListener
        getArgumnets()
        initViewModel()
        getWeatherData()
        return dataBinding.root
    }

    private fun getWeatherData() {
        repo = WeatherRepo(weatherApi,cityName)
        mViewModel.getWeatherData(repo)
    }

    private fun initViewModel() {


    }

    private fun getArgumnets() {
        arguments?.let {
             cityName = it.getString(CITY)
            dataBinding.tvCityName.text = cityName

        }?:run{
            showToast(context!!,"No Data Found in argumnet")
        }
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
