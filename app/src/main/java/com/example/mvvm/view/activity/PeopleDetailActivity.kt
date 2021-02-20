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
package com.example.mvvm.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mvvm.R
import com.example.mvvm.databinding.PeopleDetailActivityBinding
import com.example.mvvm.model.People
import com.example.mvvm.viewmodel.PeopleDetailViewModel
import java.io.Serializable

class PeopleDetailActivity : AppCompatActivity() {


    private var binding: PeopleDetailActivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.people_detail_activity)
        setSupportActionBar(binding!!.toolbar)
        displayHomeAsUpEnabled()
        extrasFromIntent()
    }

    private fun displayHomeAsUpEnabled() {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        binding!!.toolbar.setNavigationOnClickListener {
            finish()
        }
    }


    private fun extrasFromIntent()
    {
        val people =
            intent.getSerializableExtra(EXTRA_PEOPLE) as People?
        val peopleDetailViewModel = PeopleDetailViewModel(people!!)
        binding!!.peopleDetailViewModel = peopleDetailViewModel
        title = people.name!!.title + "." + people.name!!.first + " " + people.name!!.last
    }



    companion object {
        private  val EXTRA_PEOPLE = "EXTRA_PEOPLE"
        fun launchDetail(
            context: Context?,
            people: People?
        ): Intent {
            val intent = Intent(context, PeopleDetailActivity::class.java)
            intent.putExtra(EXTRA_PEOPLE,people)
            return intent
        }
    }
}