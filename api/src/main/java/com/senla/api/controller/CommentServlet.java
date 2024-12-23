package com.senla.api.controller;

import com.senla.api.dto.comment.CommentCreateDto;
import com.senla.api.dto.comment.CommentGetDto;
import com.senla.api.dto.comment.CommentUpdateDto;
import com.senla.api.service.CommentService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
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

        CommentCreateDto comment = new CommentCreateDto(postId, authorId, content, parentId);

        commentService.createComment(comment);
        response.sendRedirect(request.getContextPath() + "/comment");
    }

    private void updateComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String content = request.getParameter("content");

        CommentUpdateDto comment = new CommentUpdateDto(content);

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
