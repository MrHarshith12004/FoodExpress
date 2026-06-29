-- Create database if not exists
CREATE DATABASE IF NOT EXISTS foodexpress;
USE foodexpress;

-- Drop tables if they exist to start fresh (optional, but good for setup)
-- SET FOREIGN_KEY_CHECKS = 0;
-- DROP TABLE IF EXISTS order_items;
-- DROP TABLE IF EXISTS orders;
-- DROP TABLE IF EXISTS menu;
-- DROP TABLE IF EXISTS restaurants;
-- DROP TABLE IF EXISTS users;
-- SET FOREIGN_KEY_CHECKS = 1;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address TEXT NOT NULL,
    role VARCHAR(20) DEFAULT 'CUSTOMER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Restaurants Table
CREATE TABLE IF NOT EXISTS restaurants (
    restaurant_id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_name VARCHAR(100) NOT NULL,
    description TEXT,
    address VARCHAR(255) NOT NULL,
    phone VARCHAR(15),
    image VARCHAR(255),
    rating DOUBLE DEFAULT 4.0,
    delivery_time INT DEFAULT 30, -- in minutes
    is_active BOOLEAN DEFAULT TRUE
);

-- Menu Table
CREATE TABLE IF NOT EXISTS menu (
    menu_id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT NOT NULL,
    food_name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    category VARCHAR(50),
    is_veg BOOLEAN DEFAULT TRUE,
    image VARCHAR(255),
    rating DOUBLE DEFAULT 4.0,
    is_available BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id) ON DELETE CASCADE
);

-- Orders Table
CREATE TABLE IF NOT EXISTS orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    total_amount DECIMAL(10, 2) NOT NULL,
    delivery_address TEXT NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    order_status VARCHAR(50) DEFAULT 'Pending',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id) ON DELETE CASCADE
);

-- Order Items Table
CREATE TABLE IF NOT EXISTS order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    menu_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id) ON DELETE CASCADE
);

-- Populate Sample Restaurants (Vibrant & Appetizing Food Spots)
INSERT INTO restaurants (restaurant_name, description, address, phone, image, rating, delivery_time, is_active)
VALUES 
('The Pizza Bakery', 'Gourmet hand-tossed woodfired pizzas with fresh ingredients, local cheese, and organic toppings.', '102 Baker Street, Food Ville', '9876543210', 'images/restaurants/pizza-bakery.jpg', 4.8, 25, TRUE),
('Leon Grill', 'Juicy grilled patties, custom special sauces, melted cheddar, and crispy fresh hand-cut fries.', '456 Gourmet Lane, Food Ville', '9876543211', 'images/restaurants/leon-grill.jpg', 4.5, 20, TRUE),
('Meghana Foods', 'Authentic slow-cooked, long-grain Hyderabadi basmati biryani seasoned with rare premium spices.', '789 Nizam Road, Food Ville', '9876543212', 'images/restaurants/meghana-foods.jpg', 4.7, 35, TRUE),
('Truffles Cafe', 'Artisanal cheesecakes, freshly baked pastries, macarons, and velvety decadent chocolate fudge.', '12 Sweet Boulevard, Food Ville', '9876543213', 'images/restaurants/truffles.jpg', 4.6, 15, TRUE),
('Empire Restaurant', 'Sizzling hot noodles, dumplings, fresh stir-fries, and traditional Asian fusion comfort food.', '505 Lotus Way, Food Ville', '9876543214', 'images/restaurants/empire.jpg', 4.2, 30, TRUE);

-- Populate Sample Menus for The Pizza Bakery (restaurant_id = 1)
INSERT INTO menu (restaurant_id, food_name, description, price, category, is_veg, image, rating, is_available)
VALUES 
(1, 'Margherita Pizza', 'Classic Italian pizza with simple red tomato sauce, creamy fresh mozzarella, aromatic basil leaves, and virgin olive oil.', 249.00, 'Pizzas', TRUE, 'images/menu/pizza/margherita.jpg', 4.7, TRUE),
(1, 'Pepperoni Feast', 'Double loaded pepperoni, premium mozzarella cheese, and rich marinara sauce on a hand-tossed sourdough crust.', 399.00, 'Pizzas', FALSE, 'images/menu/pizza/pepperoni.jpg', 4.9, TRUE),
(1, 'Garden Veggie Delight', 'Assorted bell peppers, sweet corn, black olives, red onions, mushrooms, and jalapeños on a thin crust.', 329.00, 'Pizzas', TRUE, 'images/menu/pizza/farmhouse.jpg', 4.5, TRUE),
(1, 'Garlic Breadsticks', 'Golden oven-baked dough brushed with rich garlic butter, herbs, and served with a warm cheese dip.', 129.00, 'Sides', TRUE, 'images/menu/starters/garlic-bread.jpg', 4.4, TRUE);

