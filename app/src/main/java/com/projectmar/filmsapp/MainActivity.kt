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
            showFragment(ListFragment.newInstance(), false)
        }
    }

    override fun showFragment(fragment: Fragment, add: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        val container = R.id.fragment_container
        if (add)
            transaction.add(container, fragment)
                .addToBackStack(fragment.javaClass.simpleName)
        else
            transaction.add(container, fragment)
        transaction.commit()
    }

    override fun popBackStack() {
        supportFragmentManager.popBackStack()
    }
}


interface ShowFragment {

    fun showFragment(fragment: Fragment, add: Boolean)
    fun popBackStack()

    class Empty : ShowFragment {
        override fun showFragment(fragment: Fragment, add: Boolean) = Unit
        override fun popBackStack() {

        }
    }
}