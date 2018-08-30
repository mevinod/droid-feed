package com.droidfeed.ui.module.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.droidfeed.R
import com.droidfeed.databinding.ActivityMainBinding
import com.droidfeed.ui.adapter.BaseUiModelAlias
import com.droidfeed.ui.adapter.UiModelAdapter
import com.droidfeed.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main_app_bar.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainActivity : BaseActivity() {

    @Inject
    lateinit var navController: MainNavController

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isMarshmallow()) {
            setupTransparentStatusbar()
            lightStatusbarTheme()
        }
        super.onCreate(savedInstanceState)

        initBindings()
        init()
        initFilterDrawer()
    }

    private fun initBindings() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        navHeaderBinding = NavHeaderMainBinding.inflate(
//            layoutInflater,
//            binding.navView,
//            false
//        )
//
//        binding.navView.addHeaderView(navHeaderBinding.root)
    }

    private fun init() {
        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        setSupportActionBar(binding.appbar?.toolbar)
        supportActionBar?.title = getString(R.string.app_name)

//        binding.drawerLayout.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        toolbar.getChildAt(0).setOnClickListener {
            navController.scrollToTop()
        }
        navController.openNewsFragment()
    }
//
//    private fun initNavigationDrawer() {
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.appbar?.toolbar,
//            0,
//            0
//        )
//
//        toggle.isDrawerSlideAnimationEnabled = false
//        toggle.syncState()
//
//        binding.drawerLayout.addDrawerListener(object :
//            androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener() {
//            override fun onDrawerClosed(drawerView: View) {
//                super.onDrawerClosed(drawerView)
//                viewModel.shuffleHeaderImage()
//            }
//        })
//
//        binding.navView.setNavigationItemSelectedListener(navigationListener)
//        binding.navView.setCheckedItem(R.id.nav_feed)
//        navController.openNewsFragment()
//    }

    private fun initFilterDrawer() {
        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        val adapter = UiModelAdapter(layoutManager = layoutManager)

        viewModel.sourceUiModelData.observe(this, Observer {
            adapter.addUiModels(it as Collection<BaseUiModelAlias>)
        })

        (binding.filterRecycler.itemAnimator as androidx.recyclerview.widget.DefaultItemAnimator)
            .supportsChangeAnimations = false
        binding.filterRecycler.adapter = adapter
        binding.filterRecycler.overScrollMode = View.OVER_SCROLL_NEVER
        binding.filterRecycler.layoutManager = layoutManager
    }

    override fun onBackPressed() {
        when {
//            binding.drawerLayout.isDrawerOpen(GravityCompat.START) ->
//                binding.drawerLayout.closeDrawer(GravityCompat.START)
//
//            binding.drawerLayout.isDrawerOpen(GravityCompat.END) ->
//                binding.drawerLayout.closeDrawer(GravityCompat.END)

            else -> super.onBackPressed()
        }
    }

//    private val navigationListener = NavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.nav_feed -> {
//                navController.openNewsFragment()
//                binding.appbar?.toolbar?.setTitle(R.string.app_name)
//            }
//            R.id.nav_bookmarks -> {
//                navController.openBookmarksFragment()
//                binding.appbar?.toolbar?.setTitle(R.string.nav_bookmarks)
//            }
//            R.id.nav_about -> {
//                navController.openAboutFragment()
//                binding.appbar?.toolbar?.setTitle(R.string.nav_about)
//            }
//            R.id.nav_newsletter -> {
//                navController.openNewsletterFragment()
//                binding.appbar?.toolbar?.setTitle(R.string.nav_newsletter)
//            }
//            R.id.nav_contribute -> {
//                navController.openHelpUsFragment()
//                binding.appbar?.toolbar?.setTitle(R.string.nav_contribute)
//            }
//        }
//
//        binding.drawerLayout.closeDrawer(GravityCompat.START)
//        true
//    }
}
