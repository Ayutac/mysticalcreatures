## Adding an Entity
* add `MyEntity` class
* add the `EntityType` to `MysticalCreatures`
* add all the render stuff under `client`
* add biomes where the entity can spawn to `MyBiomeTagProvider`
* add food to `MyItemTagProvider`
* add avoidance and hunting targets to `MyEntityTagProvider`
* add sounds to `MySoundDefinitionsProvider` (`WeighedSoundEvents` can help)
* implement behavior, including goals
* add a drop?
  * add the item to `MysticalCreatures`
  * add the item to `MyModelProvider`
  * add the drop to `MyEntityLootTableProvider`
  * add the drop to `MyGlobalLootModifierProvider`
  * update `MyAdvancementProvider`
* add spawn egg
* update `MyLanguageProvider`