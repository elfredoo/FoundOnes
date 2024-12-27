package pl.javastart.foundones.client.discovery;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.javastart.foundones.domain.api.CategoryName;
import pl.javastart.foundones.domain.api.CategoryService;
import pl.javastart.foundones.domain.api.DiscoverySaveRequest;
import pl.javastart.foundones.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.List;

@WebServlet("/discovery/add")
@ServletSecurity(
        httpMethodConstraints ={
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER"),
                @HttpMethodConstraint(value = "POST", rolesAllowed = "USER"),
        }
)
public class DiscoveryAddController extends HttpServlet {
    private CategoryService categoryService = new CategoryService();
    private DiscoveryService discoveryService = new DiscoveryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryName> categories = categoryService.findAllCategoryName();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/views/add-discovery.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiscoverySaveRequest discovery = getDiscovery(req);
        discoveryService.add(discovery);
        resp.sendRedirect(req.getContextPath()+"/");
    }

    private static DiscoverySaveRequest getDiscovery(HttpServletRequest req) {
        String name = req.getUserPrincipal().getName();
        return new DiscoverySaveRequest(
                req.getParameter("title"),
                req.getParameter("url"),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("categoryId")),
                name
        );
    }
}
