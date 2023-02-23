package com.example.kalahagame.Model.Game;


import com.example.kalahagame.Model.Logger.LogCollection;
import com.example.kalahagame.Model.Logger.LogUtility;
import com.example.kalahagame.Model.Pits.PitCollection;
import com.example.kalahagame.Model.Pits.PitData;
import com.example.kalahagame.Model.Pits.Pit;
import com.example.kalahagame.Model.Turn.TurnData;
import com.example.kalahagame.Model.Turn.TurnUtil;

import java.util.stream.Collectors;

public class GameData{
    public PitCollection<Pit> Pits;
    public LogCollection Logger;
    public GameState NextTurnState = GameState.TurnP1;
    public Integer CurrentTurn = 0;
    public Integer CurrentPlayer = 0;
    public Integer CurrentHand = 0;

    public GameData() {
        Pits = new PitCollection<>();
        Logger = new LogCollection();
    }

    public GameData(TurnData data) {
        this();
        data.Pits.forEach((pit) -> Pits.pList.add(new Pit(pit)));
        NextTurnState = data.NextTurnState;
        CurrentPlayer = TurnUtil.GetPlayer(data);
        CurrentTurn = data.Turn;
        LogUtility.SetLoggerFromTurnData(Logger,data);
    }

    public TurnData
    ToTurnData() {
        TurnData data = new TurnData();

        data.Pits = Pits.pList.stream().map(x -> new PitData(x.Data().player, x.Data().isKalaha, x.Amount())).collect(Collectors.toList());
        data.NextTurnState = NextTurnState;
        data.Turn = CurrentTurn;
        data.Player1Score = Pits.KalahaOfPlayer1().Amount();
        data.Player2Score = Pits.KalahaOfPlayer2().Amount();
        data.Log = Logger.GetLogData();
        Logger.ClearLogs();
        return data;
    }
}
