package com.example.kotlintest1.Modals

data class ProductList2(
    val id : String,
    val indian_product_title : String,
    val indian_product_images :ArrayList<ImageList>,
    val other_country_product_title:String,
    val other_country_product_images :ArrayList<ImageList>,
    val category:String
)