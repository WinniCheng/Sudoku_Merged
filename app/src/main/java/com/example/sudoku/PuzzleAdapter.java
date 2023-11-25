package com.example.sudoku;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PuzzleAdapter extends RecyclerView.Adapter<PuzzleAdapter.ViewHolder> {

    private Context context;
    private int[] flatBoard;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView cellView;
        public ViewHolder(Context context, View view) {
            super(view);

            cellView = (TextView) view.findViewById(R.id.puzzle_cell);
            float width = context.getResources().getDisplayMetrics().widthPixels  / 9;
            getCellView().setWidth(Math.round(width));
            getCellView().setHeight(Math.round(width));
        }

        public TextView getCellView() {
            return cellView;
        }

    }

    public PuzzleAdapter(Context context, int[] flatBoard) {
        this.context = context;
        this.flatBoard = flatBoard;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.puzzle_cell, viewGroup, false);

        final ViewHolder holder = new ViewHolder(context, view);
        holder.getCellView().setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Add number")
                                .setMessage("Cell no. " + holder.getAbsoluteAdapterPosition());

                        builder.create().show();
                    }
                }
        );
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        String cellValue = String.valueOf(flatBoard[position]);
        if (cellValue.equals("0")) {
            viewHolder.getCellView().setText("");
            viewHolder.getCellView().setBackgroundColor(R.color.white);
        } else {
            viewHolder.getCellView().setText(cellValue);
        }
    }

    @Override
    public int getItemCount() {
        return 81;
    }

}
