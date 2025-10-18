import dao.IDao;
import entities.Category;
import entities.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import util.HibernateConfig;

public class Presentation2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);

        // Spring injecte les implémentations ProductDaoImpl et CategoryDaoImpl
        IDao<Product> productDao = context.getBean("productDaoImpl", IDao.class);
        IDao<Category> categoryDao = context.getBean("categoryDaoImpl", IDao.class);

        // Création et sauvegarde d'une catégorie
        Category category = new Category();
        category.setName("Informatique");
        categoryDao.create(category);
        System.out.println("Catégorie sauvegardée : " + category.getName() + " (ID: " + category.getId() + ")");

        // Création d'un produit et association avec la catégorie
        Product product = new Product();
        product.setName("Produit Spring-Postgres");
        product.setPrice(100.0);
        product.setCategory(category);

        productDao.create(product);

        System.out.println("Produit sauvegardé : " + product.getName() + " (ID: " + product.getId() + "), Catégorie : " + product.getCategory().getName());
    }
}