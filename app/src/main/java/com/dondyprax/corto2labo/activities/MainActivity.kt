package com.dondyprax.corto2labo.activities

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.dondyprax.corto2labo.R
import com.dondyprax.corto2labo.fragments.ButtonFragment
import com.dondyprax.corto2labo.fragments.imageFragment

class MainActivity : AppCompatActivity(), ButtonFragment.OnFragmentInteractionListener {

    private var count: Int =0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }

}
