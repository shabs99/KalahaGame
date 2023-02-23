package com.example.kalahagame.Model.Turn;


import com.example.kalahagame.Model.Game.GameState;

public class TurnUtil {
    public static int GetPlayer(TurnData d){
        return d.NextTurnState == GameState.TurnP1 ? 0 : 1;
    }
}
