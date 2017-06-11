(二)好友模块
   1. 添加好友
        a. 按账号添加
        b. 按手机号添加
   2. 删除好友
   3. 查看好友基本信息(这个在程序初始化时会返回好友信息的, 不请求服务器了, 但是留接口出来)
   4. 查找好友
        a. 按账号查找
        b. 按手机号查找
   5. 修改好友备注
   6. 查找自己好友(客户端本地查找)
        a. 账号查找
        b. 手机号查找
        c. 昵称查找

   chatter/contact 需要有method参数, 如果method参数为空, 返回{"action" : "1001" , "msg" : "参数解析错误: method参数缺失!"},
   method参数只能为addContact或者deleteContact或getContact或resetRemark或findContact, 其他的参数都返回{"action" : "1001" , "msg" : "参数解析错误: 非法参数!"}
1. 查找好友 chatter/contact method参数为findChatter
   所有参数:
       findStr   查找的字符串
   查找到返回

2. 添加好友 chatter/contact  method参数为addContact
   所有参数:
       contactId 必须, 且是数字
       contactGroupTypeId  可为空, 为空就是默认分组
       validationMsg  可为空

   a. 客户端要添加的好友, 填写验证信息, 点击发送  chatter/contact  post
   b. 服务端接收到请求后解析参数, 如果必须参数存在且合法, 执行c, 否则返回
   c. 服务端判断session中是否存在"chatter" 的属性, 如果存在说明已经登录, 判断参数中id值是否和属性中的一样, 一样执行d, 不一样返回
   d. 将消息存入消息表content字段写{"contactGroupTypeId" : "", "validationMsg" : ""}, sendId为当前的chatterId, receivedId 为contactId
   e. 执行f:
   f. 另一个用户收到添加好友的消息后, 解析content字段, 发送是接受好友的消息到chatter/contact  method参数为handleAddContact
      参数有:
           chatterId(自己的id, 对应上面被添加者的id)必须
           contactId(对应上面添加者的id)必须
           isAccept必须
   g. 收到处理添加好友的回复后, 判断合法性, 判断isAccept true执行添加逻辑, msg: 成功添加, false 不执行添加逻辑, msg: 对方拒绝添加


   c. 另外一个用户收到添加好友的消息后, 解析content字段, 到handleAddContact
      chatterId参数, contactId 参数 contactGroupTypeId参数, isAdd参数
      判断各个参数合法性, 如果添加, 那么将这条记录写入数据库, a添加b为好友, b添加a为好友(双向写入

   参数:















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
