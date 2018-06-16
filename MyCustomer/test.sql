/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.20 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `cid` char(32) NOT NULL,
  `cname` varchar(40) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `birthday` char(10) DEFAULT NULL,
  `cellphone` varchar(15) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_customer` */

insert  into `t_customer`(`cid`,`cname`,`gender`,`birthday`,`cellphone`,`email`,`description`) values ('B2052D4CB8D247DE81A63B9414D1EC86','zhangsan','男','2018-06-16','13572232331','destinyboom@163.com','我是张三'),('B7DD3993EB7543B2990A2E161F589953','lisi','女','2018-06-16','13572232332','destinyboom@163.com','我是李四');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
