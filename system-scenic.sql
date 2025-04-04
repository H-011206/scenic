/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50557
Source Host           : localhost:3306
Source Database       : system-scenic

Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001

Date: 2024-10-15 20:43:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `scenic_id` int(11) NOT NULL,
  `customer` varchar(255) DEFAULT NULL COMMENT '评论人',
  `cimage` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  `star` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '3', '张三', '103846106577500.png', '这个旅游景点的特色在于它的自然景观和人文景观的完美结合。无论是风景如画的山水画卷，还是历史悠久的古迹，都能让游客沉浸在其中。', '2023-11-09 18:30:26', '2');
INSERT INTO `comment` VALUES ('37', '3', '张三', '103846106577500.png', '这座旅游景点给我留下了深刻的印象。那里有美丽的山川、壮观的海浪和独特的文化风情，让我感受到了无限的魅力。', null, '4');
INSERT INTO `comment` VALUES ('38', '3', '张三', '103846106577500.png', '我对这个旅游景点的印象非常深刻，无论是自然景色还是人文历史，都非常值得一游。', '2024-05-14 22:52:53', '1');
INSERT INTO `comment` VALUES ('39', '3', '张三', '103846106577500.png', '这个景区真是美得令人陶醉，每一处都充满了大自然的魅力。清新的空气、翠绿的树木、清澈的溪流，让人仿佛置身于仙境。', '2024-05-14 22:53:49', '5');
INSERT INTO `comment` VALUES ('40', '10013', '李四', '851947490499700.png', '哈看', '2024-06-13 16:04:34', null);
INSERT INTO `comment` VALUES ('41', '10002', '张三', '26179307739300.png', 'sdasdad', '2024-06-13 16:36:27', null);
INSERT INTO `comment` VALUES ('42', '10002', '张三', '26179307739300.png', 'aaaa', '2024-06-13 16:43:04', null);
INSERT INTO `comment` VALUES ('43', '10001', '张三', '26179307739300.png', '好看', '2024-06-20 15:40:02', null);
INSERT INTO `comment` VALUES ('44', '4', '张三', '103846106577500.png', '我简直不敢相信这个景点有多美！每一处都充满了生命力和活力，让人感到无比的愉悦。景点的历史背景和人文价值也给人们带来了很多启示和思考，让我们更加珍惜眼前的美好。', '2024-07-25 18:19:26', '5');
INSERT INTO `comment` VALUES ('45', '8', '张三', '103846106577500.png', '景色很美', '2024-07-26 08:42:15', null);
INSERT INTO `comment` VALUES ('46', '7', '张三', '147434661115700.png', '景色优美', '2024-07-26 15:00:57', '4');
INSERT INTO `comment` VALUES ('48', '3', '张三', '332176745800.png', 'awewe', '2024-09-03 13:41:20', '5');
INSERT INTO `comment` VALUES ('49', '5', '张三', '332176745800.png', '很好', '2024-09-05 11:02:02', '5');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `customer_name` varchar(255) NOT NULL COMMENT '用户姓名',
  `password` varchar(255) NOT NULL DEFAULT '666' COMMENT '‘666’',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `cimage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '张三', '202cb962ac59075b964b07152d234b70', '132213123213@qq.com', '1234567899999', '四川省成都市', '332176745800.png');
INSERT INTO `customer` VALUES ('4', '李四', '202cb962ac59075b964b07152d234b70', '647760595@qq.com', '19591581167', '陕西省西安市', '289713480700.jfif');
INSERT INTO `customer` VALUES ('5', '王五', '202cb962ac59075b964b07152d234b70', '647760595@qq.com', '19591581167111', '陕西省西安市', '300430292800.png');
INSERT INTO `customer` VALUES ('13', '张三aaaaa', '202cb962ac59075b964b07152d234b70', '', '', '', '254371788015300.jfif');

-- ----------------------------
-- Table structure for hotel
-- ----------------------------
DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `hname` varchar(255) DEFAULT NULL COMMENT '用户名',
  `city` varchar(255) DEFAULT NULL COMMENT '密码',
  `address` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `sid` int(255) DEFAULT NULL COMMENT '手机',
  `descr` varchar(255) DEFAULT NULL COMMENT '手机',
  `himage` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hotel
-- ----------------------------
INSERT INTO `hotel` VALUES ('3', '美豪酒店', '西安', '西安市雁塔区', '3', '美豪酒店管理股份有限公司是中国领先的专注于中高端酒店领域的民族酒店品牌,秉持着成为世界一流酒店公司,永远为顾客创造超值服务的企业愿景', '63714639725000.png');
INSERT INTO `hotel` VALUES ('4', '希岸酒店', '陕西', '西安市雁塔区a', '3', '酒店地处西安地标景区大雁塔南广场，大唐不夜城步行街内，临近大悦城商场和银泰商场，是大雁塔北广场到大唐芙蓉园的必经之路。与陕西特色美食，购物广场，音乐厅，美术馆，太平洋影城咫尺之遥，地理位置十分优越。', '64028574107600.png');
INSERT INTO `hotel` VALUES ('5', '汉庭酒店', '西安', '西安市雁塔区', '4', '汉庭酒店创立于2005年，是华住酒店集团的创始品牌。华住创始人季琦先生，在连续成功创办了“携程旅行网”(NASDAQ:CTRP)、“如家快捷酒店”(NASDAQ:HMIN) 两家纳斯达克上市公司之后，于2005年第三次创业，推出汉庭酒店，并曾一度以“汉庭”命名集团', '823685924100.png');

-- ----------------------------
-- Table structure for h_order
-- ----------------------------
DROP TABLE IF EXISTS `h_order`;
CREATE TABLE `h_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `cid` int(11) DEFAULT NULL COMMENT '顾客id',
  `rid` int(11) DEFAULT NULL COMMENT '菜品id',
  `start_time` datetime DEFAULT NULL COMMENT '下单日期',
  `end_time` datetime DEFAULT NULL COMMENT '下单日期',
  `total` double(255,2) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of h_order
