CREATE TABLE user (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(20) NOT NULL COMMENT '登录账号',
  password VARCHAR(60) NOT NULL COMMENT '密码',
  headImage VARCHAR(60) NOT NULL DEFAULT 'D:\\chatter\\image',
  nickname VARCHAR(30) NOT NULL DEFAULT 'chatter',
  signature VARCHAR(255),
  gender VARCHAR(20) NOT NULL DEFAULT '未知性别',
  region VARCHAR(50) NOT NULL DEFAULT '未知地区',
  createTime DATETIME NOT NULL DEFAULT NOW(),
  state TINYINT DEFAULT '1' COMMENT '账号的状态, 1 为正常, 0为停用',
  phone VARCHAR(20) COMMENT '电话号码'
);

DESC user;

SELECT * FROM user;

DELETE FROM user WHERE id = 2;

UPDATE user SET nickname = "chatter", region = "hello world" WHERE id = 1;




