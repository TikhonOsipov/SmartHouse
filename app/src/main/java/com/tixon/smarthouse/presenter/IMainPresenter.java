package com.tixon.smarthouse.presenter;

/**
 * Created by tikhon.osipov on 28.04.2016
 */
public interface IMainPresenter {
    void openAutomatically();
    void closeAutomatically();
    void openManually();
    void closeManually();
    void stop();
    void removeView();
}
