package com.toyproject.springbootsimpleboard1.Controller;

import com.toyproject.springbootsimpleboard1.Repository.BoardRepository;
import com.toyproject.springbootsimpleboard1.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private final BoardRepository boardRepository;

    @GetMapping("/boards")
    public List<Board> all(@RequestParam(required = false, name = "title", defaultValue = "") String title,
                           @RequestParam(required = false, name = "content", defaultValue = "") String content) {
        //게시글 제목, 본문 내용으로 게시물을 조회해본다.
        if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
            return boardRepository.findAll();
        } else {
            return boardRepository.findByTitleOrContent(title, content);
        }
    }

    @GetMapping("/boards/like")
    public List<Board> searchKeywordsBoards(@RequestParam(required = false, name = "titleKeyword") String titleKeyword,
                                            @RequestParam(required = false, name = "contentKeyword") String contentKeyword) {

        if (StringUtils.isEmpty(titleKeyword) && StringUtils.isEmpty(contentKeyword)) {
            return boardRepository.findAll();
        } else {
            return boardRepository.findByTitleContainingOrContentContaining(titleKeyword, contentKeyword);
        }
    }

    @PostMapping("/boards")
    public Board createBoard(@RequestBody Board newBoard) {
        return boardRepository.save(newBoard);
    }

    @GetMapping("/boards/{id}")
    public Board findBoard(@PathVariable(name = "id") Long id) {
        return boardRepository.findById(id)
                .orElse(null);
    }

    @PutMapping("/boards/{id}")
    public Board replaceBoard(@RequestBody Board newBoard, @PathVariable(name = "id") Long id) {
        return boardRepository.findById(id)
                .map(board -> {
                    board.setTitle(newBoard.getTitle());
                    board.setContent(newBoard.getContent());
                    return boardRepository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return boardRepository.save(newBoard);
                });
    }

    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable(name = "id") Long id) {
        boardRepository.deleteById(id);
    }
}
