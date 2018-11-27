/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.24 : Database - db_product
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_product` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_product`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL COMMENT '用户钱包主键',
  `user_id` varchar(64) DEFAULT NULL COMMENT '用户中心的用户唯一编号',
  `user_amount` decimal(10,5) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `pay_password` varchar(64) DEFAULT NULL,
  `is_open` int(11) DEFAULT NULL COMMENT '0:代表未开启支付密码，1:代表开发支付密码',
  `version` int(11) DEFAULT NULL COMMENT '基于mysql乐观锁，解决并发访问',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

insert  into `account`(`id`,`user_id`,`user_amount`,`create_time`,`update_time`,`pay_password`,`is_open`,`version`) values (1,'1','319.40800',NULL,NULL,'1',1,22);

/*Table structure for table `item` */

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(20) NOT NULL,
  `brand` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `supplier_id` int(20) DEFAULT NULL,
  `type_id` int(20) DEFAULT NULL,
  `simple_name` varchar(20) DEFAULT NULL,
  `version` int(10) NOT NULL,
  `delete_status` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `item` */

insert  into `item`(`id`,`brand`,`name`,`supplier_id`,`type_id`,`simple_name`,`version`,`delete_status`) values (1,'波导','波导230',1,1,'手机',0,0),(2,'海信','海信x50',2,1,'就用',0,0),(3,'诺基亚','诺基亚530',3,3,'普莱达',0,0),(4,'惠普','光影精灵',1,4,'好空调',0,0),(5,'惠普','暗影精灵',2,5,'格力造',0,0),(6,'惠普','绿刃',3,6,'核心科技',0,0);

/*Table structure for table `sku` */

DROP TABLE IF EXISTS `sku`;

CREATE TABLE `sku` (
  `id` int(20) NOT NULL,
  `item_id` int(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `price` decimal(20,0) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `size` int(20) DEFAULT NULL,
  `version` int(10) NOT NULL,
  `delete_status` int(10) NOT NULL,
  `status` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `size_index` (`size`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sku` */

insert  into `sku`(`id`,`item_id`,`name`,`price`,`color`,`size`,`version`,`delete_status`,`status`) values (1,1,'1','1','1',1,5,0,0),(2,1,'波导230','44','褐色',2,0,0,0),(3,2,'海信x50','34','黑色',1,0,0,0),(4,3,'诺基亚530','999','白色',1,0,0,0),(5,3,'诺基亚530','999','黑色',2,0,0,0),(6,4,'光影精灵','5334','白色',1,0,0,0),(7,4,'光影精灵','5339','白色',2,0,0,0),(8,5,'暗影精灵','9999','黑色',3,0,0,0),(9,6,'绿刃','5333','黑色',4,0,0,0),(10,6,'绿刃2','9999','黑色',1,0,0,0),(11,6,'格力手机','9999','黑色',1,0,0,0);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` int(20) NOT NULL,
  `sell_name` varchar(20) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `version` int(10) NOT NULL,
  `delete_status` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `supplier` */

insert  into `supplier`(`id`,`sell_name`,`address`,`version`,`delete_status`) values (1,'张三','上海市浦东新区',0,0),(2,'李四','上海市陆家嘴',0,0),(3,'王二','深圳市华强北',0,0);

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `id` int(20) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pid` int(20) DEFAULT NULL,
  `version` int(10) NOT NULL,
  `delete_status` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `type` */

insert  into `type`(`id`,`name`,`pid`,`version`,`delete_status`) values (1,'手机',0,1,0),(2,'电脑',0,1,0),(3,'小灵通',1,1,0),(4,'大哥大',1,1,0),(5,'平板电脑',2,1,0),(6,'台式电脑',2,1,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
