package com.example.kalahagame.Utility;

import com.example.kalahagame.Utility.FunctionalInterfaces.Procedure;

import java.util.ArrayList;
import java.util.List;

public class ProcedureCollection implements IProcedureCollection {

    private List<Procedure> onChangedProcedures = new ArrayList<Procedure>();

    @Override
    public void Process() {
        onChangedProcedures.forEach((p) -> p.Process());
    }

    @Override
    public void Add(Procedure p) {
        onChangedProcedures.add(p);
    }

    @Override
    public void Remove(Procedure p) {
        onChangedProcedures.remove(p);
    }
}
