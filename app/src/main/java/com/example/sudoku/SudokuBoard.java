package com.example.sudoku;

public class SudokuBoard {
    private int[][] board;

    public SudokuBoard(int[][] initialValues) {
        if (initialValues.length != 9 || initialValues[0].length != 9) {
            throw new IllegalArgumentException("Invalid board size. Sudoku board must be 9x9.");
        }
        this.board = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board[i][j] = initialValues[i][j];
            }
        }
    }

    public int getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, int value) {
        board[row][col] = value;
    }

    public int[] getFlatBoard() {
        int[] flatBoard = new int[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                flatBoard[i * 9 + j] = this.board[i][j];
            }
        }
        return flatBoard;
    }
}