-- ----------------------------
INSERT INTO `h_order` VALUES ('65', '1', '4', '2024-09-01 00:00:00', '2024-09-04 00:00:00', '369.00', '3', '2');
INSERT INTO `h_order` VALUES ('68', '1', '1', '2024-09-01 00:00:00', '2024-09-05 00:00:00', '1200.00', '4', '1');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `nname` varchar(255) NOT NULL COMMENT '角色名',
  `content` varchar(255) NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '文化和旅游部办公厅关于推行应用文化和旅游市场电子证照的通知', '近日，文化和旅游部办公厅发布关于推行应用文化和旅游市场电子证照的通知。');
INSERT INTO `notice` VALUES ('4', '关于推动非物质文化遗产与旅游深度融合发展的通知', '近日，文化和旅游部发布关于推动非物质文化遗产与旅游深度融合发展的通知。');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rname` varchar(255) DEFAULT NULL COMMENT '用户名',
  `wifi` int(255) DEFAULT NULL COMMENT '密码',
  `bed` int(255) DEFAULT NULL COMMENT '邮箱',
  `big` int(255) DEFAULT NULL COMMENT '手机',
  `weiyu` int(255) DEFAULT NULL,
  `tv` int(255) DEFAULT NULL,
  `hid` int(255) DEFAULT NULL,
  `rimage` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('1', '湖景大床房', '1', '2', '80', '0', '1', '3', 'Snipaste_2024-07-19_11-37-47.png', '300.00');
INSERT INTO `room` VALUES ('3', '高级双床房', '1', '1', '68', '1', '0', '3', '68893930440000.png', '134.00');
INSERT INTO `room` VALUES ('4', '豪华单人间', '1', '1', '68', '1', '0', '4', '183777016398000.png', '123.00');

-- ----------------------------
-- Table structure for route
-- ----------------------------
DROP TABLE IF EXISTS `route`;
CREATE TABLE `route` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '读者id',
  `rname` varchar(255) NOT NULL COMMENT '读者姓名',
  `rtime` varchar(255) NOT NULL COMMENT '密码',
  `day` int(255) DEFAULT NULL COMMENT '电话',
  `descr` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `rimage` varchar(255) DEFAULT NULL COMMENT '喜欢的书籍类型',
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of route
-- ----------------------------
INSERT INTO `route` VALUES ('1', '大悦城', '7:00-21:00', '1', '大悦城，是集大型购物中心、甲级写字楼、服务公寓、高档住宅等为一体的城市综合体，实现购物、娱乐、观光、休闲、餐饮等功能，节约资源，高效运营城市生活，成为推动城市发展的重要力量。大悦城已成为中粮集团城市综合体的核心品牌', 'Snipaste_2024-07-23_16-47-40.png', '3');
INSERT INTO `route` VALUES ('3', '盛唐密盒', '7:00-21:00', '2', '“盛唐密盒”是由西安大唐不夜城景区推出的表演活动，该表演由两位扮演“房玄龄”和“杜如晦”的工作人员出题，并随机挑选游客上台参与答题。“房谋杜断”组合配合默契、谈吐风趣，与游客的互动更是“爆梗”不断，表演视频也在网络上受到广泛热议。', '2011322554100.png', '3');
INSERT INTO `route` VALUES ('4', '华灯太白', '7:00-21:00', '3', '‌华灯太白是大唐不夜城步行街区的一项互动式表演艺术，以‌李白为核心人物，通过互动对诗的形式，让游客参与到表演中，共同创作诗歌，营造出沉浸式的文化体验。表演中，李白在华灯之上边咏诗边与游客互动，通过旋转的灯台和舒展的动作，将游客带入李白诗歌创作的现场，仿佛成为与他一起吟诗作对的文人雅士。', '2642106079700.png', '3');
INSERT INTO `route` VALUES ('5', '长空栈道', '7:00-22:00', '1', '长空栈道位于华山南峰东侧山腰，是华山派第一代宗师元代高道贺志真为远离尘世静修成仙，在万仞绝壁上镶嵌石钉搭木椽而筑。栈道上下皆是悬崖绝壁，铁索横悬，由条石搭成尺许路面，下由石柱固定，游人至此，面壁贴腹，屏气挪步，被誉为“华山第一天险”。', '2811833870700.png', '4');
INSERT INTO `route` VALUES ('6', '鹞子翻身', '7:00-22:00', '2', '鹞子翻身位于渭南市华阴市华山风景区内，在华山东峰，是通往下棋亭的必由之路，为华山著名的险道之一。其路凿于倒坎悬崖上，下视唯见寒索垂于凌空，不见路径。游人至此，须面壁挽索，以脚尖探寻石窝，交替而下', '2962767467900.png', '4');
INSERT INTO `route` VALUES ('7', '华阴市区', '7:00-22:00', '3', '有许多美食可供选择，如华阴大刀面、凉皮、羊肉泡馍等', '3129083392800.png', '4');

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sname` varchar(255) DEFAULT NULL COMMENT '用户名',
  `country` varchar(255) DEFAULT NULL COMMENT '密码',
  `address` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `stime` varchar(255) DEFAULT NULL COMMENT '手机',
  `price` double(15,2) DEFAULT NULL COMMENT '手机',
  `type` varchar(255) DEFAULT NULL,
  `simage` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES ('3', '大唐不夜城·', '中国', '西安市雁塔区', '周一至周四  7:00--23:00', '32.00', '历史遗迹', '11471675446900.png', '大唐不夜城步行街，位于陕西省西安市雁塔区的大雁塔脚下，始建于2002年8月，北起大雁塔北广场，南至开元广场 [39-40]，东起慈恩东路，西至慈恩西路，街区南北长2100米，东西宽500米，总建筑面积65万平方米 [1] [27-29]，是全国唯一一个以盛唐文化为背景的大型仿唐建筑群步行街，为西安地标性景区');
