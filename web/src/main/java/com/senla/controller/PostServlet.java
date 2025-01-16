package com.senla.controller;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.post.PostCreateDto;
import com.senla.dto.post.PostGetDto;
import com.senla.dto.post.PostUpdateDto;
import com.senla.model.SubscriptionPlan;
import com.senla.model.User;
import com.senla.service.PostService;
import com.senla.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;

@Component
public class PostServlet extends HttpServlet {

    @Autowired
    private PostService postService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            listPosts(request, response);
        } else {
            switch (pathInfo) {
                case "/insert":
                    createPost(request, response);
                    break;
                case "/update":
                    updatePost(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            listPosts(request, response);
        } else {
            switch (pathInfo) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deletePost(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    private void listPosts(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<PostGetDto> listPosts = postService.getAllPosts();

        request.setAttribute("listPosts", listPosts);
        request.getRequestDispatcher("/WEB-INF/views/post/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/post/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        PostGetDto post = postService.getPostById(id);

        request.setAttribute("post", post);
        request.getRequestDispatcher("/WEB-INF/views/post/edit.jsp").forward(request, response);
    }

    private void createPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID authorId = UUID.fromString(request.getParameter("authorId"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        LocalDateTime publicationDate = validateDate(request.getParameter("publicationDate"));
        UUID subscriptionPlanId = UUID.fromString(request.getParameter("subscriptionPlanId"));

        User author = new User();
        author.setId(authorId);

        SubscriptionPlan subscriptionPlan = new SubscriptionPlan();
        subscriptionPlan.setId(subscriptionPlanId);

        PostCreateDto post = ValidationUtil.validate(new PostCreateDto(author, title, content, publicationDate, subscriptionPlan));

        postService.createPost(post);
        response.sendRedirect(request.getContextPath() + "/post");
    }

    private void updatePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        LocalDateTime publicationDate = validateDate(request.getParameter("publicationDate"));

        PostUpdateDto post = ValidationUtil.validate(new PostUpdateDto(title, content, publicationDate));

        postService.updatePost(post, id);
        response.sendRedirect(request.getContextPath() + "/post");
    }

    private void deletePost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        postService.deletePost(id);
        response.sendRedirect(request.getContextPath() + "/post");
    }

    private LocalDateTime validateDate(String date) {
        try {
            return LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid expiresDate format");
        }
    }
}
