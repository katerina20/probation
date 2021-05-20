package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;
import sigmasoftware.downloader.dto.FileData;
import sigmasoftware.downloader.services.FileService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.Principal;

@Path("/addFile")
@RolesAllowed("ADMIN")
public class AddFileResource {

    @Inject
    Template addFile;

    @Inject
    FileService fileService;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return addFile.instance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void post(FileData file) {
        // Principal user = sec.getUserPrincipal();
        // String name = user.getName();
        Principal user = securityIdentity.getPrincipal();
        String name = user.getName();
        file.setAdminName(name);
        fileService.add(file);
    }
}
