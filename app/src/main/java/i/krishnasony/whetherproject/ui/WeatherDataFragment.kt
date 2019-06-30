package i.krishnasony.whetherproject.ui


import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import i.krishnasony.whetherproject.MainActivity
import i.krishnasony.whetherproject.R
import i.krishnasony.whetherproject.api.WhetherApi
import i.krishnasony.whetherproject.databinding.FragmentWeatherDataBinding
import i.krishnasony.whetherproject.room.entities.ForeCastModel
import i.krishnasony.whetherproject.room.entities.WeatherModel
import i.krishnasony.whetherproject.room.repo.WeatherRepo
import i.krishnasony.whetherproject.utils.ApiUtils
import i.krishnasony.whetherproject.utils.showToast
import i.krishnasony.whetherproject.viewmodel.ForeCastViewModel
import i.krishnasony.whetherproject.viewmodel.WeatherDataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel





/**
 * A simple [Fragment] subclass.
 *
 */
class WeatherDataFragment : Fragment() {

    private lateinit var dataBinding: FragmentWeatherDataBinding
    private var cityName:String?= null
    val weatherApi: WhetherApi by inject()
    val mViewModel: ForeCastViewModel by viewModel()
    val mWeatherViewModel: WeatherDataViewModel by viewModel()
    private lateinit var repo: WeatherRepo
    private lateinit var weatherModel: WeatherModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_weather_data,container,false)
        dataBinding.onCityNextClickListener = onEditCityClickListener
        getArgumnets()
        setSwipeRefreshLayout()
        return dataBinding.root
    }

    private fun setSwipeRefreshLayout() {
        dataBinding.idRefresh.setOnClickListener {
            dataBinding.container.visibility = View.GONE
            dataBinding.progressBar.visibility = View.VISIBLE
            GlobalScope.launch {
                delay(1000)
                getWeatherData()
            }
        }
    }

    private fun getWeatherData() {
        repo = WeatherRepo(weatherApi,cityName)
        GlobalScope.launch(Dispatchers.Main) {
            mWeatherViewModel.getWeatherData(repo)
            delay(500)
            mWeatherViewModel.weatherData.observeForever {
                it?.let {
                    weatherModel = it
                    dataBinding.container.visibility = View.VISIBLE
                    dataBinding.progressBar.visibility = View.GONE
                }?:run{
                    showToast(context!!,"No Data Found")
                    dataBinding.container.visibility = View.VISIBLE
                    dataBinding.progressBar.visibility = View.GONE
                }
            }
        }
    }


    private fun getArgumnets() {
        arguments?.let {
            cityName = it.getString(CITY)
            weatherModel = it.getSerializable(WEATHERMODEL) as WeatherModel
            getForeCastData()
            setData()
            dataBinding.tvCityName.text = cityName

        }?:run{
            showToast(context!!,"No Data Found in argumnet")
        }
    }

    private fun getForeCastData() {
        repo = WeatherRepo(weatherApi,cityName)
        GlobalScope.launch(Dispatchers.Main) {
            mViewModel.getForeCastData(repo)
            dataBinding.container.visibility = View.GONE
            dataBinding.progressBar.visibility = View.VISIBLE
            delay(500)
            mViewModel.forecastData.observeForever {
                it?.let {
                    setForeCastData(it)
                    dataBinding.container.visibility = View.VISIBLE
                    dataBinding.progressBar.visibility = View.GONE
                }?:run{
                    showToast(context!!,"No Data Found")
                }
            }
        }

    }

    private fun setForeCastData(foreCastModel: ForeCastModel?) {
        foreCastModel?.let {
          val list =  it.list!!.distinctBy { it!!.dtTxt }
            list[0]?.let{
                val temMin = it.main!!.tempMin!! - 273.15
                val temMax = it.main!!.tempMax!! - 273.15
                dataBinding.tvMon.text = String.format("%.0f",temMin).plus("/").plus(String.format("%.0f",temMax))
                Picasso.get().load(ApiUtils.getImage(it.weather!![0]!!.icon!!)).into(dataBinding.ivMon)
                dataBinding.tvDayMon.text  = ApiUtils.getDayFromDate(it.dtTxt!!)


            }
            list[7]?.let{
                val temMin = it.main!!.tempMin!! - 273.15
                val temMax = it.main!!.tempMax!! - 273.15
                dataBinding.tvTue.text = String.format("%.0f",temMin).plus("/").plus(String.format("%.0f",temMax))
                Picasso.get().load(ApiUtils.getImage(it.weather!![0]!!.icon!!)).into(dataBinding.ivTue)
                dataBinding.tvDayTue.text  = ApiUtils.getDayFromDate(it.dtTxt!!)


            }
            list[14]?.let{
                val temMin = it.main!!.tempMin!! - 273.15
                val temMax = it.main!!.tempMax!! - 273.15
                dataBinding.tvWed.text = String.format("%.0f",temMin).plus("/").plus(String.format("%.0f",temMax))
                Picasso.get().load(ApiUtils.getImage(it.weather!![0]!!.icon!!)).into(dataBinding.ivWed)
                dataBinding.tvDayWed.text  = ApiUtils.getDayFromDate(it.dtTxt!!)


            }
            list[21]?.let{
                val temMin = it.main!!.tempMin!! - 273.15
                val temMax = it.main!!.tempMax!! - 273.15
                dataBinding.tvThu.text = String.format("%.0f",temMin).plus("/").plus(String.format("%.0f",temMax))
                Picasso.get().load(ApiUtils.getImage(it.weather!![0]!!.icon!!)).into(dataBinding.ivThu)
                dataBinding.tvDayThu.text  = ApiUtils.getDayFromDate(it.dtTxt!!)


            }
            list[28]?.let{
                val temMin = it.main!!.tempMin!! - 273.15
                val temMax = it.main!!.tempMax!! - 273.15
                dataBinding.tvFri.text = String.format("%.0f",temMin).plus("/").plus(String.format("%.0f",temMax))
                Picasso.get().load(ApiUtils.getImage(it.weather!![0]!!.icon!!)).into(dataBinding.ivFri)
                dataBinding.tvDayFri.text  = ApiUtils.getDayFromDate(it.dtTxt!!)


            }

        }

    }

    fun setTextAndImage(day:TextView,temp:TextView,img:ImageView,data: ForeCastModel.X){
        val temMin = data.main!!.tempMin!! - 273.15
        val temMax = data.main!!.tempMax!! - 273.15
        temp.text = String.format("%.0f",temMin).plus("/").plus(String.format("%.0f",temMax))
        Picasso.get().load(ApiUtils.getImage(data.weather!![0]!!.icon!!)).into(img)
        day.text  = ApiUtils.getDayFromDate(data.dtTxt!!)
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

    override fun onResume() {
        super.onResume()
       GlobalScope.launch {
           delay(5000)
           getWeatherData()
       }
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
