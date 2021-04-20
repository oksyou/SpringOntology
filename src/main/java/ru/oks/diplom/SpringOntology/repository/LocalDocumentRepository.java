package ru.oks.diplom.SpringOntology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oks.diplom.SpringOntology.entity.LocalDocument;

@Repository
public interface LocalDocumentRepository extends JpaRepository<LocalDocument, String> {
}
