package com.example.cryptobery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private CategoryFragment categoryFragment;
    private LoginFragment loginFragment;
    private MypageFragment mypageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_category:
                        setFrag(0);
                        break;
                    case R.id.menu_home:
                        setFrag(1);
                        break;
                    case R.id.menu_mypage:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        categoryFragment = new CategoryFragment();
        loginFragment    = new LoginFragment();
        mypageFragment   = new MypageFragment();

        setFrag(0); // 첫 프래그먼트 화면 지정

    }

    //프레그먼트 교체
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();

        switch (n)
        {
            case 0:
                ft.replace(R.id.main_frame, categoryFragment);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.main_frame, loginFragment);
                ft.commit();
                break;

            case 2:
                ft.replace(R.id.main_frame, mypageFragment);
                ft.commit();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu, menu); //메뉴 XML파일 인플레이션
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //화면에 설정한 메뉴를 사용자가 선택하면 onOptionsItemSelected 메소드가 호출됨
        int curId = item.getItemId();
        //어떤 메뉴를 선택했는지를 id로 구분하여 처리
        switch(curId) {
                case R.id.menu_search:
                Toast.makeText(this, "검색 메뉴가 선택되었습니다.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplication(), SearchActivity.class)); //로딩이 끝난 후
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}