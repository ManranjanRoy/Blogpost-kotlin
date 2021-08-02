package com.dca.androidpractical.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dca.androidpractical.R
import com.dca.androidpractical.adaptor.MyAdaptor
import com.dca.androidpractical.model.post
import com.dca.androidpractical.network.ApiInterface
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var progreDialog: ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclelr = v.findViewById<RecyclerView>(R.id.recyclerView);
        recyclelr.layoutManager = LinearLayoutManager(context)
        progreDialog= ProgressDialog(context)
        progreDialog.setMessage("Please Wait while loading...")
        progreDialog.show()
        ApiInterface.create().getPosts().enqueue(object : retrofit2.Callback<List<post>> {
            override fun onResponse(call: Call<List<post>>?, response: Response<List<post>>?) {
                if (response?.body() != null) {
                    progreDialog.dismiss()
                    Log.d("tag", response.body().toString())
                    recyclelr.adapter = MyAdaptor(context!!, response.body()!!)
                }
               // Toast.makeText(context, "Sucess", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<List<post>>?, t: Throwable?) {
                progreDialog.dismiss()
                Toast.makeText(context, "Error" + t.toString(), Toast.LENGTH_LONG).show()
            }
        })
        return v;
    }
}