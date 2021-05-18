package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import sigmasoftware.downloader.dto.File;
import sigmasoftware.downloader.services.FileService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/files")
public class FileResource {

    @Inject
    Template files;

    @Inject
    FileService fileService;

    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return files.data("files", fileService.getAll());
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void downloadFile(@PathParam Long id) {
        System.out.println("Downloading " + id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void post(@Context SecurityContext sec, File file) {
        Principal user = sec.getUserPrincipal();
        String name = user.getName();
        file.setAdminName(name);
        fileService.add(file);
    }
}
