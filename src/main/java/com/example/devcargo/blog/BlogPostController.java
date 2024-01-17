package com.example.devcargo.blog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class BlogPostController {
    private final BlogPostService blogPostService;

    @RequestMapping("/blog/posts")
    public String viewBlogs(Model model) {
        var blogPosts = blogPostService.listAll();
        model.addAttribute("blogPosts", blogPosts);
        return "blog";
    }
    @RequestMapping("/blog/error")
    public String viewError(Model model) {
        return "error";
    }

    @GetMapping("/blog/posts/new")
    public String showNewPostForm(Model model) {
        var blogPost = new BlogPost();
        model.addAttribute("blogPost", blogPost);
        return "new_post";
    }

    @PostMapping("/blog/posts/save")
    public String saveBlogPost(@ModelAttribute("blogPost") BlogPost blogPost) {
        blogPost.setDate(LocalDate.now());
        blogPostService.save(blogPost);
        return "redirect:/blog/posts";
    }

    @GetMapping("/blog/posts/edit/{id}")
    public ModelAndView showEditBlogPostForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_post");
        var blogPost = blogPostService.get(id);
        mav.addObject("blogPost", blogPost);
        return mav;
    }

    @PostMapping("/blog/posts/delete/{id}")
    public String deleteBlogPost(@PathVariable(name = "id") Long id) {
        try {
            blogPostService.delete(id);
            return "redirect:/blog/posts";
        } catch (Exception e) {
            // Перенаправляем пользователя на страницу с сообщением об ошибке
            return "redirect:/blog/error";
        }
    }
    }
