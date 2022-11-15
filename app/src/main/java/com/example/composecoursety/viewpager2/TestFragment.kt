package com.example.composecoursety.viewpager2

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.composecoursety.R

class TestFragment : Fragment(R.layout.fragment_test){
    companion object {
        fun newInstance(tab: String): TestFragment {
            val fragment = TestFragment()
            val bundle = Bundle()
            bundle.putString("tabTitle", tab)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv).apply {
            text = arguments?.getString("tabTitle")
        }
    }
}