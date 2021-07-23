package com.ggh.model_home

import androidx.recyclerview.widget.LinearLayoutManager
import com.ggh.app.adapter.item.AdapterItem
import com.ggh.baselibrary.base.adapter.CommonAdapter
import com.ggh.baselibrary.base.adapter.ListBean
import com.ggh.baselibrary.base.adapter.MultipleBean
import com.ggh.model_home.RecyclerView.bean.HomeEnumType
import com.ggh.model_home.RecyclerView.viewholder.HomeItemBlackViewHolder
import com.ggh.model_home.RecyclerView.viewholder.HomeItemGreenViewHolder
import com.ggh.model_home.RecyclerView.viewholder.HomeItemRedViewHolder
import com.ggh.model_home.RecyclerView.viewholder.HomeItemViewHolder
import com.mcl.kotlin_mvvm.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment(R.layout.fragment_home) {
    val mAdapter by lazy { TestAdapter() }

    var mList = mutableListOf<MultipleBean<ListBean>>()

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun main() {
        mRecycler.apply {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }

        mList.add(MultipleBean(viewType = HomeEnumType.RED,title ="红色"))
        mList.add(MultipleBean(viewType = HomeEnumType.BLANK,title ="黑色"))
        mList.add(MultipleBean(viewType = HomeEnumType.GREEN,title ="绿色"))

        mAdapter.data = mList
    }

    class TestAdapter : CommonAdapter<MultipleBean<ListBean>>(){
        override fun createItem(type: Any): AdapterItem<MultipleBean<ListBean>> {
            return when(type){
                HomeEnumType.RED -> {
                    HomeItemRedViewHolder()
                }
                HomeEnumType.BLANK -> {
                    HomeItemBlackViewHolder()
                }
                HomeEnumType.GREEN -> {
                    HomeItemGreenViewHolder()
                }
                else -> HomeItemViewHolder()
            }
        }

        override fun getItemType(t: MultipleBean<ListBean>): Any {
            return t.viewType
        }

    }

}
