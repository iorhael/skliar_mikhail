package com.senla.api.service.imp;

import com.senla.api.dao.TagDao;
import com.senla.api.model.Tag;
import com.senla.api.service.TagService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public Optional<Tag> createTag(Tag tag) {
        return tagDao.create(tag);
    }

    @Override
    public Optional<Tag> getTagById(UUID id) {
        return tagDao.getById(id);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.getAll();
    }

    @Override
    public Optional<Tag> updateTag(Tag tag, UUID id) {
        return tagDao.update(tag, id);
    }

    @Override
    public Optional<Tag> deleteTag(UUID id) {
        return tagDao.delete(id);
    }
}
