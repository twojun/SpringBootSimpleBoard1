package com.toyproject.springbootsimpleboard1.Controller;

import com.toyproject.springbootsimpleboard1.Repository.BoardRepository;
import com.toyproject.springbootsimpleboard1.model.Board;
import com.toyproject.springbootsimpleboard1.validator.BoardValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardInfoController {
    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final BoardValidator boardValidator;

    @GetMapping("/list_info")
    public String moveList(Model model,
                           @PageableDefault(size = 10) Pageable pageable,
                           @RequestParam(required = false, name = "searchText", defaultValue = "") String searchText) {
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);


        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("boards", boards);
        return "board/list_info";
    }

}
