package Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagram2.R;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import Utils.BottomNavigationHelper;
import Utils.GridImageAdapter;
import Utils.UniversalImageLoader;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 4;
private Context mContext = ProfileActivity.this;
private ImageView profilePhoto;
private static final int NUM_GRID_COLUM = 3;

private ProgressBar mProgressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: Started ");
        mProgressBar = (ProgressBar) findViewById(R.id.ProfileProgressBar);
        mProgressBar.setVisibility(View.GONE);

        setBotNavigationView();
        setupToolBar();
        setUpActivityWidgets();
        setProfileImage();
        tempGridSetup();
    }
    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://www.bigstockphoto.com/images/homepage/module-6.jpg");
        imgURLs.add("https://www.w3schools.com/w3css/img_lights.jpg");
        imgURLs.add("https://cdn.eso.org/images/thumb300y/eso1907a.jpg");
        imgURLs.add("https://s3.amazonaws.com/images.seroundtable.com/google-css-images-1515761601.jpg");
        imgURLs.add("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSm7Oom05DarxbgrqiAIMvnqu1xPSdOd82OZFuhF65zWCIe_Hxh&usqp=CAU");
        imgURLs.add("https://image.shutterstock.com/image-photo/beautiful-water-drop-on-dandelion-260nw-789676552.jpg");
        imgURLs.add("https://scx1.b-cdn.net/csz/news/800/2017/theoreticala.jpg");
        imgURLs.add("https://html5box.com/html5lightbox/");
        imgURLs.add("https://www.birthdaywishes.expert/wp-content/uploads/2016/08/Good-Morning-everyone.-On-image-of-sunlight-and-grass-500x281.jpg");
        imgURLs.add("https://www.thedivinemercy.org/sites/default/files/header-jesus-image-gold.png");

        setUpImageGrid(imgURLs);
    }

    public void setUpImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView) findViewById(R.id.gridView);

        int gridWidth  = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/NUM_GRID_COLUM;
        gridView.setColumnWidth(imageWidth);
        GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", imgURLs);
        gridView.setAdapter(adapter);
    }
private void setProfileImage(){
    Log.d(TAG, "setProfileImage: setting profile photo");
    String imgURL = "https://01rad.com/wp-content/uploads/2017/07/ANDROID.png";
    UniversalImageLoader.setImage(imgURL, profilePhoto, mProgressBar, "");

}
private void setUpActivityWidgets(){
        mProgressBar = (ProgressBar) findViewById(R.id.ProfileProgressBar);
        mProgressBar.setVisibility(View.GONE);
        profilePhoto = (ImageView) findViewById(R.id.profile_photo);
}

    private void setupToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

       ImageView profileMenu = (ImageView) findViewById(R.id.ProfileMenu);
       profileMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.d(TAG, "onClick: navigating to account settings");
               Intent intent = new Intent(mContext, AccountSettingsActivity.class);
               startActivity(intent);
           }
       });
    }
    
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
