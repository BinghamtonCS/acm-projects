package com.gabeochoa.wib;

import java.util.ArrayList;
import java.util.Queue;


public class DownloadThread extends Thread {
    private final Queue<ArrayList<Person>> results;

    public DownloadThread(Queue<ArrayList<Person>> results) {
        this.results = results;
    }

    @Override
    public void run() {
        results.add(GetMembers.getDataFromInternet());
    }
}