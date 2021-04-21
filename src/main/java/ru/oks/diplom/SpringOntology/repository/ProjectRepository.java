package ru.oks.diplom.SpringOntology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.diplom.SpringOntology.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
}
