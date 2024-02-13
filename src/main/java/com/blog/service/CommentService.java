package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;
import com.blog.vo.Post;

@Service
public class CommentService {
	@Autowired
    private CommentJpaRepository commentJpaRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentJpaRepository.findByPostIdOrderByRegDateDesc(postId);
    }
    
    public Comment getComment(Long id) {
        return commentJpaRepository.findById(id);
    }

    public List<Comment> searchCommentByPostIdAndComment(Long postId, String commentQuery) {
        return commentJpaRepository.findByPostIdAndCommentContainingOrderByRegDateDesc(postId, commentQuery);
    }
    
	public boolean saveComment(Comment comment) {
		Comment result = commentJpaRepository.save(comment);
		boolean isSuccess = true;
		
		if(result == null) {
			isSuccess = false;
		}
		
		return isSuccess;
	}
	
	public boolean deleteComment(Long id) {
		Comment result = commentJpaRepository.findById(id);
		
		if(result == null) {
			return false;
		}
		
		commentJpaRepository.deleteById(id);
		return true;
	}
	
	
}
