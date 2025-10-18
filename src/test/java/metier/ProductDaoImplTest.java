package metier;

import dao.IDao;
import entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import util.AppConfig;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Transactional
public class ProductDaoImplTest {

    @Autowired
    @Qualifier("productDaoImpl")
    private IDao<Product> productDao;

    @Test
    public void testCreate() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(150.0);
        assertTrue(productDao.create(product));
        assertNotNull(product.getId());
    }

    @Test
    public void testFindAll() {
        List<Product> products = productDao.findAll();
        assertNotNull(products);
    }

    @Test
    public void testFindById() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(150.0);
        productDao.create(product);

        Product foundProduct = productDao.findById(product.getId());
        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
    }

    @Test
    public void testUpdate() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(150.0);
        productDao.create(product);

        product.setName("Updated Product");
        assertTrue(productDao.update(product));

        Product updatedProduct = productDao.findById(product.getId());
        assertEquals("Updated Product", updatedProduct.getName());
    }

    @Test
    public void testDelete() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(150.0);
        productDao.create(product);

        assertTrue(productDao.delete(product));
        assertNull(productDao.findById(product.getId()));
    }
}