package furious.viewfragments.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import furious.phillypolicemobile.R;
import furious.utils.Utils;
import furious.viewfragments.bookmark.BookmarkFragmentActivity;
import furious.viewfragments.preferences.MainPreferenceActivity;


public class MainStartFragmentActivity extends AppCompatActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainstart);

		Utils.checkforUpdate(this.getApplicationContext());
		Toolbar mractionbar = findViewById(R.id.mr_toolbar);
		setSupportActionBar(mractionbar);

		mractionbar.setTitle("Philly Police Droid");
		mractionbar.setNavigationIcon(R.drawable.ic_launcher);
		mractionbar.setBackgroundColor(
				getResources().getColor(R.color.primary)
		);

		final ViewPager pager = findViewById(R.id.viewPager);
		pager.setAdapter(new NewsPagerAdapter(getSupportFragmentManager()));
		TabLayout tabLayout = findViewById(R.id.tab_layout);
		tabLayout.setupWithViewPager(pager);

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

	public class NewsPagerAdapter extends FragmentPagerAdapter {
		private String[] Positions = {"Main News","District News","Shootings"};
		private Fragment[] frags = {
				new MainNews(),
				new MainDistrictNews(),
				new ShootingFragment()
		};

		NewsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public int getCount() {
			return Positions.length;
		}

		@Override
		public Fragment getItem(int position){ return frags[position]; }

		@Override
		public CharSequence getPageTitle(int position) {
			return Positions[position];
		}
	}
}