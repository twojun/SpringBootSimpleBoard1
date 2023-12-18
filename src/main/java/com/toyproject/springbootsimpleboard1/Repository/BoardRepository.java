package com.toyproject.springbootsimpleboard1.Repository;

import com.toyproject.springbootsimpleboard1.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
