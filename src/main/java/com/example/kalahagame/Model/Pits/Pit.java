package com.example.kalahagame.Model.Pits;


import com.example.kalahagame.Utility.MethodCollection;

public class Pit implements IPit<Integer>{

    public MethodCollection<Integer> OnAdd = new MethodCollection<>();
    public MethodCollection<Integer> OnGrab = new MethodCollection<>();

    private PitData data;

    public Pit(PitData data) {
        this.data = data;
    }

    public Pit() {
        data = new PitData();
    }

    @Override
    public void Add(Integer stones) {
        data.stones += stones;
        OnAdd.Process(stones);
    }

    @Override
    public int Amount() {
        return data.stones;
    }

    @Override
    public Integer GrabAll() {
        Integer result = data.stones;
        data.stones = 0;
        OnGrab.Process(-result);
        return result;
    }

    public PitData Data(){
        return data;
    }
}
