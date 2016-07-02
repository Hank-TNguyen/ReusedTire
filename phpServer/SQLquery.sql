drop table if exists inventory;
CREATE TABLE `inventory` (
  `tireid` varchar(128) NOT NULL,
  `width` text NOT NULL,
  `ratio` text NOT NULL,
  `diameter` text NOT NULL,
  `brand` text NOT NULL,
  `season` text NOT NULL,
  `rating` text NOT NULL,
  PRIMARY KEY (`tireid`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

drop table if exists post;
CREATE TABLE `post` (
  `postid` varchar(128) NOT NULL,
  `tireid` int(8) NOT NULL,
  `userid` int(8) NOT NULL,
  `quantity` text NOT NULL,
  `location` text NOT NULL,
  `notes` text NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`postid`),
  KEY `tireid` (`tireid`),
  KEY `userid` (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

drop table if exists users;
CREATE TABLE `users` (
  `userid` int(8) auto_increment,
  `username` text NOT NULL,
  `email` text NOT NULL,
  `userpassword` text NOT NULL,
  `phonenumber` int(8) DEFAULT NULL,
  `description` text,
  `profilepict` text,
  `dateofbirth` text,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

drop table if exists images;
CREATE TABLE `images` (
  `postid` varchar(128) NOT NULL,
  `imagepath` varchar(128) NOT NULL,
  PRIMARY KEY (`imagepath`),
  KEY `postid` (`postid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
