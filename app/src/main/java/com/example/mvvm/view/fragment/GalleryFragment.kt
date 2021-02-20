package com.example.mvvm.view.fragment

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.databinding.FragmentGalleryBinding
import com.example.mvvm.utils.RecyclerItemDecoration
import com.example.mvvm.view.adapter.GalleryAdapter
import com.example.mvvm.viewmodel.GalleryViewModel
import java.util.*

class GalleryFragment : Fragment(), Observer {
    var galleryViewModel: GalleryViewModel? = null
    var binding: FragmentGalleryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_gallery, container, false
        )
        val view = binding!!.getRoot()
        initDataBinding()
        setupListGalleryView(binding!!.recyclerData)
        setupObserver(galleryViewModel)
        return view
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initDataBinding() {
        galleryViewModel = GalleryViewModel(requireActivity())
        binding!!.galleryViewModel = galleryViewModel
        galleryViewModel!!.setdata()
    }

    private fun setupListGalleryView(recyclergallery: RecyclerView) {
        val adapter = GalleryAdapter()

        val manager: GridLayoutManager


        // function for getting screen density of android
        

        val densityDpi = resources.displayMetrics.densityDpi

        when (densityDpi) {
            DisplayMetrics.DENSITY_LOW -> // LDPI
            {
                manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
                adapter.setcount(3)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        3
                    )
                )
                println("i am ldpi screen")
            }

            DisplayMetrics.DENSITY_MEDIUM -> {
                // MDPI
                println("i am mdpi screen")
                manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
                adapter.setcount(3)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        3
                    )
                )
            }
            DisplayMetrics.DENSITY_TV -> {
                println("i am TV screen")
                manager = GridLayoutManager(activity, 5, GridLayoutManager.VERTICAL, false)
                adapter.setcount(5)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        5
                    )
                )
            }


            DisplayMetrics.DENSITY_HIGH -> {
                // HDPI
                println("i am hdpi screen")
                manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
                adapter.setcount(3)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        3
                    )
                )
            }
            DisplayMetrics.DENSITY_XHIGH -> {
                println("i am DENSITY_XHIGH screen")
                manager = GridLayoutManager(activity, 5, GridLayoutManager.VERTICAL, false)
                adapter.setcount(5)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        5
                    )
                )
            }
            DisplayMetrics.DENSITY_280 -> { // XHDPI
                println("i am xhdpi screen")
                manager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)
                adapter.setcount(4)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        4
                    )
                )
            }
            DisplayMetrics.DENSITY_XXHIGH -> {
                println("i am DENSITY_XXHIGH screen")
                manager = GridLayoutManager(activity, 5, GridLayoutManager.VERTICAL, false)
                adapter.setcount(5)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        5
                    )
                )

            }

            DisplayMetrics.DENSITY_360 -> {
                println("i am DENSITY_360 screen")
                manager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)

                adapter.setcount(4)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        4
                    )
                )
            }
            DisplayMetrics.DENSITY_400 -> {
                println("i am DENSITY_400 screen")
                manager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)
                adapter.setcount(4)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        4
                    )
                )
            }
            DisplayMetrics.DENSITY_420 -> {
                // XXHDPI
                println("i am xxhdpi screen")
                manager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)
                adapter.setcount(4)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        4
                    )
                )
            }
            DisplayMetrics.DENSITY_XXXHIGH -> {
                println("i am DENSITY_XXXHIGH screen")
                manager = GridLayoutManager(activity, 5, GridLayoutManager.VERTICAL, false)
                adapter.setcount(5)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        5
                    )
                )
            }
            DisplayMetrics.DENSITY_560 -> {
                println("i am xxxhdpi screen")
                manager = GridLayoutManager(activity, 4, GridLayoutManager.VERTICAL, false)
                adapter.setcount(4)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        4
                    )
                )
            }// XXXHDPI
            else -> {
                println("i am in default")
                manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
                adapter.setcount(3)
                recyclergallery.addItemDecoration(
                    RecyclerItemDecoration(
                        resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
                        3
                    )
                )

            }
        }
//        val manager =
//            GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
//        recyclergallery.addItemDecoration(
//            RecyclerItemDecoration(
//                resources.getDimensionPixelSize(R.dimen.photos_list_spacing),
//                resources.getInteger(R.integer.photo_list_preview_columns)
//            )
//        )
        recyclergallery.layoutManager = manager
        recyclergallery.adapter = adapter
        recyclergallery.setHasFixedSize(true)
    }

    fun setupObserver(observable: Observable?) {
        observable!!.addObserver(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        galleryViewModel!!.reset()
    }

    override fun update(observable: Observable?, data: Any?) {
        if (observable is GalleryViewModel) {
            val galleryadapter =
                binding!!.recyclerData.adapter as GalleryAdapter?
            galleryadapter?.setGalleryList(observable.getstrList())
        }
    }
}