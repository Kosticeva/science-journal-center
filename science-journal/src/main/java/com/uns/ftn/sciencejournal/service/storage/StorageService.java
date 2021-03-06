package com.uns.ftn.sciencejournal.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

    void init();

    Path store(MultipartFile file, Path destination);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void deleteDirectory(Path directory);

    Path createDirectory(String directoryName, Path parent);

    Path copyFile(Path source, Path destination);

}