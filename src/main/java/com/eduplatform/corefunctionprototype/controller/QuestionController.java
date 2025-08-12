package com.eduplatform.corefunctionprototype.controller;

import com.eduplatform.corefunctionprototype.entity.Question;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * REST controller for managing questions in the educational platform.
 * This is a simulation without a real database, storing data in memory.
 */
@RestController
@RequestMapping("/questions")
public class QuestionController {
    // Simulated in-memory database
    private final Map<Long, Question> questionDB = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);


    /**
     * Create a new question
     * Example: POST /questions
     * Request Body:
     * {
     *   "content": "What is Java?",
     *   "answer": "A programming language."
     * }
     */
    @PostMapping
    public Question createQuestion(@RequestBody Map<String, String> request) {
        Long id = idGenerator.getAndIncrement();
        Question q = new Question(id, request.get("content"), request.get("answer"));
        questionDB.put(id, q);
        return q;
    }

    /**
     * Get all questions
     * Example: GET /questions
     */
    @GetMapping
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questionDB.values());
    }

    /**
     * Get a question by ID
     * Example: GET /questions/1
     */
    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id) {
        return questionDB.getOrDefault(id, null);
    }

    /**
     * Delete a question by ID
     * Example: DELETE /questions/1
     */
    @DeleteMapping("/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        if (questionDB.containsKey(id)) {
            questionDB.remove(id);
            return "Question deleted successfully.";
        }
        return "Question not found.";
    }
}

