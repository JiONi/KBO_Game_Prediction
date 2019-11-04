package com.koreabaseball.koreabaseball.controller;

import com.koreabaseball.koreabaseball.service.CrawlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrawlingController {
    @Autowired
    CrawlingService service;

    @RequestMapping(value = "/playerInfoCrawling", method = RequestMethod.POST)
    public void playerInfoCrawling(@RequestParam("playerId") String playerId){
        service.crawlingPlayerInfo(Integer.parseInt(playerId));
    }

    @RequestMapping(value = "/playerFirstStringYnUpdate", method = RequestMethod.POST)
    public void playerFirstStringYnUpdate(){

    }
}
