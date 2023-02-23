package com.example.kalahagame.Utility;


import com.example.kalahagame.Utility.FunctionalInterfaces.Method;

public interface IMethodCollection<T> {
    void Process(T argument);
    void Add(Method<T> method);
    void Remove(Method<T> method);
}