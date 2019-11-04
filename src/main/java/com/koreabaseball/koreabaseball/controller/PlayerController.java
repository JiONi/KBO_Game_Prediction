package com.koreabaseball.koreabaseball.controller;

import com.koreabaseball.koreabaseball.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @RequestMapping(value="/updateFirstStringYn", method= RequestMethod.POST)
    public void updateFirstStringYn(@RequestParam(value="backNoList") List<Integer> backNoList, String teamId){
        playerService.updateFirstStringYn(backNoList, teamId);
    }

    @RequestMapping(value="/insertPlayerRecord", method=RequestMethod.POST)
    public void insertPlayerRecord(@RequestParam(value="season") String season, @RequestParam(value="year") String year){
        playerService.insertPlayerRecord(year+season);
    }
}
