package io.jyryuitpro.shoppi.android.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    // https://developer.android.com/guide/components/activities/activity-lifecycle
    // layoutInflater, 데이터 초기화
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // layoutInflater
        setContentView(R.layout.activity_product_detail)
        Log.d(TAG, "onCreate")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    // 애니매이션의 실행, 데이터의 갱신
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    // Activity가 focus를 얻음, 자주 호출될 수 있기 때문에 너무 오래 걸리는 연산을 여기에서 처리하면 안됨
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    // Activity가 focus를 잃음, 자주 호출될 수 있기 때문에 너무 오래 걸리는 연산을 여기에서 처리하면 안됨
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    // 애니매이션의 종료, 데이터의 갱신 중단
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    // 사용 중인 앱 목록에서도 완전히 제거됨, 리소스 해제
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}