package com.example.httputilwithhttpurlconnection;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.PropertyChangeRegistry;

public class Student extends BaseObservable {
    private String name;
    private String addr;
    public ObservableInt observableInt = new ObservableInt();

    public Student() {
    }

    public Student(String name, String addr) {
        this.name = name;
        this.addr = addr;
    }
   @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
       // notifyPropertyChanged(BR.name);
    }
    @Bindable
    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}

