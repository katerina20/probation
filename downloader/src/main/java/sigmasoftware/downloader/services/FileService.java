package sigmasoftware.downloader.services;

import sigmasoftware.downloader.dto.File;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class FileService {

    @Inject
    EntityManager entityManager;

    public List<File> getAll() {
        return entityManager.createQuery("from File", File.class).getResultList();
    }
}
