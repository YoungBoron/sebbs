package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.repository.BoardRepository;
import com.rgsj3.sebbs.repository.ZoneRepository;
import com.rgsj3.sebbs.service.BoardService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BoardController {

    @Resource
    BoardService boardService;

    @RequestMapping("/update/board")
    public Result updateBoard(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("zoneId") Integer zoneId,
                              @RequestParam("description") String description,
                              HttpServletRequest httpServletRequest) {
        return boardService.updateBoard(id, name, zoneId, description, httpServletRequest);
    }

    @RequestMapping("/delete/board")
    public Result deleteBoard(@RequestParam("id") Integer id,
                              HttpServletRequest httpServletRequest){
        return boardService.deleteBoard(id, httpServletRequest);
    }

    @RequestMapping("/add/board")
    public Result addBoard(@RequestParam("name") String name,
                              @RequestParam("zoneId") Integer zoneId,
                              @RequestParam("description") String description,
                              HttpServletRequest httpServletRequest) {
        return boardService.addBoard(name, zoneId, description, httpServletRequest);
    }
}
