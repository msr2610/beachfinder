package com.beachfinder.ls.service.impl;

import java.util.Optional;

import com.beachfinder.ls.persistence.model.Task;
import com.beachfinder.ls.persistence.repository.ITaskRepository;
import com.beachfinder.ls.service.ITaskService;

public class TaskServiceImpl implements ITaskService {
    private ITaskRepository taskRepository;

    public TaskServiceImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task save(Task project) {
        return taskRepository.save(project);
    }
}
