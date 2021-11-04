package kz.news.app.ui.activities.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kz.news.app.R
import kz.news.app.databinding.ActivityMainBinding
import kz.news.app.ui_common.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel = getViewModel(MainViewModel::class.java)
        binding.viewModel = viewModel

        initView()
    }

    private fun initView() {
        navController = findNavController(R.id.nav_host_fragment)

        initToolbar()
        initOnDestinationChangedListener()
        initNavigationView()
    }

    private fun initToolbar() {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.everythingFragment,
                R.id.topHeadlinesFragment,
                R.id.navigation_news_details
            )
        )

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun initOnDestinationChangedListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.everythingFragment,
                R.id.topHeadlinesFragment,
                R.id.navigation_news_details
                -> {
                    //do nothing
                }
            }
        }
    }

    private fun initNavigationView() {
        binding.bottomNavigation.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        binding.bottomNavigation.setupWithNavController(navController)
    }


}