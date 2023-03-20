package com.example.e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.databinding.ActivityDrawerMenuBinding

class DrawerMenu :AppCompatActivity() {
    private lateinit var binding: ActivityDrawerMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDrawerMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val sliderItems = listOf(
            SliderItem(R.drawable.discountberry),
            SliderItem(R.drawable.discountbrocoli),
            SliderItem(R.drawable.discountmeat),
            SliderItem(R.drawable.discountmeat),
            SliderItem(R.drawable.discountmeat),
            SliderItem(R.drawable.discountmeat),
            // add more items as needed
        )

        val recyclerView = binding.discountedRecycler
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = SliderAdapter(sliderItems)




        val categoryItems = listOf(
            CategoryItem(R.drawable.electronics, "Fruits"),
            CategoryItem(R.drawable.electronics, "Vegetables"),
            CategoryItem(R.drawable.electronics, "Meat"),
            CategoryItem(R.drawable.electronics, "Meats"),
            CategoryItem(R.drawable.electronics, "Meatss"),
            // add more items as needed
        )
        val categoryRecycler = binding.categoryRecycler
        categoryRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryRecycler.adapter = CategoryAdapter(categoryItems)



    }


    data class CategoryItem(val image: Int, val title: String)

    data class SliderItem(val image: Int)
}



