package com.toyproject.springbootsimpleboard1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Entity
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 10, max = 70, message = "제목은 10 글자 이상 70 글자 이하로 작성해 주세요")
    private String title;

    @NotNull
    @Size(min = 10, max = 70 ,message = "내용은 10 글자 이상 70 글자 이하로 작성해 주세요")
    private String content;
}
