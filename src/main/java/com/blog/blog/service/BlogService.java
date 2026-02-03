package com.blog.blog.service;

import com.blog.blog.entity.Blog;
import com.blog.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    // Absolute upload path (already created)
    private static final String UPLOAD_DIR = "C:/blog_uploads/";

    // CREATE BLOG
    public Blog createBlog(String title, String content, MultipartFile image) throws IOException {

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);

        if (image != null && !image.isEmpty()) {

            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            File destinationFile = new File(uploadDir, imageName);

            image.transferTo(destinationFile);
            blog.setImageName(imageName);
        }

        return blogRepository.save(blog);
    }

    // GET ALL BLOGS
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    // GET BLOG BY ID
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElse(null);
    }

    // DELETE BLOG
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
