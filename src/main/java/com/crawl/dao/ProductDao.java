package com.crawl.dao;

import com.crawl.model.shopee.ShopeeProductDto;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.logging.Level;


@Repository
@Log
public class ProductDao {

    private static final String INSERT_PRODUCT_BY_PROVIDER = """
            INSERT INTO shopee_product (prod_id, shop_id, name, currency, image, images, price, min_price, max_price, price_before_discount,
             price_max_before_discount, price_min_before_discount, shop_location, description, stock, discount, provider_created_time, 
             status, rating, like_count, attributes, skus)
            VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
            ON DUPLICATE KEY UPDATE name=VALUES(name), currency=VALUES(currency), image=VALUES(image), images=VALUES(images), price =VALUES(price),
            min_price = VALUES(min_price), max_price = VALUES(max_price), price_before_discount = VALUES(price_before_discount), price_max_before_discount = VALUES(price_max_before_discount),
            price_min_before_discount = VALUES(price_min_before_discount), shop_location = VALUES(shop_location), description = VALUES(description), stock = VALUES(stock),
            discount = VALUES(discount), provider_created_time = VALUES(provider_created_time), status = VALUES(status), rating = VALUES(rating),
            like_count = VALUES(like_count), attributes = VALUES(attributes), skus = VALUES(skus)
            """;
    private static final String MAP_CATEGORY = """
            INSERT INTO shopee_category_product(cat_id, prod_id) VALUES (?,?)
            ON DUPLICATE KEY UPDATE cat_id=VALUES(cat_id), prod_id=VALUES(prod_id)
            """;
    @Autowired
    private JdbcTemplate jdbc;

    public void add(ShopeeProductDto dto) {
        try {
            var param = new Object[]{
                    dto.getProdId(), dto.getShopId(), dto.getName(), dto.getCurrency(),
                    dto.getImage(), dto.getImages(), dto.getPrice(), dto.getMinPrice(),
                    dto.getMaxPrice(), dto.getPriceBeforeDiscount(), dto.getPriceMaxBeforeDiscount(), dto.getPriceMinBeforeDiscount(),
                    dto.getShopLocation(), dto.getDescription(), dto.getStock(), dto.getDiscount(),
                    dto.getCtime(), dto.getStatus(), dto.getRating(), dto.getLikeCount(), dto.getAttributes(), dto.getSku()
            };
            jdbc.update(INSERT_PRODUCT_BY_PROVIDER, param);
        } catch (Exception exception) {
            log.log(Level.WARNING, "ProductDao >> exception >> ", exception);
        }
    }

    public void mapToCategory(Long catId, Long productId) {
        jdbc.update(MAP_CATEGORY, catId, productId);
    }
}
