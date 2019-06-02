package com.rgsj3.sebbs.service;

import com.rgsj3.sebbs.domain.Board;
import com.rgsj3.sebbs.domain.Result;
import com.rgsj3.sebbs.repository.BoardRepository;
import com.rgsj3.sebbs.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

@Service
public class BoardService {

    @Resource
    BoardRepository boardRepository;

    @Resource
    ZoneRepository zoneRepository;

    public Result updateBoard(Integer id,
                              String name,
                              Integer zoneId,
                              String description,
                              HttpServletRequest httpServletRequest) {
        var boardOptional = boardRepository.findById(id);
        var zoneOptional = zoneRepository.findById(zoneId);
        if (boardOptional.isEmpty()) {
            return Result.error(3, "板块错误");
        } else if (zoneOptional.isEmpty()) {
            return Result.error(3, "区域错误");
        } else {
            var board = boardOptional.get();
            var zone = zoneOptional.get();
            board.setName(name);
            board.setZone(zone);
            board.setDescription(description);
            boardRepository.save(board);
            return Result.success();
        }
    }

    public Result deleteBoard(Integer id, HttpServletRequest httpServletRequest) {
        var boardOptional = boardRepository.findById(id);
        if (boardOptional.isEmpty()) {
            return Result.error(3, "板块错误");
        } else {
            var board = boardOptional.get();
            boardRepository.delete(board);
            return Result.success();
        }
    }

    public Result addBoard(String name,
                              Integer zoneId,
                              String description,
                              HttpServletRequest httpServletRequest) {
        var zoneOptional = zoneRepository.findById(zoneId);
        if (zoneOptional.isEmpty()) {
            return Result.error(3, "区域错误");
        } else {
            var board = new Board();
            var zone = zoneOptional.get();
            board.setName(name);
            board.setZone(zone);
            board.setDescription(description);
            boardRepository.save(board);
            return Result.success();
        }
    }
}
