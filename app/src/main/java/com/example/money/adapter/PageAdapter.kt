package com.example.money.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.example.money.fragment.ConfigFragment
import com.example.money.fragment.EgresosFragment
import com.example.money.fragment.IngresosFragment



@Suppress("UNREACHABLE_CODE")
class PageAdapter (fm:FragmentManager?, val numTabs:Int):FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return return when(position){
            0 -> EgresosFragment()
            1 -> IngresosFragment()
            2 -> ConfigFragment()
            else -> null
        }

    }
    override fun getCount(): Int {

        return numTabs
    }
}