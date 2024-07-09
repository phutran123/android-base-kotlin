package com.example.aspenbase.ui.dashboard

import android.os.*
import android.view.*
import androidx.fragment.app.*
import androidx.viewpager2.widget.*
import com.example.aspenbase.*
import com.example.aspenbase.databinding.*
import com.google.android.material.tabs.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding

    private var adapter: TabLayoutAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initListener() {
        binding?.run {
            // Handle tab selection changes
            tabLayoutHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        viewPageHome.currentItem = it.position
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    // Not needed for this implementation
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    // Not needed for this implementation
                }
            })

            // Sync ViewPager2 with TabLayout on page changes
            viewPageHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tabLayoutHome.selectTab(tabLayoutHome.getTabAt(position))
                }
            })

            viewPageHome.let {
                TabLayoutMediator(tabLayoutHome, it) { tab, position ->
                    tab.text = getTabTitle(position) // Replace with your tab title logic
                }.attach()
            }
        }
    }

    private fun initView() {
        binding?.run {
            // Initialize TabLayoutAdapter
            adapter = TabLayoutAdapter(childFragmentManager, lifecycle)

            // Set adapter to ViewPager2
            viewPageHome.adapter = adapter
            // Add tabs dynamically based on adapter count
            for (i in 0 until (adapter?.itemCount ?: 0)) {
                tabLayoutHome.addTab(
                    tabLayoutHome.newTab().setText(getTabTitle(i))
                )
            }
        }
    }

    private fun getTabTitle(position: Int): String {
        return when (position) {
            0 -> getString(R.string.location)
            1 -> getString(R.string.hotel)
            2 -> getString(R.string.food)
            3 -> getString(R.string.adventure)
            4 -> getString(R.string.visit)
            else -> getString(R.string.tabPosition, position)
        }
    }
}