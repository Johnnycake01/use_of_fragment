package com.example.applicationusingfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager


//class main activity that extends appCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var fragmentManager: FragmentManager //declared as late init to be used later in the project
    //onCreate lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//set content view to connect to activity view
        //linking to views
        val addButton = findViewById<Button>(R.id.addButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val textCount = findViewById<TextView>(R.id.updateFragCount)
        // use instance of support fragment manager to set the manager for transaction
        fragmentManager = supportFragmentManager
        //using fragment manager to update back stack entry count using a listener
        fragmentManager.addOnBackStackChangedListener{
            ("Fragment: " + fragmentManager.backStackEntryCount).also { textCount.text = it }
        }
        //button to add new fragment when clicked
        addButton.setOnClickListener {
            addFragment()
        }
        //button to delete new fragment when clicked
        deleteButton.setOnClickListener {
            removeFragment()
        }
    }
    // function to add new fragment
    private fun addFragment() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment:Fragment = when(fragmentManager.backStackEntryCount){
            0 -> BlankFragment()
            1 -> FirstFragment()
            2 -> SecondFragment()
            3 -> ThirdFragment()
            else -> BlankFragment()
        }

        fragmentTransaction.add(R.id.fragmentContainer, fragment).addToBackStack(null).commit()
    }
    //function to remove fragment
    private fun removeFragment() {
        val fragment: Fragment? = fragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment != null) {
            fragmentManager.popBackStack()
        }

    }

}
