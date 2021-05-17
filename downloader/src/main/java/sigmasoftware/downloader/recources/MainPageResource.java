package sigmasoftware.downloader.recources;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import sigmasoftware.downloader.dto.UserInfo;
import sigmasoftware.downloader.services.UserService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/main")
@PermitAll
public class MainPageResource {

    @Inject
    Template main;

    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@Context SecurityContext sec) {
        Principal user = sec.getUserPrincipal();
        if (user == null) return main.instance();
        String name = user.getName();
        userService.update(new UserInfo.Builder().build());
        return main.data("userInfo", userService.getFullName(name));
    }
}
