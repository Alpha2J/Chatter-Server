验证模块:
功能:
1. chatter/validation 需要有method参数, 如果method参数为空, 返回{"action" : "1001" , "msg" : "参数解析错误: method参数缺失!"},
   method参数只能为login或者register或logOut或checkAccountAvailable, 其他的参数都返回{"action" : "1001" , "msg" : "参数解析错误: 非法参数!"}
   a. 登录  chatter/validation   方式post method参数值为login, 登录成功返回{"action" : "101" , "msg" : "登录成功!"},
      账号或密码缺失导致的登录失败返回{"action" : "1001" , "msg" : "参数解析错误: account或password参数缺失!"},
      账号或密码验证错误导致的失败返回{"action" : "102" , "msg" : "登录失败, 请检查账号和密码!"}
   b. 注册  chatter/validation   方式post 需要有method参数, 如果参数为空, 返回参数缺失提示, method参数为register
   c. 下线  chatter/validation   方式post 需要有method参数, 如果...........................................
   d. 检测账号是否可用 chatter/validation  .....

登录:
    必须参数 account  password
    一个用户只能在一个地方登录, 不能同时登录, 后面需要做消息同步, 这样更方便
注册
   注册时提供填写的字段:(参数)
       account  必须
       password 必须
       nickName 可为空
       gender   可为空
       region   可为空
       phone    可为空
下线
检测账号是否存在
   必须参数 account


实体说明:
Chatter实体
字段名             java类型             sql类型
id                int                 int not null primary key auto_increment
account           String              varchar(20) not null unique  //最大长度为20个字符, 且只能是数字
headImage         String              varchar(255) default '/chatterData/default/image/default-head-image.png'
nickName          String              varchar(20) not null default 'chatter'//姓名最长20个字符
signature         String              varchar(255) //最大255个字符
gender            String              varchar(10) not null default '未知性别'
region            String              varchar(50) not null default '未知地区'//最大50个字符
createTime        java.util.Date      datetime not null default now()
phone             String              varchar(30)

chatterPrivate
字段名             java类型             sql类型
id                int                 int not null primary key auto_increment
chatterId         int                 int not null
password          String              varchar(40) not null
state             int                 tinyint not null default 0
