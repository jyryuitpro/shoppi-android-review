package io.jyryuitpro.shoppi.android.review.before.aac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.jyryuitpro.shoppi.android.review.R

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    // https://developer.android.com/guide/components/activities/activity-lifecycle
    // layoutInflater, 데이터 초기화
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // layoutInflater
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null

        // NavHostFragment가 소유하고 있는 NavController에 대한 참조를 반환
        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        navController?.let {
            // setupWithNavController: FragmentContainerView, BottomNavigationView 연결
            // NavController : NavHostFragment에서 destination의 이동을 관리하는 객체
            bottomNavigationView.setupWithNavController(it)
        }
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