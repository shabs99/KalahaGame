package com.example.kalahagame.Model;


import com.example.kalahagame.Model.Logger.LogUtility;
import com.example.kalahagame.Model.Logger.TextLog;
import com.example.kalahagame.Model.Pits.PitUtil;
import com.example.kalahagame.Model.Turn.TurnData;
import com.example.kalahagame.Model.Turn.TurnUtil;

public class InputUtility {

    public static TurnData IsValidInput(int selectedIndex, TurnData data){
        int currentPlayer = TurnUtil.GetPlayer(data);

        if(selectedIndex > data.Pits.size() || selectedIndex < 0)
            return InvalidInput(data, String.format("%s Error!! - Index %d Out of Bounds",
                    LogUtility.LogStart(data),
                    selectedIndex
            ));

        if(PitUtil.FirstKalaha() == selectedIndex || selectedIndex == PitUtil.SecondKalaha(data.Pits.size()))
            return InvalidInput(data, String.format("%s Error!! - Index %d is a Kalaha",
                    LogUtility.LogStart(data),
                    selectedIndex
            ));

        if(PitUtil.GetPlayer(data.Pits.size(),selectedIndex) != currentPlayer)
            return InvalidInput(data, String.format("%s Error!! - Index %d does not belong to %s",
                    LogUtility.LogStart(data),
                    selectedIndex,
                    LogUtility.LogPlayer(currentPlayer)
            ));

        if(data.Pits.get(selectedIndex).stones == 0)
            return InvalidInput(data, String.format("%s Error!! - Index %d does not have any stones!",
                    LogUtility.LogStart(data),
                    selectedIndex
            ));

        return null;
    }

    private static TurnData InvalidInput(TurnData data, String message){
        data.Log.clear();
        data.Log.add(new TextLog(message).GetLogData());
        System.out.println(message);
        return data;
    }
}
