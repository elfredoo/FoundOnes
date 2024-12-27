package pl.javastart.foundones.client.home;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.javastart.foundones.domain.api.CategoryName;
import pl.javastart.foundones.domain.api.CategoryService;
import pl.javastart.foundones.domain.api.DiscoveryBasicInfo;
import pl.javastart.foundones.domain.api.DiscoveryService;

import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeController extends HttpServlet {
    private final DiscoveryService discoveryService = new DiscoveryService();
    private final CategoryService categoryService = new CategoryService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DiscoveryBasicInfo> discoveries = discoveryService.findAll();
        req.setAttribute("discoveries", discoveries);
        List<CategoryName> categories = categoryService.findAllCategoryName();
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
