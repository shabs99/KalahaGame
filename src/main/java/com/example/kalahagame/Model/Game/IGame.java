package com.example.kalahagame.Model.Game;


import com.example.kalahagame.Model.Turn.TurnData;

public interface IGame {
    TurnData DoTurn(Integer SelectedIndex);
    TurnData SetupNewGame();
    TurnData SetUpGameFromTurnData(TurnData data);
}
