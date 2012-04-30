-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 30. April 2012 um 17:49
-- Server Version: 5.1.41
-- PHP-Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `roomanizer`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `addresses`
--

CREATE TABLE IF NOT EXISTS `addresses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `idCountries` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_addresses_countries1` (`idCountries`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=135 ;

--
-- Daten für Tabelle `addresses`
--

INSERT INTO `addresses` (`id`, `street`, `city`, `zip`, `email`, `phone`, `fax`, `idCountries`) VALUES
(1, 'Waldhalde 11', 'Zargingen', 'Saalingen', 'info@baalingen.at', '048 6733 82', '048 6733 83', 1),
(2, 'Carlos-Waltinger-Weg 12', 'Leutkirch', '56783', 'markus.bahlinger@gmx.net', '088 123444 31', '088 123444 32', 1),
(3, 'Schandweg 6', 'Waltingen', '787222', '', '04989 6645656', '04989 2365624', 1),
(6, 'Amsel-Fink-Weg 8', 'Lauterach', '767655', 'info@lauterach.at', '0900 66554', '0900 66553', 1),
(7, 'Johann-Weber-Weg 3', 'Wangen', '77654', 'bahlinger@prvinz.at', '04989 665422', '04989 665421', 1),
(8, 'Katzenstraße 5', 'Milchtrittstadt', '55643', 'Kater@maus.zz', '0666 555444', '0666 555442', 1),
(9, 'Zebrastraße 8', 'Wien', '77332', NULL, '0666 555444', '0666 555446', 1),
(10, 'WalterKirchweg 5', 'Hergatz', '88145', NULL, '08385 65232', NULL, 1),
(11, 'Vogelstraße 1', 'Wolfurt', '666642', NULL, NULL, NULL, 1),
(12, 'Webermannstraße 3', 'Karstadt', '23234', NULL, NULL, NULL, 1),
(61, 'Waldweg 7', 'Waltershofen', '77651', '', '', '', 1),
(62, 'Zaunweg 99', 'Karstadt', '6544', 'grashalm@blume.at', '043 5567 92', '', 1),
(63, 'WinterStraße 73', 'Baumingen', '38748', '', '', '0238437 774', 1),
(64, 'Pflaumenhalde 2', 'Winterberg', '663243', 'Ursula.weiner@gard.org', '07522 6494', '07522 6493', 1),
(65, 'Pfingststraße 54', 'Saalingen', '343211', '', '', '', 1),
(66, 'Osterweg 6', 'Hartz', '92924', 'harz@fear.at', '023457 56465', '023457 56434', 1),
(95, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(96, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(97, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(98, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(100, 'Somestreet 1', 'Somecity', '1234', 'some@email.at', '81818181818181', '91919191919191', 1),
(101, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(102, 'Souvenirstraße 8', 'Essex', '78787', 'Selectionsixteen@ultravisitor.de', '04989 6665666', '04989 6665666', 1),
(103, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(104, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(105, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(106, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(107, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(108, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(109, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(110, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(111, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(112, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(113, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(114, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(115, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(116, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(117, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(118, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(119, 'Räterstraße 1', 'Milchmannstadt', '767655', 'jesus@gott.ba', '0900 66554', '0900 66553', 1),
(120, 'Johann-Weber-Weg 3', 'Wangen', '77654', 'zwiebel@suppe.at', '04989 665422', '04989 665421', 1),
(121, 'hans 1', 'Dornbirn', '1234', 'irgendwas@something.com', '12345', '1232321', 1),
(122, 'Haslach 1', 'Dornbirn', '6850', 'markus.mo@gmx.net', '1234', '823', 1),
(123, 'Irgendwas', 'Göfis', '12', 'stefan.dunst@students.fhv.at', '1234', '547954798789179031', 1),
(124, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(125, 'Römerstraße 1', 'Kinderstadt', '78787', 'info@support.com', '04989 6665666', '04989 6665666', 1),
(126, 'Schandweg 6', 'Waltingen', '787222', '', '04989 6645656', '04989 2365624', 1),
(127, 'Amsel-Fink-Weg 8', 'Lauterach', '767655', 'info@lauterach.at', '0900 66554', '0900 66553', 1),
(128, 'Johann-Weber-Weg 3', 'Wangen', '77654', 'bahlinger@prvinz.at', '04989 665422', '04989 665421', 1),
(132, 'Schandweg 6', 'Waltingen', '787222', '', '04989 6645656', '04989 2365624', 1),
(133, 'Amsel-Fink-Weg 8', 'Lauterach', '767655', 'info@lauterach.at', '0900 66554', '0900 66553', 1),
(134, 'Johann-Weber-Weg 3', 'Wangen', '77654', 'bahlinger@prvinz.at', '04989 665422', '04989 665421', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `allocations`
--

CREATE TABLE IF NOT EXISTS `allocations` (
  `idGuests` int(11) NOT NULL,
  `idService` int(11) NOT NULL,
  PRIMARY KEY (`idGuests`,`idService`),
  KEY `fk_allocations_guests1` (`idGuests`),
  KEY `fk_allocations_habitations1` (`idService`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `allocations`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `companies`
--

CREATE TABLE IF NOT EXISTS `companies` (
  `idParties` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `idCompanyTypes` int(11) NOT NULL,
  PRIMARY KEY (`idParties`),
  KEY `fk_companies_companyTypes1` (`idCompanyTypes`),
  KEY `i_name` (`name`),
  KEY `fk_companies_customers1` (`idParties`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `companies`
--

INSERT INTO `companies` (`idParties`, `name`, `idCompanyTypes`) VALUES
(8, 'Planet Mu', 1),
(9, 'Warp', 1),
(10, 'Peace Off', 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `companiespersons`
--

CREATE TABLE IF NOT EXISTS `companiespersons` (
  `idParties` int(11) NOT NULL,
  `idCompanies` int(11) NOT NULL,
  PRIMARY KEY (`idParties`,`idCompanies`),
  KEY `fk_companies_has_persons_persons1` (`idParties`),
  KEY `fk_companiesPersons_companies1` (`idCompanies`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `companiespersons`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `companytypes`
--

CREATE TABLE IF NOT EXISTS `companytypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `companytypes`
--

INSERT INTO `companytypes` (`id`, `name`) VALUES
(1, 'Company'),
(2, 'Travel Agency');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `countries`
--

CREATE TABLE IF NOT EXISTS `countries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nameShort` char(2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  UNIQUE KEY `nameShort_UNIQUE` (`nameShort`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=319 ;

--
-- Daten für Tabelle `countries`
--

INSERT INTO `countries` (`id`, `name`, `nameShort`) VALUES
(1, 'Austria', 'AT'),
(71, 'Afghanistan', 'AF'),
(72, 'Åland Islands', 'AX'),
(73, 'Albania', 'AL'),
(74, 'Algeria', 'DZ'),
(75, 'American Samoa', 'AS'),
(76, 'Andorra', 'AD'),
(77, 'Angola', 'AO'),
(78, 'Anguilla', 'AI'),
(79, 'Antarctica', 'AQ'),
(80, 'Antigua and Barbuda', 'AG'),
(81, 'Argentina', 'AR'),
(82, 'Armenia', 'AM'),
(83, 'Aruba', 'AW'),
(84, 'Australia', 'AU'),
(85, 'Azerbaijan', 'AZ'),
(86, 'Bahamas', 'BS'),
(87, 'Bahrain', 'BH'),
(88, 'Bangladesh', 'BD'),
(89, 'Barbados', 'BB'),
(90, 'Belarus', 'BY'),
(91, 'Belgium', 'BE'),
(92, 'Belize', 'BZ'),
(93, 'Benin', 'BJ'),
(94, 'Bermuda', 'BM'),
(95, 'Bhutan', 'BT'),
(96, 'Bolivia, Plurinational State of', 'BO'),
(97, 'Bonaire, Sint Eustatius and Saba', 'BQ'),
(98, 'Bosnia and Herzegovina', 'BA'),
(99, 'Botswana', 'BW'),
(100, 'Bouvet Island', 'BV'),
(101, 'Brazil', 'BR'),
(102, 'British Indian Ocean Territory', 'IO'),
(103, 'Brunei Darussalam', 'BN'),
(104, 'Bulgaria', 'BG'),
(105, 'Burkina Faso', 'BF'),
(106, 'Burundi', 'BI'),
(107, 'Cambodia', 'KH'),
(108, 'Cameroon', 'CM'),
(109, 'Canada', 'CA'),
(110, 'Cape Verde', 'CV'),
(111, 'Cayman Islands', 'KY'),
(112, 'Central African Republic', 'CF'),
(113, 'Chad', 'TD'),
(114, 'Chile', 'CL'),
(115, 'China', 'CN'),
(116, 'Christmas Island', 'CX'),
(117, 'Cocos (Keeling) Islands', 'CC'),
(118, 'Colombia', 'CO'),
(119, 'Comoros', 'KM'),
(120, 'Congo', 'CG'),
(121, 'Congo, the Democratic Republic of the', 'CD'),
(122, 'Cook Islands', 'CK'),
(123, 'Costa Rica', 'CR'),
(124, 'Côte d''Ivoire', 'CI'),
(125, 'Croatia', 'HR'),
(126, 'Cuba', 'CU'),
(127, 'Curaçao', 'CW'),
(128, 'Cyprus', 'CY'),
(129, 'Czech Republic', 'CZ'),
(130, 'Denmark', 'DK'),
(131, 'Djibouti', 'DJ'),
(132, 'Dominica', 'DM'),
(133, 'Dominican Republic', 'DO'),
(134, 'Ecuador', 'EC'),
(135, 'Egypt', 'EG'),
(136, 'El Salvador', 'SV'),
(137, 'Equatorial Guinea', 'GQ'),
(138, 'Eritrea', 'ER'),
(139, 'Estonia', 'EE'),
(140, 'Ethiopia', 'ET'),
(141, 'Falkland Islands (Malvinas)', 'FK'),
(142, 'Faroe Islands', 'FO'),
(143, 'Fiji', 'FJ'),
(144, 'Finland', 'FI'),
(145, 'France', 'FR'),
(146, 'French Guiana', 'GF'),
(147, 'French Polynesia', 'PF'),
(148, 'French Southern Territories', 'TF'),
(149, 'Gabon', 'GA'),
(150, 'Gambia', 'GM'),
(151, 'Georgia', 'GE'),
(152, 'Germany', 'DE'),
(153, 'Ghana', 'GH'),
(154, 'Gibraltar', 'GI'),
(155, 'Greece', 'GR'),
(156, 'Greenland', 'GL'),
(157, 'Grenada', 'GD'),
(158, 'Guadeloupe', 'GP'),
(159, 'Guam', 'GU'),
(160, 'Guatemala', 'GT'),
(161, 'Guernsey', 'GG'),
(162, 'Guinea', 'GN'),
(163, 'Guinea-Bissau', 'GW'),
(164, 'Guyana', 'GY'),
(165, 'Haiti', 'HT'),
(166, 'Heard Island and McDonald Islands', 'HM'),
(167, 'Holy See (Vatican City State)', 'VA'),
(168, 'Honduras', 'HN'),
(169, 'Hong Kong', 'HK'),
(170, 'Hungary', 'HU'),
(171, 'Iceland', 'IS'),
(172, 'India', 'IN'),
(173, 'Indonesia', 'ID'),
(174, 'Iran, Islamic Republic of', 'IR'),
(175, 'Iraq', 'IQ'),
(176, 'Ireland', 'IE'),
(177, 'Isle of Man', 'IM'),
(178, 'Israel', 'IL'),
(179, 'Italy', 'IT'),
(180, 'Jamaica', 'JM'),
(181, 'Japan', 'JP'),
(182, 'Jersey', 'JE'),
(183, 'Jordan', 'JO'),
(184, 'Kazakhstan', 'KZ'),
(185, 'Kenya', 'KE'),
(186, 'Kiribati', 'KI'),
(187, 'Korea, Democratic People''s Republic of', 'KP'),
(188, 'Korea, Republic of', 'KR'),
(189, 'Kuwait', 'KW'),
(190, 'Kyrgyzstan', 'KG'),
(191, 'Lao People''s Democratic Republic', 'LA'),
(192, 'Latvia', 'LV'),
(193, 'Lebanon', 'LB'),
(194, 'Lesotho', 'LS'),
(195, 'Liberia', 'LR'),
(196, 'Libya', 'LY'),
(197, 'Liechtenstein', 'LI'),
(198, 'Lithuania', 'LT'),
(199, 'Luxembourg', 'LU'),
(200, 'Macao', 'MO'),
(201, 'Macedonia, the former Yugoslav Republic of', 'MK'),
(202, 'Madagascar', 'MG'),
(203, 'Malawi', 'MW'),
(204, 'Malaysia', 'MY'),
(205, 'Maldives', 'MV'),
(206, 'Mali', 'ML'),
(207, 'Malta', 'MT'),
(208, 'Marshall Islands', 'MH'),
(209, 'Martinique', 'MQ'),
(210, 'Mauritania', 'MR'),
(211, 'Mauritius', 'MU'),
(212, 'Mayotte', 'YT'),
(213, 'Mexico', 'MX'),
(214, 'Micronesia, Federated States of', 'FM'),
(215, 'Moldova, Republic of', 'MD'),
(216, 'Monaco', 'MC'),
(217, 'Mongolia', 'MN'),
(218, 'Montenegro', 'ME'),
(219, 'Montserrat', 'MS'),
(220, 'Morocco', 'MA'),
(221, 'Mozambique', 'MZ'),
(222, 'Myanmar', 'MM'),
(223, 'Namibia', 'NA'),
(224, 'Nauru', 'NR'),
(225, 'Nepal', 'NP'),
(226, 'Netherlands', 'NL'),
(227, 'New Caledonia', 'NC'),
(228, 'New Zealand', 'NZ'),
(229, 'Nicaragua', 'NI'),
(230, 'Niger', 'NE'),
(231, 'Nigeria', 'NG'),
(232, 'Niue', 'NU'),
(233, 'Norfolk Island', 'NF'),
(234, 'Northern Mariana Islands', 'MP'),
(235, 'Norway', 'NO'),
(236, 'Oman', 'OM'),
(237, 'Pakistan', 'PK'),
(238, 'Palau', 'PW'),
(239, 'Palestinian Territory, Occupied', 'PS'),
(240, 'Panama', 'PA'),
(241, 'Papua New Guinea', 'PG'),
(242, 'Paraguay', 'PY'),
(243, 'Peru', 'PE'),
(244, 'Philippines', 'PH'),
(245, 'Pitcairn', 'PN'),
(246, 'Poland', 'PL'),
(247, 'Portugal', 'PT'),
(248, 'Puerto Rico', 'PR'),
(249, 'Qatar', 'QA'),
(250, 'Réunion', 'RE'),
(251, 'Romania', 'RO'),
(252, 'Russian Federation', 'RU'),
(253, 'Rwanda', 'RW'),
(254, 'Saint Barthélemy', 'BL'),
(255, 'Saint Helena, Ascension and Tristan da Cunha', 'SH'),
(256, 'Saint Kitts and Nevis', 'KN'),
(257, 'Saint Lucia', 'LC'),
(258, 'Saint Martin (French part)', 'MF'),
(259, 'Saint Pierre and Miquelon', 'PM'),
(260, 'Saint Vincent and the Grenadines', 'VC'),
(261, 'Samoa', 'WS'),
(262, 'San Marino', 'SM'),
(263, 'Sao Tome and Principe', 'ST'),
(264, 'Saudi Arabia', 'SA'),
(265, 'Senegal', 'SN'),
(266, 'Serbia', 'RS'),
(267, 'Seychelles', 'SC'),
(268, 'Sierra Leone', 'SL'),
(269, 'Singapore', 'SG'),
(270, 'Sint Maarten (Dutch part)', 'SX'),
(271, 'Slovakia', 'SK'),
(272, 'Slovenia', 'SI'),
(273, 'Solomon Islands', 'SB'),
(274, 'Somalia', 'SO'),
(275, 'South Africa', 'ZA'),
(276, 'South Georgia and the South Sandwich Islands', 'GS'),
(277, 'South Sudan', 'SS'),
(278, 'Spain', 'ES'),
(279, 'Sri Lanka', 'LK'),
(280, 'Sudan', 'SD'),
(281, 'Suriname', 'SR'),
(282, 'Svalbard and Jan Mayen', 'SJ'),
(283, 'Swaziland', 'SZ'),
(284, 'Sweden', 'SE'),
(285, 'Switzerland', 'CH'),
(286, 'Syrian Arab Republic', 'SY'),
(287, 'Taiwan, Province of China', 'TW'),
(288, 'Tajikistan', 'TJ'),
(289, 'Tanzania, United Republic of', 'TZ'),
(290, 'Thailand', 'TH'),
(291, 'Timor-Leste', 'TL'),
(292, 'Togo', 'TG'),
(293, 'Tokelau', 'TK'),
(294, 'Tonga', 'TO'),
(295, 'Trinidad and Tobago', 'TT'),
(296, 'Tunisia', 'TN'),
(297, 'Turkey', 'TR'),
(298, 'Turkmenistan', 'TM'),
(299, 'Turks and Caicos Islands', 'TC'),
(300, 'Tuvalu', 'TV'),
(301, 'Uganda', 'UG'),
(302, 'Ukraine', 'UA'),
(303, 'United Arab Emirates', 'AE'),
(304, 'United Kingdom', 'GB'),
(305, 'United States', 'US'),
(306, 'United States Minor Outlying Islands', 'UM'),
(307, 'Uruguay', 'UY'),
(308, 'Uzbekistan', 'UZ'),
(309, 'Vanuatu', 'VU'),
(310, 'Venezuela, Bolivarian Republic of', 'VE'),
(311, 'Viet Nam', 'VN'),
(312, 'Virgin Islands, British', 'VG'),
(313, 'Virgin Islands, U.S.', 'VI'),
(314, 'Wallis and Futuna', 'WF'),
(315, 'Western Sahara', 'EH'),
(316, 'Yemen', 'YE'),
(317, 'Zambia', 'ZM'),
(318, 'Zimbabwe', 'ZW');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `customers`
--

CREATE TABLE IF NOT EXISTS `customers` (
  `idParties` int(11) NOT NULL,
  `idAddresses` int(11) NOT NULL,
  PRIMARY KEY (`idParties`),
  KEY `fk_customers_addresses1` (`idAddresses`),
  KEY `fk_customers_persons1` (`idParties`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `customers`
--

INSERT INTO `customers` (`idParties`, `idAddresses`) VALUES
(8, 12),
(7, 61),
(6, 62),
(9, 63),
(10, 64);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `extraservices`
--

CREATE TABLE IF NOT EXISTS `extraservices` (
  `idServices` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idServices`),
  KEY `fk_extraServices_services1` (`idServices`),
  KEY `i_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `extraservices`
--

INSERT INTO `extraservices` (`idServices`, `name`, `price`) VALUES
(1, 'Breakfast only', '25.00'),
(2, 'Thaimassage', '300.00'),
(3, 'Full pension', '100.00'),
(4, 'Half pension', '50.00'),
(5, 'Wellness package', '20.00'),
(6, 'Qi Gong Class', '44.00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `guests`
--

CREATE TABLE IF NOT EXISTS `guests` (
  `idParties` int(11) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`idParties`),
  UNIQUE KEY `idPersons_UNIQUE` (`idParties`),
  KEY `fk_guest_persons1` (`idParties`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `guests`
--

INSERT INTO `guests` (`idParties`, `fname`, `lname`, `birthday`) VALUES
(1, 'Aaron', 'Funk', '2012-04-24'),
(2, 'Richard', 'James', '2012-04-11'),
(3, 'Tom', 'Jenkinson', '2012-04-11'),
(4, 'Mike', 'Castaneda', '2012-04-11'),
(5, 'Keith', 'Withman', '2012-04-11'),
(95, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(96, 'Castaneda', 'Castaneda', '2012-04-11'),
(97, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(98, 'Castaneda', 'Castaneda', '2012-04-11'),
(99, 'Funk', 'Funk', '2012-04-24'),
(100, 'Castaneda', 'Castaneda', '2012-04-11'),
(101, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(102, 'Castaneda', 'Castaneda', '2012-04-11'),
(103, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(104, 'Castaneda', 'Castaneda', '2012-04-11'),
(105, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(106, 'Castaneda', 'Castaneda', '2012-04-11'),
(107, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(108, 'Castaneda', 'Castaneda', '2012-04-11'),
(109, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(110, 'Castaneda', 'Castaneda', '2012-04-11'),
(111, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(112, 'Castaneda', 'Castaneda', '2012-04-11'),
(113, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(114, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(115, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(116, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(117, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(118, 'Castaneda', 'Castaneda', '2012-04-11'),
(119, 'Withman', 'Withman', '2012-04-11'),
(120, 'adsf', 'adsf', '2012-04-30'),
(121, 'Mohanty', 'Mohanty', '2012-04-30'),
(122, 'Dunst', 'Dunst', '2012-04-30'),
(123, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(124, 'Jenkinson', 'Jenkinson', '2012-04-11'),
(125, 'Tom', 'Jenkinson', '2012-04-11'),
(126, 'Mike', 'Castaneda', '2012-04-11'),
(127, 'Keith', 'Withman', '2012-04-11'),
(129, 'Tom', 'Jenkinson', '2012-04-11'),
(130, 'Mike', 'Castaneda', '2012-04-11'),
(131, 'Keith', 'Withman', '2012-04-11');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `habitations`
--

CREATE TABLE IF NOT EXISTS `habitations` (
  `idServices` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `deposit` varchar(255) DEFAULT NULL,
  `idRooms` int(11) NOT NULL,
  `idUsers` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idServices`),
  KEY `fk_habitation_rooms1` (`idRooms`),
  KEY `fk_habitations_services1` (`idServices`),
  KEY `i_star` (`startDate`),
  KEY `i_end` (`endDate`),
  KEY `fk_habitations_users1` (`idUsers`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `habitations`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `invoiceitems`
--

CREATE TABLE IF NOT EXISTS `invoiceitems` (
  `idServices` int(11) NOT NULL,
  `idInvoice` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `idUsers` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idHabitations` int(11) NOT NULL,
  PRIMARY KEY (`idServices`,`idInvoice`),
  KEY `fk_services_has_invoice_invoice1` (`idInvoice`),
  KEY `fk_services_has_invoice_services1` (`idServices`),
  KEY `fk_invoiceItems_users1` (`idUsers`),
  KEY `fk_invoiceItems_habitations1` (`idHabitations`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `invoiceitems`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `invoices`
--

CREATE TABLE IF NOT EXISTS `invoices` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceNumber` varchar(255) NOT NULL,
  `discount` decimal(4,2) DEFAULT NULL,
  `expiration` date NOT NULL,
  `fulfilled` tinyint(1) NOT NULL,
  `idUsers` int(11) NOT NULL,
  `idpaymentMethods` int(11) NOT NULL,
  `idCustomers` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `invoiceNumber_UNIQUE` (`invoiceNumber`),
  KEY `fk_invoices_employees1` (`idUsers`),
  KEY `fk_invoices_paymentMethods1` (`idpaymentMethods`),
  KEY `i_expiration` (`expiration`),
  KEY `i_fulfilled` (`fulfilled`),
  KEY `fk_invoices_customers1` (`idCustomers`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Daten für Tabelle `invoices`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `parties`
--

CREATE TABLE IF NOT EXISTS `parties` (
  `idParties` int(11) NOT NULL AUTO_INCREMENT,
  `idAddresses` int(11) NOT NULL,
  PRIMARY KEY (`idParties`),
  KEY `fk_persons_addresses1` (`idAddresses`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=132 ;

--
-- Daten für Tabelle `parties`
--

INSERT INTO `parties` (`idParties`, `idAddresses`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 6),
(5, 7),
(6, 8),
(7, 9),
(8, 10),
(9, 11),
(10, 12),
(95, 95),
(96, 96),
(97, 97),
(98, 98),
(99, 100),
(100, 101),
(101, 102),
(102, 103),
(103, 104),
(104, 105),
(105, 106),
(106, 107),
(107, 108),
(108, 109),
(109, 110),
(110, 111),
(111, 112),
(112, 113),
(113, 114),
(114, 115),
(115, 116),
(116, 117),
(117, 118),
(118, 119),
(119, 120),
(120, 121),
(121, 122),
(122, 123),
(123, 124),
(124, 125),
(125, 126),
(126, 127),
(127, 128),
(129, 132),
(130, 133),
(131, 134);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `paymentmethods`
--

CREATE TABLE IF NOT EXISTS `paymentmethods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Daten für Tabelle `paymentmethods`
--

INSERT INTO `paymentmethods` (`id`, `name`) VALUES
(2, 'Cash'),
(3, 'Credit'),
(1, 'Credit card');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `permissions`
--

CREATE TABLE IF NOT EXISTS `permissions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `permissions`
--

INSERT INTO `permissions` (`id`, `name`) VALUES
(1, 'Check-In');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `privateperson`
--

CREATE TABLE IF NOT EXISTS `privateperson` (
  `idParties` int(11) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `lname` varchar(255) NOT NULL,
  PRIMARY KEY (`idParties`),
  KEY `fk_privatePerson_customers1` (`idParties`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `privateperson`
--

INSERT INTO `privateperson` (`idParties`, `fname`, `lname`) VALUES
(6, 'Otto', 'von Schirach'),
(7, 'Erik', 'Satie');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservationitems`
--

CREATE TABLE IF NOT EXISTS `reservationitems` (
  `idReservations` int(11) NOT NULL,
  `idRoomCategories` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`idReservations`,`idRoomCategories`),
  KEY `fk_reservations_has_roomCategories_roomCategories1` (`idRoomCategories`),
  KEY `fk_reservations_has_roomCategories_reservations1` (`idReservations`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `reservationitems`
--

INSERT INTO `reservationitems` (`idReservations`, `idRoomCategories`, `amount`) VALUES
(5, 1, 1),
(6, 3, 2),
(7, 3, 3),
(8, 2, 4),
(9, 6, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservationoptions`
--

CREATE TABLE IF NOT EXISTS `reservationoptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `expiration` date NOT NULL,
  `prepayment` decimal(10,2) NOT NULL,
  `fulfilled` tinyint(1) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `idReservations` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservationOption_reservations1` (`idReservations`),
  KEY `i_expiration` (`expiration`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `reservationoptions`
--


-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservations`
--

CREATE TABLE IF NOT EXISTS `reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservationNumber` varchar(255) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `idParties` int(11) NOT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idUsers` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `reserationNumber_UNIQUE` (`reservationNumber`),
  KEY `fk_reservations_persons1` (`idParties`),
  KEY `i_start` (`startDate`),
  KEY `i_end` (`endDate`),
  KEY `fk_reservations_users1` (`idUsers`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Daten für Tabelle `reservations`
--

INSERT INTO `reservations` (`id`, `reservationNumber`, `startDate`, `endDate`, `comment`, `idParties`, `created`, `idUsers`) VALUES
(5, '1', '2012-04-02', '2012-04-05', 'asfd', 1, '2012-04-26 23:36:59', NULL),
(6, '2', '2012-04-26', '2012-04-30', 'asdf', 2, '2012-04-26 23:36:59', NULL),
(7, '3', '2012-04-11', '2012-04-17', NULL, 3, '2012-04-26 23:36:59', NULL),
(8, '4', '2012-04-21', '2012-04-26', NULL, 4, '2012-04-26 23:36:59', NULL),
(9, '5', '2012-04-06', '2012-04-30', NULL, 5, '2012-04-26 23:36:59', NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservationsguests`
--

CREATE TABLE IF NOT EXISTS `reservationsguests` (
  `reservationsID` int(11) NOT NULL,
  `personsID` int(11) NOT NULL,
  PRIMARY KEY (`reservationsID`,`personsID`),
  KEY `fk_guests_has_reservations_reservations1` (`reservationsID`),
  KEY `fk_guests_has_reservations_guests1` (`personsID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `reservationsguests`
--

INSERT INTO `reservationsguests` (`reservationsID`, `personsID`) VALUES
(5, 3),
(5, 4),
(5, 5),
(6, 1),
(6, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rolepermissions`
--

CREATE TABLE IF NOT EXISTS `rolepermissions` (
  `idPermissions` int(11) NOT NULL,
  `idRoles` int(11) NOT NULL,
  PRIMARY KEY (`idPermissions`,`idRoles`),
  KEY `fk_permissions_has_roles_roles1` (`idRoles`),
  KEY `fk_permissions_has_roles_permissions1` (`idPermissions`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `rolepermissions`
--

INSERT INTO `rolepermissions` (`idPermissions`, `idRoles`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'admin');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomcategories`
--

CREATE TABLE IF NOT EXISTS `roomcategories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `bedCount` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Daten für Tabelle `roomcategories`
--

INSERT INTO `roomcategories` (`id`, `name`, `bedCount`) VALUES
(1, 'Double room', 2),
(2, 'Single room', 1),
(3, 'Triple room', 3),
(4, 'Bachside double room', 2),
(5, 'Beachside single room', 1),
(6, 'Four persons room', 4),
(7, 'Presidential suite', 1);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomcategoryprices`
--

CREATE TABLE IF NOT EXISTS `roomcategoryprices` (
  `idRoomCategories` int(11) NOT NULL,
  `idSeasons` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `priceMin` decimal(10,2) NOT NULL,
  PRIMARY KEY (`idRoomCategories`,`idSeasons`),
  KEY `fk_roomCategories_has_season_season1` (`idSeasons`),
  KEY `fk_roomCategories_has_season_roomCategories1` (`idRoomCategories`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `roomcategoryprices`
--

INSERT INTO `roomcategoryprices` (`idRoomCategories`, `idSeasons`, `price`, `priceMin`) VALUES
(1, 1, '40.00', '10.00'),
(1, 2, '50.00', '20.00'),
(1, 3, '60.00', '30.00'),
(1, 4, '70.00', '50.00'),
(2, 1, '20.00', '15.00'),
(2, 2, '30.00', '25.00'),
(2, 3, '40.00', '35.00'),
(2, 4, '50.00', '45.00'),
(3, 1, '80.00', '79.99'),
(3, 2, '81.00', '80.99'),
(3, 3, '82.00', '81.99'),
(3, 4, '83.00', '82.99'),
(4, 1, '9.99', '3.99'),
(4, 2, '19.99', '13.99'),
(4, 3, '29.99', '23.99'),
(4, 4, '39.99', '33.99'),
(5, 1, '55.55', '44.44'),
(5, 2, '66.66', '55.55'),
(5, 3, '77.77', '66.66'),
(5, 4, '88.88', '77.77'),
(6, 1, '12.34', '1.23'),
(6, 2, '23.45', '12.34'),
(6, 3, '34.56', '23.45'),
(6, 4, '45.67', '34.56'),
(7, 1, '999.99', '999.99'),
(7, 2, '999.99', '999.99'),
(7, 3, '999.99', '999.99'),
(7, 4, '99999.99', '9999.99');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomoptions`
--

CREATE TABLE IF NOT EXISTS `roomoptions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Daten für Tabelle `roomoptions`
--

INSERT INTO `roomoptions` (`id`, `name`) VALUES
(2, 'Animals'),
(6, 'children'),
(5, 'eatable'),
(4, 'expensive'),
(8, 'luxurious'),
(1, 'Smoker'),
(3, 'under water'),
(7, 'video controlled');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roomNumber` varchar(255) NOT NULL,
  `idRoomCategories` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `roomnr_UNIQUE` (`roomNumber`),
  KEY `fk_rooms_categories1` (`idRoomCategories`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=35 ;

--
-- Daten für Tabelle `rooms`
--

INSERT INTO `rooms` (`id`, `roomNumber`, `idRoomCategories`) VALUES
(1, '201', 1),
(2, '202', 1),
(3, '203', 1),
(4, '204', 1),
(5, '205', 2),
(6, '206', 2),
(7, '207', 2),
(8, '208', 2),
(9, '301', 3),
(10, '302', 3),
(11, '303', 3),
(12, '304', 3),
(13, '401', 4),
(14, '402', 4),
(15, '403', 4),
(16, '405', 4),
(17, '501', 5),
(18, '502', 5),
(19, '503', 5),
(20, '504', 5),
(21, '506', 5),
(22, '505', 5),
(23, '601', 6),
(24, '602', 6),
(25, '603', 6),
(26, '604', 6),
(27, '605', 6),
(28, '666', 6),
(29, '701', 7),
(30, '702', 7),
(31, '703', 7),
(32, '704', 7),
(33, '705', 7),
(34, '708', 7);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomsroomoptions`
--

CREATE TABLE IF NOT EXISTS `roomsroomoptions` (
  `idRooms` int(11) NOT NULL,
  `idOptions` int(11) NOT NULL,
  PRIMARY KEY (`idRooms`,`idOptions`),
  KEY `fk_room_has_roomoptions_roomoptions1` (`idOptions`),
  KEY `fk_room_has_roomoptions_room` (`idRooms`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `roomsroomoptions`
--

INSERT INTO `roomsroomoptions` (`idRooms`, `idOptions`) VALUES
(1, 1),
(3, 1),
(4, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomsroomstatus`
--

CREATE TABLE IF NOT EXISTS `roomsroomstatus` (
  `idRooms` int(11) NOT NULL,
  `idRoomStatus` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  PRIMARY KEY (`idRooms`,`idRoomStatus`),
  KEY `fk_rooms_has_roomStatus_roomStatus1` (`idRoomStatus`),
  KEY `fk_rooms_has_roomStatus_rooms1` (`idRooms`),
  KEY `i_start` (`startDate`),
  KEY `i_end` (`endDate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `roomsroomstatus`
--

INSERT INTO `roomsroomstatus` (`idRooms`, `idRoomStatus`, `startDate`, `endDate`) VALUES
(1, 1, '2012-04-24', '2012-06-30'),
(2, 3, '2012-04-01', '2012-04-30'),
(3, 3, '2012-04-01', '2012-04-30'),
(4, 4, '2012-04-01', '2012-04-30'),
(5, 2, '2012-04-01', '2012-04-30'),
(6, 3, '2012-04-01', '2012-04-30'),
(7, 2, '2012-04-01', '2012-04-30'),
(8, 3, '2012-04-01', '2012-04-30'),
(9, 3, '2012-04-01', '2012-04-30'),
(11, 3, '2012-04-01', '2012-04-30'),
(12, 2, '2012-04-01', '2012-04-30'),
(13, 3, '2012-04-01', '2012-04-30'),
(14, 1, '2012-04-01', '2012-04-30'),
(15, 3, '2012-04-01', '2012-04-30'),
(16, 2, '2012-04-01', '2012-04-30'),
(17, 3, '2012-04-01', '2012-04-30'),
(18, 4, '2012-04-01', '2012-04-30'),
(19, 3, '2012-04-01', '2012-04-30'),
(20, 1, '2012-04-01', '2012-04-30'),
(21, 3, '2012-04-01', '2012-04-30'),
(22, 2, '2012-04-01', '2012-04-30'),
(23, 2, '2012-04-18', '2012-04-28'),
(23, 3, '2012-04-01', '2012-04-30'),
(24, 4, '2012-04-01', '2012-04-30'),
(25, 3, '2012-04-01', '2012-04-30'),
(26, 1, '2012-04-01', '2012-04-30'),
(27, 3, '2012-04-01', '2012-04-30'),
(28, 2, '2012-04-01', '2012-04-30'),
(29, 3, '2012-04-01', '2012-04-30'),
(30, 4, '2012-04-01', '2012-04-30');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `roomstatus`
--

CREATE TABLE IF NOT EXISTS `roomstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Daten für Tabelle `roomstatus`
--

INSERT INTO `roomstatus` (`id`, `name`) VALUES
(3, 'Free - Clean'),
(4, 'Free - Dirty'),
(1, 'Occupied - Clean'),
(2, 'Occupied - Dirty'),
(5, 'Out of order');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `seasons`
--

CREATE TABLE IF NOT EXISTS `seasons` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL COMMENT 'problem with the year',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `i_start` (`startDate`),
  KEY `i_end` (`endDate`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Daten für Tabelle `seasons`
--

INSERT INTO `seasons` (`id`, `name`, `startDate`, `endDate`) VALUES
(1, 'Summer', '2012-03-30', '2012-08-31'),
(2, 'Atumn', '2012-09-01', '2012-11-30'),
(3, 'Winter', '2012-12-01', '2013-02-28'),
(4, 'Spring', '2013-03-01', '2013-05-31');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `services`
--

CREATE TABLE IF NOT EXISTS `services` (
  `idServices` int(11) NOT NULL AUTO_INCREMENT,
  `idServiceTypes` int(11) NOT NULL,
  PRIMARY KEY (`idServices`),
  KEY `fk_services_serviceTypes1` (`idServiceTypes`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=103 ;

--
-- Daten für Tabelle `services`
--

INSERT INTO `services` (`idServices`, `idServiceTypes`) VALUES
(6, 2),
(1, 3),
(3, 3),
(4, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 3),
(17, 3),
(18, 3),
(19, 3),
(20, 3),
(21, 3),
(22, 3),
(23, 3),
(24, 3),
(25, 3),
(38, 3),
(39, 3),
(40, 3),
(41, 3),
(42, 3),
(43, 3),
(44, 3),
(45, 3),
(46, 3),
(47, 3),
(48, 3),
(49, 3),
(50, 3),
(51, 3),
(52, 3),
(53, 3),
(54, 3),
(55, 3),
(56, 3),
(57, 3),
(58, 3),
(59, 3),
(60, 3),
(61, 3),
(62, 3),
(63, 3),
(64, 3),
(65, 3),
(66, 3),
(67, 3),
(68, 3),
(69, 3),
(70, 3),
(71, 3),
(72, 3),
(73, 3),
(74, 3),
(75, 3),
(76, 3),
(77, 3),
(78, 3),
(79, 3),
(80, 3),
(81, 3),
(82, 3),
(83, 3),
(84, 3),
(85, 3),
(86, 3),
(87, 3),
(88, 3),
(89, 3),
(90, 3),
(91, 3),
(92, 3),
(93, 3),
(94, 3),
(95, 3),
(96, 3),
(97, 3),
(98, 3),
(99, 3),
(100, 3),
(102, 3),
(2, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `servicetypes`
--

CREATE TABLE IF NOT EXISTS `servicetypes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `taxRate` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Daten für Tabelle `servicetypes`
--

INSERT INTO `servicetypes` (`id`, `name`, `taxRate`) VALUES
(1, 'Drinks', '20.00'),
(2, 'Food', '10.00'),
(3, 'Habitation', '20.00'),
(4, 'Wellness', '20.00'),
(5, 'Others', '20.00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `userroles`
--

CREATE TABLE IF NOT EXISTS `userroles` (
  `idRoles` int(11) NOT NULL,
  `idUsers` int(11) NOT NULL,
  PRIMARY KEY (`idRoles`,`idUsers`),
  KEY `fk_role_has_employee_employee1` (`idUsers`),
  KEY `fk_role_has_employee_role1` (`idRoles`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `userroles`
--

INSERT INTO `userroles` (`idRoles`, `idUsers`) VALUES
(1, 4);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` char(32) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `employeeCode_UNIQUE` (`username`),
  KEY `i_password` (`password`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Daten für Tabelle `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `active`) VALUES
(3, 'user', 'asdf', 1),
(4, 'vollbrecht', 'vollbrecht', 1);

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `addresses`
--
ALTER TABLE `addresses`
  ADD CONSTRAINT `fk_addresses_countries1` FOREIGN KEY (`idCountries`) REFERENCES `countries` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `allocations`
--
ALTER TABLE `allocations`
  ADD CONSTRAINT `fk_allocations_guests1` FOREIGN KEY (`idGuests`) REFERENCES `guests` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_allocations_habitations1` FOREIGN KEY (`idService`) REFERENCES `habitations` (`idServices`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `companies`
--
ALTER TABLE `companies`
  ADD CONSTRAINT `fk_companies_companyTypes1` FOREIGN KEY (`idCompanyTypes`) REFERENCES `companytypes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_companies_customers1` FOREIGN KEY (`idParties`) REFERENCES `customers` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `companiespersons`
--
ALTER TABLE `companiespersons`
  ADD CONSTRAINT `fk_companies_has_persons_persons1` FOREIGN KEY (`idParties`) REFERENCES `parties` (`idParties`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_companiesPersons_companies1` FOREIGN KEY (`idCompanies`) REFERENCES `companies` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_customers_addresses1` FOREIGN KEY (`idAddresses`) REFERENCES `addresses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_customers_persons1` FOREIGN KEY (`idParties`) REFERENCES `parties` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `extraservices`
--
ALTER TABLE `extraservices`
  ADD CONSTRAINT `fk_extraServices_services1` FOREIGN KEY (`idServices`) REFERENCES `services` (`idServices`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `guests`
--
ALTER TABLE `guests`
  ADD CONSTRAINT `fk_guest_persons1` FOREIGN KEY (`idParties`) REFERENCES `parties` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `habitations`
--
ALTER TABLE `habitations`
  ADD CONSTRAINT `fk_habitations_services1` FOREIGN KEY (`idServices`) REFERENCES `services` (`idServices`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_habitations_users1` FOREIGN KEY (`idUsers`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_habitation_rooms1` FOREIGN KEY (`idRooms`) REFERENCES `rooms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `invoiceitems`
--
ALTER TABLE `invoiceitems`
  ADD CONSTRAINT `fk_services_has_invoice_services1` FOREIGN KEY (`idServices`) REFERENCES `services` (`idServices`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_services_has_invoice_invoice1` FOREIGN KEY (`idInvoice`) REFERENCES `invoices` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_invoiceItems_users1` FOREIGN KEY (`idUsers`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_invoiceItems_habitations1` FOREIGN KEY (`idHabitations`) REFERENCES `habitations` (`idServices`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `invoices`
--
ALTER TABLE `invoices`
  ADD CONSTRAINT `fk_invoices_employees1` FOREIGN KEY (`idUsers`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_invoices_paymentMethods1` FOREIGN KEY (`idpaymentMethods`) REFERENCES `paymentmethods` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_invoices_customers1` FOREIGN KEY (`idCustomers`) REFERENCES `customers` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `parties`
--
ALTER TABLE `parties`
  ADD CONSTRAINT `fk_persons_addresses1` FOREIGN KEY (`idAddresses`) REFERENCES `addresses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `privateperson`
--
ALTER TABLE `privateperson`
  ADD CONSTRAINT `fk_privatePerson_customers1` FOREIGN KEY (`idParties`) REFERENCES `customers` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `reservationitems`
--
ALTER TABLE `reservationitems`
  ADD CONSTRAINT `fk_reservations_has_roomCategories_reservations1` FOREIGN KEY (`idReservations`) REFERENCES `reservations` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reservations_has_roomCategories_roomCategories1` FOREIGN KEY (`idRoomCategories`) REFERENCES `roomcategories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `reservationoptions`
--
ALTER TABLE `reservationoptions`
  ADD CONSTRAINT `fk_reservationOption_reservations1` FOREIGN KEY (`idReservations`) REFERENCES `reservations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `fk_reservations_persons1` FOREIGN KEY (`idParties`) REFERENCES `parties` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reservations_users1` FOREIGN KEY (`idUsers`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `reservationsguests`
--
ALTER TABLE `reservationsguests`
  ADD CONSTRAINT `fk_guests_has_reservations_guests1` FOREIGN KEY (`personsID`) REFERENCES `guests` (`idParties`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_guests_has_reservations_reservations1` FOREIGN KEY (`reservationsID`) REFERENCES `reservations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `rolepermissions`
--
ALTER TABLE `rolepermissions`
  ADD CONSTRAINT `fk_permissions_has_roles_permissions1` FOREIGN KEY (`idPermissions`) REFERENCES `permissions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_permissions_has_roles_roles1` FOREIGN KEY (`idRoles`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `roomcategoryprices`
--
ALTER TABLE `roomcategoryprices`
  ADD CONSTRAINT `fk_roomCategories_has_season_roomCategories1` FOREIGN KEY (`idRoomCategories`) REFERENCES `roomcategories` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_roomCategories_has_season_season1` FOREIGN KEY (`idSeasons`) REFERENCES `seasons` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `fk_rooms_categories1` FOREIGN KEY (`idRoomCategories`) REFERENCES `roomcategories` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `roomsroomoptions`
--
ALTER TABLE `roomsroomoptions`
  ADD CONSTRAINT `fk_room_has_roomoptions_room` FOREIGN KEY (`idRooms`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_room_has_roomoptions_roomoptions1` FOREIGN KEY (`idOptions`) REFERENCES `roomoptions` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `roomsroomstatus`
--
ALTER TABLE `roomsroomstatus`
  ADD CONSTRAINT `fk_rooms_has_roomStatus_rooms1` FOREIGN KEY (`idRooms`) REFERENCES `rooms` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_rooms_has_roomStatus_roomStatus1` FOREIGN KEY (`idRoomStatus`) REFERENCES `roomstatus` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `fk_services_serviceTypes1` FOREIGN KEY (`idServiceTypes`) REFERENCES `servicetypes` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `userroles`
--
ALTER TABLE `userroles`
  ADD CONSTRAINT `fk_role_has_employee_role1` FOREIGN KEY (`idRoles`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_role_has_employee_employee1` FOREIGN KEY (`idUsers`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
