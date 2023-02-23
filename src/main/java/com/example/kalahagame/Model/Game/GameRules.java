package com.example.kalahagame.Model.Game;


import com.example.kalahagame.Model.Logger.LogUtility;
import com.example.kalahagame.Model.Pits.Pit;
import com.example.kalahagame.Model.KalahaGame;
import com.example.kalahagame.Utility.FunctionCollection;

public class GameRules {
    public static void WireKalahaGameRules(KalahaGame game){

        game.Functionality.SpecialEndOfTurnScenarios = new FunctionCollection<>();

        game.Functionality.SpecialEndOfTurnScenarios.Add((d) ->{
            return GameUtil.EmptyFieldsWinCondition(d);
        });

        game.Functionality.SpecialEndOfTurnScenarios.Add((d) ->{
            return GameUtil.UnwinnableWinCondition(d);
        });

        game.Functionality.SpecialEndOfTurnScenarios.Add((d) ->{
            LogUtility.Log(game.Data.Logger, String.format("%sNew Turn",LogUtility.LogStart(GameUtil.GetPlayerNextTurn(d),game.Data.CurrentTurn + 1)));
            return null;
        });

        game.Data.Pits.Stream().forEach((pit) ->{
            pit.OnAdd.Add((stones) -> LogUtility.LogPit(game.Data, pit, stones));
            pit.OnGrab.Add((stones) -> LogUtility.LogPit(game.Data, pit, stones));
        });

        game.Data.Pits.Stream()
            .filter((p) -> p.Data().isKalaha)
            .forEach((pit) ->{
                pit.OnAdd.Add((stones) -> {
                    if(game.Data.CurrentHand == 1 && pit.Data().player == game.Data.CurrentPlayer){

                        LogUtility.Log(game.Data.Logger,String.format("%sGains an Extra Turn for dropping the last stone in his own Kalaha.",
                                LogUtility.LogStart(game.Data.CurrentPlayer ,  game.Data.CurrentTurn)
                        ));

                        GameUtil.FlipGameState(game.Data);
                    }
                });
        });

        game.Data.Pits.pList.stream()
                .filter((p) -> !p.Data().isKalaha)
                .forEach((pit) ->{
            pit.OnAdd.Add((changed) ->{
                if(game.Data.CurrentHand == 1 && pit.Amount()-changed == 0 && pit.Data().player == game.Data.CurrentPlayer){

                    Pit opposite = game.Data.Pits.Opposite(pit);
                    Pit kalaha = game.Data.Pits.KalahaOfPlayer(game.Data.CurrentPlayer);

                    game.Data.CurrentHand=0;
                    pit.GrabAll();
                    Integer capturedStones = opposite.GrabAll();

                    kalaha.Add(1);
                    kalaha.Add(capturedStones);

                    LogUtility.Log(game.Data.Logger,String.format("%sCaptured %d stones from opposite pit %d and scored %d.",
                            LogUtility.LogStart(game.Data.CurrentPlayer ,  game.Data.CurrentTurn),
                            capturedStones,
                            game.Data.Pits.IndexOf(opposite),
                            capturedStones + 1
                    ));

                }
            });
        });

        game.Functionality.TurnProcedure = (index) -> {
            Pit current = game.Data.Pits.Get(index);
            game.Data.CurrentHand = current.GrabAll();

            LogUtility.Log(game.Data.Logger,
                String.format("%sGrabbed %d stones from pit %d.",
                    LogUtility.LogStart(game.Data.CurrentPlayer ,  game.Data.CurrentTurn),
                    game.Data.CurrentHand,
                    index
                )
            );

            while(game.Data.CurrentHand > 0){
                current = game.Data.Pits.Right(current);

                if (current.Data().isKalaha && current.Data().player != game.Data.CurrentPlayer) {
                    LogUtility.Log(game.Data.Logger, String.format("Turn %d - Skipped dropping a stone at opponents Kalaha.", game.Data.CurrentTurn));
                    continue;
                }

                current.Add(1);
                game.Data.CurrentHand--;
            }
        };
    }
}
