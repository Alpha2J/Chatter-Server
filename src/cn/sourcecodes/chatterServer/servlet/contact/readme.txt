(二)好友模块
   1. 添加好友
        a. 按账号添加
        b. 按手机号添加
   2. 删除好友
   3. 查看好友基本信息(这个在程序初始化时会返回好友信息的, 不请求服务器了)
   4. 修改好友备注
   5. 查找自己好友(本地查找)
        a. 账号查找
        b. 手机号查找
        c. 昵称查找


1. chatter/contact 需要有method参数, 如果method参数为空, 返回{"action" : "1001" , "msg" : "参数解析错误: method参数缺失!"},
   method参数只能为addContact或者deleteContact或logOut或checkAccountAvailable, 其他的参数都返回{"action" : "1001" , "msg" : "参数解析错误: 非法参数!"}
method参数
1. 添加好友
   a. 客户端选择要添加的好友, 填写验证信息, 点击发送  chatter/contact/addContact  post
   b. 服务端收到添加好友的消息, 解析, 将消息存入消息表, content字段写{"chatterId" : "", "contactId" : "", "contactGroupTypeId" : "", "msg" : ""}
   c. 另外一个用户收到添加好友的消息后, 解析content字段, 到handleAddContact
      chatterId参数, contactId 参数 contactGroupTypeId参数, isAdd参数
      判断各个参数合法性, 如果添加, 那么将这条记录写入数据库, a添加b为好友, b添加a为好友(双向写入

   参数: chatterId 必须, 且是数字
        contactId 必须, 且是数字
        contactGroupTypeId  可为空, 为空就是默认分组
         添加成功: 添加失败:















实体:
Contact(联系人)
数据库只做映射, 建表语句
CREATE TABLE contactMapping (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  chatterId INT NOT NULL,
  contactId INT NOT NULL COMMENT '其实也是chatterId, 只是名字不同',
  contactGroupTypeId INT NOT NULL DEFAULT 1 COMMENT '1表示默认分组, 要确保这张表中的id为1的数据为默认分组',
  remark VARCHAR(50)
);

#分组类型(每个人的分组的名字)
CREATE TABLE contactGroupType (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  chatterId INT NOT NULL DEFAULT -1 COMMENT 'chatterId 没有-1的, -1的话要么是测试数据, 要么是id为0的默认值',
  typeName VARCHAR(50) NOT NULL DEFAULT '默认分组'
);

