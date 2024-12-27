package pl.javastart.foundones.client.vote;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.javastart.foundones.domain.api.DiscoveryVote;
import pl.javastart.foundones.domain.api.DiscoveryVoteService;

import java.io.IOException;

@WebServlet("/discovery/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class DiscoveryVoteController extends HttpServlet {
    private DiscoveryVoteService discoveryVoteService = new DiscoveryVoteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiscoveryVote vote = getVote(req);
        discoveryVoteService.addVote(vote);
        resp.sendRedirect(req.getContextPath()+"/");
    }

    private DiscoveryVote getVote(HttpServletRequest req) {
        Integer discoveryId = Integer.valueOf(req.getParameter("id"));
        String type = req.getParameter("type");
        String name = req.getUserPrincipal().getName();
        return new DiscoveryVote(name, discoveryId, type);
    }
}
