-- MySQL dump 10.13  Distrib 5.6.23, for Win32 (x86)
--
-- Host: localhost    Database: nuevabd
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `elementos`
--

DROP TABLE IF EXISTS `elementos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `elementos` (
  `idelemento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `tipoElem` varchar(45) NOT NULL,
  PRIMARY KEY (`idelemento`),
  KEY `a_idx` (`tipoElem`),
  CONSTRAINT `a` FOREIGN KEY (`tipoElem`) REFERENCES `tipos` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elementos`
--

LOCK TABLES `elementos` WRITE;
/*!40000 ALTER TABLE `elementos` DISABLE KEYS */;
INSERT INTO `elementos` VALUES (1,'Sony VPL-HW40ES','Proyector'),(2,'Philips PPX3411','Proyector'),(3,'Epson EH-TW5200','Proyector'),(4,'Epson EB-S03','Proyector'),(5,'Macbook Pro 13.3','Notebook'),(6,'Lenovo I5','Notebook'),(7,'Asus I3','Notebook'),(8,'Dell Inspiron','Notebook'),(9,'Cochera 03','Cochera'),(10,'Cochera 04','Cochera'),(11,'Cochera 05','Cochera'),(12,'Cochera 06','Cochera'),(13,'Multifunción EPSON XP-241','Impresora'),(14,'Laser Samsung SL-M2020W/XBG','Impresora'),(15,'Laser Brother DCP-1617NW','Impresora'),(16,'Multifunción HP DESKJET 2135','Impresora'),(17,'Aula 110','Aula'),(18,'Aula 210','Aula'),(19,'Laboratorio Física','Aula'),(20,'Sala de computación','Aula');
/*!40000 ALTER TABLE `elementos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `idpersona` int(11) NOT NULL AUTO_INCREMENT,
  `dni` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `apellido` varchar(45) NOT NULL,
  `habilitado` tinyint(1) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `contraseña` varchar(45) NOT NULL,
  `categoria` varchar(45) NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE KEY `idpersona_UNIQUE` (`idpersona`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES (1,'12121212','Juan','Perez',1,'jperez','jperez1','usuario'),(2,'13131313','Carlos','Gomez',0,'cgomez','cgomez1','usuario'),(3,'14141414','Hernan','Caparros',1,'hcaparros','hcaparros1','admin'),(4,'15151515','Walter','Dominguez',1,'wdominguez','wdominguez1','encargado');
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservas`
--

DROP TABLE IF EXISTS `reservas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservas` (
  `idreservas` int(11) NOT NULL AUTO_INCREMENT,
  `nomTipo` varchar(45) NOT NULL,
  `idElementos` int(11) NOT NULL,
  `fechayhora` datetime NOT NULL,
  `tiempoUso` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `detalle` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idreservas`),
  KEY `t_idx` (`nomTipo`),
  KEY `u_idx` (`idUsuario`),
  KEY `e_idx` (`idElementos`),
  CONSTRAINT `e` FOREIGN KEY (`idElementos`) REFERENCES `elementos` (`idelemento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t` FOREIGN KEY (`nomTipo`) REFERENCES `tipos` (`nombre`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `u` FOREIGN KEY (`idUsuario`) REFERENCES `personas` (`idpersona`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservas`
--

LOCK TABLES `reservas` WRITE;
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` VALUES (1,'Cochera',11,'2017-12-18 07:00:00',168,4,'Reserva Decano Fin de Año'),(2,'Impresora',13,'2017-11-01 10:00:00',2,1,NULL),(3,'Impresora',13,'2017-11-08 10:00:00',2,1,NULL),(6,'Aula',20,'2017-11-10 18:00:00',2,3,'Clase Java'),(7,'Aula',18,'2017-10-27 19:00:00',4,3,'Parcial'),(8,'Notebook',5,'2017-06-15 10:00:00',1,1,NULL);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipos` (
  `idtipo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `cantMaxRes` int(11) NOT NULL,
  `horasMax` int(11) DEFAULT NULL,
  `maxDiasAnticip` int(11) NOT NULL,
  `soloEncarg` tinyint(1) NOT NULL,
  PRIMARY KEY (`idtipo`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES (1,'Proyector',3,3,21,0),(2,'Notebook',1,0,28,1),(3,'Cochera',2,0,0,0),(4,'Impresora',2,0,0,0),(5,'Aula',2,3,0,0),(7,'Pruebaa',5,4,4,0);
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-29  3:07:56
