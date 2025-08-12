package com.eduplatform.corefunctionprototype.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xzr
 */
@Data
@AllArgsConstructor
public class Question {
    private Long id;
    private String content;
    private String answer;

}
