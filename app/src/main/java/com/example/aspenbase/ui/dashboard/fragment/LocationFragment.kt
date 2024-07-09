package com.example.aspenbase.ui.dashboard.fragment

import android.annotation.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import androidx.recyclerview.widget.*
import com.example.aspenbase.*
import com.example.aspenbase.data.*
import com.example.aspenbase.databinding.*
import com.example.aspenbase.ui.*
import com.example.aspenbase.ui.dashboard.*

@SuppressLint("NotifyDataSetChanged")
class LocationFragment : Fragment() {

    private var _binding: FragmentLocationBinding? = null
    private val binding get() = _binding
    private var popularList: MutableList<CardItem> = mutableListOf()
    private var cardAdapter: CardAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        initDummyData()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding?.recyclerViewPopular?.run {
            cardAdapter = CardAdapter(popularList)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = cardAdapter
        }
    }

    private fun initDummyData() {
        popularList.run {
            add(CardItem(R.drawable.img_danang, "Home Stay", 4.1, true))
            add(CardItem(R.drawable.bg_welcome, "Alley Place", 5.0, true))
            add(CardItem(R.drawable.img_danang, "Home Stay", 4.1, false))
            add(CardItem(R.drawable.bg_welcome, "Alley Place", 5.0, true))
            add(CardItem(R.drawable.img_danang, "Home Stay", 4.1))
            add(CardItem(R.drawable.bg_welcome, "Alley Place", 5.0))
            add(CardItem(R.drawable.img_danang, "Home Stay", 4.1))
            add(CardItem(R.drawable.bg_welcome, "Alley Place", 5.0))
        }
    }

    private fun initListener() {
        cardAdapter?.run {
            onClickItem = {
                Toast.makeText(context, "Its a toast! $it.title", Toast.LENGTH_SHORT).show()
                (activity as? HomeActivity)?.addDetailFragment(it)
            }

            onClickFavorite = { position ->
                popularList[position].run {
                    isFavorite = !isFavorite
                }
                cardAdapter?.notifyDataSetChanged()

                Toast.makeText(context, "Its a toast! Favorite ${popularList[position].title}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

}