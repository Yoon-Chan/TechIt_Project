package com.example.memoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.memoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val dataList = mutableListOf<Memo>()
    private val requestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == RESULT_OK){
            val title = it.data?.getStringExtra("title")
            val content = it.data?.getStringExtra("content")
            val date = it.data?.getStringExtra("date")

            Log.d("result", "title : $title\ncontent : $content\ndate : $date")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = "메모앱"

        //임시 데이터
        dataList.add(Memo("제목1", "내용1", "2023-07-07"))
        dataList.add(Memo("제목2", "내용2", "2023-07-08"))
        dataList.add(Memo("제목3", "내용3", "2023-07-09"))

        //리사이클러 뷰 적용
        binding.recyclerView.apply {
            adapter = MemoRecyclerViewAdapter(dataList)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add -> {
                Toast.makeText(this, "add menu 클릭", Toast.LENGTH_LONG).show()
                val intent = Intent(this, memoActivity::class.java)
                requestLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}