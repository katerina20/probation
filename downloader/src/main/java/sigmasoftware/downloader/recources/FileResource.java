package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import sigmasoftware.downloader.services.FileService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/files")
@RolesAllowed({"ADMIN", "USER"})
public class FileResource {

    @Inject
    Template files;

    @Inject
    FileService fileService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return files.data("files", fileService.getAll());
    }

    @GET
    @Path("/{id}")
    public Response downloadFile(@PathParam("id") String id) {
        File nf = fileService.download((Long.parseLong(id)));
        Response.ResponseBuilder response = Response.ok((Object) nf);
        response.header("Content-Disposition", "attachment;filename=" + nf);
        // Uni<Response> re = Uni.createFrom().item(response.build());
        return response.build();
    }
}
