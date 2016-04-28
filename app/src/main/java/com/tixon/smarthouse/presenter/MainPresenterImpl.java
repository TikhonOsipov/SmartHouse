package com.tixon.smarthouse.presenter;

import android.util.Log;

import com.tixon.smarthouse.view.IMainView;

/**
 * Created by tikhon.osipov on 28.04.2016
 */
public class MainPresenterImpl implements IMainPresenter {

    private IMainView view;

    public MainPresenterImpl(IMainView view) {
        this.view = view;
    }

    @Override
    public void openAutomatically() {
        Log.d("myLogs", "start auto open");
    }

    @Override
    public void closeAutomatically() {
        Log.d("myLogs", "start auto close");
    }

    @Override
    public void openManually() {
        Log.d("myLogs", "open manually");
    }

    @Override
    public void closeManually() {
        Log.d("myLogs", "close manually");
    }

    @Override
    public void stop() {
        Log.d("myLogs", "stop manually");
    }

    @Override
    public void removeView() {
        this.view = null;
    }
}