INSERT INTO `scenic` VALUES ('4', '华山', '中国', '陕西省华阴市', '周一至周五  7:00--23:00', '66.00', '自然风光', '55395721126600.png', '华山');
INSERT INTO `scenic` VALUES ('5', '金字塔', '埃及', '开罗西南10公里处的吉萨区', '周一至周五  7:00--23:00', '123.00', '历史遗迹', '55535218353500.png', 'wdq');
INSERT INTO `scenic` VALUES ('6', '马尔代夫', '马尔代夫', '马尔代夫共和国', '周一至周五  7:00--23:00', '321.00', '自然风光', '55917437006500.png', '马尔代夫共和国（The Republic of Maldives，原名马尔代夫群岛），简称“马尔代夫”(Maldives)，印度洋上的群岛国家。距离印度南部约600公里，距离斯里兰卡西南部约750公里。26组自然环礁、1192个珊瑚岛分布在9万平方公里的海域内，其中约200个岛屿有人居住');
INSERT INTO `scenic` VALUES ('7', '耶稣山·', '巴西', '里约热内卢', '周一至周五  7:00--23:00', '321.00', '历史遗迹', '56705738876900.png', '耶稣山，正式名为科科瓦多山，位于巴西第二大城市里约热内卢。这座山丘高709米，是观赏里约美景的最佳地点。其山顶塑有一座巨大的耶稣像，高达38米，宽28米，重达一千一百四十五吨。这座雕像面向大西洋，张开双臂，仿佛一个巨大的十字架，显得庄重、威严。在巴西人眼中，耶稣山不仅是里约的象征，更是整个巴西的象征。');
INSERT INTO `scenic` VALUES ('8', '富士山', '日本', '日本本州岛中南部', '周一至周五  7:00--23:00', '123.00', '自然风光', '58153965832700.png', '富士山是日本精神、文化的经典象征之一， [3]在日本人的心中是一座蕴含着自然魅力，优美、庄严的神山。自古以来一直是日本文学者讴歌的主题，同时也是日本人崇敬的圣地，是日本民俗宗教的一部分。 [4]富士山山体呈圆锥状，山顶长年积雪。富士山山麓周围，分布着五个淡水湖，统称“富士五湖“。');
INSERT INTO `scenic` VALUES ('9', '纽约城', '美国', '美国东北部沿海哈德逊河口', '24小时开放', '0.00', '城市风光', '58418480851600.png', '纽约（英语：New York），隶属于美国纽约州，美国第一大城市，位于美国东北部沿海哈德逊河口，濒临大西洋，属温带大陆性湿润气候。总面积1214平方千米，下辖5个区，市政厅驻曼哈顿市政厅公园内。 [22]截至2022年，总人口约834万人。');
INSERT INTO `scenic` VALUES ('10', '极光', '中国', '黑龙江漠河', '24小时开放', '321.00', '天象气候', '58863722853000.png', '主要指千变万化的气象景观、天气现象以及不同地区的气候资源所构成的丰富多彩的气候天象景观');
INSERT INTO `scenic` VALUES ('11', '夏威夷', '美国', '夏威夷州', '周一至周五  7:00--23:00', '321.00', '自然风光', '59748584736900.png', '夏威夷州（Hawaii State）是美国唯一的群岛州，由太平洋中部的132个岛屿组成。首府位于瓦胡岛上的火奴鲁鲁（檀香山）。夏威夷州是美国唯一的群岛州，由太平洋中部的132个岛屿组成。陆地面积为1.67万平方千米。夏威夷属于海岛型气候，终年有季风调节，每年温度约在摄26℃-31℃。');

