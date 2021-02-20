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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.ItemPeopleBinding
import com.example.mvvm.model.People
import com.example.mvvm.viewmodel.ItemPeopleViewModel

class PeopleAdapter :
    RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder>() {
    private var peopleList: List<People>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PeopleAdapterViewHolder {
        val itemPeopleBinding: ItemPeopleBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_people,
                parent,
                false
            )
        return PeopleAdapterViewHolder(itemPeopleBinding)
    }

    override fun onBindViewHolder(
        holder: PeopleAdapterViewHolder,
        position: Int
    ) {
        holder.bindPeople(peopleList[position])
    }

    override fun getItemCount(): Int {
        return peopleList.size
    }

    fun setPeopleList(peopleList: List<People>) {
        this.peopleList = peopleList
        notifyDataSetChanged()
    }

    class PeopleAdapterViewHolder(var binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.itemPeople) {
        fun bindPeople(people: People?) {
            if (binding!!.itempeopleViewModel == null) {
                binding!!.itempeopleViewModel=(ItemPeopleViewModel(itemView.context, people!!))
            } else {
                binding!!.itempeopleViewModel!!.setPeopleList(people!!)
            }
        }

    }

    init {
        peopleList = emptyList()
    }
}