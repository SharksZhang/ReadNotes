* given a store owner using WOW product system, when create a product in system, then create with a name, origin SellIn and origin Quality.
* given a product defined with SellIn and Quality in system, when check product info, then should show its SellIn and Quality info.

* given an on-date normal product, when days go by, then SellIn should decrease 1 point per day.
* given an on-date normal product, when days go by, then Quality should decrease 1 point per day.
* given a normal product which is out of date, when days still go by, then Quality should decrease 2 points per day.
* given a normal product which is out of date, when check product info, then show how many days it has expired.
* given a product in any time, when check product info, then show Quality but never less than 0.
* given a product in any time, when check product info, then show Quality but never greater than 0.


* given an on-date Aged Brie, when days go by, then SellIn should decrease 1 point per day.
* given an on-date Aged Brie, when days go by, then SellIn should increase 1 point per day.
* given an out-of-date Aged Brie, when days go by, then SellIn should increase 2 points per day.

* given a Sulfuras, when days go by, then Quality should never change.
* given a Sulfuras, when days go by, then show SellIn is undefined.