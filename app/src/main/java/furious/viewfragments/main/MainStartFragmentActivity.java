package furious.viewfragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import furious.phillypolicemobile.R;
import furious.viewfragments.bookmark.BookmarkFragmentActivity;
import furious.viewfragments.preferences.MainPreferenceActivity;


public class MainStartFragmentActivity extends AppCompatActivity{
	

	@Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainstart);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.addTab(tabLayout.newTab().setText("Main News"));
		tabLayout.addTab(tabLayout.newTab().setText("District News"));
		tabLayout.addTab(tabLayout.newTab().setText("Shootings"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


		Toolbar mractionbar = (Toolbar) findViewById(R.id.mr_toolbar);
		setSupportActionBar(mractionbar);

        final ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new NewsPagerAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {

				pager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
        
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
		    case R.id.action_settings:
		    	 Intent intent = new Intent();
		         intent.setClass(MainStartFragmentActivity.this, MainPreferenceActivity.class);
		         startActivity(intent); 
		   
		        return true;
		        
		    case R.id.action_bookmark:
		    	 Intent bookIntent = new Intent();
		    	 bookIntent.setClass(MainStartFragmentActivity.this, BookmarkFragmentActivity.class);
		         startActivity(bookIntent); 
		   
		        return true;
	        
		    default:
		        break;
	    }

	    return false;
	}
	
	public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    	private String[] Positions = {"District News","Main News","Shootings"};

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return Positions.length;
        }

        @Override
        public Fragment getItem(int position){
        	if(position == 0){
        		return new MainNews();
        	}else if(position == 1){
        		return new MainDistrictNews();
        	}else if(position == 2){
				return new ShootingFragment();
			}else{
        		return new MainDistrictNews();
        	}
        }

		public String getTitle(int position) {
			return Positions[position];
		}

		public String[] getPositions() {
			return Positions;
		}

		public void setPositions(String[] positionsTitles) {
			Positions = positionsTitles;
		}
  }
	

	
	
}