package com.senla.controller;

import com.senla.controller.dto.ResponseInfoDto;
import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;
import com.senla.service.PollService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/polls")
@RequiredArgsConstructor
public class PollController {
    private static final String POLL_DELETION_MESSAGE = "Poll deleted successfully";

    private final PollService pollService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PollGetDto> findAllPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PollGetDto findPollById(@PathVariable UUID id) {
        return pollService.getPollById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PollGetDto createPoll(@Valid @RequestBody PollCreateDto poll) {
        return pollService.createPoll(poll);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PollGetDto updatePoll(@Valid @RequestBody PollUpdateDto poll,
                                 @PathVariable UUID id) {
        return pollService.updatePoll(poll, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseInfoDto deletePoll(@PathVariable UUID id) {
        pollService.deletePoll(id);

        return ResponseInfoDto.builder()
                .message(POLL_DELETION_MESSAGE)
                .build();
    }
}
