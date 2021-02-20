/**
 * Copyright 2016 Erik Jhordan Rey.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.mvvm.view.adapter

import android.R.attr.resource
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.ItemGalleryBinding
import com.example.mvvm.viewmodel.ItemGalleryViewModel
import java.util.*


class GalleryAdapter :
    RecyclerView.Adapter<GalleryAdapter.PeopleAdapterViewHolder>() {
    var binding: ItemGalleryBinding? = null
    var context: Context? = null
    var gridcount=0
    private var strlist: List<String?> =
        ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeopleAdapterViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_gallery,
            parent,
            false
        )
        return PeopleAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PeopleAdapterViewHolder,
        position: Int
    ) {
        val displaymetrics = DisplayMetrics()
        (context as AppCompatActivity?)!!.windowManager.defaultDisplay
            .getMetrics(displaymetrics)
        //if you need three fix imageview in width
        val devicewidth = displaymetrics.widthPixels / gridcount

        //if you need 4-5-6 anything fix imageview in height
        binding!!.ivImg.layoutParams.width = devicewidth

        //if you need same height as width you can set devicewidth in holder.image_view.getLayoutParams().height
        binding!!.ivImg.layoutParams.height = devicewidth
        holder.bind(strlist[position])
    }

    private fun dptopx(dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(), context!!.resources.getDisplayMetrics()
        ).toInt()
    }

    override fun getItemCount(): Int {
        return strlist.size
    }

    fun setGalleryList(strlist: List<String?>) {
        this.strlist = strlist
        notifyDataSetChanged()
    }

    class PeopleAdapterViewHolder // tvText = (CustomTextView) itemView.findViewById(R.id.lbl_title);
        (var binding: ItemGalleryBinding?) :
        RecyclerView.ViewHolder(binding!!.itemLayout) {
        fun bind(imgurl: String?) {
            if (binding!!.itemgalleryViewModel == null) {
                binding!!.itemgalleryViewModel = ItemGalleryViewModel(imgurl!!)
            } else {
                binding!!.itemgalleryViewModel!!.setImageUrl(imgurl!!)
            }
        }

    }

    init {
        strlist = ArrayList()
    }

   fun setcount(count:Int)
   {
       gridcount=count
   }
}