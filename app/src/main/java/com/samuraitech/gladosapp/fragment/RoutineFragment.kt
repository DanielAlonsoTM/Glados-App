package com.samuraitech.gladosapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.samuraitech.gladosapp.R
import com.samuraitech.gladosapp.adapter.RoutinesAdapter
import com.samuraitech.gladosapp.api.RoutineRestAPI
import com.samuraitech.gladosapp.model.Routine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoutineFragment : Fragment() {

    companion object {
        fun newInstance(): RoutineFragment = RoutineFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val viewOfLayout = inflater.inflate(R.layout.fragment_routine, container, false)

        val listRoutines: ArrayList<Routine> = ArrayList()

        //Load data
        loadData(listRoutines, viewOfLayout)

        //Refresh
        val swipeRefresh: SwipeRefreshLayout = viewOfLayout.findViewById(R.id.swipe_refresh_routines)
        swipeRefresh.setOnRefreshListener {
            listRoutines.clear()
            loadData(listRoutines, viewOfLayout)
            swipeRefresh.isRefreshing = false
        }

        return viewOfLayout
    }

    private fun loadData(listRoutines: ArrayList<Routine>, viewOfLayout: View) {
        RoutineRestAPI()
            .getAllRoutines()
            .enqueue(object : Callback<List<Routine>> {
                override fun onFailure(call: Call<List<Routine>>?, t: Throwable?) {
                    t!!.printStackTrace()
                }

                override fun onResponse(call: Call<List<Routine>>?, response: Response<List<Routine>>?) {
                    try {
                        if (response!!.body() == null) {
                            Log.e("NULL_RESPONSE", "Response is null: $response")
                        } else {
                            response.body().forEach {
                                listRoutines.add(it)
                            }

                            //Build RecyclerView
                            val recyclerRoutines: RecyclerView = viewOfLayout.findViewById(R.id.recycler_view_routines)
                            recyclerRoutines.layoutManager = LinearLayoutManager(context!!)
                            recyclerRoutines.adapter = RoutinesAdapter(listRoutines, context!!)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            })
    }
}
