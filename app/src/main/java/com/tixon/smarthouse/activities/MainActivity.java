package com.tixon.smarthouse.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.tixon.smarthouse.dialogs.AlertDialogEnableManual;
import com.tixon.smarthouse.adapters.HistoryAdapter;
import com.tixon.smarthouse.R;
import com.tixon.smarthouse.model.ArduinoHistory;
import com.tixon.smarthouse.presenter.IMainPresenter;
import com.tixon.smarthouse.presenter.MainPresenterImpl;
import com.tixon.smarthouse.view.IMainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        CompoundButton.OnCheckedChangeListener,
        View.OnTouchListener,
        IMainView {

    TextView tvCurtainState, tvConnectionStatus;
    Button buttonOpen, buttonClose;
    Switch switchManualAuto;
    RecyclerView recyclerView;

    IMainPresenter presenter;

    boolean manual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonOpen = (Button) findViewById(R.id.buttonOpen);
        buttonClose = (Button) findViewById(R.id.buttonClose);
        switchManualAuto = (Switch) findViewById(R.id.switchManualAuto);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewHistory);
        tvConnectionStatus = (TextView) findViewById(R.id.tvConnectionStatus);
        tvCurtainState = (TextView) findViewById(R.id.tvCurtainState);
        //set onTouch listener
        buttonOpen.setOnTouchListener(this);
        buttonClose.setOnTouchListener(this);
        //set switch listener
        assert switchManualAuto != null;
        switchManualAuto.setOnCheckedChangeListener(this);

        ArrayList<ArduinoHistory> historyArrayList = new ArrayList<>();
        HistoryAdapter adapter = new HistoryAdapter(historyArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter = new MainPresenterImpl(this);
    }

    @Override
    protected void onStop() {
        presenter.removeView();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setupSchedule:
                startActivity(new Intent(MainActivity.this, ScheduleActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    // Buttons onClick methods

    public void autoOpen(View v) {
        if(manual) {
            return;
        }
        presenter.openAutomatically();
    }

    public void autoClose(View v) {
        if(manual) {
            return;
        }
        presenter.closeAutomatically();
    }

    // Switch listener

    @Override
    public void onCheckedChanged(final CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            manual = false;
            switchManualAuto.setText(getString(R.string.switch_manual_mode));
            AlertDialogEnableManual dialog = new AlertDialogEnableManual(MainActivity.this) {
                @Override
                public void positiveAction() {
                    manual = true;
                    buttonView.setChecked(true);
                }

                @Override
                public void negativeAction() {
                    manual = false;
                    buttonView.setChecked(false);
                }
            };
            dialog.show();
        } else {
            switchManualAuto.setText(getString(R.string.switch_auto_mode));
            manual = false;
        }
    }

    // Buttons onTouch listener

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(!manual) {
            return false;
        } else {
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                switch(v.getId()) {
                    case R.id.buttonOpen:
                        Log.d("myLogs", "button open press");
                        presenter.openManually();
                        break;
                    case R.id.buttonClose:
                        Log.d("myLogs", "button close press");
                        presenter.closeManually();
                        break;
                    default: break;
                }
                return false;
            } else if(event.getAction() == MotionEvent.ACTION_UP) {
                switch(v.getId()) {
                    case R.id.buttonOpen:
                        Log.d("myLogs", "button open release");
                        presenter.stop();
                        break;
                    case R.id.buttonClose:
                        Log.d("myLogs", "button close release");
                        presenter.stop();
                        break;
                    default: break;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public void setCurtainState(String state) {
        tvCurtainState.setText(state);
    }

    @Override
    public void setConnectionStatus(String status) {
        tvConnectionStatus.setText(status);
    }
}
