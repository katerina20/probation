package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import sigmasoftware.downloader.dto.UserInfo;
import sigmasoftware.downloader.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/info")
public class UserInfoResource {

    @Inject
    Template info;

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return info.instance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserInfo post(UserInfo userInfo) {
        return userService.update(userInfo);
    }
}
