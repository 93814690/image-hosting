/*
MySQL - 5.7.24 : Database - imagehosting
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`imagehosting` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `imagehosting`;

/*Table structure for table `SysPermission` */

DROP TABLE IF EXISTS `SysPermission`;

CREATE TABLE `SysPermission` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `resource_type` enum('menu','button') NOT NULL,
  `url` varchar(255) NOT NULL,
  `permission` varchar(255) NOT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `available` tinyint(1) NOT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `SysPermission` */

insert  into `SysPermission`(`pid`,`name`,`resource_type`,`url`,`permission`,`parent_id`,`available`) values 
(1,'权限测试','menu','/test3','test3',NULL,1);

/*Table structure for table `SysRole` */

DROP TABLE IF EXISTS `SysRole`;

CREATE TABLE `SysRole` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `available` int(1) NOT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `SysRole` */

insert  into `SysRole`(`rid`,`role`,`description`,`available`) values 
(1,'普通用户',NULL,1);

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `role_permission` */

insert  into `role_permission`(`id`,`rid`,`pid`) values 
(1,1,1);

/*Table structure for table `t_image` */

DROP TABLE IF EXISTS `t_image`;

CREATE TABLE `t_image` (
  `image_id` bigint(20) unsigned NOT NULL,
  `image_name` varchar(255) NOT NULL,
  `uid` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `url` varchar(255) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_image` */

/*Table structure for table `t_operation` */

DROP TABLE IF EXISTS `t_operation`;

CREATE TABLE `t_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator` varchar(255) NOT NULL,
  `operation_type` varchar(255) NOT NULL,
  `operation` varchar(255) NOT NULL,
  `operation_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_operation` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `register_time` datetime NOT NULL,
  `login_time` datetime NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user` */

insert  into `t_user`(`uid`,`username`,`password`,`salt`,`register_time`,`login_time`) values 
(9999,'test','d6d1265d6f9536c52e36a4081659c9f2','abcd','2019-01-04 19:02:41','2019-01-04 19:02:41');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`uid`,`rid`) values 
(1,9999,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
