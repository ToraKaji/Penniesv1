        CREATE TABLE IF NOT EXISTS `Play`
        (`unit_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
         `play_id` INTEGER NOT NULL,
         `button_clicked` INTEGER NOT NULL,
         `button_id` INTEGER NOT NULL,
         `timestamp` INTEGER,
         `value` INTEGER NOT NULL,
         FOREIGN KEY(`play_id`) REFERENCES `play`(`play_id`) ON UPDATE NO ACTION ON DELETE CASCADE );

         CREATE  INDEX `index_Scratcher_play_id` ON `Play` (`play_id`);

         CREATE TABLE IF NOT EXISTS `Play`
         (`play_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
          `outcome` INTEGER NOT NULL,
          `won` INTEGER NOT NULL,
          `user_id` INTEGER NOT NULL,
           FOREIGN KEY(`user_id`) REFERENCES `User`(`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE );

          CREATE UNIQUE INDEX `index_play_play_id` ON `Play` (`play_id`);

         CREATE TABLE IF NOT EXISTS `User`
         (`user_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
         `wins` INTEGER NOT NULL, `coins` INTEGER NOT NULL);

