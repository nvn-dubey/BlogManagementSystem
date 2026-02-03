package com.blog.blog.controller;

import com.blog.blog.entity.Blog;
import com.blog.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // TEST API
    @GetMapping("/test")
    public String test() {
        return "Blog Management System is running!";
    }

    // CREATE BLOG
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Blog> createBlog(
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws Exception {

        return ResponseEntity.ok(
                blogService.createBlog(title, content, image)
        );
    }

    // GET ALL BLOGS
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // GET BLOG BY ID
    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    // DELETE BLOG
    @DeleteMapping("/{id}")
    public void deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
    }
}
