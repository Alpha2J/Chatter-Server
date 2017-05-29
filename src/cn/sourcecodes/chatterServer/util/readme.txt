AccountGenerator类在数据库会有一张表, 账号(纯数字)生成流程:
1. 生成一个20个数字以内的纯数字的账号
2. 用这个账号到数据库查找:
   a. 如果没有找到, 说明账号可用, 返回这个账号
   b. 如果找到, 说明这个账号已存在不可用, 执行 1


account表:
CREATE TABLE accountCheck (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  account VARCHAR(20) NOT NULL
);

