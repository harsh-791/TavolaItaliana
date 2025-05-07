-- Insert sample chefs
INSERT INTO chefs (name, email, specialization, years_of_experience) VALUES
('Gordon Ramsay', 'gordon@example.com', 'British Cuisine', 30),
('Jamie Oliver', 'jamie@example.com', 'Italian Cuisine', 25),
('Wolfgang Puck', 'wolfgang@example.com', 'French Cuisine', 35),
('Bobby Flay', 'bobby@example.com', 'American Cuisine', 28),
('Ming Tsai', 'ming@example.com', 'Asian Fusion', 20),
('Emeril Lagasse', 'emeril@example.com', 'Cajun Cuisine', 32),
('Rachael Ray', 'rachael@example.com', 'Quick Meals', 15),
('Mario Batali', 'mario@example.com', 'Italian Cuisine', 27),
('Thomas Keller', 'thomas@example.com', 'French Cuisine', 40),
('Alton Brown', 'alton@example.com', 'Food Science', 22);

-- Insert sample dishes
INSERT INTO dishes (name, description, price, category, chef_id) VALUES
('Beef Wellington', 'Classic British dish with beef fillet wrapped in puff pastry', 45.99, 'Main Course', 1),
('Spaghetti Carbonara', 'Traditional Italian pasta with eggs, cheese, pancetta, and black pepper', 24.99, 'Pasta', 2),
('Truffle Risotto', 'Creamy Italian rice dish with black truffles', 32.99, 'Rice', 2),
('Coq au Vin', 'French braised chicken in wine, lardons, mushrooms, and onions', 28.99, 'Main Course', 3),
('Smoked Salmon Pizza', 'Innovative pizza with smoked salmon and crème fraîche', 26.99, 'Pizza', 3),
('Southwestern Burger', 'Spicy burger with chipotle mayo and avocado', 18.99, 'Burger', 4),
('Pad Thai', 'Stir-fried rice noodles with eggs, tofu, and peanuts', 16.99, 'Noodles', 5),
('Shrimp Etouffee', 'Cajun-style shrimp in a rich, spicy sauce', 29.99, 'Seafood', 6),
('30-Minute Pasta', 'Quick and easy pasta with fresh vegetables', 19.99, 'Pasta', 7),
('Osso Buco', 'Braised veal shanks in white wine and broth', 34.99, 'Main Course', 8),
('Roast Chicken', 'Perfectly roasted chicken with herbs', 27.99, 'Main Course', 9),
('Mac and Cheese', 'Creamy macaroni with three cheeses', 15.99, 'Pasta', 9),
('Good Eats Meatloaf', 'Classic meatloaf with a twist', 22.99, 'Main Course', 10),
('Chocolate Souffle', 'Light and airy chocolate dessert', 12.99, 'Dessert', 3),
('Tiramisu', 'Classic Italian dessert with coffee and mascarpone', 9.99, 'Dessert', 2); 