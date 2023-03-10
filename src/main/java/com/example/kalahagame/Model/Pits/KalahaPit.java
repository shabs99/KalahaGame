package com.example.kalahagame.Model.Pits;


import com.example.kalahagame.Utility.IProcedureCollection;

public class KalahaPit implements IKalahaPit{
    private PitData Data;
    private IProcedureCollection OnChanged;

    public KalahaPit(IProcedureCollection onChanged) {
        OnChanged = onChanged;
        Data = new PitData();
    }

    public KalahaPit(PitData data, IProcedureCollection onChanged) {
        Data = data;
        OnChanged = onChanged;
    }

    @Override
    public Boolean IsKalaha() {
        return Data.isKalaha;
    }

    @Override
    public void MakeKalaha() {
        Data.isKalaha = true;
    }

    @Override
    public Integer GetPlayer() {
        return Data.player;
    }

    @Override
    public void SetPlayer(Integer player) {
        Data.player = player;
    }

    @Override
    public PitData Data() {
        return Data;
    }

    @Override
    public void Add(Integer stones) {
        Data.stones += stones;
        OnChanged.Process();
    }

    @Override
    public int Amount() {
        return Data.stones;
    }

    @Override
    public Integer GrabAll() {
        Integer grabbed = Data.stones;
        Data.stones = 0;
        OnChanged.Process();
        return grabbed;
    }

    @Override
    public IProcedureCollection OnChanged() {
        return OnChanged;
    }
}