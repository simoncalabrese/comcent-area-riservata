CREATE TABLE IF NOT EXISTS `anag_docs` (
  `NAME` varchar(50) DEFAULT NULL,
  `URL` varchar(2048) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;


INSERT INTO `anag_docs` (`NAME`, `URL`) VALUES
('Promo Tim', 'www.brandsrl.com');

CREATE TABLE IF NOT EXISTS `anag_user` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `SURNAME` varchar(100) DEFAULT NULL,
  `COD_FISC` varchar(16) DEFAULT NULL,
  `P_IVA` varchar(11) DEFAULT NULL,
  `REFERENCE` int(11) DEFAULT NULL,
  `READ_PERMISSION` varchar(1) NOT NULL DEFAULT 'S',
  `WRITE_PERMISSION` varchar(1) NOT NULL DEFAULT 'N',
  `EMAIL` varchar(100) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `PSW` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `REFERENCE_ID_idx` (`REFERENCE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `anag_user` (`ID`, `NAME`, `SURNAME`, `COD_FISC`, `P_IVA`, `REFERENCE`, `READ_PERMISSION`, `WRITE_PERMISSION`, `EMAIL`, `PHONE`, `PSW`) VALUES
(0, 'Sandro', 'Gentile', '', '', NULL, 'S', 'S', 'INFO@COMUNICATIONCENTER.IT', '3284242344', 'HOUSE1973'),
(1, 'Dionigi', 'Marullo', 'MRLDGV83M12E506A', NULL, 0, 'S', 'N', 'DIONIGI.M@LIBERO.IT', '3884190542', 'DIONIGI'),
(12, 'Cristian', 'Tecno Service Galatina', '', '', 1, 'S', 'N', 'INFO@TECNOSERVICEGALATINA.it', '', 'CRISTIAN');

CREATE TABLE IF NOT EXISTS `app_activation` (
  `ID` int(11) NOT NULL,
  `USER` int(11) NOT NULL,
  `DES_ACTIVATION` varchar(100) NOT NULL,
  `AMNT_PLAFONT` int(11) NOT NULL,
  `DAT_ATT` varchar(10) DEFAULT NULL,
  `USER_INSERT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ACT_USER_idx` (`USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `app_hierarchy` (
`bottom` int(11)
,`center` int(11)
,`top` int(11)
);

CREATE TABLE IF NOT EXISTS `app_transactions` (
  `ID` int(11) NOT NULL,
  `USER` int(11) NOT NULL,
  `DAT_MOV` varchar(10) NOT NULL,
  `AMOUNT` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `USER_AMOUNT_idx` (`USER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `app_hierarchy`;

CREATE ALGORITHM=UNDEFINED DEFINER=`Sql1151252`@`%` SQL SECURITY DEFINER VIEW `app_hierarchy` AS select `a`.`ID` AS `bottom`,`b`.`ID` AS `center`,`c`.`ID` AS `top` from ((`anag_user` `a` left join `anag_user` `b` on((`a`.`REFERENCE` = `b`.`ID`))) left join `anag_user` `c` on((`b`.`REFERENCE` = `c`.`ID`)));

ALTER TABLE `anag_user`
  ADD CONSTRAINT `REFERENCE_ID` FOREIGN KEY (`REFERENCE`) REFERENCES `anag_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_activation`
  ADD CONSTRAINT `ACT_USER` FOREIGN KEY (`USER`) REFERENCES `anag_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `app_transactions`
  ADD CONSTRAINT `USER_AMOUNT` FOREIGN KEY (`USER`) REFERENCES `anag_user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
