package com.alamir.drinkdetail.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alamir.drinkdetail.adapter.DBAdapter;


/**
 * @Class_name:  DataBase
 * @Description: this class create and control the database
 * @Version:     0.0
 * @Created_by:  yousef alamair
 * @Application: Drink Details
 */
public class DataBase extends SQLiteOpenHelper {
    public static final String Dbname = "DRINK_DETAILS";
    public static final int as = 1;

    public DataBase(Context context) {
        super(context, Dbname, null, as);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLe1 = "create table if not exists Recipe (RecipeID int(200) PRIMARY KEY ,name text, description text, preparation text,categories text,imagephath text);";
        String TABLe2 = "create table if not exists Ingredients ( name text, quantity text,recipeid int(200), FOREIGN KEY (recipeid)  REFERENCES Recipe (RecipeID));";
        String TABLe3 = "create table if not exists Favorite (FavoriteID INTEGER PRIMARY KEY AUTOINCREMENT,boolFavorite INTEGER DEFAULT 0,recipeid int(200), FOREIGN KEY (recipeid) REFERENCES Recipe (RecipeID));";
        db.execSQL(TABLe1);
        db.execSQL(TABLe2);
        db.execSQL(TABLe3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query1;
        String query2;
        String query3;
        query1 = "DROP TABLE IF EXISTS "+ List.Prodactdetails.TABALE_NAME +";";
        query2 = "DROP TABLE IF EXISTS "+ List.Prodactdetails.TABALE_NAME2 +";";
        query3 = "DROP TABLE IF EXISTS "+ List.Prodactdetails.TABALE_NAME3 +";";

        db.execSQL(query1);
        db.execSQL(query2);
        db.execSQL(query3);
        onCreate(db);
    }

    /**
     * @method_name: addToDataBase
     * @Description: add item to recipe and favorite tables {@link DataBase} .
     *
     * @param:       int boolFavorite , int recipeId
     * @return:      none
     */
    public void addToDataBase(int boolFavorite,int recipeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM  "+ List.Prodactdetails.TABALE_NAME+";",null);
        if(cursor.getCount()==0 && boolFavorite==0 && recipeId==0) {
            db.execSQL("INSERT INTO Recipe VALUES (1,'Best-Ever Hot Chocolate','Nice hot chocolate with delicious taste','1.\tIn a small saucepan over medium heat, bring milk to simmer. Whisk in sugar and cocoa powder and stir until no lumps remain. Stir in chocolate chips and vanilla and cook, stirring occasionally, until chocolate is completely melted. Turn off heat.\n" +
                    "2.\tPour hot chocolate into mugs, then top with whipped cream and chocolate shavings.\n','cocoa','best_ever_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (2,'Brownie Batter Hot Chocolate','Yes, it is as AMAZING as it sounds. Pour the extra sauce over ice cream. Or just make a ton of hot chocolate','1.\tIn a small saucepan over medium-low heat, combine heavy cream, corn syrup, brown sugar, brownie mix, vanilla and 1/2 cup chocolate chips. Cook until melted, stirring often. Remove from heat and add remaining 1/2 cup chocolate chips. Stir until smooth.\n" +
                    "2.\tTo make hot chocolate, stir into warmed milk.\n','cocoa','brownie_batter_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (3,'Mexican Hot Chocolate','Cinnamon makes a WORLD of difference here and we love it','1.\tIn a small saucepan over medium heat, whisk to combine milk, cinnamon, and cayenne, if using. Heat, stirring occasionally, until bubbles begin to appear where the milk meets the pan.\n" +
                    "2.\tRemove pan from heat and whisk in chocolate, vanilla, and salt until chocolate is melted. \n" +
                    "3.\tDivide into mugs and top with marshmallows, a sprinkle of cinnamon, and chopped chocolate before serving.\n','cocoa','mexican_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (4,'Rumchata Hot Cocoa','A match made in heaven','1.\tBring milk to a simmer in small saucepan over medium heat. Whisk in sugar and cocoa powder and stir until no lumps remain. Stir in chocolate chips and vanilla and cook, stirring occasionally, until the chocolate is completely melted. Stir in Rumchata, then turn off heat.\n" +
                    "2.\tPour caramel into a small dish. Dip cups in the caramel to coat the rim. Pour hot chocolate into each glass, then top with whipped cream, a sprinkle of cinnamon sugar, more caramel, and a cinnamon stick.\n','cocoa','rumchata_hot_cocoa')");
            db.execSQL("INSERT INTO Recipe VALUES (5,'Butterbeer Hot Cocoa','Butterbeer everything','1.\tIn a medium saucepan over medium heat, bring milk to a simmer. Turn off heat, then add white chocolate chips, butterscotch chips, melted butter and vanilla. Stir until white chocolate and butterscotch chips have melted. Remove from heat.\n" +
                    "2.\tCarefully pour hot cocoa into mugs. Top with whipped cream and sprinkles then drizzle with butterscotch syrup. Serve.\n','cocoa','butterbeer_hot_cocoa')");
            db.execSQL("INSERT INTO Recipe VALUES (6,'Andes Mint HotChata','It may taste innocent, but there is a lot of booze in there!','1.\tIn a small saucepan over medium heat, bring milk to a simmer. Whisk in sugar and cocoa powder and stir until no lumps remain. Stir in chocolate chips and cook, stirring occasionally, until the chocolate is completely melted. Turn heat to low and stir in in Rumchata and schnapps. Warm to desired temperature, then remove from heat and pour into mugs.\n" +
                    "2.\tTop each mug with whipped cream, a drizzle of chocolate sauce, chopped Andes, and a whole Andes, and serve.\n','cocoa','andes_mint_hotchata')");
            db.execSQL("INSERT INTO Recipe VALUES (7,'Bailey is Frozen Hot Chocolate','Boozy (frozen) cocoa for the win','1.\tFill a blender with ice, then pour over chocolate milk, hot cocoa mix, and Baileys. Blend until frothy. \n" +
                    "2.\tDrizzle chocolate syrup inside each glasses , then pour over frozen hot chocolate. Top with whipped cream and chocolate shavings.\n','cocoa','bailey_s_hrozen_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (8,'Pumpkin Spice Hot Cocoa','Fall comfort in a mug','1.\tIn a medium saucepan over medium heat, combine milk, pumpkin puree, pumpkin spice and vanilla. Heat, whisking occasionally, until the mixture comes to a low simmer. Stir in white chocolate chips and cook until the chocolate has melted. Remove from heat.\n" +
                    "2.\tLadle into mugs then top with whipped cream and sprinkle with cinnamon sugar. Garnish with cinnamon sticks, if desired.\n','cocoa','pumpkin_spice_hot_cocoa')");
            db.execSQL("INSERT INTO Recipe VALUES (9,'Mudslide Hot Chocolate','This is not your average hot chocolate','1.\tBring milk to a simmer in small saucepan over medium heat. Whisk in cocoa powder and sugar and stir until no lumps remain. Stir in chocolate chips and vanilla and cook, stirring occasionally, until the chocolate is completely melted. Turn off heat.\n" +
                    "2.\tPour chocolate sauce in a small dish. Dip cups in the chocolate to rim the cup. Pour hot chocolate into each glass, then top with whipped cream. Garnish with crushed Oreos and drizzle more with chocolate sauce.\n','cocoa','mudslide_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (10,'Elsa is Hot Chocolate','We can it let it go','1.\tIn a small saucepan over medium heat, heat milk until it starts to steam. Turn off heat and add white chocolate chips, vanilla, and 3 drops of blue food coloring. Whisk until the chocolate is melted.\n" +
                    "2.\tServe immediately, and garnish with whipped cream, sprinkles and crushed blue rock candy.\n','cocoa','elsa_s_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (11,'Santa Clause-Inspired Hot Cocoa','Dark chocolate with marshmallows','1.\tIn a medium-sized saucepan, warm milk over medium heat, stirring occasionally so it starts to bubble at the edges but doesn’t burn.\n" +
                    "2.\tWhisk in the chocolate and sugar, stirring until combined (about 2 minutes).\n" +
                    "3.\tKeep it at a simmer and lower the temperature if it starts to boil. Once it’s thick enough to coat the back of a spoon, remove from heat.\n" +
                    "4.\tPour into mugs, topping with marshmallows and shaved chocolate pieces. Garnish with a candy cane.\n','cocoa','santa_clause_inspired_hot_cocoa')");
            db.execSQL("INSERT INTO Recipe VALUES (12,'Chocolate Dippers','Upgrade your favorite hot drink with these customizable hot chocolate dippers','1.\tGrease mini muffin pan cups with nonstick cooking spray.\n" +
                    "2.\tDivide melted chocolate between muffin tins.\n" +
                    "3.\tTop as desired (see variations) then chill in refrigerator until set, about 30 minutes.\n','cocoa','chocolate_dippers')");
            db.execSQL("INSERT INTO Recipe VALUES (13,'Hot Chocolate Float','Hot cocoa is the new hot fudge','1.\tIn a small saucepan over medium heat, bring milk to a simmer. Whisk in sugar and cocoa powder and stir until no lumps remain. Stir in chocolate chips and vanilla and cook, stirring occasionally, until the chocolate is melty. Turn off heat.\n" +
                    "2.\tPour hot fudge in a small dish. Dip the tops of mugs in hot fudge to rim the cup, then pour hot chocolate into each mug. Top with a scoop of ice cream and garnish with chocolate shavings.\n','cocoa','hot_chocolate_float')");
            db.execSQL("INSERT INTO Recipe VALUES (14,'Chocolate Covered Cherry Hot Chocolate','This warm, cocktail will be the cherry on top of the whole winter season','1.\tAdd vodka, cooled hot chocolate, maraschino cherries, and cherry juice to a blender. Blend mixture then reheat hot chocolate.\n" +
                    "2.\tAdd heated hot chocolate to serving glass. Garnish with whipped cream, chocolate sauce, and a cherry.\n','cocoa','chocolate_covered_cherry_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (15,'Oreo Mint Hot Chocolate','This drink has everything you could possibly ever want: Oreos, chocolate, and mint vodka','1.\tAdd mint vodka, white hot chocolate, and Oreo mint cookies to a blender. Blend mixture then reheat.\n" +
                    "2.\tAdd heated hot chocolate to serving glass. Garnish with Oreo and marshmallows.\n','cocoa','oreo_mint_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (16,'Pecan Pie Hot Chocolate','Christmas and Thanksgiving come together in this killer hot chocolate','1.\tAdd bourbon, cooled hot chocolate, pecans, and pecan pie filling to a blender. Blend mixture, then reheat.\n" +
                    "2.\tAdd heated hot chocolate to serving glass. Garnish with whipped cream, pecan pie filling, and pecans.\n','cocoa','pecan_pie_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (17,'Pumpkin Spice Eggnog White Hot Chocolate','Pumpkin spice eggnog makes this hot chocolate recipe the ultimate holiday hybrid','1.\tAdd white hot chocolate, rum, and eggnog into a sauce pan. Heat mixture, then add to serving glass.\n" +
                    "2.\tGarnish with marshmallows and cinnamon stick.\n','cocoa','pumpkin_spice_eggnog_white_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (18,'Salted Caramel White Hot Chocolate','Get a little salty with your hot chocolate','1.\tAdd caramel to bottom of serving glass.\n" +
                    "2.\tCombine Bailey is, white hot chocolate, and salt. Add mixture to serving glass.\n" +
                    "3.\tGarnish with whipped cream, caramel sauce, and a pinch of salt.\n','cocoa','salted_caramel_white_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (19,'Toasted Coconut Hot Chocolate','Give this holiday favorite an island getaway vibe with toasted coconut and rum','1.\tCombine rum, half of toasted coconut, and white-hot chocolate mixture.\n" +
                    "2.\tServe garnished with whipped cream and toasted coconut.\n','cocoa','toasted_oconut_hot_chocolate')");
            db.execSQL("INSERT INTO Recipe VALUES (20,'Vanilla Latte Hot Chocolate','This hot coffee it is the best with vanilla','1.\tCombine Kahlua, Starbucks Via Vanilla Latte, and hot chocolate to a mixing glass.\n" +
                    "2.\tServe garnished with whipped cream and cinnamon.\n','cocoa','vanilla_latte')");
            db.execSQL("INSERT INTO Recipe VALUES (21,'Mojito','is one of the most refreshing cocktails you’ll ever have! Made with white rum, lime juice and fresh mint leaves, this easy drink is a definite crowd pleaser','Add the white rum to a highball glass. Add 8 – 10 mint leaves and sugar syrup and lime juice.Muddle with bar spoon. Add crushed ice and a splash of soda. Mix drink down with bar spoon. Taste. Top up with more crushed ice. Slap 2 mint sprigs to release essence and put into drink. Add small splash of soda and straw','coocktails','mojito');");
            db.execSQL("INSERT INTO Recipe VALUES (22,'Martini','is one of the most iconic and classic cocktails around','Chill martini glass with soda water and ice. Fill mixing glass to top with ice. Add the dry vermouth to the mixing glass, giving a small stir to coat the ice with the vermouth. Drain out glass, leaving just the coating on the ice. Add the gin to the mixing glass. Stir for 15 seconds, always making sure that the glass is full to the brim with ice. Taste. Fine strain into chilled martini glass. Zest with lemon peel and add twist unto drink','coocktails','martini');");
            db.execSQL("INSERT INTO Recipe VALUES (23,'Daiquiri','Simple drinks can often be the hardest to get right; with only a few ingredients','Chill coupe glass with soda water. Add sugar to mixing tin and then add white rum and lime juice. Add a little cracked ice to the bottom then fill the rest of the tin with ice. Shake for 10 – 15 seconds, taste and then fine strain into coupe glass. Garnish glass with lime slice.','coocktails','daiquiri');");
            db.execSQL("INSERT INTO Recipe VALUES (24,'old fashioned','One of the great classic bourbon cocktails','Begin by chilling rocks glass with ice and soda water. Add 1 bar spoon of sugar into mixing glass. Add 2 dashes of Angostura bitters and 1 dash of orange bitters. Muddle to break down the sugar into a paste. Add the bourbon into a mixing glass and fill the rest of the mixing glass with ice. Stir with bar spoon for at least 20 seconds. Top up with ice and taste. Put ice into rocks glass and strain the drink into the glass. Zest the glass with orange peel and add twist into drink','coocktails','old_fashioned');");
            db.execSQL("INSERT INTO Recipe VALUES (25,'pina colada ','It is creamy, tart, and sweet and just what the doctor ordered','Chill highball glass. Add all ingredients to mixing tin and fill with ice. Shake vigorously for 10 – 15 seconds. Fill highball glass with fresh ice and strain drink into glass. Garnish with pineapple slice, add straw and serve.','coocktails','pina_colada');");
            db.execSQL("INSERT INTO Recipe VALUES (26,'CUBA LIBRE','The national drink of Cuba celebrating its independence. There’s more to it than just cola, rum and lime','Add rum into rocks glass and squeeze the lime wedges over the top. Give quick churn with bar spoon and top up with ice. Fill to brim with cola and add in lime wedge as garnish','coocktails','cuba_libre');");
            db.execSQL("INSERT INTO Recipe VALUES (27,'MAI-TAI','is one of the most famous Tiki drinks in the world.','Add all ingredients into mixing tin. Fill mixing tin with cubed ice. Shake very hard for 10 seconds to achieve desired dilution. Fill goblet style glass with crushed ice and strain mixture into glass. Add a short straw, mint and a splash of demerara rum','coocktails','mai_tai');");
            db.execSQL("INSERT INTO Recipe VALUES (28,'A ESPRESSO MARTINI','Quite simply Salcombe Gin is ultimate espresso martini','Add all ingredients into a Boston cocktail shaker and fill with ice. Shake hard for up to 30 seconds to achieve the desired consistency of the drink. Double strain the contents of the shaker into a chilled martini glass. Garnish with 3 coffee beans','coocktails','espresso_martini');");
            db.execSQL("INSERT INTO Recipe VALUES (29,'BRAMBLE','is the perfect summer sipper and it’s wonderfully refreshing and an easy drinker. It might look fancy, but it’s incredibly easy to make this classic cocktail at home. ','Add all ingredients (except creme de mure) into mixing glass. Fill mixing glass with cubed ice and fill rocks glass with crushed ice. Shake for 10 seconds. Strain mixture into glass and top up with crushed ice. Pour creme de mure over drink using bar spoon. Garnish with 2 lemon slices and blackberry','coocktails','bramble');");
            db.execSQL("INSERT INTO Recipe VALUES (30,'TEQUILA SUNRISE','is sweet and smooth and has the gorgeous color gradient of a summer sunrise!','Add tequila into highball glass. Top up with ice. Add freshly squeezed orange juice , squeeze of lime and dash of grenadine syrup.','coocktails','tequila_sunrise');");
            db.execSQL("INSERT INTO Recipe VALUES (31,'AMERICANO','This delightful summer cocktail  and famous drinks ','Begin by topping up long glass with cubed ice. Add both alcohols and top up with soda water. Stir with bar spoon to make sure ingredients are mixed properly. Garnish with a lemon zest and add 2 long straws','coocktails','americano');");
            db.execSQL("INSERT INTO Recipe VALUES (32,'ACCOMPLICE','For a romantic drink idea, or a fruity cocktail that is a tasty beach drink','Muddle 2 strawberries in a Boston Mixing Glass. Add vodka, simple syrup and lemon juice. Fill with ice and shake for 10 seconds. Double strain into a chilled martini glass. Top-up with Champagne and garnish the glass with a strawberry.','coocktails','accomplice');");
            db.execSQL("INSERT INTO Recipe VALUES (33,'SOUTHSIDE','t’s a simple cocktail recipe – Gin-based, mint-kissed with a touch of citrus','Add ingredients to a tall glass and stir to begin dissolving the sugar. Add crushed ice and muddle with a bar spoon. Top up with crushed ice, add 2 straws and garnish with mint','coocktails','southside');");
            db.execSQL("INSERT INTO Recipe VALUES (34,'A BOSTON SOUR','is so easy to make with only a few ingredients','Put all ingredients into Boston shaker with ice. Prep your lemon zest garnish. Shake hard for about 15 seconds. Strain with a Hawthorne strainer into ice-filled Rocks glass. Zest the drink and the glass using the lemon zest.','coocktails','oston_sour');");
            db.execSQL("INSERT INTO Recipe VALUES (35,'A CHEEKY VIMTO','It is very easy homemade drink with little INGREDIENTS','Mix both ingredients with ice in a pub glass.','coocktails','cheeky_vimto');");
            db.execSQL("INSERT INTO Recipe VALUES (36,'BERRY NICE','Fruity flirty and fabulous','Add the blackberries and vodka to the mixing tin and muddle well. Add the liqueur and lemon juice and lots of ice and shake well. Fine strain into Collins glass over ice and top up with ginger beer','coocktails','berry_nice');");
            db.execSQL("INSERT INTO Recipe VALUES (37,'CANTARITO','It is an  essential drink recipe that everyone should know and one of the best brunch drink ','Rim a Collins glass with salt. Fill the glass with ice and add the tequila and juices.Top it off with grapefruit soda. Garnish with fruit wedges.','coocktails','cantarito');");
            db.execSQL("INSERT INTO Recipe VALUES (38,'CHAMPAGNE','They’re easy to make, and add class and style to any celebration','Add 1 heaped bar spoon of sugar into a champagne glass. Add bitters and brandy. Churn with bar spoon to break down the sugar. Top off with champagne. Careful of the fizz!','coocktails','champagne_cocktail');");
            db.execSQL("INSERT INTO Recipe VALUES (39,'SIDECAR','It’s simple, sophisticated, and totally refreshing.','Chill a coupe glass with soda water. Add all the ingredients to a mixing tin and dry shake for 10 seconds. Add ice to the tin and shake vigorously for 10 seconds. Taste. Fine strain ingredients into chilled coupe glass. Zest orange peel into the drink and place orange twist into glass.','coocktails','sidecar');");
            db.execSQL("INSERT INTO Recipe VALUES (40,'BAJA GOLD','drink swaps out lime juice for fresh pineapple juice','Chill a martini glass with ice and soda water. Add all ingredients into a mixing glass. Fill up mixing glass with cubed ice. Shake hard for 10 seconds. Double strain mixture into chilled martini glass. Garnish with lime wedge.','coocktails','baja_gold');");
            db.execSQL("INSERT INTO Recipe VALUES (41,'Pina Colada Protein Smoothie','Get a boost of energy and feel like you are on vacay','1.\tCombine coconut milk, banana, pineapple, mango and protein powder in a blender and blend on high until smooth.\n" +
                    "2.\tGarnish with a wedge of pineapple and serve.\n','milkshake','pina_colada_protein_smoothie')");
            db.execSQL("INSERT INTO Recipe VALUES (42,'Bourbon Milk Punch','This boozy milkshake packs some punch.','1.\tIn a shallow dish, rim two glasses with caramel and cinnamon sugar and set aside.\n" +
                    "2.\tIn a blender, combine ice cream, bourbon, vanilla, honey, and cinnamon and blend until smooth. Pour mixture into glasses and garnish with whipped cream and ground cinnamon\n','milkshake','bourbon_milk_punch')");
            db.execSQL("INSERT INTO Recipe VALUES (43,'Creamsicle Milkshake Shots','These milkshake shots are creamy, fruity, and boozy','1.\tAdd rum, heavy cream, ice cream, and fruit to a blender and blend until smooth.\n" +
                    "2.\tPour into serving glasses. Cheers!\n','milkshake','creamsicle_milkshake_shots')");
            db.execSQL("INSERT INTO Recipe VALUES (44,'Firecracker Milkshake','Allow this milkshake to bring a firework explosion of flavor to your mouth.','1.\tSoften ice cream to a soft serve/yogurt texture. Evenly divide ice cream into three bowls.\n" +
                    "2.\tPlace red food coloring in one bowl and blue food coloring in the other, using a whisk beat each bowl until color is well blended.\n" +
                    "3.\tLayer bottom of glass with red ice cream, then plain vanilla and blue ice cream, using long spoon or knife drag it from the bottom to the top, stirring in between to mix the color (do not overmix).\n" +
                    "4.\tOptional decorating idea: Finish with whipped cream and red, white and blue confetti\n','milkshake','firecracker_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (45,'Capn Crunch Milkshakes',' your milkshake with a delicious vanilla ice cream!','1.\tCombine cereal and milk in a large measuring cup (or medium bowl). Stir, then refrigerate for at least 20 minutes and up to 1 hour to infuse the cereal flavor.\n" +
                    "2.\tAdd infused cereal-milk mixture to a blender along with 2 tablespoons peanut butter and as much as ice cream as desired. Blend until thick and smooth.\n" +
                    "3.\tTo decorate serving glasses, spread remaining peanut butter along the rims of the cups, then roll in Cap’n Crunch crumbles. Pour milkshake into glasses, top with whipped cream and drizzle with melted peanut butter and more cereal.\n','milkshake','cap_n_crunch_milkshakes')");
            db.execSQL("INSERT INTO Recipe VALUES (46,'Red White & Boozy Shakes','Because freedom means you get drunk off ice cream if you want to.','1.\tIn a blender, combine 3 scoops vanilla ice cream, 2 tablespoons milk, and 1 ounce Curaçao. Blend until creamy and pour into two glasses. Freeze 15 to 20 minutes.\n" +
                    "2.\tRinse blender and add 3 scoops vanilla ice cream, 2 tablespoons milk, and 1 ounce vodka. Blend until combined, then pour into glasses on top of blue layer and freeze 15 to 20 minutes.\n" +
                    "3.\tRinse blender and add 3 scoops raspberry sorbet, 2 tablespoons milk, and 1 ounce raspberry vodka. Blend until combined, then pour onto white layer.\n" +
                    "4.\tTop each shake with whipped cream and garnish with sprinkles.\n','milkshake','red__white___boozy_shakes')");
            db.execSQL("INSERT INTO Recipe VALUES (47,'Classic Vanilla Malted Milkshake','This unique, over the top malted milkshake uses delicious Biscoff cookies in three different ways','Gather the ingredients.\n" +
                    "2 Combine ingredients in a blender, or place in a tall and wide glass to mix with an immersion blender beaker.\n" +
                    "3 Blend until creamy, 10 to 20 seconds.\n" +
                    "4 Transfer to a tall glass.\n" +
                    "5 Decorate with your favorite toppings, insert a straw, and enjoy.\n','milkshake','vanilla_malted_milk_shake')");
            db.execSQL("INSERT INTO Recipe VALUES (48,'Chocolate Milkshake','easy and quick recipe to prepare delicious chocolate shake made with cocoa powder.','Gather the ingredients.\n" +
                    "2-\tPlace the ice cream, milk, and chocolate syrup into the blender. If using chocolate chips, add those as well. Be mindful that the harder the ice cream is, the better, as the blending process can liquefy the ice cream too much and make the milkshake too thin.\n" +
                    "3-\tBlend the ingredients until completely smooth.\n" +
                    "4-\tPour into your glasses immediately, top with whipped cream, and decorate with shaved chocolate. Enjoy!\n','milkshake','chocolate_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (49,'Strawberry Milkshake','it’s the perfect shake for a hot summer day.','1-\tGather the ingredients.\n" +
                    "2-\tCut the tops off the strawberries and slice them into a few pieces\n" +
                    "3-\tIn a medium bowl, combine the sliced strawberries, sugar, and vanilla extract and stir to combine well.\n" +
                    "4-\tSet aside and allow to sit for at least 20 minutes and for up to 1 hour.\n" +
                    "5-\tPlace the strawberries with any juices, ice cream, and milk in a blender.\n" +
                    "6-\tBlend until smooth.\n" +
                    "7-\tPour into large glasses and, if desired, put a strawberry on the rim of each glass.\n','milkshake','strawberry_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (50,'Oreo Milkshake','A super easy milkshake made with vanilla ice cream, milk, and Oreo cookies!  This sweet treat is a hit with the whole family on a hot day!','1-\tGather the ingredients.\n" +
                    "2-\tPlace the vanilla ice cream, milk, 8 Oreo cookies, and the chocolate sauce in a blender and puree until smooth.\n" +
                    "3-\tCrush the remaining 2 Oreo cookies for the garnish by placing them in a zip-close plastic bag and pounding on them a few times with a rolling pin until they crumble.\n" +
                    "4-\tPour the milkshakes into 2 tall glasses and top each with the crushed Oreo cookie crumbs. Garnish with a straw.\n','milkshake','oreo_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (51,'Cookies and Cream Milkshake','Make this cookies and cream milkshake and enjoy the flavor in every sip! This comes together in no time and is perfect for a hot day!','1.\tGather the ingredients.\n" +
                    "2.\tCrush the cookies to your desired size. For the best control of the pieces, break them up by hand or use a mortar and pestle\n" +
                    "3.\tIn a blender add 1/4 cup of milk and the ice cream.\n" +
                    "4.\tBlend until smooth. Add more ice cream for a thicker consistency and more milk for a thinner milkshake.\n" +
                    "5.\tAdd the majority of the cookies (reserving some as a topping) and pulse a few times, just enough to mix in the cookies but not chop them further.\n" +
                    "6.\tPour the milkshake into one tall glass or two smaller glasses.\n" +
                    "7.\tAdd more crushed cookies on top and a whole cookie for a garnish. Serve and enjoy.\n','milkshake','cookies_and_cream_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (52,'Peanut Butter Milkshake','is gluten-free and vegan, and is arguably the tastiest milkshake you could ever eat!','1.\tGather your ingredients.\n" +
                    "2.\tPlace the ice cream, whole milk, and peanut butter in your blender.\n" +
                    "3.\tBlend until completely blended and smooth.\n" +
                    "4.\tPour the shakes into 2 large glasses. Then, top with whipped cream and chopped peanuts.\n','milkshake','peanutbuttermilkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (53,'Toffee and Salted Caramel Biscoff Malted Milkshake','In simple words, it is a milkshake gone crazy. There is a lot of madness going on this ultimate luscious Lotus Biscoff Freakshake.','1.\tGather the ingredients.\n" +
                    "2.\tSpread 2 tablespoons of the Biscoff cookie butter around the top edge of each milkshake glass. Roll each glass in the crushed Biscoff cookies, allowing the crushed cookies to stick to the cookie butter.\n" +
                    "3.\tDrizzle the inside of the glass with the prepared caramel sauce. Sprinkle the caramel drizzles with flaked sea salt. Place the glasses in the freezer to set until you are ready to prepare and serve the milkshake.\n" +
                    "4.\tIn a blender combine the milk, ice cubes, vanilla ice cream, remaining Biscoff cookie butter, malted milk powder, and cinnamon. Blend until smooth and when the milkshake has reached the desired consistency. Add additional ice cubes to thicken the milkshake or add additional milk for a thinner consistency.\n" +
                    "5.\tPour the malted milkshake into the prepared glasses.\n" +
                    "6.\tTop each milkshake with a tall swirl of whipped cream, a hearty drizzle of caramel sauce, a sprinkle of toffee pieces, and two whole cookies.\n','milkshake','toffee_and_salted_caramel_biscoff_malted_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (54,'Cookie Milkshake',' Sugar-Free Vegan Grasshopper Milkshake ','1.\tGather the ingredients.\n" +
                    "2.\tAdd first 3 ingredients into a high-powered blender and blend until smooth.\n" +
                    "3.\tTaste and adjust mint extract as needed. If using chocolate syrup, drizzle some into a mason jar or serving glass and freeze glass for 5 minutes.\n" +
                    "4.\tPour shake into each glass.\n" +
                    "5.\tTop with whipped cream if desired.\n','milkshake','chocolate_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (55,'Peach Milkshake',' Peach Milkshake has delicious chunks of fresh peaches, and creamy vanilla ice cream blended together for the most amazing shake right at home!','1.\tSlice the peaches into large chunks, removing the pit and the peel.\n" +
                    "2.\tPuree the peaches in a blender until they are completely smooth.\n" +
                    "3.\tAdd the ice cream and milk to the blender and mix to combine.\n" +
                    "4.\tCheck the consistency to see if the shake needs any more milk or ice cream to achieve your ideal texture.\n" +
                    "5.\tPour the milkshake into two chilled glasses, serve, and enjoy.\n','milkshake','peach_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (56,'Boozy S mores Milkshake',' This boozy s mores milkshake is everything! Blended with your favorite rich chocolate ice cream and roasted marshmallows for the ultimate campfire flavor. You won it want to drink anything else. ','1.\tGather the ingredients.\n" +
                    "2.\tRim a tall glass with graham crackers: Dip the rim in a shallow dish with one of the liqueurs then roll in finely crushed graham crackers. If you likes drizzle chocolate syrup inside the glass, , and place it in the freezer while you prepare the cocktail.\n" +
                    "3.\tIn a blender, combine the vodka, chocolate liqueur, Drambuie, RumChata, and ice cream.\n" +
                    "4.\tBlend until smooth to create a thicker shake, add more ice cream. If it is too thick, add more RumChata.\n" +
                    "5.\tPour the blended shake into the prepared frozen glass.\n" +
                    "6.\tGarnish with a toasted marshmallow and chocolate shavings. Serve and enjoy.\n','milkshake','boozy_s_mores_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (57,'Pumpkin Vanilla Milkshake','Sugar Free Vegan is quick ‘n easy, ready in minutes. creamy, perfectly spiced, vegan pumpkin pie in smoothie form!','1.\tGather the ingredients.\n" +
                    "2.\tScoop ice cream into a blender with the rest of the ingredients.\n" +
                    "3.\tBlend until smooth.\n" +
                    "4.\tTaste and decide if you need any other sweetener.\n" +
                    "5.\tEnjoy!\n','milkshake','sugar_free_vegan_pumpkin_vanilla_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (58,'Oat Milk Milkshake','his oat milk smoothie is so thick, sweet, and creamy, you won’t believe it is dairy free','1.\tGather the ingredients. Chill glasses in the freezer.\n" +
                    "2.\tScoop the pint of ice cream into a blender. Add the vanilla extract, ground cinnamon, oat milk, and oatmeal cookies. Blend on high speed until blended, but leaving the cookie bits still partially intact.\n" +
                    "3.\tDrizzle the caramel sauce into the inside of each glass.\n" +
                    "4.\tPour the milkshake into each glass. Top with vegan whipped cream, more caramel sauce, and cookie crumbles, if you wish.\n','milkshake','oat_milk_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (59,'Pineapple Milkshake','Is a delicious smoothie that can be made with fresh, frozen, or canned fruits and is perfect for breakfast or a snack','1.\tGather the ingredients.\n" +
                    "2.\tAdd the milk and pineapple to a blender.\n" +
                    "3.\tPulse until the pineapple is broken up and incorporated into the milk.\n" +
                    "4.\tAdd the ice cream and continue to pulse until all ingredients are well combined.\n" +
                    "5.\tPour into a large glass.\n" +
                    "6.\tTop with a slice of pineapple or some fresh whipped cream and enjoy.\n','milkshake','pineapple_milkshake')");
            db.execSQL("INSERT INTO Recipe VALUES (60,'Coca cola cinnamon','A wonderful cold soft drink','Mix all the ingredients together ','softDrink','coca_cola')");
            db.execSQL("INSERT INTO Recipe VALUES (61,'Fanta Piña Colada','A refreshing soft drink','Mix all the ingredients together','softDrink','fanta_pina_colada')");
            db.execSQL("INSERT INTO Recipe VALUES (62,'Surge','A soft drink with a distinct flavor','Mix all the ingredients together','softDrink','surge_soda_can')");
            db.execSQL("INSERT INTO Recipe VALUES (63,'Crush Pineapple','A soft drink with delicious pineapple flavor','Mix all the ingredients together','softDrink','crush_pineapple')");
            db.execSQL("INSERT INTO Recipe VALUES (64,'Sunkist Pineapple','A SOFT DRINK WITH A UNIQUE FLAVOR WITH PINEAPPLE','Mix all the ingredients together','softDrink','sunkist_pineapple')");
            db.execSQL("INSERT INTO Recipe VALUES (65,'Crush Peach','A SOFT DRINK WITH A WONDERFUL PEACH FLAVOR','Mix all the ingredients together','softDrink','crush_peach')");
            db.execSQL("INSERT INTO Recipe VALUES (66,'Sunkist Fruit Punch','A soft drink with a refreshing fruit flavor','Mix all the ingredients together','softDrink','sunkist_fruit_punch')");
            db.execSQL("INSERT INTO Recipe VALUES (67,'Mug Cream Soda','A new soft drink with a delicious creamy flavor','Mix all the ingredients together','softDrink','mug_cream_soda')");
            db.execSQL("INSERT INTO Recipe VALUES (68,'Mtn Dew Live Wire','A NEW AND DISTINCTIVE SOFT DRINK','Mix all the ingredients together','softDrink','mountain_dew_live_wire')");
            db.execSQL("INSERT INTO Recipe VALUES (69,'Mtn Dew Code Red','A refreshing soft drink that gives you energy','Mix all the ingredients together','softDrink','mountain_dew_code_red_soda')");
            db.execSQL("INSERT INTO Recipe VALUES (70,'Mtn Dew','A REFRESHING SOFT DRINK WITH A WONDERFUL TASTE OF LEMON','Mix all the ingredients together','softDrink','mtn_dew')");
            db.execSQL("INSERT INTO Recipe VALUES (71,'A&W Cream Soda','A NEW FLAVOR SOFT DRINK MIXED WITH DELICIOUS CREAM','Mix all the ingredients together','softDrink','a_w_cream_soda')");
            db.execSQL("INSERT INTO Recipe VALUES (72,'A&W Root Beer','A SOFT DRINK WITH BEER FLAVOR','Mix all the ingredients together','softDrink','aw_rootbeer_original')");
            db.execSQL("INSERT INTO Recipe VALUES (73,'Mello Yello','The new and distinctive soft drink','Mix all the ingredients together','softDrink','mello_yello_soda')");
            db.execSQL("INSERT INTO Recipe VALUES (74,'Stewart is Orangeen Cream Soda','A soft drink with a refreshing orange flavor','Mix all the ingredients together','softDrink','stewarts_orange_cream_soda')");
            db.execSQL("INSERT INTO Recipe VALUES (75,'Stewart is Grape Soda','A soft drink with a delicious grape flavor','Mix all the ingredients together','softDrink','stewarts_grape_soda')");
            db.execSQL("INSERT INTO Recipe VALUES (76,'Dr. Brown is Cream Soda','A soft drink with a wonderful cream flavor','Mix all the ingredients together','softDrink','dr_brown_s_cream_soda')");
            db.execSQL("INSERT INTO Recipe VALUES (77,'Dr. Brown is Root Beer','A soft drink with the new beer flavor','Mix all the ingredients together','softDrink','dr_browns_root_beer')");
            db.execSQL("INSERT INTO Recipe VALUES (78,'Wild Cherry Pepsi','Soft drink with refreshing cherry flavor','Mix all the ingredients together','softDrink','wild_cherry_pepsi')");
            db.execSQL("INSERT INTO Recipe VALUES (79,'Pepsi-Cola','A refreshing and cool soft drink','Mix all the ingredients together','softDrink','pepsi_soda')");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','2 c',1)");
            db.execSQL("INSERT INTO Ingredients VALUES ('granulated sugar','1/4 c',1)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cocoa powder','2 tbsp',1)");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate chips (or chopped chocolate)','1 c',1)");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla extract','1 tsp',1)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',1)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate shavings, for serving','',1)");
            db.execSQL("INSERT INTO Ingredients VALUES ('heavy cream','2/3 c',2)");
            db.execSQL("INSERT INTO Ingredients VALUES ('light corn syrup','1/4 c',2)");
            db.execSQL("INSERT INTO Ingredients VALUES ('packed brown sugar','1/2 c',2)");
            db.execSQL("INSERT INTO Ingredients VALUES ('brownie mix','1/4 c',2)");
            db.execSQL("INSERT INTO Ingredients VALUES ('semisweet chocolate chips','1 c',2)");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla extract','1 tsp',2)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Warmed milk, for serving','',2)");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','2 1/2 c',3)");
            db.execSQL("INSERT INTO Ingredients VALUES ('ground cinnamon, plus more for garnish','1/2 tsp',3)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Pinch of cayenne pepper (optional)','',3)");
            db.execSQL("INSERT INTO Ingredients VALUES ('semisweet chocolate, chopped, plus more for garnish','6 oz',3)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pure vanilla extract','1/2 tsp',3)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Pinch of kosher salt','',3)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Marshmallows, for serving','',3)");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','2 1/2 c',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('granulated sugar','1/4 c',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cocoa powder','2 tbsp',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pure vanilla extract','1 tsp',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate chips (or chopped bittersweet chocolate)','6 oz',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Rumchata','1/2 c',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('warm caramel','1/3 c',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',4)");
            db.execSQL("INSERT INTO Ingredients VALUES ('milk (preferably whole)','4 c',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('white chocolate chips','3/4 c',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('butterscotch chips','1/2 c.',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('melted butter','1 tbsp',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pure vanilla extract','1 tsp',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('whipped cream, for garnish (optional)','',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Gold sprinkles, for garnish (optional)','',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('butterscotch syrup, for garnish (optional)','',5)");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','2 c',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('granulated sugar','2 tbsp',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('unsweetened cocoa powder','2 tbsp',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Pinch kosher salt','',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('semisweet chocolate chips','1/2 c',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('RumChata','1 c',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('peppermint schnapps','2 oz',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('FOR TOPPING','',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream','',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chopped Andes','',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whole Andes','',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate sauce, for drizzling','',6)");
            db.execSQL("INSERT INTO Ingredients VALUES ('for blending','Ice',7)");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate milk','4 c',7)");
            db.execSQL("INSERT INTO Ingredients VALUES ('packets hot cocoa mix','4 (1-oz.)',7)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Baileys Irish Cream','4 oz',7)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',7)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate syrup, for serving','',7)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate shavings, for garnish','',7)");
            db.execSQL("INSERT INTO Ingredients VALUES ('milk (preferably whole)','2 c',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pumpkin puree','1/2 c',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pumpkin spice','2 tsp',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pure vanilla extract','1 tsp',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('white chocolate chips','2/3 c',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cinnamon-sugar, for sprinkling','',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Cinnamon sticks, optional','',8)");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','2 c',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cocoa powder','2 tbsp',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','1/4 c',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla extract','1 tsp',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate chips','6 oz',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Baileys','1/4 c',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('vodka','1/4 c',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Kahlua','1/4 c',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate Sauce, for serving','',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('crushed oreos, for serving','',9)");
            db.execSQL("INSERT INTO Ingredients VALUES ('milk (preferably 2% or whole)','4 c.',10)");
            db.execSQL("INSERT INTO Ingredients VALUES ('white chocolate chips','1 c.',10)");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla','1 tsp',10)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Blue food coloring','',10)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Reddi-wip','',10)");
            db.execSQL("INSERT INTO Ingredients VALUES ('blue rock candy','',10)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Purple sprinkles','',10)");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','2 c.',11)");
            db.execSQL("INSERT INTO Ingredients VALUES ('dark chocolate','6 oz',11)");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','1 tbsp',11)");
            db.execSQL("INSERT INTO Ingredients VALUES ('large marshmallows','',11)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Candy cane, for garnish','',11)");
            db.execSQL("INSERT INTO Ingredients VALUES ('melted chocolate','16 oz',12)");
            db.execSQL("INSERT INTO Ingredients VALUES ('popsicle sticks','24 ',12)");
            db.execSQL("INSERT INTO Ingredients VALUES ('nonstick cooking spray','',12)");
            db.execSQL("INSERT INTO Ingredients VALUES ('VARIATIONS','',12)");
            db.execSQL("INSERT INTO Ingredients VALUES ('mini marshmallows','',12)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Crushed candy canes','',12)");
            db.execSQL("INSERT INTO Ingredients VALUES ('chopped peanut butter cups','',12)");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','2 c',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('granulated sugar','1/4 c',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cocoa powder','2 tbsp',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate chips','1 c.',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla extract','1 tsp',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('hot fudge, for serving','2 tbsp',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate shavings, for serving','',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Vanilla ice cream, for serving','',13)");
            db.execSQL("INSERT INTO Ingredients VALUES ('vodka','1 oz',14)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cooled hot chocolate','1 c',14)");
            db.execSQL("INSERT INTO Ingredients VALUES ('maraschino cherries,plus more for serving','5 ',14)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Maraschino Cherry Juice','2 tsp',14)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',14)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate Sauce, for serving','',14)");
            db.execSQL("INSERT INTO Ingredients VALUES (' mint vodka','1 oz',15)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cooled white hot chocolate','1 c',15)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Oreo mint cookies, plus more for garnish','3',15)");
            db.execSQL("INSERT INTO Ingredients VALUES ('marshmallows','3',15)");
            db.execSQL("INSERT INTO Ingredients VALUES ('bourbon','1 oz',16)");
            db.execSQL("INSERT INTO Ingredients VALUES ('cooled hot chocolate','1c',16)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pecans, plus more for serving','5',16)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pecan pie filling, plus more for serving','2',16)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',16)");
            db.execSQL("INSERT INTO Ingredients VALUES ('white hot chocolate','1/2 c',17)");
            db.execSQL("INSERT INTO Ingredients VALUES ('rum','1 oz',17)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pumpkin spice eggnog','1/2 c',17)");
            db.execSQL("INSERT INTO Ingredients VALUES ('marshmallows, for serving','3',17)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Cinnamon stick, for serving','',17)");
            db.execSQL("INSERT INTO Ingredients VALUES ('caramel sauce, plus more for serving','1/4 c',18)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Bailey is Salted Caramel Irish Cream','1 1/2 oz',18)");
            db.execSQL("INSERT INTO Ingredients VALUES ('white hot chocolate','1 c',18)");
            db.execSQL("INSERT INTO Ingredients VALUES ('salt, plus more for serving','1 tsp',18)");
            db.execSQL("INSERT INTO Ingredients VALUES ('rum','1 oz',19)");
            db.execSQL("INSERT INTO Ingredients VALUES ('toasted coconut, plus more for serving','1/4 c',19)");
            db.execSQL("INSERT INTO Ingredients VALUES ('prepared white-hot chocolate made with coconut milk ','1 c',19)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving\','',19)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Kahlua','1 1/2 oz',20)");
            db.execSQL("INSERT INTO Ingredients VALUES ('prepared Starbucks Via Instant Vanilla Latte mix','1/2 c',20)");
            db.execSQL("INSERT INTO Ingredients VALUES ('prepared and heated hot chocolate','1/2 c',20)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Cinnamon, for serving','',20)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',20)");
            db.execSQL("INSERT INTO Ingredients VALUES ('White Rum','50 ml',21)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Mint leaves','8',21)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar Syrup','12 1/2 ml ',21)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime Juice','25 ml',21)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Mint sprigs','2',21)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Gin','50 ml ',22)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Dry Vermouth','10 ml ',22)");
            db.execSQL("INSERT INTO Ingredients VALUES (' Lemon twist','',22)");
            db.execSQL("INSERT INTO Ingredients VALUES ('White Rum','50 ml ',23)");
            db.execSQL("INSERT INTO Ingredients VALUES ('spoons Sugar','1 1/2 bar',23)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime Juice','25 ml ',23)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime sliceLime slice','',23)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Bourbon','50 ml ',24)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Angostura Bitters','2 dashes',24)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orange Bitters','1 dashes',24)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar','1 bar spoon ',24)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orange peel','',24)");
            db.execSQL("INSERT INTO Ingredients VALUES ('White Rum,','50 ml ',25)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Pineapple Juice','100 ml ',25)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Coconut Cream','25 ml',25)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Cream','25 ml',25)");
            db.execSQL("INSERT INTO Ingredients VALUES (' Pineapple Slice, ','',25)");
            db.execSQL("INSERT INTO Ingredients VALUES ('pinch of Salt','',25)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Anejo Rum','50 ml ',26)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime wedges','8',26)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Cola','100 ml ',26)");
            db.execSQL("INSERT INTO Ingredients VALUES (' Lime wedge','',26)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Dark Rum','20 ml ',27)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Light Rum','20 ml ',27)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Triple Sec','splash Demerara 20 ml ',27)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime Juice','20 ml ',27)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orgeat Syrup','10 ml ',27)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Mint sprig,','',27)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orange wedge','',27)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Vanilla Vodka','37 1/2 ml ',28)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Kahlua','12 1/2 ml ',28)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Double Espresso','',28)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar Syrup','12 1/2 ml ',28)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Coffee Beans','3',28)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Gin','50 ml ',29)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Crème de Mure','10 ml ',29)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon Juice','25 ml',29)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar Syrup','12 1/2 ml',29)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Blackberry','',29)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Tequila','',30)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orange Juice','',30)");
            db.execSQL("INSERT INTO Ingredients VALUES ('dash Grenadine Syrup','',30)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime wedge','',30)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orange slice','',30)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Campari','25 ml',31)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sweet Vermouth','25 ml',31)");
            db.execSQL("INSERT INTO Ingredients VALUES ('dash of Soda Water','',31)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon Peel','',31)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Vodka','50 ml',32)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon Juice','25 ml',32)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar Syrup','12 1/2 ml ',32)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Champagne','',32)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Strawberries','3',32)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Gin','50 ml',33)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime Juice','25 ml',33)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Soda Water','35 ml',33)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Grapefruit Bitters','2 dashes ',33)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Superfine Sugar','2 Bar Spoons ',33)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Mint Leaves','6',33)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Bourbon Whiskey','50 ml',34)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon Juice','25 ml',34)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar Syrup','12 1/2 ml ',34)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Egg-White','12 1/2 ml ',34)");
            db.execSQL("INSERT INTO Ingredients VALUES (' Lemon Zest','',34)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Blue WKD ','275 ml',35)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Port','50 ml',35)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Raspberry Vodka','50 ml',36)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Black Raspberry Liqueur','5 ml',36)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon Juice','12 1/2 ml ',36)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Blackberries','8',36)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Fruity flirty and fabulous','',36)");
            db.execSQL("INSERT INTO Ingredients VALUES (' Top-Up Ginger Beer','',36)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Tequila','37 1/2 ml ',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime Juice','12 1/2 ml ',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orange Juice','12 1/2 ml ',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon Juice','12 1/2 ml ',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Grapefruit Soda','10 ml',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon','',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime & Orange wedges','',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Salt','',37)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Champagne','100 ml',38)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Brandy','25 ml',38)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar','1 heaped bar spoon',38)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Angostura Bitters','4 dashes',38)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Cognac','37 1/2 ml ',39)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Triple Sec','12 1/2 ml ',39)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lemon Juice','20 ml ',39)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Egg White','dash ',39)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Sugar Syrup','5 ml',39)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Orange peel','',39)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Tequila','50 ml ',40)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Triple Sec','12 1/2 ml ',40)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Pineapple Juice','50',40)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Lime Juice','12 1/2 ml ',40)");
            db.execSQL("INSERT INTO Ingredients VALUES ('Simple Syrup, Lime Wedge','12 1/2 ml ',40)");
            db.execSQL("INSERT INTO Ingredients VALUES ('coconut milk','1 can',41);");
            db.execSQL("INSERT INTO Ingredients VALUES ('banana','1',41);");
            db.execSQL("INSERT INTO Ingredients VALUES ('frozen pineapple','2/3 c',41);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Frozen Mango','1/3 c',41);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Protein powder','1 scoop',41);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Pineapple wedge, for garnish','',41);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Caramel, for rimming glasses','',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('cinnamon sugar','2 tbsp',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('container vanilla ice cream','1 (1.5-qt.) ',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('bourbon','1 c',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('pure vanilla extract','1 tsp',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('honey','1 tsp',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('ground cinnamon, plus more for garnish','1 tsp',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for garnish','',42);");
            db.execSQL("INSERT INTO Ingredients VALUES ('rum','2 oz',43);");
            db.execSQL("INSERT INTO Ingredients VALUES ('heavy cream','1/4 c',43);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','3 scoops ',43);");
            db.execSQL("INSERT INTO Ingredients VALUES ('fruit (strawberries, blackberries, and peaches), plus more for garnish','1/2 c',43);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','1 half g',44);");
            db.execSQL("INSERT INTO Ingredients VALUES ('red food coloring','3 drops',44);");
            db.execSQL("INSERT INTO Ingredients VALUES ('blue food coloring','3 drops ',44);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Cap n Crunch cereal, plus more for garnish','1 c',45);");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','1 1/2 c',45);");
            db.execSQL("INSERT INTO Ingredients VALUES ('peanut butter, divided','1/4 c',45);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','1 qt',45);");
            db.execSQL("INSERT INTO Ingredients VALUES ('crushed Cap n Crunch cereal, for glass rims','1/4 c',45);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, for serving','',45);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Melted peanut butter, for serving','',45);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream (like Edy is)','6 scoops ',46);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Curaçao','1 oz',46);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Milk, Divided','6 tbsp',46);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vodka','1 oz',46);");
            db.execSQL("INSERT INTO Ingredients VALUES ('raspberry sorbet','3 scoops ',46);");
            db.execSQL("INSERT INTO Ingredients VALUES ('raspberry vodka','1 oz',46);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Red, white and blue sprinkles, for garnish','',46);");
            db.execSQL("INSERT INTO Ingredients VALUES ('low-fat milk','1 cup ',47);");
            db.execSQL("INSERT INTO Ingredients VALUES (' ice cream slow-churned or reduced-fat','1 cup',47);");
            db.execSQL("INSERT INTO Ingredients VALUES (' malted milk powder, or Ovaltine','4 tablespoons',47);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla extract ','1/2 tablespoon',47);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','4 cup',48);");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk, cold','1/2 cup',48);");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate syrup','1/4 cup',48);");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate chips, optional','1/4 cup',48);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Whipped cream, garnish','',48);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Shaved chocolate, garnish','',48);");
            db.execSQL("INSERT INTO Ingredients VALUES (' fresh strawberries','1/2-pound',49);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','1 tablespoon ',49);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla extract','1 tablespoon',49);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream ','1 pint',49);");
            db.execSQL("INSERT INTO Ingredients VALUES ('milk','1/2 cup',49);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Small whole strawberries, garnish','',49);");
            db.execSQL("INSERT INTO Ingredients VALUES (' vanilla ice cream','1 pint',50);");
            db.execSQL("INSERT INTO Ingredients VALUES ('milk (preferably whole milk)','1 cup',50);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Oreo cookies (or other chocolate sandwich cookies)','8',50);");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate sauce','1 tablespoon',50);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Oreo cookies, garnish','8',50);");
            db.execSQL("INSERT INTO Ingredients VALUES ('cookies','4 to 6',51);");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','1/4 cup',51);");
            db.execSQL("INSERT INTO Ingredients VALUES ('ice cream (about 3 large scoops; softened)','1 /2 to 2 cups',51);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Garnish: cookies','',51);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','2 cups',52);");
            db.execSQL("INSERT INTO Ingredients VALUES ('whole milk','3/4 cup ',52);");
            db.execSQL("INSERT INTO Ingredients VALUES ('creamy peanut butter','1/2 cup',52);");
            db.execSQL("INSERT INTO Ingredients VALUES ('whipped cream, optional garnish','',52);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chopped peanuts, optional garnish','',52);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Biscoff cookie butter','4 tablespoons',53);");
            db.execSQL("INSERT INTO Ingredients VALUES ('cookies, crushed','10 Biscoff',53);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Caramel sauce','',53);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Flaked sea salt','',53);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar-free ice cream','2 cups',54);");
            db.execSQL("INSERT INTO Ingredients VALUES ('fresh spinach','1 cup',54);");
            db.execSQL("INSERT INTO Ingredients VALUES ('mint extract or to taste','1/2 teaspoon',54);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar-free chocolate syrup (such as NuNaturals cocoa syrup)','1/2 cups',54);");
            db.execSQL("INSERT INTO Ingredients VALUES ('peppermint liquid stevia (optional)','1/4 teaspoon',54);");
            db.execSQL("INSERT INTO Ingredients VALUES (' sugar-free whipped cream','1 cup',54);");
            db.execSQL("INSERT INTO Ingredients VALUES ('peaches','2',55);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','2 cup',55);");
            db.execSQL("INSERT INTO Ingredients VALUES ('milk','1/2 cup',55);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Finely crushed graham crackers, for rim','',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate syrup, for optional garnish','',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('marshmallow vodka','1 1/2 ounces',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('chocolate liqueur','1 ounce',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Drambuie liqueur','1 ounce',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Rumchata liqueur','1/2 ounce',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','2 scoops',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Toasted marshmallow, for garnish','',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Chocolate shavings, for garnish','',56);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Homemade sugar-free dairy-free vanilla ice cream ','2 cups',57);");
            db.execSQL("INSERT INTO Ingredients VALUES ('pumpkin puree','1/2 cup',57);");
            db.execSQL("INSERT INTO Ingredients VALUES ('pumpkin pie spice','1 tps',57);");
            db.execSQL("INSERT INTO Ingredients VALUES ('Pumpkin Whipped Cream (and some sugar-free chocolate chips!)','1/2 cup',57);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vegan vanilla ice cream','1 pint',58);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla extract','1 teaspoon',58);");
            db.execSQL("INSERT INTO Ingredients VALUES ('ground cinnamon','1 teaspoon',58);");
            db.execSQL("INSERT INTO Ingredients VALUES ('oat milk','1/3 cup',58);");
            db.execSQL("INSERT INTO Ingredients VALUES ('cookies (crushed)','3 oatmeal',58);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vegan caramel sauce','2 tablespoons',58);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vegan whipped cream','',58);");
            db.execSQL("INSERT INTO Ingredients VALUES ('milk','1/2 cup',59);");
            db.execSQL("INSERT INTO Ingredients VALUES ('pineapple cubes','1 cup',59);");
            db.execSQL("INSERT INTO Ingredients VALUES ('vanilla ice cream','2 cups',59);");
            db.execSQL("INSERT INTO Ingredients VALUES ('pineapple, optional, garnish','1 slice ',59);");
            db.execSQL("INSERT INTO Ingredients VALUES ('whipped cream, optional, garnish','2 tablespoons',59);");
            db.execSQL("INSERT INTO Ingredients VALUES ('calories','240',60);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium,','55 mg',60);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','65 g ',60);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','65 g ',60);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','230 ',61);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','50 mg',61);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','61 g',61);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','61 g',61);");
            db.execSQL("INSERT INTO Ingredients VALUES ('protein','0 g',61);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','230 ',62);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','50 mg',62);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','62 g',62);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','56 g',62);");
            db.execSQL("INSERT INTO Ingredients VALUES ('calories','190 ',63);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','65 mg',63);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','52 g',63);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','51 g',63);");
            db.execSQL("INSERT INTO Ingredients VALUES ('calories','190 ',64);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','60 mg',64);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','51 g',64);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','51 g',64);");
            db.execSQL("INSERT INTO Ingredients VALUES ('calories','190 ',65);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','60 mg',65);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','50 g',65);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','50 g',65);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','180',66);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','60 mg',66);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','49 g',66);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','48 g',66);");
            db.execSQL("INSERT INTO Ingredients VALUES ('calories','180 ',67);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','60 mg',67);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','47 g',67);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','47 g',67);");
            db.execSQL("INSERT INTO Ingredients VALUES ('calories','230 ',68);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','55 mg',68);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','61 g',68);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','61 g',68);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','180 ',69);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','65 mg',69);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','46 g',69);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','46 g',69);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','170 ',70);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','60 mg',70);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','61 g',70);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','61 g',70);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','170 ',71);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','70 mg',71);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','46 g',71);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','46 g',71);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','170 ',72);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','80 mg',72);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','47 g',72);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','46 g',72);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','170 ',73);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','50 mg',73);");
            db.execSQL("INSERT INTO Ingredients VALUES ('47 g','carbs ',73);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','41 g',73);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','180 ',74);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','70 mg',74);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','45 g',74);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','45 g',74);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','180 ',75);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','70 mg',75);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','45 g',75);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','45 g',75);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','180 ',76);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','25 mg',76);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','44 g',76);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','44 g',76);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','170 ',77);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','30 mg',77);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','42 g',77);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','42 g',77);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','160 ',78);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','30 mg',78);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','40 g',78);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','41 g',78);");
            db.execSQL("INSERT INTO Ingredients VALUES (' calories','150 ',79);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sodium','30 mg',79);");
            db.execSQL("INSERT INTO Ingredients VALUES ('carbs ','42 g',79);");
            db.execSQL("INSERT INTO Ingredients VALUES ('sugar','41 g',79);");

        } else {
            ContentValues values=new ContentValues();
            values.put("boolFavorite",boolFavorite);
            //db.insert(list.Mohmatditals.COL2,null,values);
            values.put("recipeid",recipeId);
            db.insert(List.Prodactdetails.TABALE_NAME3,null,values);
        }
    }


    /**
     * @method_name: selectAllRandomly
     * @Description: select all items from database randomly and return them within a cursor{@link DataBase} .
     *
     * @param:       none
     * @return:      Cursor cursor
     */
    public Cursor selectAllRandomly() {
        String query = "SELECT * FROM  "+ List.Prodactdetails.TABALE_NAME+" order by RANDOM() limit 20;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    /**
     * @method_name: selectAll
     * @Description: select all items from database and return them within a cursor{@link DataBase} .
     *
     * @param:       none
     * @return:      Cursor cursor
     */
    public Cursor selectAll() {
        String query = "SELECT * FROM  "+ List.Prodactdetails.TABALE_NAME+";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor search(String name) {
        String query = "SELECT * FROM  "+ List.Prodactdetails.TABALE_NAME+" where name ==  "+name+ ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * @method_name: selectIngredientsByItemId
     * @Description: select get the ingredients of the product with the given id and return them within a cursor{@link DataBase} .
     *
     * @param:       string id
     * @return:      Cursor cursor
     */
    public Cursor selectIngredientsByItemId(String id) {
        String query = "SELECT  name, quantity FROM  Ingredients where recipeid=" + id + ";";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    /**
     * @method_name: checkFavoriteList
     * @Description: checks if the favorite table has items or not {@link DataBase} .
     *
     * @param:       string IDF
     * @return:      int  count
     */
    public int checkFavoriteList(String IDF) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("select * from Favorite Where recipeid ='" + IDF + "'", null);
        int count = rs.getCount();
        return count;
    }


    /**
     * @method_name: getAllFavoriteListItems
     * @Description: select all the favorites table items and return them within a cursor{@link DataBase} .
     *
     * @param:       none
     * @return:      Cursor cursor
     */
    public Cursor getAllFavoriteListItems() {
        String query = "SELECT * FROM Recipe  JOIN  Favorite on Recipe.RecipeID=Favorite.recipeid;";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    /**
     * @method_name: getAllFavoriteListItems
     * @Description: select the favorites items from te favorites table by given id {@link DataBase} .
     *
     * @param:       string row_id
     * @return:      none
     */
    public void deleteFavoriteItem(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Favorite where recipeid="+row_id+";");
    }


    /**
     * @method_name: isCocoa
     * @Description: select all the Cocoa items and return them within a cursor{@link DataBase} .
     *
     * @param:       none
     * @return:      Cursor cursor
     */


    public Cursor isCocoa() {
        String query = "SELECT * FROM  Recipe where categories Like 'cocoa'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * @method_name: isCocoa
     * @Description: select all the Coctail items and return them within a cursor{@link DataBase} .
     *
     * @param:       none
     * @return:      Cursor cursor
     */

    public Cursor isCocktail() {
        String query = "SELECT * FROM  Recipe where categories Like 'coocktails'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * @method_name: isMilkshake
     * @Description: select all the Milkshake items and return them within a cursor{@link DataBase} .
     *
     * @param:       none
     * @return:      Cursor cursor
     */

    public Cursor isMilkshake() {
        String query = "SELECT * FROM  Recipe where categories Like 'milkshake'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    /**
     * @method_name: isSoftDrink
     * @Description: select all the SoftDrink items and return them within a cursor{@link DataBase} .
     *
     * @param:       none
     * @return:      Cursor cursor
     */

    public Cursor isSoftDrink() {
        String query = "SELECT * FROM  Recipe where categories Like 'softDrink'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}

