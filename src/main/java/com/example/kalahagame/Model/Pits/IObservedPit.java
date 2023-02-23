package com.example.kalahagame.Model.Pits;

import com.example.kalahagame.Utility.IProcedureCollection;

interface IObservedPit<T> extends IPit<T>{
    IProcedureCollection OnChanged();
}
