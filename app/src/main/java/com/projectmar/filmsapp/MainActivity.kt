package com.projectmar.filmsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.projectmar.filmsapp.presentation.ListFragment

class MainActivity : AppCompatActivity(), ShowFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListFragment.newInstance(), null)
                .commit()
        }
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, null)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }
}

interface ShowFragment {

    fun showFragment(fragment: Fragment)

    class Empty : ShowFragment {
        override fun showFragment(fragment: Fragment) = Unit
    }
}