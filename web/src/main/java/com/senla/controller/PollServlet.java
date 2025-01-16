package com.senla.controller;

import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;
import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;
import com.senla.model.Post;
import com.senla.model.User;
import com.senla.service.PollService;
import com.senla.util.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class PollServlet extends HttpServlet {

    @Autowired
    private PollService pollService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            listPolls(request, response);
        } else {
            switch (pathInfo) {
                case "/insert":
                    createPoll(request, response);
                    break;
                case "/update":
                    updatePoll(request, response);
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
            listPolls(request, response);
        } else {
            switch (pathInfo) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/delete":
                    deletePoll(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
            }
        }
    }

    private void listPolls(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<PollGetDto> listPolls = pollService.getAllPolls();

        request.setAttribute("listPolls", listPolls);
        request.getRequestDispatcher("/WEB-INF/views/poll/list.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/poll/create.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        PollGetDto poll = pollService.getPollById(id);

        request.setAttribute("poll", poll);
        request.getRequestDispatcher("/WEB-INF/views/poll/edit.jsp").forward(request, response);
    }

    private void createPoll(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID postId = UUID.fromString(request.getParameter("postId"));
        UUID authorId = UUID.fromString(request.getParameter("authorId"));
        String description = request.getParameter("description");

        Post post = new Post();
        post.setId(postId);

        User author = new User();
        author.setId(authorId);

        PollCreateDto poll = ValidationUtil.validate(new PollCreateDto(post, author, description));

        pollService.createPoll(poll);
        response.sendRedirect(request.getContextPath() + "/poll");
    }

    private void updatePoll(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String description = request.getParameter("description");

        PollUpdateDto poll = ValidationUtil.validate(new PollUpdateDto(description));

        pollService.updatePoll(poll, id);
        response.sendRedirect(request.getContextPath() + "/poll");
    }

    private void deletePoll(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        pollService.deletePoll(id);
        response.sendRedirect(request.getContextPath() + "/poll");
    }
}
