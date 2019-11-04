package com.koreabaseball.koreabaseball.controller;

import com.koreabaseball.koreabaseball.DTO.GameRecordPlayerDTO;
import com.koreabaseball.koreabaseball.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    @RequestMapping(value="/saveGameRecordHitter", method= RequestMethod.POST)
    public void saveGameRecordHitter(@ModelAttribute GameRecordPlayerDTO gameRecordPlayerDTO,
                                     @RequestParam(value="gameDate") String gameDate,
                                     @RequestParam(value="gameSect") String gameSect,
                                     @RequestParam(value="homeTeam") String homeTeam,
                                     @RequestParam(value="awayTeam") String awayTeam){

        gameService.saveGameRecord(gameRecordPlayerDTO, gameDate, gameSect, homeTeam, awayTeam);
    }
}
