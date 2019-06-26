package com.lovelz.lzmvvm;

import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class MainModel extends ViewModel {

    private ObservableField<String> username;

    public MainModel() {
        this.username = new ObservableField<>();
    }

}
