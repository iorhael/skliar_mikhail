package com.senla.controller;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.comment.CommentCreateDto;
import com.senla.dto.comment.CommentGetDto;
import com.senla.dto.comment.CommentUpdateDto;
import com.senla.model.Comment;
import com.senla.model.Post;
import com.senla.model.User;
import com.senla.service.CommentService;
import com.senla.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class CommentServlet extends HttpServlet {

    @Autowired
    private CommentService commentService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            listComments(request, response);
        } else {
            switch (pathInfo) {
                case "/insert":
                    createComment(request, response);
                    break;
                case "/update":
                    updateComment(request, response);
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
            listComments(request, response);
        } else {
            switch (pathInfo) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deleteComment(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    private void listComments(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<CommentGetDto> listComments = commentService.getAllComments();

        request.setAttribute("listComments", listComments);
        request.getRequestDispatcher("/WEB-INF/views/comment/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/comment/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        CommentGetDto comment = commentService.getCommentById(id);

        request.setAttribute("comment", comment);
        request.getRequestDispatcher("/WEB-INF/views/comment/edit.jsp").forward(request, response);
    }

    private void createComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID postId = UUID.fromString(request.getParameter("postId"));
        UUID authorId = UUID.fromString(request.getParameter("authorId"));
        String content = request.getParameter("content");

        String parentIdParam = request.getParameter("parentId");
        UUID parentId = (parentIdParam == null || parentIdParam.isEmpty()) ? null : UUID.fromString(parentIdParam);

        Post post = new Post();
        post.setId(postId);

        User author = new User();
        author.setId(authorId);

        Comment parentComment = new Comment();
        parentComment.setId(parentId);

        CommentCreateDto comment = ValidationUtil.validate(new CommentCreateDto(post, author, content, parentComment));

        commentService.createComment(comment);
        response.sendRedirect(request.getContextPath() + "/comment");
    }

    private void updateComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String content = request.getParameter("content");

        CommentUpdateDto comment = ValidationUtil.validate(new CommentUpdateDto(content));

        commentService.updateComment(comment, id);
        response.sendRedirect(request.getContextPath() + "/comment");
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        commentService.deleteComment(id);
        response.sendRedirect(request.getContextPath() + "/comment");
    }
}
