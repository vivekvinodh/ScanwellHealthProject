package com.example.scanwellhealthproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.scanwellhealthproject.models.Result
import com.example.scanwellhealthproject.viewmodels.DetailActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModels<DetailActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        title = ""

        intent?.getIntExtra(EXTRAS_ACCOUNT_ID, 0)?.let { id ->
            viewModel.fetchUserDetails(id)
            subscribe()
        } ?: Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
    }

    private fun subscribe(){
        viewModel.user.observe(this, { result ->
            when(result?.status){
                Result.Status.SUCCESS -> {
                    title = result.data?.displayName
                    Glide.with(this@DetailActivity).load(result.data?.profileImage).into(profileImageView)
                    displayNameTextView.text = result.data?.displayName
                    reputationTextView.text = result.data?.reputation.toString()
                    goldBadgeCountTextView.text = result.data?.badgeCounts?.gold.toString()
                    silverBadgeCountTextView.text = result.data?.badgeCounts?.silver.toString()
                    bronzeBadgeCountTextView.text = result.data?.badgeCounts?.bronze.toString()
                    detailsTextView.text = result.data?.toString()
                    loadingBar.visibility = View.GONE
                }
                Result.Status.ERROR -> {
                    Toast.makeText(this@DetailActivity, getString(R.string.error), Toast.LENGTH_SHORT).show()
                    loadingBar.visibility = View.GONE
                }
                Result.Status.LOADING -> loadingBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val EXTRAS_ACCOUNT_ID = "account_id"
    }
}