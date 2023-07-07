package com.example.memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.memoapp.databinding.ActivityDetailMemoBinding

class DetailMemo : AppCompatActivity() {
    private lateinit var binding : ActivityDetailMemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //받은 데이터를 표시하기 위한 setText
        setText()
    }

    fun setText(){
        //데이터 가져오기
        val pos = intent.getIntExtra("position", -1)

        if(pos == -1) return
        val memo = Memo.dataList[pos]

        binding.titleTextView.text = memo.title
        binding.contentTextView.text = memo.content
        binding.dateTextView.text = memo.date
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_memo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.update -> {
                Toast.makeText(this, "수정버튼 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.delete -> {
                Toast.makeText(this, "삭제버튼 클릭", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }
}