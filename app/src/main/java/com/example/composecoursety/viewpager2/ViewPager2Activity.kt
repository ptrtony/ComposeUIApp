package com.example.composecoursety.viewpager2

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.composecoursety.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPager2Activity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    private var activeColor = Color.parseColor("#ff678f")
    private var normalColor = Color.parseColor("#666666")

    private var activeSize = 20
    private var normalSize = 14

    private var fragments: ArrayList<Fragment>? = null
    private var mediator: TabLayoutMediator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager2)

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);


        val tabs = listOf(
            "关注",
            "推荐",
            "最新"
        )

        //禁用预加载
        viewPager2.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        //Adapter
        viewPager2.adapter = object: FragmentStateAdapter(supportFragmentManager, lifecycle) {
            override fun getItemCount(): Int {
                return tabs.size
            }

            override fun createFragment(position: Int): Fragment {
                return TestFragment.newInstance(tabs[position])
            }
        }
        //viewPager 页面切换监听
        viewPager2.registerOnPageChangeCallback(changeCallback)

        mediator = TabLayoutMediator(tabLayout, viewPager2) { tab, position -> //这里可以自定义TabView
            val tabView = TextView(this@ViewPager2Activity)
            val states = arrayOfNulls<IntArray>(2)
            states[0] = intArrayOf(android.R.attr.state_selected)
            states[1] = intArrayOf()
            val colors = intArrayOf(
                activeColor,
                normalColor
            )
            val colorStateList = ColorStateList(states, colors)
            tabView.text = tabs[position]
            tabView.textSize = normalSize.toFloat()
            tabView.setTextColor(colorStateList)
            tab.customView = tabView
        }
        mediator?.attach()
    }

    private val changeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val tabCount = tabLayout.tabCount
            for (count in 0 until tabCount) {
                val tab = tabLayout.getTabAt(count)
                val tabView = tab?.customView as TextView
                if (tab.position == position) {
                    tabView.textSize = activeSize.toFloat()
                    tabView.typeface = Typeface.DEFAULT_BOLD
                } else {
                    tabView.textSize = normalSize.toFloat()
                    tabView.typeface = Typeface.DEFAULT
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(changeCallback)
    }
}