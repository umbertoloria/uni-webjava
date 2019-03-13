-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorie`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--


--
-- Table structure for table `prodotti`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `prodotti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `prezzo` decimal(5,2) NOT NULL,
  `produttore` int(11) NOT NULL,
  `immagine` varchar(500) NOT NULL,
  `descrizione` text NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produttore` (`produttore`),
  CONSTRAINT `prodotti_ibfk_1` FOREIGN KEY (`produttore`) REFERENCES `produttori` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotti`
--

INSERT INTO `prodotti` VALUES (1,'Sonor 507',550.00,1,'https://d1aeri3ty3izns.cloudfront.net/media/9/9349/1200/preview.jpg','Ottima batteria per cominciare!'),(2,'Fender Stratocaster',900.00,2,'https://dzpybaqldk5xx.cloudfront.net/prod/spree/1086/products/974/product/EKO_-_S-300_SUNBURST_MODELLO_FENDER_STRATOCASTER_01.jpg?1506678297','Chitarra assolutamente epica.'),(3,'CS5 Natural',49.00,3,'https://www.strumentimusicali.net/images/product/600x450/2017/09/25/24/eko-cs5nat-1.jpg','La CS 5 è una chitarra classica con body da 36\'\', top, fondo e fasce in Tiglio. Il manico e la tastiera sono invece in Betulla.'),(4,'S300 Sunburst',99.00,3,'https://images-na.ssl-images-amazon.com/images/I/71wtrMlDdTL._SX466_.jpg','configurazione dei pickup S/S/S, due toni, un volume ed un selettore a 5 posizioni.'),(5,'AG1 Blue',69.00,4,'https://cdn1.bigcommerce.com/server1000/e1ff9/products/140/images/1410/CG183SBL__79363.1544574408.900.700.jpg?c=2','La selezione dei materiali e la precisione dei dettagli ne fa emergere la notevole sonorità della cassa armonica'),(6,'TMB30 BK Black',170.00,5,'https://www.strumentimusicali.net/images/product/600x450/2016/11/15/17/ibanez-tmb30bk.jpg','BASSO ELETTRICO NERO La nuova serie di bassi Talman combina il riemergere di una forma storica '),(7,'Passenger Acoustic',285.00,6,'https://www.strumentimusicali.net/images/product/600x450/2018/08/27/f0/kala-ubasswandererfs-3.jpg','BASSO ACUSTICO ELETTRIFICATO A SCALA RIDOTTA CON BORSA'),(8,'Marcus Miller',298.00,7,'https://www.woodbrass.com/images/SQUARE400/woodbrass/MARCUSMILLER+MM086K.JPG',' linea di bassi Marcus Miller con un ottimo suono, un look fantastico ed una qualità mai vista ad un prezzo simile.'),(9,'Junior Blu',179.00,8,'https://www.strumentimusicali.net/images/product/600x450/2018/06/21/be/batteria-acusticajunior-2.jpg','BATTERIA ACUSTICA JUNIOR COMPLETA DI HARDWARE E PIATTI BLU'),(10,'SamplePAd 4',155.00,9,'https://7132afa424c2f1a2ab6d-54d68a14e2e7c1f76563a2d8c3e9fd82.ssl.cf2.rackcdn.com/1489/images/SamplePad4_Angle-Left_RGB.png','PERCUSSIONE A 4 PAD CON INGRESSI TRIGGER Alesis SamplePad 4 '),(11,'JDR5 Red',199.00,4,'https://www.erretimusica.it/7743-large_default/darestone-jdr5-red.jpg','BATTERIA JUNIOR 5 PEZZI ROSSA COMPLETA DI ACCESSORI '),(12,'Performer Black',259.00,10,'https://www.strumentimusicali.net/images/product/600x450/2019/03/04/f0/artesia-performerblack-1.jpg','PIANOFORTE DA PALCO 88 TASTI PESATI NERO'),(13,'RP25 stage digital piano',248.00,11,'https://www.strumentimusicali.net/images/product/600x450/2017/11/02/6b/ringway-rp20-5.jpg','PIANOFORTE DIGITALE 88 TASTI PESATI NERO'),(14,'SA46',44.90,12,'https://www.strumentimusicali.net/images/product/600x450/2017/05/18/ff/casio-sa46-2.jpg','TASTIERA PORTATILE 32 TASTI MINI Più che un giocattolo'),(15,'DM800',20.00,13,'https://images-na.ssl-images-amazon.com/images/I/41nRRbAIMML._SX425_.jpg','MICROFONO CARDIOIDE PER VOCE '),(16,'XM8500',30.00,14,'https://www.bhphotovideo.com/images/images500x500/Behringer_Behringer_XM8500_15_XLR_Cable_Foam_890290.jpg','MICROFONO CARDIOIDE DINAMICO');

--
-- Table structure for table `produttori`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `produttori` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produttori`
--

INSERT INTO `produttori` VALUES (1,'Sonor'),(2,'Fender'),(3,'EKO'),(4,'Datestore'),(5,'Ibanez'),(6,'Kala U-BASS'),(7,'Sire'),(8,'Beat'),(9,'Alesis'),(10,'Artesia'),(11,'Ringway'),(12,'Casio'),(13,'Proel'),(14,'Behringer');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-13 15:39:20
