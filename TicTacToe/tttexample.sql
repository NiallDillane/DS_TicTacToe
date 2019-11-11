--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `autokey` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isactive` tinyint(4) DEFAULT '0',
  `registered` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`autokey`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
INSERT INTO `users` VALUES (1,'James','Murphy','jmurphy','*10F4A36855CBD789AB12C69D7ACDBD2B0D5973EC',1,'2016-11-22 09:21:53');
UNLOCK TABLES;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
CREATE TABLE `games` (
  `autokey` int(11) NOT NULL AUTO_INCREMENT,
  `p1` int(11) DEFAULT NULL,
  `p2` int(11) DEFAULT NULL,
  `gamestate` tinyint(4) DEFAULT '0',
  `started` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`autokey`),
  KEY `p1` (`p1`),
  KEY `p2` (`p2`),
  CONSTRAINT `games_ibfk_1` FOREIGN KEY (`p1`) REFERENCES `users` (`autokey`),
  CONSTRAINT `games_ibfk_2` FOREIGN KEY (`p2`) REFERENCES `users` (`autokey`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
UNLOCK TABLES;

--
-- Table structure for table `moves`
--

DROP TABLE IF EXISTS `moves`;
CREATE TABLE `moves` (
  `autokey` int(11) NOT NULL AUTO_INCREMENT,
  `pId` int(11) DEFAULT NULL,
  `x` int(11) DEFAULT NULL,
  `y` int(11) DEFAULT NULL,
  `played` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `gId` int(11) DEFAULT NULL,
  PRIMARY KEY (`autokey`),
  KEY `pId` (`pId`),
  KEY `gId` (`gId`),
  CONSTRAINT `moves_ibfk_1` FOREIGN KEY (`pId`) REFERENCES `users` (`autokey`),
  CONSTRAINT `moves_ibfk_2` FOREIGN KEY (`gId`) REFERENCES `games` (`autokey`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `moves`
--

LOCK TABLES `moves` WRITE;
UNLOCK TABLES;
