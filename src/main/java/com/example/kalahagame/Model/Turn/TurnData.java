package com.example.kalahagame.Model.Turn;



import com.example.kalahagame.Model.Game.GameState;
import com.example.kalahagame.Model.Logger.LogData;
import com.example.kalahagame.Model.Pits.PitData;
import com.example.kalahagame.Model.Pits.PitUtil;

import java.util.ArrayList;
import java.util.List;

public class TurnData {
    public int Turn = 0;
    public int Player1Score = 0;
    public int Player2Score = 0;
    public GameState NextTurnState = GameState.TurnP1;
    public List<PitData> Pits;
    public List<LogData> Log;

    public TurnData() {
        Pits = new ArrayList<>();
        Log = new ArrayList<>();
    }

    public TurnData(List<PitData> pits) {
        this();
        Pits = pits;
        Player1Score = pits.get(PitUtil.FirstKalaha()).stones;
        Player2Score = pits.get(PitUtil.SecondKalaha(pits.size())).stones;
    }

    public TurnData(int turn, GameState nextTurnState, List<PitData> pits) {
        this(turn,nextTurnState,pits,new ArrayList<>());
    }

    public TurnData(int turn, GameState nextTurnState, List<PitData> pits, List<LogData> log) {
        this(pits);
        Turn = turn;
        NextTurnState = nextTurnState;
        Log = log;
    }
}
