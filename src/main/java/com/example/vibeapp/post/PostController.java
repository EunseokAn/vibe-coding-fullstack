package com.example.vibeapp.post;

import com.example.vibeapp.post.dto.PostCreateDto;
import com.example.vibeapp.post.dto.PostListDto;
import com.example.vibeapp.post.dto.PostResponseDto;
import com.example.vibeapp.post.dto.PostUpdateDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public String listPosts(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        List<PostListDto> posts = postService.findAllPaged(page, pageSize);
        int totalPages = postService.getTotalPages(pageSize);

        model.addAttribute("posts", posts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "post/posts";
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable("id") Long id, Model model) {
        PostResponseDto post = postService.findById(id);
        model.addAttribute("post", post);
        return "post/post_detail";
    }

    @GetMapping("/posts/new")
    public String newPostForm(Model model) {
        model.addAttribute("postCreateDto", new PostCreateDto());
        return "post/post_new_form";
    }

    @PostMapping("/posts/add")
    public String createPost(@Valid @ModelAttribute("postCreateDto") PostCreateDto postCreateDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "post/post_new_form";
        }
        postService.save(postCreateDto);
        return "redirect:/posts";
    }

    @GetMapping("/posts/{id}/edit")
    public String editPostForm(@PathVariable("id") Long id, Model model) {
        PostResponseDto post = postService.findById(id);
        model.addAttribute("post", post);
        model.addAttribute("postUpdateDto", new PostUpdateDto(post.getTitle(), post.getContent()));
        return "post/post_edit_form";
    }

    @PostMapping("/posts/{id}/save")
    public String updatePost(@PathVariable("id") Long id,
            @Valid @ModelAttribute("postUpdateDto") PostUpdateDto postUpdateDto,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("post", postService.findById(id));
            return "post/post_edit_form";
        }
        postService.updatePost(id, postUpdateDto);
        return "redirect:/posts/" + id;
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable("id") Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