-- ----------------------------
-- Table structure for score_scenic
-- ----------------------------
DROP TABLE IF EXISTS `score_scenic`;
CREATE TABLE `score_scenic` (
  `user_id` int(11) DEFAULT NULL,
  `scenic_id` int(11) DEFAULT NULL,
  `score` int(255) DEFAULT NULL,
  `time` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of score_scenic
-- ----------------------------
INSERT INTO `score_scenic` VALUES ('1', '3', '5', null);
INSERT INTO `score_scenic` VALUES ('1', '4', '3', null);
INSERT INTO `score_scenic` VALUES ('4', '3', '3', null);
INSERT INTO `score_scenic` VALUES ('4', '4', '5', null);
INSERT INTO `score_scenic` VALUES ('4', '5', '5', null);
INSERT INTO `score_scenic` VALUES ('4', '6', '5', null);
INSERT INTO `score_scenic` VALUES ('4', '7', '5', null);
INSERT INTO `score_scenic` VALUES ('4', '8', '4', null);
INSERT INTO `score_scenic` VALUES ('4', '9', '5', null);
INSERT INTO `score_scenic` VALUES ('4', '10', '5', null);
INSERT INTO `score_scenic` VALUES ('4', '11', '5', null);
INSERT INTO `score_scenic` VALUES ('5', '3', '5', null);
INSERT INTO `score_scenic` VALUES ('5', '4', '5', null);
INSERT INTO `score_scenic` VALUES ('5', '5', '4', null);
INSERT INTO `score_scenic` VALUES ('5', '6', '5', null);
INSERT INTO `score_scenic` VALUES ('5', '7', '2', null);
INSERT INTO `score_scenic` VALUES ('5', '8', '5', null);
INSERT INTO `score_scenic` VALUES ('5', '9', '3', null);
INSERT INTO `score_scenic` VALUES ('5', '10', '4', null);
INSERT INTO `score_scenic` VALUES ('5', '11', '3', null);
INSERT INTO `score_scenic` VALUES ('6', '3', '4', null);
INSERT INTO `score_scenic` VALUES ('6', '4', '4', null);
INSERT INTO `score_scenic` VALUES ('6', '5', '3', null);
INSERT INTO `score_scenic` VALUES ('6', '6', '3', null);
INSERT INTO `score_scenic` VALUES ('6', '7', '2', null);
INSERT INTO `score_scenic` VALUES ('6', '8', '4', null);
INSERT INTO `score_scenic` VALUES ('6', '9', '4', null);
INSERT INTO `score_scenic` VALUES ('6', '10', '2', null);
INSERT INTO `score_scenic` VALUES ('7', '3', '4', null);
INSERT INTO `score_scenic` VALUES ('7', '4', '5', null);
INSERT INTO `score_scenic` VALUES ('7', '5', '2', null);
INSERT INTO `score_scenic` VALUES ('7', '6', '5', null);
INSERT INTO `score_scenic` VALUES ('7', '7', '4', null);
INSERT INTO `score_scenic` VALUES ('7', '8', '5', null);
INSERT INTO `score_scenic` VALUES ('7', '9', '3', null);
INSERT INTO `score_scenic` VALUES ('7', '10', '4', null);
INSERT INTO `score_scenic` VALUES ('7', '11', '3', null);
INSERT INTO `score_scenic` VALUES ('8', '3', '4', null);
INSERT INTO `score_scenic` VALUES ('8', '4', '5', null);
INSERT INTO `score_scenic` VALUES ('8', '5', '4', '2024-05-14');
INSERT INTO `score_scenic` VALUES ('8', '6', '3', '2024-05-21');
INSERT INTO `score_scenic` VALUES ('8', '7', '2', '2024-06-13');
INSERT INTO `score_scenic` VALUES ('8', '8', '5', '2024-06-13');
INSERT INTO `score_scenic` VALUES ('8', '9', '5', '2024-06-13');
INSERT INTO `score_scenic` VALUES ('8', '10', '5', '2024-06-20');
INSERT INTO `score_scenic` VALUES ('1', '7', '1', '2024-07-26');
INSERT INTO `score_scenic` VALUES (null, '4', '2', '2024-09-02');
INSERT INTO `score_scenic` VALUES ('1', '3', '5', '2024-09-03');
INSERT INTO `score_scenic` VALUES ('1', '5', '5', '2024-09-05');

-- ----------------------------
-- Table structure for s_order
-- ----------------------------
DROP TABLE IF EXISTS `s_order`;
CREATE TABLE `s_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `cid` int(11) DEFAULT NULL COMMENT '顾客id',
  `sid` int(11) DEFAULT NULL COMMENT '菜品id',
  `start_time` datetime DEFAULT NULL COMMENT '下单日期',
  `end_time` datetime DEFAULT NULL COMMENT '下单日期',
  `total` double(255,2) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `sid` (`sid`),
  CONSTRAINT `s_order_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `customer` (`id`),
  CONSTRAINT `s_order_ibfk_2` FOREIGN KEY (`sid`) REFERENCES `scenic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_order
-- ----------------------------
INSERT INTO `s_order` VALUES ('1', '1', '3', '2024-07-22 17:31:01', '2024-07-27 17:31:04', '64.00', '2', '2');
INSERT INTO `s_order` VALUES ('58', '1', '3', null, null, null, null, '3');
INSERT INTO `s_order` VALUES ('59', '1', '4', null, null, null, null, '3');
INSERT INTO `s_order` VALUES ('60', '1', '8', null, null, null, null, '3');
INSERT INTO `s_order` VALUES ('91', '1', '5', '2024-09-01 00:00:00', '2024-09-05 00:00:00', '246.00', '2', '1');
INSERT INTO `s_order` VALUES ('93', '1', '7', null, null, null, null, '3');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `type` varchar(255) NOT NULL COMMENT '类型名称',
  `feature` varchar(255) NOT NULL COMMENT '类型特征',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '自然风光', '自然风光是自然界中各种景观的总称，‌包括山川、‌河流、‌森林、‌草原、‌沙漠、‌海洋等自然元素和景观。‌这些景观不仅具有极高的美学价值，‌还是地球生态系统的重要组成部分，‌对人类的生存和发展有着不可替代的作用。‌');
INSERT INTO `type` VALUES ('2', '历史遗迹', '历史遗迹是古代人类活动留下的遗迹，包括各种建筑群体、村寨、城堡、烽燧等建筑残迹，以及对自然环境的利用和加工而遗留的场所。这些遗迹大多湮没已久，有的成为沙漠中的废墟。通过对各种类型的古遗址的调查发掘，可以揭示许多古代社会的面貌，进而考察有关的社会状况。中国已公布为各级文物保护单位的古遗址在千处以上，其中全国重点文物保护单位有85处，包括各种有代表性的遗址。‌');
INSERT INTO `type` VALUES ('4', '城市美景', '城市美景是城市的重要组成部分，‌它们不仅为居民提供了休闲和娱乐的场所，‌也是游客向往的旅游目的地');
INSERT INTO `type` VALUES ('8', '天象气候', '天象主要指的是天文学领域中的各种天文现象，‌包括行星的位置、‌相合、‌凌日、‌凌月等，‌以及日月食、‌彗星、‌流星雨等天文事件。‌这些现象的发生和变化，‌不仅展示了宇宙的奥秘，‌也为人类提供了观测和研究的对象。‌例如，‌11月份的夜间星空，‌可以看到土星、‌木星等行星的位置变化，‌以及南鱼座的北落师门等亮星的分布。‌这些观察不仅有助于我们了解宇宙的结构和运动规律，‌也是天文学教育和普及的重要内容');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `phone` varchar(255) NOT NULL COMMENT '手机',
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '202cb962ac59075b964b07152d234b70', '234760595678@qq.com', '312321321321', '80349826673600.jfif');
