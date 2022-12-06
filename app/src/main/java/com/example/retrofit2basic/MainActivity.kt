package com.example.retrofit2basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit2basic.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG : String by lazy {
        MainActivity::class.java.simpleName
    }

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val emgMedAdapter : EmgMedAdapter by lazy {
        EmgMedAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = emgMedAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        binding.btnGet.setOnClickListener {
            retrofitWork()
        }
    }

    private fun retrofitWork(){
        val service = RetrofitApi.emgMedService

        service.getEmgMedData(getString(R.string.api_key), Type = "json")
            .enqueue(object : Callback<EmgMedResponse> {
                override fun onResponse(
                    call: Call<EmgMedResponse>,
                    response: Response<EmgMedResponse>
                ) {
                    if(response.isSuccessful){
                        Log.e(TAG, response.body().toString())
//                        val result : EmgMedInfo? = response.body()?.emgMedInfo?.get(1)?.row
                        val result : List<Row?>? = response.body()?.emgMedInfo?.get(1)?.row
                        emgMedAdapter.submitList(result!!)
                    }
                }

                override fun onFailure(call: Call<EmgMedResponse>, t: Throwable) {
                    Log.e(TAG, t.message.toString())
                }
            })
    }
}