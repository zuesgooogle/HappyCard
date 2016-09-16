-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: game_server
-- ------------------------------------------------------
-- Server version   5.6.22

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
-- Table structure for table `id_gen`
--

DROP TABLE IF EXISTS `id_gen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `id_gen` (
  `id` varchar(100) NOT NULL DEFAULT '' COMMENT '主键',
  `module_name` varchar(36) NOT NULL DEFAULT '' COMMENT '模块名称',
  `value` bigint(10) unsigned NOT NULL COMMENT 'id值',
  `prefix` varchar(10) NOT NULL COMMENT '前缀',
  `version` int(10) unsigned NOT NULL COMMENT '版本',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_account`
--

DROP TABLE IF EXISTS `role_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_account` (
  `id` varchar(36) NOT NULL,
  `user_role_id` varchar(36) NOT NULL COMMENT '角色唯一id',
  `tongqian` bigint(11) NOT NULL COMMENT '游戏币数量',
  `bind_lingshi` bigint(11) NOT NULL COMMENT '绑定元宝数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL,
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_role_account_user_role_id` (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_bag_slot`
--

DROP TABLE IF EXISTS `role_bag_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_bag_slot` (
  `id` varchar(36) NOT NULL,
  `slot_num` int(11) NOT NULL COMMENT '格子编号',
  `user_role_id` varchar(36) NOT NULL,
  `goods_id` varchar(36) NOT NULL COMMENT '物品ID',
  `count` int(36) DEFAULT '0' COMMENT '物品数量',
  `bind` int(11) NOT NULL DEFAULT '0' COMMENT '是否绑定',
  `expire_time` bigint(20) DEFAULT NULL COMMENT '物品过期时间毫秒数',
  `rare_level` int(11) DEFAULT NULL COMMENT '物品的稀有等级',
  `item_level` int(11) DEFAULT NULL COMMENT '物品等级',
  `attributes` text COMMENT '物品附加属性',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_role_bag_slot_user_role_id` (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_equip_slot`
--

DROP TABLE IF EXISTS `role_equip_slot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_equip_slot` (
  `id` varchar(36) NOT NULL COMMENT '主键',
  `slot_num` int(11) NOT NULL COMMENT '对应装备格子',
  `user_role_id` varchar(36) NOT NULL COMMENT '角色主键id',
  `goods_id` varchar(36) NOT NULL COMMENT '装备物品id',
  `count` int(36) DEFAULT '0' COMMENT '数量，始终为1',
  `bind` int(11) NOT NULL DEFAULT '0' COMMENT '是否绑定',
  `expire_time` bigint(20) DEFAULT NULL COMMENT '物品过期时间毫秒数',
  `rare_level` int(11) DEFAULT NULL COMMENT '物品的稀有等级',
  `item_level` int(11) DEFAULT NULL COMMENT '物品等级',
  `attributes` text COMMENT '物品附加属性',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_role_equip_slot_user_role_id` (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role_stage`
--

DROP TABLE IF EXISTS `role_stage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_stage` (
  `user_role_id` varchar(36) NOT NULL,
  `map_id` varchar(255) DEFAULT NULL,
  `map_x` int(11) DEFAULT NULL,
  `map_y` int(11) DEFAULT NULL,
  `hp` int(11) DEFAULT NULL,
  `mp` int(11) DEFAULT NULL,
  `max_hp` int(11) DEFAULT NULL,
  `max_mp` int(11) DEFAULT NULL,
  `buff` text,
  `props` text,
  `state` int(1) DEFAULT NULL,
  `map_node` text,
  `ti_li` int(11) DEFAULT '0',
  `line_no` int(11) DEFAULT '0',
  `pk_info` text,
  `shanbi_val` int(11) DEFAULT '0',
  `meiren_info` text,
  `zuoqi_info` text,
  `free_fly_count` int(11) DEFAULT '0' COMMENT '今日免费飞行次数',
  `fly_count_refresh_time` bigint(20) DEFAULT '0' COMMENT '免费飞行次数刷新时间',
  `copy_info` varchar(255) DEFAULT NULL,
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `server_info`
--

DROP TABLE IF EXISTS `server_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server_info` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开服时间',
  `prefix_id` varchar(20) DEFAULT NULL COMMENT '角色名前缀',
  `hefu_time` timestamp NULL DEFAULT NULL COMMENT '合服时间',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` varchar(48) NOT NULL COMMENT '主键，根据user_role表里面的 user_id和server_id加@号拼接',
  `user_guid` varchar(36) NOT NULL COMMENT '对应user_role表里面的user_id',
  `lingshi` bigint(11) NOT NULL COMMENT '元宝数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `server_id` varchar(36) DEFAULT NULL COMMENT '服务器id',
  `recive_yb` int(11) DEFAULT NULL COMMENT '可领取元宝，存储无充值的玩家，所有获得的元宝（此功能已经作废）',
  `is_recharge` int(11) DEFAULT NULL COMMENT '是否充值',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_account_index` (`user_guid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` varchar(36) NOT NULL COMMENT '角色唯一主键',
  `user_id` varchar(36) NOT NULL COMMENT '角色账号id',
  `name` varchar(36) NOT NULL COMMENT '角色名称',
  `job` varchar(36) NOT NULL COMMENT '职业',
  `sex` int(1) NOT NULL COMMENT '性别',
  `card` int(1) NOT NULL COMMENT '房卡',
  `face` varchar(64) DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `online_time` bigint(20) DEFAULT NULL COMMENT '上线时间',
  `offline_time` bigint(20) DEFAULT NULL COMMENT '下线时间',
  `server_id` varchar(36) DEFAULT NULL COMMENT '服务器id',
  `platform` varchar(36) DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `log_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_role_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-17  0:01:16
