package com.example.kalahagame.Controllers;


import com.example.kalahagame.Model.Turn.TurnData;
import com.example.kalahagame.Model.Turn.TurnInputData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameService game;

    @RequestMapping("/game")
    public TurnData StartNewGame() {
        return game.StartNewGame();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/game")
    public TurnData DoTurn(@RequestBody TurnInputData input){
        return game.DoTurn(input);
    }

    @RequestMapping("/sample")
    public TurnInputData GetSample() {
        return new TurnInputData();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sample")
    public TurnData DoTurn(@RequestBody TurnData input){
        return input;
    }
}
