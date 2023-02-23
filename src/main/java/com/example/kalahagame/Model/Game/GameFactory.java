package com.example.kalahagame.Model.Game;


import com.example.kalahagame.Model.KalahaGame;

public class GameFactory implements IGameFactory {

    @Override
    public IGame Create() {
        return new KalahaGame();
    }
}
