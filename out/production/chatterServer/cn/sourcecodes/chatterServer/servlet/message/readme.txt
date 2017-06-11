(四)消息模块
   1. 消息接收(服务端接收客户端发送过来的消息)
      a. 消息类型:
         1) 私聊
         /message/sendMessage/privateMessage
         2) 群聊
         /message/sendMessage/groupMessage
         2) 添加好友消息:
            a. 添加好友请求消息
            b. 添加好友确认消息
         3) action消息

   2. 消息发送
   3. 消息检测(包括消息的同步机制)









消息同步机制(说明: 不允许允许单用户多地登录)
初始化:
1. 用户登录时, 账号和密码验证成功后
2. 获取ServletContext对象, 用这个对象的getAttribute("chatterNotifierMap")获得在ServletContext初始化时传入的Map<Integer, MessageNotifier>类型对象
3. 查询这个Map里面是否有key为刚才验证用过的账号的id
   a. 如果有, 说明还在另外一个地方登录或者上次登录没有执行退出, 还存在相应的session, 要等最多10分钟才能登录
   b. 如果没有, 说明允许登录, 执行 步骤4
4. 将用户的chatterId作为键, chatter对象作为值, 放到session中.
5. 用chatterId获取上次的消息记录信息, 封装成对象
6. 将这个对象作为值存到上面获得的map中(chatterId作为key)
7. 消息同步初始化完成

客户端获取
8. 客户端每次发送一个get请求到 /chatter/message/newMsgCheck, 如果客户端没有登录, 不做处理, 如果已经登录: map中获取对应的MessageNotifier对象,
调用这个对象的checkAndGetLastMessageId()(这个不方法不加synchronize, 获取到早一点的消息的id都没问题的, 这次获取不到的下次可以获取), 返回消息:
   a. 如果返回没有新消息的json数据, 则继续做同步检测
   b. 如果返回有新消息的json数据, 那么到 chatter/message/getMessage 获取消息
      1) 服务器从数据库中获得消息的list后, 获得这个list中id最大的消息
      2) 用上面那个map获取对应的chatterId的MessageNotifier对象, 调用这个MessageNotifier对象的setLastAccessMessage(int messageId) 方法设置上次访问
      的最后一个消息(这个方法是同步方法, 获得对象的锁, 下面新消息的设置不会跟这个冲突, 那么就不会有线程安全问题)

新消息到来
9. 各个不同的用户从不同的地方发送消息到 chatter/message/sendMessage(post方式)
10. 服务器收到消息, 封装消息对象, 获得消息的接收者是谁(群聊的话用群id获取群员), 像上面一样获取MessageNotifier,调用这个对象的setLastNewMessage(int messageId).

销毁
11. 用户退出系统时, 发送消息到( ) ,服务器收到消息后从上面的map获取响应用户的对象, 将之更新到数据库, 将之从map中移除, 调用session.invalidate()
12. 如果用户服务器没有收到用户退出的请求, 那么session会在10分钟内自动销毁(web.xml里面配置了10分钟): 设置了HttpSession监听器, 在session销毁的时候将上面map中的
相应用户的数据删除.



获取消息流程:
获取消息:
1. 客户端打开一个get链接, 如果有新的消息, 那么立即返回, 如果没有新的消息, 每隔0.5秒检查一次, 十秒内如果还没有新的消息, 那么返回这个get请求;
3. 如果有新的消息, 那么到 chatter/message/getMessage  获取消息, get请求
4. get到消息后返回json格式的消息
5. 客户端接收到后解析, 如果是图片, 音频, 视频,文件这些消息, 那么获取路径, 接着到以下路径拉取内容:
   私聊消息:
   图片 /chatter/privateChat/image
   文件 /chatter/privateChat/file
   视频 /chatter/privateChat/vedio
   语音 /chatter/privateChat/voice










实体:
Message实体:
字段名             java类型             sql类型
id                 int                  int not null primary key auto_increment
uuid               String               varchar(50) not null     //用UUID类生成, 然后截取的字符串长度为32, 用50保险点
messageType        int                  smallint(6) not null     //6位数字的smallint(规定Message常量的这两个字段int值位数不能大于6)
contentType        int                  smallint(6) not null     //6位数字的smallint
sendTime           java.util.Date       datetime    not null             //有日期有时间
sendId             int                  int    not null
receiveId          int                  int    not null
content            String               varchar(255) 最大长度为255个字符(不是字节), 一个中文算一个字符   //mediumblob 2^24次方也就是16mb的数据(图片也存文件系统, 不存数据库了, 所以不用blob类型了)


MessageNotifier 已读消息映射(和用户也就是chatter的映射)实体
字段名                    java类型             sql类型
id                        int                  int not null primary key auto_increment
lastAccessMessageId       int                  int not null default -1 //-1 表示从来没有从消息表读取过消息
lastNewMessageId          int                  int not null default -1 //-1表示没有登录
hasNewMessage             boolean              //数据库中不要这个字段了, 登录进系统的时候直接用上次访问的消息id来查, 不用看这个字段了, 如果看这个字段, 那么用户不在线时, 新消息来还要访问这个表.  tinyint not null default 0 //0表示没有, mysql中, 如果java插入false, 那么表示为0, true表示为1
chatterId                 int                  int not null unique //表示这个通知器是哪个用户的










