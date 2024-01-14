package com.example.devcargo.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    public List<BlogPost> listAll() {
        return blogPostRepository.findAll();
    }

    public void save(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
    }

    public BlogPost get(Long id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        blogPostRepository.deleteById(id);
    }
}
