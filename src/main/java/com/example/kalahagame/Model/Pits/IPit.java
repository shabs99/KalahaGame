package com.example.kalahagame.Model.Pits;

public interface IPit<T> {
    public void Add(T stones);
    public int Amount();
    public T GrabAll();
}



