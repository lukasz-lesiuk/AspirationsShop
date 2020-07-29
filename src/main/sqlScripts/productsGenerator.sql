DROP TABLE IF EXISTS products;

CREATE TABLE products (
    product_id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    cost int NOT NULL,
    quanity int NOT NULL,
    category character varying(255) NOT NULL,
);

INSERT INTO customers VALUES
  (1, 'Always Welcome', 'Always Welcome Sims will act more at home when at others residence, and the host wont mind at all!',500,10,'Basic'),
  (2, 'Gym Rat', 'Gym Rats build Fun and dont lose Hygiene while exercising!',500,20,'Basic'),
  (3, 'Observant', 'Observant Sims learn the traits of others just by meeting them!', 500,100,'Basic'),
  (4, 'Speed Cleaner', 'Speed Cleaners tidy up much faster!', 500, 150,'Basic'),
  (5, 'Mentor', 'Once Mentors reach level 10 in certain skills, the Mentor interaction becomes available and they can help other Sims improve their skills even more quickly!', 1000, 100,'Basic'),
  (6, '	Morning Sim', '	Morning Sims build extra skill in the morning.',1000,48,'Basic'),
  (7, '	Night Owl', 'Night Owls build extra skill at night.',1000,896, 'Basic'),
  (8, 'Speed Reader', '	Speed Readers read books faster than your average Sim.', 1000, 89,'Basic'),
  (9, '	Free Services', 'All single use service requests are free.',1500,47, 'Basic'),
  (10, 'Marketable', 'Marketable Sims sell items they have crafted for more Simoleons!', 1500,23, 'Basic')
  (11, 'Creative Visionary', 'Creative Visionaries have a higher chance of painting and writing masterworks.',2000,110,'Basic'),
  (12, 'Entrepreneurial', '	Enterpreneurial Sims are more likely to get promoted in their careers.',2000,210,'Basic'),
  (13, 'Frugal', '	A Frugal Sims household has reduced bills.', 2000,142,'Basic'),
  (14, 'Independent', 'Independent Sims Social Need decays slower.', 2000, 101,'Basic'),
  (15, 'Shameless', 'Shameless Sims will never get Embarrassed!', 2000, 18,'Basic'),
  (16, 'Steel Bladder', 'Sims with a Steel Bladder rarely have to pay attention to their Bladder Need!',2000,78,'Basic'),
  (17, 'Beguiling', 'Beguiling Sims can put anyone in a flirty mood with just one look.',2500,6, 'Basic'),
  (18, 'Antiseptic', 'Antiseptic Sims Hygiene Need decays much slower.', 3000, 819,'Basic'),
  (19, 'Carefree', 'Carefree Sims will never get Tense.',3000,473, 'Basic'),
  (20, 'Connections', 'Sims with Connections start all careers several levels ahead!', 3000,23, 'Basic')
  (21, 'Fertile', 'Fertile Sims have an easier time when trying for a baby, and a higher chance of twins or triplets.',3000,34,'Basic'),
  (22, 'Great Kisser', 'Great Kissers have amazing success when kissing, and increase charisma with every kiss.	',3000,473, 'Basic'),
  (23, 'Hardly Hungry', 'Sometimes Sims just dont have much of an appetite.',4000,473, 'Basic'),
  (24, 'Professional Slacker', 'Professional Slackers have no fear of being demoted or fired.',4000,493, 'Basic'),
  (25, 'Savant', 'Savants gain all skills much faster!',4000,473, 'Basic'),
  (26, 'Seldom Sleepy', 'Seldom Sleepy Sims need less sleep than other Sims.',4000,4743, 'Basic'),
  (27, 'Super Green Thumb', 'Super Green Thumbs find that the plants they garden have amazing vitality!',4000,1473, 'Basic'),
  (28, 'Needs No One', 'Sims that Need No One never need to socialize.',5000,4703, 'Basic'),
  (29, 'Forever Fresh', 'Forever Fresh Sims never need to shower or bathe!',8000,4, 'Basic'),
  (30, 'Forever Full', 'Forever Full Sims never need to eat!',10000,3, 'Basic'),
  (31, 'Never Weary', 'Never Weary Sims never need sleep.',10000,7, 'Basic'),
  (32, 'Great Storyteller', 'Great Storytellers are more successful at telling bigger and better stories!',500,700, 'Outdoor Retreat'),
  (33, 'Stoves and Grills Master', 'Stoves and Grills Masters have a chance to create Impeccable Quality consumables that use stoves and grills!',1500,71, 'Outdoor Retreat'),
  (34, 'Incredibly Friendly', 'Incredibly Friendly Sims are immediately liked when met with a nice introduction!',2000,117, 'Outdoor Retreat'),
  (35, 'Cold Acclimation', 'Sims with Cold Acclimation arent affected as much by chilly weather, but can still feel the effects.',500,1117, 'Seasons'),
  (36, 'Heat Acclimation', 'Sims with Heat Acclimation arent affected as much by hot weather, but can still feel the effects.',500,2117, 'Seasons'),
  (37, 'Waterproof', 'Waterproof Sims do not get wet in the rain.',500,1917, 'Seasons'),
  (38, 'Storm Chaser', 'Storm Chaser Sims love stormy weather and thrive in terrifying conditions.',1000,1717, 'Seasons'),
  (39, 'Iceproof', 'Iceproof Sims arent negatively affected by cold temperatures and enjoy the cold.',2000,817, 'Seasons'),
  (40, 'Heatproof', 'Heatproof Sims arent negatively affected by hot temperatures and enjoy the heat.',2000,717, 'Seasons'),
  (41, 'Money Tree', 'A Money Tree for your Sims to grow their riches.',5000,17, 'Miscellaneous'),
  (42, 'Mermadic Kelp', 'Eat this to turn into a Mermaid!',500,7617, 'Island Living'),
  (43, 'Potion of Curse Cleansing', 'A potion to remove all curses from a Sim.',500,7697, 'Realm of Magic'),
  (44, 'Quirk-B-Gone', 'This potion removes one Fame Quirk of your choosing from a Celebrity Sim.',250,7647, 'Get Famous'),
  (45, 'Instant Fun', 'Instantly fills the Fun Need!',100,7367, 'Potions'),
  (46, 'Instant Hygiene', 'Instantly fills the Hygiene Need!',100,7627, 'Potions'),
  (47, 'Confident Potion', 'Drink this to temporarily make your Sim Confident!',200,7167, 'Potions'),
  (48, 'Energized Potion', 'Drink this to temporarily make your Sim Energized!',200,5767, 'Potions'),
  (49, 'Flirty Potion', 'Drink this to temporarily make your Sim Flirty!',200,67, 'Potions'),
  (50, 'Focused Potion', 'Drink this to temporarily make your Sim Focused!',200,77, 'Potions'),
  (51, 'Happy Potion', 'Drink this to temporarily make your Sim Happy!',200,47, 'Potions'),
  (52, 'Inspired Potion', 'Drink this to temporarily make your Sim Inspired!',200,76, 'Potions'),
  (53, 'Sleep Replacement', 'Instantly fills the Energy Need!',400,767, 'Potions'),
  (54, 'Moodlet Solver', 'Instantly removes all negative moodlets from your Sim.',500,7671, 'Potions'),
  (55, 'Insta-Large', 'Drink this to become a bigger you!',750,157, 'Potions'),
  (56, 'Insta-Lean', 'The most efficient diet beverage on the market.',750,337, 'Potions'),
  (57, 'Potion of Youth', 'Drink this to turn back the clock on your Sims current age.',1500,200, 'Potions'),
  (58, 'Re-Traiting Potion', '	Drinking this will allow you to reset all your Traits!',5000,300, 'Potions')


