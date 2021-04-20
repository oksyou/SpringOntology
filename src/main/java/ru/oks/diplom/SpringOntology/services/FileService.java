//package ru.oks.diplom.SpringOntology.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import ru.oks.diplom.SpringOntology.repository.LocalDocumentRepository;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.StandardCopyOption;
//
//@Service
//public class FileService {
//
//    private String basePathForUpload = "D:\\example\\upload\\";
//
//    @Autowired
//    private LocalDocumentRepository localDocumentRepository;
//
//
//    public ResponseEntity upload(MultipartFile file) {
//        String fileName;
//        if (file.getOriginalFilename() != null) {
//            fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        } else return null;
//        Path path = Paths.get(basePathForUpload + fileName);
//        try {
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        localDocumentRepository.save(new LocalDocument(fileName));
//        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/files/download/")
//                .path(fileName)
//                .toUriString();
//        return ResponseEntity.ok(fileDownloadUri);
//    }
//
//    public ResponseEntity download(HttpServletRequest request, @PathVariable String fileName) {
//        if (localDocumentRepository.getOne(fileName) == null) {
//            return null;
//        }
//        Path path = Paths.get(basePathForUpload + fileName);
//        Resource resource = null;
//        try {
//            resource = new UrlResource(path.toUri());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        String contentType;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (Exception ex) {
//            return ResponseEntity.badRequest().header("Could not determine file type.").build();
//        }
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
//}