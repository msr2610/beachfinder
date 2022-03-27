package com.beachfinder.ls.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import com.beachfinder.ls.persistence.model.Task;

public interface ITaskRepository extends CrudRepository<Task, Long> {
}
