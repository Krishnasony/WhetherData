package i.krishnasony.whetherproject.ui


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import i.krishnasony.whetherproject.MainActivity
import i.krishnasony.whetherproject.R
import i.krishnasony.whetherproject.databinding.FragmentWeatherDataBinding
import i.krishnasony.whetherproject.room.entities.WeatherModel
import i.krishnasony.whetherproject.utils.ApiUtils
import i.krishnasony.whetherproject.utils.showToast


/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherDataFragment : Fragment() {

    private lateinit var dataBinding: FragmentWeatherDataBinding
    private var cityName:String?= null
    private lateinit var weatherModel: WeatherModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather_data,container,false)
        dataBinding.onCityNextClickListener = onEditCityClickListener
        getArgumnets()
        initViewModel()
        return dataBinding.root
    }

    private fun initViewModel() {


    }

    private fun getArgumnets() {
        arguments?.let {
            cityName = it.getString(CITY)
            weatherModel = it.getSerializable(WEATHERMODEL) as WeatherModel
            setData()
            dataBinding.tvCityName.text = cityName

        }?:run{
            showToast(context!!,"No Data Found in argumnet")
        }
    }

    private fun setData() {
        Picasso.get().load(ApiUtils.getImage(weatherModel.weather!![0]!!.icon!!)).into(dataBinding.idWeatherCloudIcon)
        dataBinding.idTvTitleWh.text = weatherModel.weather!![0]!!.main
        val temp = weatherModel.main!!.temp!! - 273.15
        val tempmin = weatherModel.main!!.tempMin!! - 273.15
        val tempmax = weatherModel.main!!.tempMax!! - 273.15
        dataBinding.idTemperatureWh.text = String.format("%.1f °C",temp)
        dataBinding.txtTempA.text = String.format("%.0f °C",tempmin)
        dataBinding.txtTempB.text = String.format("%.0f °C",tempmax)
    }


    private var onEditCityClickListener = View.OnClickListener {
            MainActivity.start(context!!,"Bhopal")
    }
    companion object {
        const val WEATHERMODEL = "weathermodel"
        const val CITY = "city"
        fun newInstance(weatherModel: WeatherModel?,cityName:String?):WeatherDataFragment{
            val bundle = Bundle()
            bundle.putSerializable(WEATHERMODEL,weatherModel)
            bundle.putString(CITY,cityName)
            val fragment = WeatherDataFragment()
            fragment.arguments = bundle
            return fragment
        }
    }


}
