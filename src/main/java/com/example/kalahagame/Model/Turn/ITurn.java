package com.example.kalahagame.Model.Turn;


import com.example.kalahagame.Model.Pits.IKalahaPit;
import com.example.kalahagame.Model.Pits.IPitCollection;
import com.example.kalahagame.Utility.IMethodScheduler;


public interface ITurn{

    IMethodScheduler<ITurn> EndOfTurn();



    Integer Player();
    IPitCollection<IKalahaPit> pits();
}
