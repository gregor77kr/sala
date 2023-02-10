INSERT INTO category(category_id, name, description, is_active) VALUES(1, 'voice call', 'The category for voice call products', 1);
INSERT INTO category(category_id, name, description, is_active) VALUES(2, 'whiteboard', 'The category for whiteboard products', 1);

INSERT INTO product(product_id, name, description, is_active) VALUES(1, 'voice call', 'real-time voice call', 1);
INSERT INTO product(product_id, name, description, is_active) VALUES(2, 'whitboard', 'interactive whitboard', 1);

INSERT INTO product_price(product_price_id, product_id, currency, monthly_price, annual_price, is_active, creation_date_time, expiry_date_time) VALUES(1, 2, 'KRW', 12500, 130000, 1, NOW(), TIMESTAMPADD(YEAR, 1, NOW()));
INSERT INTO product_price(product_price_id, product_id, currency, monthly_price, annual_price, is_active, creation_date_time, expiry_date_time) VALUES(2, 2, 'USD', 10, 90, 1, NOW(), TIMESTAMPADD(YEAR, 1, NOW()));
INSERT INTO product_price(product_price_id, product_id, currency, monthly_price, annual_price, is_active, creation_date_time, expiry_date_time) VALUES(3, 2, 'KRW', 1200, 120000, 1, null, null);

INSERT INTO category_product(category_product_id, category_id, product_id) VALUES(1, 1, 1);
INSERT INTO category_product(category_product_id, category_id, product_id) VALUES(3, 2, 2);