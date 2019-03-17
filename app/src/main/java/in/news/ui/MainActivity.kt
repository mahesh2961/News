package `in`.news.ui

import `in`.news.R
import `in`.news.databinding.ActivityMainBinding
import `in`.news.ui.utils.IActivityEventHandler
import `in`.news.ui.utils.IBackPressListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Main Host actvitiy of the application.
 *
 */
class MainActivity : AppCompatActivity(),HasSupportFragmentInjector,IActivityEventHandler{


    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    private lateinit var navController: NavController
    private var backPressListener :IBackPressListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.news_nav_fragment)
        AppBarConfiguration(navController.graph) //Using Nav graph for fragment navigation
        setSupportActionBar(binding.toolbarMain)

    }

    override fun setOnBackPressListener(backPressListener: IBackPressListener?) {
        this.backPressListener=backPressListener
    }

    /**
     * Checks if any fragment is handling back button press event
     */
    override fun onBackPressed() {
        var result=backPressListener?.handleBackPress()
        if(result==false || result==null) super.onBackPressed()

    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector


}