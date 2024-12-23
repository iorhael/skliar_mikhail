package com.senla.api.service.imp;

import com.senla.api.dao.TagDao;
import com.senla.api.dao.exception.TagNotFoundException;
import com.senla.api.model.Tag;
import com.senla.api.service.TagService;
import com.senla.api.service.exception.tag.TagCreateException;
import com.senla.api.service.exception.tag.TagDeleteException;
import com.senla.api.service.exception.tag.TagUpdateException;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public Tag createTag(Tag tag) {
        return tagDao.create(tag).orElseThrow(() -> new TagCreateException("Can't create tag"));
    }

    @Override
    public Tag getTagById(UUID id) {
        return tagDao.getById(id).orElseThrow(() -> new TagNotFoundException("No tag found"));
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    @Override
    public Tag updateTag(Tag tag, UUID id) {
        return tagDao.update(tag, id).orElseThrow(() -> new TagUpdateException("Can't update tag"));
    }

    @Override
    public Tag deleteTag(UUID id) {
        return tagDao.delete(id).orElseThrow(() -> new TagDeleteException("Can't delete tag"));
    }
}
