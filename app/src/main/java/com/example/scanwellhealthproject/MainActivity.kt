package com.example.scanwellhealthproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scanwellhealthproject.adapters.UsersAdapter
import com.example.scanwellhealthproject.models.Result.Status.*
import com.example.scanwellhealthproject.models.User
import com.example.scanwellhealthproject.viewmodels.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val userList = ArrayList<User>()
    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Stack Overflow Users"
        val linearLayoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(
            recycler_view.context,
            linearLayoutManager.orientation
        )

        recycler_view.addItemDecoration(dividerItemDecoration)
        usersAdapter = UsersAdapter(this, userList)
        recycler_view.adapter = usersAdapter

        subscribe()
    }

    private fun subscribe(){
        viewModel.userList.observe(this, { result ->
            when(result.status){
                SUCCESS -> {
                    result.data?.items?.let { list -> usersAdapter.updateUsers(list) }
                    loadingBar.visibility = View.GONE
                }
                ERROR -> {
                    Toast.makeText(applicationContext, getString(R.string.error), Toast.LENGTH_SHORT).show()
                    result.message?.let {
                        Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
                    }
                }
                LOADING -> {
                    loadingBar.visibility = View.VISIBLE
                }
            }
        })
    }
}