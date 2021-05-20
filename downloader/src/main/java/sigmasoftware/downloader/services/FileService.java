package sigmasoftware.downloader.services;

import io.vavr.control.Try;
import sigmasoftware.downloader.dto.FileData;
import sigmasoftware.downloader.utils.FileUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@ApplicationScoped
public class FileService {

    @Inject
    EntityManager entityManager;

    @Inject
    FileUtil fileUtil;

    public List<FileData> getAll() {
        return entityManager.createQuery("from FileData", FileData.class).getResultList();
    }

    @Transactional
    public void add(FileData file) {
        entityManager.persist(file);
    }

    @Transactional
    public File download(Long id) {
        FileData file = entityManager.find(FileData.class, id);
        String url = file.getUrl();
        String name = url.substring(url.lastIndexOf("/") + 1);
        byte[] bytes = Try.of(() -> fileUtil.download(url)).get();
        Path path = Paths.get("C:\\tmp\\" + name);
        OutputStream outputStream = Try.of(() -> Files.newOutputStream(path)).get();
        bytesToOutputStream(bytes, outputStream);
        return path.toFile();
    }

    private void bytesToOutputStream(byte[] data, OutputStream outputStream) {
        int count = 0;
        int n = Math.min(data.length, 100000);
        while (n != 0) {
            int piece = n;
            int start = count;
            Try.run(() -> outputStream.write(data, start, piece));
            count += n;
            n = Math.min(data.length - count, 100000);
        }
    }
}
