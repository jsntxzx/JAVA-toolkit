-- ----------------------------
-- Table structure for `Seckill`
-- ----------------------------
DROP TABLE IF EXISTS `seckill`;
CREATE TABLE `seckill` (
  `seckill_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀数据表的自增主键',
  `product_name` varchar(120) NOT NULL COMMENT '秒杀商品名称',
  `product_number` int(11) NOT NULL COMMENT '秒杀商品剩余',
  `start_time` timestamp NOT NULL  COMMENT '秒杀开始时间',
  `end_time` timestamp NOT NULL  COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '秒杀活动创建时间',
  PRIMARY KEY (`seckill_id`),
  KEY `idx_pname` (`product_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2004 DEFAULT CHARSET=utf8 COMMENT='秒杀的数据表';

-- ----------------------------
-- Records of Seckill
-- ----------------------------
INSERT INTO `seckill` VALUES ('2000', '100元秒杀iphone5S', '100', '2016-08-02 00:00:00', '2016-08-03 00:00:00', '2016-08-02 21:43:48');
INSERT INTO `seckill` VALUES ('2001', '200元秒杀iphone6', '200', '2016-08-02 00:00:00', '2016-08-03 00:00:00', '2016-08-02 21:43:48');
INSERT INTO `seckill` VALUES ('2002', '250元秒杀iphone6S', '100', '2016-08-02 00:00:00', '2016-08-03 00:00:00', '2016-08-02 21:43:48');
INSERT INTO `seckill` VALUES ('2003', '300元秒杀iphone7', '200', '2016-08-02 00:00:00', '2016-08-03 00:00:00', '2016-08-02 21:43:48');

-- ----------------------------
-- Table structure for `success_record`
-- ----------------------------
DROP TABLE IF EXISTS `success_record`;
CREATE TABLE `success_record` (
  `seckill_id` bigint(20) NOT NULL COMMENT '秒杀商品主键',
  `phone` bigint(20) NOT NULL COMMENT '秒杀人',
  `time` timestamp NOT NULL  COMMENT '创建时间',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0代表未发货',
  PRIMARY KEY (`seckill_id`,`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='秒杀成功记录表';

-- ----------------------------
-- Records of success_record
-- ----------------------------