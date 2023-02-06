INSERT INTO category(category_id, name, description, is_active) VALUES(1, 'voice call', 'The category for voice call products', 1);
INSERT INTO category(category_id, name, description, is_active) VALUES(2, 'whiteboard', 'The category for whiteboard products', 1);

INSERT INTO product(product_id, name, description, is_active) VALUES(1, 'voice call', 'monthly voice call', 1);
INSERT INTO product(product_id, name, description, is_active) VALUES(2, 'voice call', 'annual voice call', 1);
INSERT INTO product(product_id, name, description, is_active) VALUES(3, 'whitboard', 'monthly whitboard', 1);
INSERT INTO product(product_id, name, description, is_active) VALUES(4, 'whitboard', 'annual whitboard', 1);

INSERT INTO category_product(category_product_id, category_id, product_id) VALUES(1, 1, 1);
INSERT INTO category_product(category_product_id, category_id, product_id) VALUES(2, 1, 2);
INSERT INTO category_product(category_product_id, category_id, product_id) VALUES(3, 2, 3);
INSERT INTO category_product(category_product_id, category_id, product_id) VALUES(4, 2, 4);