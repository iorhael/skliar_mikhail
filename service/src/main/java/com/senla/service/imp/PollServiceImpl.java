package com.senla.service.imp;

import com.senla.aspect.Benchmarked;
import com.senla.dto.poll.PollCreateDto;
import com.senla.dto.poll.PollGetDto;
import com.senla.dto.poll.PollUpdateDto;
import com.senla.model.Poll;
import com.senla.model.Post;
import com.senla.model.User;
import com.senla.repository.PollRepository;
import com.senla.repository.PostRepository;
import com.senla.repository.UserRepository;
import com.senla.service.PollService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Benchmarked
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {
    public static final String POLL_NOT_FOUND = "Poll not found";

    private final PollRepository pollRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public PollGetDto createPoll(PollCreateDto pollCreateDto) {
        Poll poll = modelMapper.map(pollCreateDto, Poll.class);

        User author = userRepository.getReferenceById(pollCreateDto.getAuthorId());
        Post post = postRepository.getReferenceById(pollCreateDto.getPostId());

        poll.setAuthor(author);
        poll.setPost(post);

        pollRepository.save(poll);

        return modelMapper.map(poll, PollGetDto.class);
    }

    @Override
    public PollGetDto getPollBy(UUID id) {
        return pollRepository.findWithPostAndAuthorById(id)
                .map(poll -> modelMapper.map(poll, PollGetDto.class))
                .orElseThrow(() -> new EntityNotFoundException(POLL_NOT_FOUND));
    }

    @Override
    public List<PollGetDto> getAllPolls() {
        return pollRepository.findWithPostAndAuthorBy()
                .stream()
                .map(poll -> modelMapper.map(poll, PollGetDto.class))
                .toList();
    }

    @Override
    public List<PollGetDto> getAllPollsForPost(UUID postId) {
        return pollRepository.findByPostId(postId)
                .stream()
                .map(poll -> modelMapper.map(poll, PollGetDto.class))
                .toList();
    }

    @Override
    @Transactional
    public PollGetDto updatePoll(PollUpdateDto postUpdateDto, UUID id) {
        Poll post = pollRepository.findWithPostAndAuthorById(id)
                .orElseThrow(() -> new EntityNotFoundException(POLL_NOT_FOUND));

        post.setDescription(postUpdateDto.getDescription());

        return modelMapper.map(post, PollGetDto.class);
    }

    @Override
    public void deletePoll(UUID id) {
        pollRepository.deleteById(id);
    }
}
