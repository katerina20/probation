package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/info")
public class UserInfoResource {

    @Inject
    Template info;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return info.instance();
    }
}
