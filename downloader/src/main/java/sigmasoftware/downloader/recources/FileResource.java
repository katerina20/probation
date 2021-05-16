package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/files")
public class FileResource {

    @Inject
    Template files;

    @GET
    @RolesAllowed("ADMIN")
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return files.instance();
    }
}