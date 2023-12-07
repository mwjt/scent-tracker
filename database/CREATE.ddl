
DROP TABLE IF EXISTS  `user`;
DROP TABLE IF EXISTS  `review`;
DROP TABLE IF EXISTS  `perfume_tag`;
DROP TABLE IF EXISTS  `perfume`;
DROP TABLE IF EXISTS  `collection_entry`;
DROP TABLE IF EXISTS  `perfumer`;
DROP TABLE IF EXISTS  `brand`;
DROP TABLE IF EXISTS  `tag`;
DROP TABLE IF EXISTS  `concentration`;
DROP TABLE IF EXISTS  `country`;
DROP TABLE IF EXISTS  `collection_type`;
DROP TABLE IF EXISTS  `gallery`;


-- `scent-tracker`.gallery definition

CREATE TABLE `gallery` (
  `id` bigint(20) NOT NULL,
  `create_date` datetime(6) NOT NULL,
  `image` longblob DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- `scent-tracker`.collection_type definition

CREATE TABLE `collection_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_amt0lv2lxll4ddjy29t4qgl42` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.concentration definition

CREATE TABLE `concentration` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4hs8lqm42gdurb8fpu381da2u` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.country definition

CREATE TABLE `country` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_llidyp77h6xkeokpbmoy710d4` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.tag definition

CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wdpsed5kna2y38hnbgrnhi5b` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.brand definition

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `text_path` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `text` text DEFAULT NULL,
  `gallery_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rdxh7tq2xs66r485cc8dkxt77` (`name`),
  KEY `FK8iehti3u9vucvh3qnd6s143pf` (`country_id`),
  KEY `FK76mbjywpfqhyixwpy2hoy85l7` (`gallery_id`),
  CONSTRAINT `FK76mbjywpfqhyixwpy2hoy85l7` FOREIGN KEY (`gallery_id`) REFERENCES `gallery` (`id`),
  CONSTRAINT `FK8iehti3u9vucvh3qnd6s143pf` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.perfumer definition

CREATE TABLE `perfumer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `photo_path` varchar(255) DEFAULT NULL,
  `text_path` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  `text` text DEFAULT NULL,
  `gallery_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mu4cv2iiacn4expddtp6y0w8g` (`name`),
  KEY `FK4g21v7jh4thdd0cpxci7082sh` (`country_id`),
  KEY `FKx662aru4ojt2dtahc4upsanu` (`gallery_id`),
  CONSTRAINT `FK4g21v7jh4thdd0cpxci7082sh` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`),
  CONSTRAINT `FKx662aru4ojt2dtahc4upsanu` FOREIGN KEY (`gallery_id`) REFERENCES `gallery` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.perfume definition

CREATE TABLE `perfume` (
  `id` bigint(20) NOT NULL,
  `bottle` float NOT NULL,
  `longevity` float NOT NULL,
  `name` varchar(100) NOT NULL,
  `scent` float NOT NULL,
  `sillage` float NOT NULL,
  `value` float NOT NULL,
  `year` smallint(6) DEFAULT NULL,
  `brand_id` bigint(20) NOT NULL,
  `concentration_id` bigint(20) NOT NULL,
  `perfumer_id` bigint(20) DEFAULT NULL,
  `gallery_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKagaxabwb3gjkqna3aqjj3unei` (`brand_id`),
  KEY `FK44guq67his6vn15uqi0148gsx` (`concentration_id`),
  KEY `FKpoe8hp3p8hb10hccoccyvi6hk` (`perfumer_id`),
  KEY `FKp5v3eiupbnn51rsienjwchlwg` (`gallery_id`),
  CONSTRAINT `FK44guq67his6vn15uqi0148gsx` FOREIGN KEY (`concentration_id`) REFERENCES `concentration` (`id`),
  CONSTRAINT `FKagaxabwb3gjkqna3aqjj3unei` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`),
  CONSTRAINT `FKp5v3eiupbnn51rsienjwchlwg` FOREIGN KEY (`gallery_id`) REFERENCES `gallery` (`id`),
  CONSTRAINT `FKpoe8hp3p8hb10hccoccyvi6hk` FOREIGN KEY (`perfumer_id`) REFERENCES `perfumer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.perfume_tag definition

CREATE TABLE `perfume_tag` (
  `perfume_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`perfume_id`,`tag_id`),
  KEY `FKc4tbrwlagmdmi9rwnfoowifu6` (`tag_id`),
  CONSTRAINT `FKc4tbrwlagmdmi9rwnfoowifu6` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `FKgts6ktvofsm6e2xwytstd9815` FOREIGN KEY (`perfume_id`) REFERENCES `perfume` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.`user` definition

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `avatar_path` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `gallery_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_ew1hvam8uwaknuaellwhqchhb` (`login`),
  KEY `FK266lk6t6v8q8bv66wv7tu2unv` (`gallery_id`),
  CONSTRAINT `FK266lk6t6v8q8bv66wv7tu2unv` FOREIGN KEY (`gallery_id`) REFERENCES `gallery` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.collection_entry definition

CREATE TABLE `collection_entry` (
  `id` bigint(20) NOT NULL,
  `note` mediumblob DEFAULT NULL,
  `quantity` smallint(6) NOT NULL,
  `collection_type_id` bigint(20) NOT NULL,
  `perfume_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `collection_type` smallint(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmmmhdns6k6xppf1d9wjpu1tnf` (`collection_type_id`),
  KEY `FKgvihyvygmdqy34rjl5a30bx5i` (`perfume_id`),
  KEY `FKg8eutdd3cs9v20uvr8hpnoi1` (`user_id`),
  CONSTRAINT `FKg8eutdd3cs9v20uvr8hpnoi1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKgvihyvygmdqy34rjl5a30bx5i` FOREIGN KEY (`perfume_id`) REFERENCES `perfume` (`id`),
  CONSTRAINT `FKmmmhdns6k6xppf1d9wjpu1tnf` FOREIGN KEY (`collection_type_id`) REFERENCES `collection_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- `scent-tracker`.review definition

CREATE TABLE `review` (
  `id` bigint(20) NOT NULL,
  `bottle` smallint(6) DEFAULT NULL,
  `longevity` smallint(6) DEFAULT NULL,
  `scent` smallint(6) DEFAULT NULL,
  `sillage` smallint(6) DEFAULT NULL,
  `text_path` varchar(255) DEFAULT NULL,
  `value` smallint(6) DEFAULT NULL,
  `perfume_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm04tf2g1l2ciaki0m96qqyan0` (`perfume_id`),
  KEY `FKiyf57dy48lyiftdrf7y87rnxi` (`user_id`),
  CONSTRAINT `FKiyf57dy48lyiftdrf7y87rnxi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKm04tf2g1l2ciaki0m96qqyan0` FOREIGN KEY (`perfume_id`) REFERENCES `perfume` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;