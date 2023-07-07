package com.example.memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.memoapp.databinding.ActivityMemoBinding
import java.time.LocalDate
import kotlin.concurrent.thread

class memoActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMemoBinding
    private lateinit var imm : InputMethodManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.memoToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        //titleEditText 포커스 요청 및 패드 올리기
        requestFocus(binding.titleEditText)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.memo_create, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun requestFocus(view : View){
        view.requestFocus()
        thread {
            SystemClock.sleep(500)
            imm.showSoftInput(view, 0)
        }
    }

    private fun hideFocus(){
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        currentFocus?.clearFocus()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            //세이브 버튼을 눌렀을 때
            R.id.save -> {
                //포커스 지우기
                hideFocus()



                val title = binding.titleEditText.text.toString()
                val content = binding.contentEditTxt.text.toString()

                //Log.e("content", "${content.isBlank()}")

                //둘 중 하나 비어있는 경우 다시 입력 받도록 한다.
                if(title.isEmpty() || content.isEmpty() || content.isBlank()){
                    Toast.makeText(this, "빈 값을 있으면 안됩니다.", Toast.LENGTH_LONG).show()
                    requestFocus(binding.titleEditText)

                }else{
                    val local = LocalDate.now().toString()

                    //데이터 전달하기
                    intent.putExtra("title", title)
                    intent.putExtra("content", content)
                    intent.putExtra("date", local)
                    setResult(RESULT_OK, intent)
                    finish()
                }

                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}