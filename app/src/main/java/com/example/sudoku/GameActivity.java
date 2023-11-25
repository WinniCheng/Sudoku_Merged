package com.example.sudoku;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GameActivity extends MenuClass {

    PuzzleAdapter adapter;
    ArrayList<Integer> numList;
    RecyclerView recyclerView;
    MediaPlayer mediaPlayer;
    private static final String TAG = "GameActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = MediaPlayer.create(this, R.raw.game_bgm);

        setContentView(R.layout.activity_game);

        int[][] initialValues = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        SudokuBoard board = new SudokuBoard(initialValues);

        adapter = new PuzzleAdapter(this, board.getFlatBoard());

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager (GameActivity.this, 9));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Resources res = getResources();
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean defaultValue = res.getBoolean(R.bool.pref_background_music_default);
        boolean musicOn = prefs.getBoolean(getString(R.string.pref_background_music_key), defaultValue);
        Log.i(TAG, Boolean.toString(musicOn));
        if (musicOn) {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }


}