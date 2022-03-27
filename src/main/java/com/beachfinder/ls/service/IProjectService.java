package com.beachfinder.ls.service;

import java.util.Optional;

import com.beachfinder.ls.persistence.model.Project;

public interface IProjectService {
    Optional<Project> findById(Long id);

    Project save(Project project);

    Iterable<Project> findAll();

    void delete(Long id);
}
