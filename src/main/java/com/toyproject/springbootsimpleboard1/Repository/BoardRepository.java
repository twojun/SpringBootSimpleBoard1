package com.toyproject.springbootsimpleboard1.Repository;

import com.toyproject.springbootsimpleboard1.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // Get 요청 시 게시글의 제목, 내용만으로 특정 게시글 조회
    List<Board> findByTitleOrContent(String title, String content);

    // 특정 키워드만으로 조회
    List<Board> findByTitleContainingOrContentContaining(String titleKeywords, String contentKeyword);

    // 게시글 검색
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
