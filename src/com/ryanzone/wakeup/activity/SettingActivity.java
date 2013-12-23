package com.ryanzone.wakeup.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ryanzone.wakeup.R;
import com.ryanzone.wakeup.activity.base.RTActivity;
import com.ryanzone.wakeup.adapter.TimeSelectAdapter;
import com.ryanzone.wakeup.utils.Common;

/**
 * SettingActivity.java
 * @author walker02
 * Create at 2013-12-12 下午2:14:22
 */
public class SettingActivity extends RTActivity {

    private ListView          lvHour;
    private ListView          lvMinute;
    private GridView          gvWeek;
    private TimeSelectAdapter hourAdapter;
    private TimeSelectAdapter minuteAdapter;
    private GvWeekAdapter     gvWeekAdapter;
    private int               currentPosition = Integer.MAX_VALUE / 2 + 1;
    private int               currentPositionHour = Integer.MAX_VALUE / 2 + 1;
    private PopupWindow       mPopupWindow;
    private List<String>      dayStrings;
    private RelativeLayout rlSettingRoot;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initView() {
        rlSettingRoot = (RelativeLayout) findViewById(R.id.rlSettingRoot);
        lvHour = (ListView) findViewById(R.id.lvHour);
        lvMinute = (ListView) findViewById(R.id.lvMinute);
        gvWeek = (GridView) findViewById(R.id.gvWeek);
        findViewById(R.id.btnChoiceMisic).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showPopuWindowMusic(arg0);
            }
        });
        findViewById(R.id.btnShakeStrength).setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                showPopuWindowShake(arg0);
            }
        });
        
        /**
         * Modified by Ryan 2013.12.18 18:52
         */
        ((Button)this.findViewById(R.id.btnRight)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/**
				 * Open share activity
				 */
				//FIXME
				openActivity(ShareActivity.class);
			}
		});
    }

    @SuppressLint("NewApi")
    protected void showPopuWindowMusic(View arg0) {
        View popupView = getLayoutInflater().inflate(R.layout.ppw_setting, null);
        mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        ((TextView)popupView.findViewById(R.id.tvSelectTitle)).setText(getResources().getString(R.string.choice_music));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.anim_bottom_menu);
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAtLocation(arg0, Gravity.BOTTOM, 0, 0);
            rlSettingRoot.setAlpha((float) 0.4);
        }
        
        mPopupWindow.setOnDismissListener(new OnDismissListener() {
            
            @Override
            public void onDismiss() {
                rlSettingRoot.setAlpha(1);
            }
        });
        
        ListView lvShakeStrengh = (ListView) popupView.findViewById(R.id.lvShakeStrengh);
        List<String> shakeStrenghStrings = new ArrayList<String>();
        shakeStrenghStrings.add(getResources().getString(R.string.shake));
        shakeStrenghStrings.add(getResources().getString(R.string.old_music_bell));
        shakeStrenghStrings.add(getResources().getString(R.string.valley_voice));
        SettingAdapter settingAdapter = new SettingAdapter(SettingActivity.this, R.layout.shake_music_list_item, shakeStrenghStrings);
        lvShakeStrengh.setAdapter(settingAdapter);
        lvShakeStrengh.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get the selection content
            }
        });
        
    }
    @SuppressLint("NewApi")
    protected void showPopuWindowShake(View arg0) {
        View popupView = getLayoutInflater().inflate(R.layout.ppw_setting, null);
        mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        ((TextView)popupView.findViewById(R.id.tvSelectTitle)).setText(getResources().getString(R.string.shake_strengh));
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.anim_bottom_menu);
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAtLocation(arg0, Gravity.BOTTOM, 0, 0);
            rlSettingRoot.setAlpha((float) 0.4);
        }
        
        mPopupWindow.setOnDismissListener(new OnDismissListener() {
            
            @Override
            public void onDismiss() {
                rlSettingRoot.setAlpha(1);
            }
        });
        
        
       ListView lvShakeStrengh = (ListView) popupView.findViewById(R.id.lvShakeStrengh);
       List<String> shakeStrenghStrings = new ArrayList<String>();
       shakeStrenghStrings.add(getResources().getString(R.string.easy_shake));
       shakeStrenghStrings.add(getResources().getString(R.string.middle_shake));
       shakeStrenghStrings.add(getResources().getString(R.string.hard_shake));
       SettingAdapter settingAdapter = new SettingAdapter(SettingActivity.this, R.layout.shake_music_list_item, shakeStrenghStrings);
       lvShakeStrengh.setAdapter(settingAdapter);
       lvShakeStrengh.setOnItemClickListener(new OnItemClickListener() {

           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //get the selection content
           }
       });
    }

    private void initData() {

        dayStrings = new ArrayList<String>();
        dayStrings.add("MON");
        dayStrings.add("TUR");
        dayStrings.add("WEN");
        dayStrings.add("THR");
        dayStrings.add("FRI");
        dayStrings.add("SAT");
        dayStrings.add("SUN");

        hourAdapter = new TimeSelectAdapter(this, Common.getTimeStrings(5, 9));
        minuteAdapter = new TimeSelectAdapter(this, Common.getTimeStrings(0, 59));
        gvWeekAdapter = new GvWeekAdapter(SettingActivity.this, R.layout.gv_list_item_week,
            dayStrings);

        gvWeek.setAdapter(gvWeekAdapter);
        lvHour.setAdapter(hourAdapter);
        lvHour.setSelection(Integer.MAX_VALUE / 2 + 2);
        hourAdapter.setSelectItem(Integer.MAX_VALUE / 2 + 2);
        
        lvMinute.setAdapter(minuteAdapter);
        lvMinute.setSelection(Integer.MAX_VALUE / 2 + 26);
        minuteAdapter.setSelectItem(Integer.MAX_VALUE / 2 + 2);
        lvMinute.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    //change the selection text color by setSelectItem
                    minuteAdapter.setSelectItem(currentPosition + 2);
                    minuteAdapter.notifyDataSetChanged();
                    //select the middle
                    lvMinute.setSelection(currentPosition + 1);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                //set current Item
                currentPosition = firstVisibleItem;
            }
        });
        //set hour selection and change the color 
        lvHour.setOnScrollListener(new OnScrollListener() {
            
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    //change the selection text color by setSelectItem
                    hourAdapter.setSelectItem(currentPositionHour + 2);
                    hourAdapter.notifyDataSetChanged();
                    //select the middle
                    lvHour.setSelection(currentPositionHour + 1);
                }
            }
            
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                //set current Item
                currentPositionHour = firstVisibleItem;
            }
        });
    }

    /**
     * week adapter
     * @author walker
     */
    class GvWeekAdapter extends ArrayAdapter<String> {
        private Typeface     fontFace;
        private GvWeekHolder holder;
        int                  resourceId;
        LayoutInflater       inflater;

        public GvWeekAdapter(Context context, int resourceId, List<String> objects) {
            super(context, resourceId, objects);
            this.resourceId = resourceId;
            inflater = SettingActivity.this.getLayoutInflater();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            String bean = getItem(position);
            holder = null;
            if (convertView == null) {
                holder = new GvWeekHolder();
                convertView = inflater.inflate(resourceId, parent, false);
                holder.tvDay = (TextView) convertView.findViewById(R.id.tvWeekOfDay);
                convertView.setTag(holder);
            } else {
                holder = (GvWeekHolder) convertView.getTag();
            }
            if (fontFace == null)
                fontFace = Typeface.createFromAsset(getAssets(), "time_font.otf");
            holder.tvDay.setTypeface(fontFace);
            holder.tvDay.setText(bean);
            return convertView;
        }
    }

    class GvWeekHolder {
        TextView tvDay;
    }
    
    class SettingAdapter extends ArrayAdapter<String> {

        private LvSettingHolder   holder;
        int resourceId;
        LayoutInflater inflater;
        
        public SettingAdapter(Context context, int resourceId, List<String> objects) {
            super(context, resourceId, objects);
            this.resourceId = resourceId;
            inflater = SettingActivity.this.getLayoutInflater();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            String bean = getItem(position);
            holder = null;
            if (convertView == null) {
                holder = new LvSettingHolder();
                convertView = inflater.inflate(resourceId, parent, false);
                holder.tvSelection = (TextView) convertView.findViewById(R.id.tvSelection);
                convertView.setTag(holder);
            } else {
                holder = (LvSettingHolder) convertView.getTag();
            }

            holder.tvSelection.setText(bean);
            return convertView;
        }
    }
    
    class LvSettingHolder {
        TextView tvSelection;
    }
    @Override
    public void init() {
    }

    @Override
    public void loadViewLayout() {
        setContentView(R.layout.activity_setting);
    }
}
