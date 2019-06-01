package com.rgsj3.sebbs.controller;

import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.repository.BoardRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class BoardController {

    @Resource
    BoardRepository boardRepository;

    @RequestMapping("/update/board")
    public Result updateBoard(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              HttpServletRequest httpServletRequest) {
        var boardOptional = boardRepository.findById(id);
        if (boardOptional.isEmpty()) {
            return Result.error(3, "板块错误");
        } else {
            var board = boardOptional.get();
            board.setName(name);
            boardRepository.save(board);
            return Result.success();
        }
    }
}
