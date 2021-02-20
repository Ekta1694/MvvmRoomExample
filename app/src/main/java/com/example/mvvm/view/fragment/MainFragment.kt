package com.example.mvvm.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentMainBinding
import com.example.mvvm.view.adapter.PeopleAdapter
import com.example.mvvm.viewmodel.PeopleViewModel
import java.util.*

class MainFragment : Fragment(), Observer {
    private var peopleViewModel: PeopleViewModel? = null
    private var binding: FragmentMainBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )
        val view = binding!!.getRoot()
        initDataBinding()
        setupListPeopleView(binding!!.recyclerPeople)
        setupObserver(peopleViewModel)
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDataBinding() {
        peopleViewModel = PeopleViewModel(requireActivity())
        binding!!.mainViewModel = peopleViewModel
        peopleViewModel!!.setdata()
    }

    private fun setupListPeopleView(recyclerPeople: RecyclerView) {
        val adapter = PeopleAdapter()
        recyclerPeople.adapter = adapter
        recyclerPeople.setHasFixedSize(true)
    }

    fun setupObserver(observable: Observable?) {
        observable!!.addObserver(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        peopleViewModel!!.reset()
    }

    override fun update(observable: Observable?, data: Any?) {
        if (observable is PeopleViewModel) {
            val peopleAdapter =
                binding!!.recyclerPeople.adapter as PeopleAdapter?
            peopleAdapter?.setPeopleList(observable.plist)
        }
    }
}