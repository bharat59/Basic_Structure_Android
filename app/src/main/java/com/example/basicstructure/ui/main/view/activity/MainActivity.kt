package com.example.basicstructure.ui.main.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.basicstructure.data.model.Photo
import com.example.basicstructure.data.model.UserResponse
import com.example.basicstructure.data.source.network.NetworkResult
import com.example.basicstructure.databinding.ActivityMainBinding
import com.example.basicstructure.ui.base.BaseActivity
import com.example.basicstructure.ui.main.adapter.UserAdapter
import com.example.basicstructure.ui.main.viewmodel.HomeViewModel
import com.example.basicstructure.util.hide
import com.example.basicstructure.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private var loadMore: Boolean = false
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter
    private var photos: ArrayList<Photo> = ArrayList()
    private lateinit var binding: ActivityMainBinding

    private var adapterClickCallbacks = object : UserAdapter.ClickListener {
        override fun itemClick(position: Int) {
            Toast.makeText(this@MainActivity, "$position is clicked", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpObserver()
        setClickListener()
        setLoadMoreListener()
        getUserData()

        binding.endlessList.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        userAdapter = UserAdapter(photos, adapterClickCallbacks)
        binding.endlessList.adapter = userAdapter
    }


    private fun setLoadMoreListener() {

        binding.endlessList.setEndlessScrollCallback {
            //Now list view can be set so that it will block load until certain task finish
//            endless_list.blockLoading()
            loadMore = true
            homeViewModel.updatePage()
            homeViewModel.fetchUserData()
        }
    }

    private fun setClickListener() {
    }

    private fun setUpObserver() {
        homeViewModel.userLiveData.observe(this,
            {
                if (it != null) {
                    handleUserData(it)
                }
            })
    }

    private fun handleUserData(result: NetworkResult<UserResponse>) {
        when (result) {
            is NetworkResult.Loading -> {
                // show a progress bar
                Log.e("TAG", "handleUserData() --> Loading  $result")
                binding.progressBarMain.show()
            }
            is NetworkResult.Success -> {
                // bind data to the view
                Log.e("TAG", "handleUserData() --> Success  $result")
                binding.progressBarMain.hide()
                result.data?.let { userAdapter.setData(it.photos, loadMore) }
            }
            is NetworkResult.Error -> {
                // show error message
                Log.e("TAG", "handleUserData() --> Error ${result.message}")
                binding.progressBarMain.hide()
            }
        }
    }

    private fun getUserData() {
        homeViewModel.fetchUserData()
    }

}
