package com.koreabaseball.koreabaseball.controller;

import com.koreabaseball.koreabaseball.entity.Player;
import com.koreabaseball.koreabaseball.entity.Team;
import com.koreabaseball.koreabaseball.service.PlayerService;
import com.koreabaseball.koreabaseball.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {
    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;

    @RequestMapping(value="/getCrawlingPage", method = RequestMethod.GET)
    public String getCrawlingPage(){
        return "crawlingPage";
    }

    @RequestMapping(value="/getGameRecordPage", method = RequestMethod.GET)
    public String getRecordPage(@RequestParam(value="homeTeam", required = true) String homeTeam,
                                @RequestParam(value="awayTeam", required = true) String awayTeam,
                                Model model) {
        List<Player> homeTeamHitter = playerService.getEntryPlayer(homeTeam, "H");
        List<Player> homeTeamPitcher = playerService.getEntryPlayer(homeTeam, "P");
        List<Player> awayTeamHitter = playerService.getEntryPlayer(awayTeam, "H");
        List<Player> awayTeamPitcher = playerService.getEntryPlayer(awayTeam, "P");

        model.addAttribute("homeTeamHitter", homeTeamHitter);
        model.addAttribute("homeTeamPitcher", homeTeamPitcher);
        model.addAttribute("awayTeamHitter", awayTeamHitter);
        model.addAttribute("awayTeamPitcher", awayTeamPitcher);
        model.addAttribute("homeTeam", homeTeam);
        model.addAttribute("awayTeam", awayTeam);
        return "gameRecordPage";
    }

    @RequestMapping(value="/getGameRecordIndexPage", method = RequestMethod.GET)
    public String getGameRecordInitPage(Model model) {

        List<Team> teamList = teamService.getTeamList();
        model.addAttribute("teamList", teamList);
        return "gameRecordIndexPage";
    }

    @RequestMapping(value="/getFirstStringPlayerPage", method = RequestMethod.GET)
    public String getFirstStringPlayerPage(Model model){
        List<Team> teamList = teamService.getTeamList();
        model.addAttribute("teamList", teamList);
        return "firstStringPlayerPage";
    }
}
