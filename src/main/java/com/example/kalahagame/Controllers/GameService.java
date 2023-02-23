package com.example.kalahagame.Controllers;

import com.example.kalahagame.Model.Game.IGame;
import com.example.kalahagame.Model.KalahaGame;
import com.example.kalahagame.Model.Turn.TurnData;
import com.example.kalahagame.Model.Turn.TurnInputData;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private IGame game;

    public TurnData StartNewGame() {
        game = new KalahaGame();
        return game.SetupNewGame();
    }

    public TurnData DoTurn(TurnInputData input) {
        return game.DoTurn(input.SelectedBucket);
    }
}
