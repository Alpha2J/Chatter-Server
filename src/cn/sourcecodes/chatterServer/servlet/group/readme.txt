(三)群组模块
   1. 新建群组
   2. 解散群组
   3. 拉人进群
   4. t人出群
   5. 搜索群组
   6. 申请加入群组

群组模块:
实体ChatterGroup  表名chatterGroup(本来想用group的, 但是数据库中用group做表名报错, group应该是保留字或关键字)
字段名             java类型                       sql类型
id                int                           int not primary key auto_increment
account           String                        varchar(20) not null unique
headImage         String                        varchar(255) default '/chatterData/default/image/default-head-image.png'
groupName         String                        varchar(50) not null default 'untitled group'//姓名最长20个字符
notice            String//群公告                 varchar(255)
createTime        Date                          datetime not null default now()
state             int//群状态, 仅允许群主发言等     tinyint not null default 0
groupOwnerId      int//群主, 没有管理员           int not null
groupMember       List<Integer>//群成员


#群组和群成员的映射映射
CREATE TABLE chatterGroupMapping (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  chatterGroupId INT NOT NULL,
  chatterId INT NOT NULL
);

