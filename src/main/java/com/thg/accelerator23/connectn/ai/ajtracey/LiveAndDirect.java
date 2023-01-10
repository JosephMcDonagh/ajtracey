package com.thg.accelerator23.connectn.ai.ajtracey;

import com.thehutgroup.accelerator.connectn.player.*;
import com.thg.accelerator23.connectn.ai.ajtracey.analysis.BoardAnalyser;
import com.thg.accelerator23.connectn.ai.ajtracey.analysis.GameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LiveAndDirect extends Player {
  public LiveAndDirect(Counter counter) {
    super(counter, LiveAndDirect.class.getName());
  }

  @Override
  public int makeMove(Board board) {
    BoardAnalyser BA = new BoardAnalyser(board.getConfig());

      if(BA.winningPositionExists(this.getCounter(), board)){
        return BA.winningPosition(this.getCounter(),board);
      } else if (BA.winningPositionExists(this.getCounter().getOther(), board)){
      return BA.winningPosition(this.getCounter().getOther(), board);

    } else {
        List<Integer> possibleMoves = new ArrayList<>();
        for (int i = 0; i < board.getConfig().getWidth(); i++) {
          try {
            new Board(board, i, this.getCounter());
            possibleMoves.add(i);
          } catch (InvalidMoveException e) {
          }
        }
        return BA.returnsXValueForOurBestMove(board, getCounter());
      }


    //TODO: some crazy analysis
    //TODO: make sure said analysis uses less than 2G of heap and returns within 10 seconds on whichever machine is running it
  }
}
