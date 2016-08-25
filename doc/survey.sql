-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.12-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.3.0.5043
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 wen_juan.survey_paper 结构
CREATE TABLE IF NOT EXISTS `survey_paper` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL COMMENT '调查问卷标题',
  `start_time` datetime NOT NULL COMMENT '调查问卷开始时间',
  `end_time` datetime NOT NULL COMMENT '调查问卷结束时间',
  `is_publish` tinyint(3) unsigned NOT NULL COMMENT '是否发布 0未发布 1已发布',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `is_del` tinyint(4) NOT NULL COMMENT '0未删除 1已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='调查问卷表';

-- 数据导出被取消选择。
-- 导出  表 wen_juan.survey_paper_question 结构
CREATE TABLE IF NOT EXISTS `survey_paper_question` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `paper_id` bigint(20) unsigned NOT NULL COMMENT 'survey_paper.id',
  `question_id` bigint(20) unsigned NOT NULL COMMENT 'survey_question.id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问卷试题关联表';

-- 数据导出被取消选择。
-- 导出  表 wen_juan.survey_question 结构
CREATE TABLE IF NOT EXISTS `survey_question` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL COMMENT '题干',
  `mode` tinyint(4) NOT NULL COMMENT '1单选 2多选 3问答题',
  `options` varchar(2000) DEFAULT NULL COMMENT '{order:1,option:"xx"} 问答题NULL',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0未删除 1已删除',
  `update_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='问卷试题表';

-- 数据导出被取消选择。
-- 导出  表 wen_juan.survey_statistics 结构
CREATE TABLE IF NOT EXISTS `survey_statistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `question_id` bigint(20) NOT NULL COMMENT '试题ID',
  `option_order` bigint(20) NOT NULL COMMENT '选项顺序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `survey_id` bigint(20) NOT NULL COMMENT '调查id',
  `ip` varchar(200) NOT NULL COMMENT '填写调查的ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='调查统计';

-- 数据导出被取消选择。
-- 导出  表 wen_juan.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL COMMENT '登录用户名',
  `password` varchar(200) NOT NULL COMMENT '密码',
  `login_time` datetime NOT NULL COMMENT '上一次登录时间',
  `create_time` datetime NOT NULL COMMENT '记录创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 数据导出被取消选择。
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
