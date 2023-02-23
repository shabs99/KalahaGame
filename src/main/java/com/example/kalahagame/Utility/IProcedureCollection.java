package com.example.kalahagame.Utility;


import com.example.kalahagame.Utility.FunctionalInterfaces.Procedure;

public interface IProcedureCollection {
    void Process();
    void Add(Procedure p);
    void Remove(Procedure p);
}