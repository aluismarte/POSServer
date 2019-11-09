package com.alsjava.courses.servers.utils.bootstrap.control;

import com.alsjava.courses.servers.domain.control.Product;
import com.alsjava.courses.servers.repository.control.ProductRespository;
import com.alsjava.courses.servers.utils.Constants;
import com.alsjava.courses.servers.utils.bootstrap.BootStrapInsert;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by aluis on 11/2/19.
 */
public class BasicProducts implements BootStrapInsert {

    @Autowired
    private ProductRespository productRespository;

    public BasicProducts() {
        Constants.get().autoWiredClass(this);
    }

    @Override
    public void insert() {
        create("Limón", 20, 10, "https://media.metrolatam.com/2019/07/01/dietalimn-65c34166ec988a6ae136e7cbbdea9f80-600x400.jpg");
        create("Naranja", 100, 10, "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201811/26/00118105700068____2__600x600.jpg");
        create("Martillo", 0, 250, "https://d2mq510qed945.cloudfront.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/S/T/STAN0475.jpg");
    }

    private Product create(String name, long quantity, double price, String image) {
        Product product = productRespository.findByName(name);
        if (product == null) {
            product = new Product();
            product.setName(name);
            product.setQuantity(quantity);
            product.setPrice(price);
            product.setImage(image);
            return productRespository.saveAndFlush(product);
        }
        return product;
    }
}
