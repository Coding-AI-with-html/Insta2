package home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.instagram2.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import Login.LoginActivity;
import Login.RegisterActivity;
import Utils.BottomNavigationHelper;
import Utils.SectionPagerAdapter;
import Utils.UniversalImageLoader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ACTIVITY_NUM = 0;
    private Context mContext = MainActivity.this;
    //firebase
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started.");
        mAuth = FirebaseAuth.getInstance();
        initProfileLoader();
        setBotNavigationView();
        setupViewPager();

    }
 /*
 ----------firebasee------------------------
  */
 @Override
 public void onStart() {
     super.onStart();
     // Check if user is signed in (non-null) and update UI accordingly.
     FirebaseUser currentUser = mAuth.getCurrentUser();
     updateUI(currentUser);
 }

    private void updateUI(FirebaseUser currentUser) {
     if(currentUser != null){
         Log.d(TAG, "updateUI:signed_in " + currentUser.getUid());

     } else {
         Log.d(TAG, "updateUI:signed_out ");
     }
    }


    private  void initProfileLoader(){
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(mContext);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }

    //responsible for adding camera,home,messages
    private void setupViewPager(){
        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        adapter.addfragment(new CameraFragment()); //indx 0;
        adapter.addfragment(new HomeFragment()); //indx 1;
        adapter.addfragment(new MessagesFragment()); //indx 1;
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);

        TabLayout  tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_insta);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);

    }

    //bottomNavigation setup
    private void setBotNavigationView(){
        Log.d(TAG, "setBotNavigationView: Setting up BotNavigatioonView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationHelper.setupBotNavigationView(bottomNavigationViewEx);
        BottomNavigationHelper.EnableNavigation(mContext, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }
}
