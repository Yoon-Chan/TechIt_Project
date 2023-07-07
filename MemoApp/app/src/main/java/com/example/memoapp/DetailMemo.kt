package com.example.memoapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.view.View.TEXT_ALIGNMENT_CENTER
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    override fun onRestart() {
        setText()
        super.onRestart()
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
                val updateIntent = Intent(this, UpdateActivity::class.java)
                val pos = intent.getIntExtra("position", -1)
                if(pos != -1){
                    updateIntent.putExtra("position", pos)
                    startActivity(updateIntent)
                }
                true
            }
            R.id.delete -> {
                //Toast.makeText(this, "삭제버튼 클릭", Toast.LENGTH_SHORT).show()
                val text = TextView(this)
                text.text = "메모를 삭제하겠습니까?"
                text.setPadding(70, 8, 0, 0)
                AlertDialog.Builder(this)
                    .setTitle("메모 삭제")
                    .setView(text)
                    .setNegativeButton("취소"){ _, _ ->
                        //Toast.makeText(this, "취소버튼 클릭", Toast.LENGTH_SHORT).show()
                    }
                    .setPositiveButton("삭제"){ _, _ ->
                        Toast.makeText(this, "삭제버튼 클릭", Toast.LENGTH_SHORT).show()
                        val pos = intent?.getIntExtra("position", -1)
                        pos?.let {
                            Memo.dataList.removeAt(it)
                        }
                        finish()
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }

    }
}