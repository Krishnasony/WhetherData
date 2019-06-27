package i.krishnasony.whetherproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import i.krishnasony.whetherproject.databinding.ActivityMainBinding
import i.krishnasony.whetherproject.ui.WeatherDataFragment
import i.krishnasony.whetherproject.utils.replaceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setStatusBarGradiant(this)
        dataBinding.onNextClickListener = onNextClickListener
    }

    private var onNextClickListener = View.OnClickListener {
        val fragment = WeatherDataFragment.newInstance("Bhopal")
        replaceFragment(fragment,R.id.container)
    }

    fun setStatusBarGradiant(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            val background = ContextCompat.getDrawable(activity, R.drawable.weather_bg)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
            window.setBackgroundDrawable(background)
        }
    }

    companion object {
        const val CITY_NAME = "city_name"
        fun start(context:Context,cityName:String){
            val intent = Intent(context,MainActivity::class.java)
            intent.putExtra(CITY_NAME,cityName)
            context.startActivity(intent)
        }
    }

}