-- Populate Sample Menus for Leon Grill (restaurant_id = 2)
INSERT INTO menu (restaurant_id, food_name, description, price, category, is_veg, image, rating, is_available)
VALUES 
(2, 'Classic Cheese Burger', 'Flame-grilled prime beef patty topped with double cheddar, fresh lettuce, tomatoes, dill pickles, and secret chef sauce.', 189.00, 'Burgers', FALSE, 'images/menu/burgers/classic-burger.jpg', 4.6, TRUE),
(2, 'Crispy Chicken Burger', 'Crispy batter-fried chicken breast, creamy mayo, fresh lettuce, and pickles on a toasted bun.', 199.00, 'Burgers', FALSE, 'images/menu/burgers/chicken-burger.jpg', 4.7, TRUE),
(2, 'Crunchy Paneer Burger', 'Crispy batter-fried paneer patty seasoned with spices, topped with creamy coleslaw, lettuce, and spicy mayo.', 169.00, 'Burgers', TRUE, 'images/menu/burgers/veg-burger.jpg', 4.4, TRUE),
(2, 'Loaded Cheese Fries', 'Golden hand-cut potato fries drowned in melted liquid cheddar cheese sauce and garnished with spicy jalapeño bits.', 149.00, 'Sides', TRUE, 'images/menu/starters/french-fries.jpg', 4.5, TRUE),
(2, 'Spicy Peri Peri Fries', 'Crispy golden fries tossed in hot peri peri spices and served with a garlic aioli dip.', 159.00, 'Sides', TRUE, 'images/menu/starters/peri-peri-fries.jpg', 4.6, TRUE),
(2, 'Fiery Grilled Chicken', 'Half-grilled chicken marinated in fiery herbs and spices, served with fresh grilled onions.', 279.00, 'Starters', FALSE, 'images/menu/main-course/grilled-chicken.jpg', 4.8, TRUE),
(2, 'Grilled Chicken Wrap', 'Toasted tortilla loaded with grilled chicken strips, shredded lettuce, tomatoes, and garlic cream sauce.', 179.00, 'Mains', FALSE, 'images/menu/wraps/chicken-wrap.jpg', 4.5, TRUE),
(2, 'Garden Veggie Wrap', 'Soft tortilla wrap filled with crispy lettuce, cucumber slices, shredded carrots, tomatoes, and hummus.', 149.00, 'Mains', TRUE, 'images/menu/wraps/veg-wrap.jpg', 4.4, TRUE);

-- Populate Sample Menus for Meghana Foods (restaurant_id = 3)
INSERT INTO menu (restaurant_id, food_name, description, price, category, is_veg, image, rating, is_available)
VALUES 
(3, 'Hyderabadi Chicken Biryani', 'Fragrant premium basmati rice layered with succulent marinated chicken, saffron, and cooked on slow fire dum.', 289.00, 'Biryani', FALSE, 'images/menu/biryani/chicken-biryani.jpg', 4.8, TRUE),
(3, 'Spicy Mutton Biryani', 'Authentic long-grain basmati rice layered with tender mutton chunks and aromatic spices.', 349.00, 'Biryani', FALSE, 'images/menu/biryani/mutton-biryani.jpg', 4.9, TRUE),
(3, 'Paneer Tikka Biryani', 'Fragrant basmati rice layered with grilled paneer cubes, saffron, mint, and special spices.', 259.00, 'Biryani', TRUE, 'images/menu/biryani/paneer-biryani.jpg', 4.5, TRUE),
(3, 'Spicy Chicken 65', 'Deep-fried spicy, red chicken pieces tossed with curry leaves, green chilies, and tangy yogurt base.', 199.00, 'Starters', FALSE, 'images/menu/starters/chicken-65.jpg', 4.6, TRUE);

-- Populate Sample Menus for Truffles Cafe (restaurant_id = 4)
INSERT INTO menu (restaurant_id, food_name, description, price, category, is_veg, image, rating, is_available)
VALUES 
(4, 'Classic Beef Burger', 'Flame-grilled prime beef patty topped with cheddar, fresh lettuce, tomatoes, and Truffles special sauce.', 219.00, 'Burgers', FALSE, 'images/menu/burgers/classic-burger.jpg', 4.6, TRUE),
(4, 'Crispy Chicken Burger', 'Crispy batter-fried chicken breast, creamy coleslaw, lettuce, and spicy mayo on a brioche bun.', 199.00, 'Burgers', FALSE, 'images/menu/burgers/chicken-burger.jpg', 4.7, TRUE),
(4, 'Choco Lava Cake', 'Oven-baked chocolate cake with a rich, warm, liquid chocolate center that flows out when cut.', 139.00, 'Desserts', TRUE, 'images/menu/desserts/chocolate-lava-cake.jpg', 4.8, TRUE),
(4, 'Royal Rose Falooda', 'Delicious rose-flavored milk drink topped with sweet basil seeds, vermicelli, jelly, and vanilla ice cream.', 149.00, 'Desserts', TRUE, 'images/menu/desserts/falooda.jpg', 4.5, TRUE);

-- Populate Sample Menus for Empire Restaurant (restaurant_id = 5)
INSERT INTO menu (restaurant_id, food_name, description, price, category, is_veg, image, rating, is_available)
VALUES 
(5, 'Schezwan Chicken Fried Rice', 'Fiery hot fried rice tossed with bell peppers, green onions, and loaded with egg, chicken, and spicy Schezwan sauce.', 189.00, 'Mains', FALSE, 'images/menu/rice/chicken-fried-rice.jpg', 4.5, TRUE),
(5, 'Veg Fried Rice', 'Stir-fried basmati rice with finely chopped carrots, beans, peas, and spring onions with light soy sauce.', 169.00, 'Mains', TRUE, 'images/menu/rice/veg-fried-rice.jpg', 4.3, TRUE),
(5, 'Hakka Chicken Noodles', 'Stir-fried noodles with crisp julienned vegetables, chicken strips, soy sauce, and white pepper.', 179.00, 'Mains', FALSE, 'images/menu/noodles/chicken-noodles.jpg', 4.4, TRUE),
(5, 'Butter Chicken Masala', 'Tender tandoori chicken cooked in a rich, creamy, and mildly sweet tomato-based butter gravy.', 299.00, 'Mains', FALSE, 'images/menu/main-course/butter-chicken.jpg', 4.8, TRUE),
(5, 'Gobi Manchurian Dry', 'Deep-fried cauliflower florets tossed in a sweet, tangy, and slightly spicy indo-chinese sauce.', 159.00, 'Starters', TRUE, 'images/menu/starters/gobi-manchurian.jpg', 4.4, TRUE);
