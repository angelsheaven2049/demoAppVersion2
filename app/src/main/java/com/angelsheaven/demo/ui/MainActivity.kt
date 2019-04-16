package com.angelsheaven.demo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.angelsheaven.demo.R
import com.angelsheaven.demo.ui.listArticle.DisplayListArticlesFragmentViewModel
import com.angelsheaven.demo.utilities.MyLogger
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

/**
 * MainActivity class: this is the container of all fragments
 * in application, it has navigation component used to navigate
 * between fragments
 * @author Quan Nguyen
 */
class MainActivity : AppCompatActivity(),
    HasSupportFragmentInjector, MyLogger {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * @mviewModel object variable used to hold data
     * and conduct business logic requests such as
     * send request refreshing data to fragments
     */
    val mViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(MainActivityViewModel::class.java)
    }

    /**
     * appBarConfiguration control the navigation between fragments
     */
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val host = supportFragmentManager
            .findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)

        /**
         * Register appBarConfiguRation to MainActivity
         * to handle fragments navigation
         */
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.my_nav_host_fragment)
            .navigateUp(appBarConfiguration)
    }

    override fun onResume() {
        super.onResume()
        /**
         * If user back to app
         * send request to refresh data
         * to fragments handle display data
         */
        DisplayListArticlesFragmentViewModel.instance()?.run {
            this.loadArticles()
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
