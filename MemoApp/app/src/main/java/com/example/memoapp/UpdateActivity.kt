package com.example.memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.memoapp.databinding.ActivityUpdateBinding
import kotlin.concurrent.thread

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateBinding
    private lateinit var imm : InputMethodManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        imm =getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        //수정 전 제목, 내용 저장
        setText()

        //포커스 설정
        requestFocus(binding.titleEditTextView)

    }

    fun requestFocus(view : View){
        view.requestFocus()
        thread {
            SystemClock.sleep(500)
            imm.showSoftInput(view, 0)
        }
    }

    private fun setText(){
        val pos = intent.getIntExtra("position", -1)
        val memo = Memo.dataList[pos]

        binding.titleEditTextView.setText(memo.title)
        binding.contentEditTextView.setText(memo.content)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.memo_create, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
            }
            R.id.save ->{

                val title = binding.titleEditTextView.text.toString()
                val content = binding.contentEditTextView.text.toString()
                if(title.isEmpty() || content.isEmpty() || content.isBlank()) {
                    Toast.makeText(this, "빈값 입니다.", Toast.LENGTH_SHORT).show()
                }else{
                    val pos = intent.getIntExtra("position", -1)
                    Memo.dataList[pos] = Memo(title, content, Memo.dataList[pos].date)
                    Toast.makeText(this, "저장 완료", Toast.LENGTH_SHORT).show()
                    finish()
                }

            }
        }
        return super.onOptionsItemSelected(item)
    }
}