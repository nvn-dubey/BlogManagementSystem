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

    // TEST
    @GetMapping("/test")
    public String test() {
        return "Blog Management System is running!";
    }

    // CREATE
    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Blog> createBlog(
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws Exception {

        return ResponseEntity.ok(blogService.createBlog(title, content, image));
    }

    // READ ALL
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // READ ONE
    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable Long id) {
        return blogService.getBlogById(id);
    }

    // UPDATE
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Blog> updateBlog(
            @PathVariable Long id,
            @RequestPart("title") String title,
            @RequestPart("content") String content,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) throws Exception {

        return ResponseEntity.ok(blogService.updateBlog(id, title, content, image));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable Long id) {
        blogService.deleteBlog(id);
        return ResponseEntity.ok("Blog deleted successfully");
    }
}
