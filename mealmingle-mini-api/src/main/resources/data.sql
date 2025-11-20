INSERT INTO recipe (id, title, description, steps)
VALUES (1, 'Avocado Toast', 'Simple breakfast', 'Toast bread; mash avocado; season.');

INSERT INTO recipe_ingredient (recipe_id, ingredient_name, quantity, unit)
VALUES (1, 'Bread slice', 2, 'pcs'),
       (1, 'Avocado', 1, 'pcs'),
       (1, 'Salt', 1, 'pinch');