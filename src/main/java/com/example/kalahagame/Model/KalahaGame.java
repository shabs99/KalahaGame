package com.example.kalahagame.Model;

import com.example.kalahagame.Model.Game.*;
import com.example.kalahagame.Model.Logger.LogUtility;
import com.example.kalahagame.Model.Pits.PitUtil;
import com.example.kalahagame.Model.Turn.TurnData;

import java.util.List;
import java.util.stream.Collectors;

public class KalahaGame implements IGame {

    public GameFunctionality Functionality;
    public GameData Data;

    public KalahaGame() {
        Functionality = new GameFunctionality();
    }

    @Override
    public TurnData SetupNewGame() {
        TurnData setup = new TurnData(PitUtil.CreatePitDataList(6,6));

        return SetUpGameFromTurnData(setup);
    }

    @Override
    public TurnData SetUpGameFromTurnData(TurnData data) {
        Data = new GameData(data);

        GameRules.WireKalahaGameRules(this);

        Data.Pits.pList.stream().filter((p) -> !p.Data().isKalaha)
                .forEach((p) -> LogUtility.Log(Data.Logger,Data.Pits,p,6));

        LogUtility.Log(Data.Logger,"Set up a new Kalaha Game!");
        LogUtility.Log(Data.Logger, String.format("%sNew Turn",LogUtility.LogStart(Data.CurrentPlayer, Data.CurrentTurn +1)));
        return this.Data.ToTurnData();
    }

    @Override
    public TurnData DoTurn(Integer SelectedIndex) {
        TurnData error = InputUtility.IsValidInput(SelectedIndex,Data.ToTurnData());
        if(error != null)
            return error;

        GameUtil.NewTurnSetup(Data);

        if(Functionality.TurnProcedure != null)
            Functionality.TurnProcedure.Process(SelectedIndex);

        List<TurnData> endScenarios = Functionality.SpecialEndOfTurnScenarios.Process(Data).stream().filter((end) -> end!=null).collect(Collectors.toList());
        if(endScenarios.size() > 0)
            return endScenarios.get(0);

        return Data.ToTurnData();
    }
}


