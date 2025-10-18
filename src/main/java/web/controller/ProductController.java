package web.controller;

import dao.IDao;
import entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    @Qualifier("productDaoImpl")
    private IDao<Product> productDao;

    @RequestMapping("/")
    public String index() {
        return "redirect:/products";
    }

    @RequestMapping("/products")
    public String listProducts(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
