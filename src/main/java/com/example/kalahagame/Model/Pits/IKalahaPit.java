package com.example.kalahagame.Model.Pits;

public interface IKalahaPit extends IObservedPit<Integer> {
    Boolean IsKalaha();
    void MakeKalaha();
    Integer GetPlayer();
    void SetPlayer(Integer player);
    PitData Data();
}
