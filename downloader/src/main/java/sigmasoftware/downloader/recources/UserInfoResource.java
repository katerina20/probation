package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;
import sigmasoftware.downloader.dto.UserInfo;
import sigmasoftware.downloader.services.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.Principal;

@Path("/info")
@RolesAllowed({"ADMIN", "USER"})
public class UserInfoResource {

    @Inject
    Template info;

    @Inject
    UserService userService;

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return info.instance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserInfo post(UserInfo userInfo) {
        Principal user = securityIdentity.getPrincipal();
        String name = user.getName();
        return userService.update(userInfo, name);
    }
}
