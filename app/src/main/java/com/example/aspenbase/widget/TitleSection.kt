package com.example.aspenbase.widget

import android.content.*
import android.util.*
import android.view.*
import android.widget.*
import androidx.appcompat.widget.*
import com.example.aspenbase.*
import com.example.aspenbase.databinding.*

class TitleSection(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayoutCompat(context, attrs) {

    private var binding: TitleSectionBinding? = null
    internal var onSeeAll: () -> Unit = {}

    init {
        initView(attrs)
        initListener()
    }

    private fun initView(attrs: AttributeSet?) {
        binding = TitleSectionBinding.inflate(LayoutInflater.from(context), this, true)
        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.TitleSection)
        try {
            binding?.apply {
                tvSectionTitle.text =
                    styleAttrs.getString(R.styleable.TitleSection_sectionTitle)

                tvSeeAll.text =
                    styleAttrs.getString(R.styleable.TitleSection_seeAllText)

            }
        } finally {
            styleAttrs.recycle()
        }
    }

    private fun initListener() {
        binding?.tvSeeAll?.setOnClickListener {
            Toast.makeText(context, "Click See All", Toast.LENGTH_SHORT).show()
            onSeeAll()
        }
    }
}
