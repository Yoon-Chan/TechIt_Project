package com.example.memoapp

data class Memo(val title : String, val content : String, val date: String){
    companion object{
        val dataList = mutableListOf<Memo>()
    }

}
