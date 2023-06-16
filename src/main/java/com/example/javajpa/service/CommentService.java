package com.example.javajpa.service;

import com.example.javajpa.domain.Comment;
import com.example.javajpa.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init() {
        for (int i=0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("최고에요");

            commentRepository.save(comment);
        }
    }

//    readOnly = true 를 추가하면 dirty_check 이 발생하지 않는다.
//    save 를 하지 않으면 update 발생하지 않는다.
//    readOnly = true 가 없으면 save 를 하지 않아도 dirty_check 이 발생해서 update 가 발생한다.
    @Transactional(readOnly = true)
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for (Comment comment : comments) {
            comment.setComment("별로에요");

            commentRepository.save(comment);
        }
    }

    @Transactional
    public void insertSomething() {
//        insert 의 경우에는 영속화가 이루어지지 않는다.
//        transactional 이라고 해도 save() 를 통해서 저장해야된다.
        Comment comment = new Comment();
        comment.setComment("안녕하세요");

        commentRepository.save(comment);
    }
}
