# IM
第三方技术：
 netty--NIO网络通信
 protobuf--制作通信协议
 spring--IOC容器
 mybatis--数据持久化
 
项目关键点：
1 .protobuf技术的引进，大大简化了通信协议的协调。没有引入protobuf前，想要协调好CS的通信协议比较麻烦，要手动写的代码量比较大，
   还容易出错，下面是项目的.proto文件的一部分，message=消息头msgtype+可选的消息体
   syntax = "proto3";

message Message{
     int32 msgtype = 1;
     ReqLogin reqlogin = 2;
     ResLogin reslogin = 3;
     ReqFriends reqfriends = 4;
     ResFriends resfriends = 5;
     ChatWith chat_with = 6;
}

message ReqLogin{
     int32 userid = 1;
     string password = 2;
}

message ResLogin{
     string status = 1;
     int32 userid = 2;
     string username = 3;
     string sex = 4;
}
 
2 .不同消息的处理
