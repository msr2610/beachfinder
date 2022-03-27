package com.beachfinder.ls.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.beachfinder.ls.persistence.model.Project;
import com.beachfinder.ls.persistence.repository.IProjectRepository;
import com.beachfinder.ls.service.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {

    private IProjectRepository projectRepository;

    public ProjectServiceImpl(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project save(Project project) {
        if (StringUtils.isEmpty(project.getId())) {
            project.setDateCreated(LocalDate.now());
        }
        return projectRepository.save(project);
    }

    @Override
    public Iterable<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        projectRepository.deleteById(id);
    }

}