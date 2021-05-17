package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import sigmasoftware.downloader.dto.UserInfo;

import javax.inject.Inject;
import javax.ws.rs.*;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void post(UserInfo userInfo) {
        System.out.println(userInfo);
    }
}
