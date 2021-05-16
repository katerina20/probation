package sigmasoftware.downloader;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

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
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return files.instance();
    }
}
