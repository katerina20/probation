package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import sigmasoftware.downloader.dto.UserInfo;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/main")
@PermitAll
public class MainPageResource {

    @Inject
    Template main;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return main.data("userInfo", new UserInfo.Builder().name("Katet").build());
    }
}
