package com.beachfinder.ls.service;

import java.util.Optional;

import com.beachfinder.ls.persistence.model.Task;

public interface ITaskService {
    Optional<Task> findById(Long id);

    Task save(Task project);
}
