# 仿写网易云

## 写在之前

> 此项目2022/1/20左右才创建，在2022/1/29才第三次提交，速度比较慢。这接近10天的时间里除了忙着做家务维持亲情和下各种各样的苦力以外，还学习了一些知识，比如MVVM框架和room。
>
> 从2022/1/29开始正式进入开发。

## 简要介绍

仿照网易云音乐app做了一个丐版网易云，实现了核心功能——网抑，包括播放音乐和查看热门评论，评论歌曲，回复热门评论等。

## API接口

[网易云音乐API](https://neteasecloudmusicapi.vercel.app/#/)

## 已经实现的所有功能

* 登录/修改密码
* 游客模式（只能查看三个“精品”歌单）
* 查看已经登录账号的日推（歌曲和歌单，每隔一个小时刷新一次）
* 查看已经登录账号的个人歌单（创建的歌单和收藏的歌单）
* 查看歌单的歌曲（由于接口限制，普通歌单只能查看~~10~~  20首歌曲，已经登录账号的个人歌单的所有歌曲）
* 播放音乐功能（暂时不支持自动切换下一首）
* 查看歌词功能（暂时不支持点击歌词跳转到相应进度）
* 查看歌曲评论功能（暂时只支持查看热评）
* 回复歌曲热评和评论歌曲功能
* 搜索歌曲功能
* 登出功能

## 之后可能会做

* 用自定义view优化歌词展示
* 增加自动切换下一首歌曲
* 增加查看普通评论功能
* 增加本地缓存歌曲

## 功能展示

<img src="http://42.192.50.23:8888/down/tdC280KlD827" style="zoom:25%;" />



<img src="http://42.192.50.23:8888/down/GkTKMGtRDJpX" style="zoom:25%;" />

以上演示了部分功能（录演示视频的时候发现歌曲评论接口挂了）。

## 技术亮点（自认为）

* 使用了MVVM架构

* 使用了room存储数据

* 部分地方使用了Material Design的控件

  

## 不足之处

* 全套还是使用的Java开发
* 还有很多设想的功能没有实现
* UI界面简陋粗糙（从开始开发到写这个文档，我一直对这个很不满意）
* 没有用自定义view封装一些控件来实现自己想要的功能
* 有些地方照猫画虎，还没有完全理解

## 开发过程中遇到的部分难点（不完全列举且“难”建立在我的水平之上）

### 歌词制作

在歌词的滑动制作中想了很多办法，一直没有得到自己想要的效果，最后勉强用RV的自动滑动实现了功能，以后想办法再用自定义view试着实现一下

### room的使用

在开发这个项目之前一直没有接触过数据库这个东西，对SQL的语法没有一丝的了解，所以在使用中遇到了很多问题，但最后都通过谷歌等搜索工具克服了下来，达到了效果

### MVVM的使用

在这个项目之前甚至没有接触过MVVM这个概念，所以在初期也在使用时遇到了一些问题，后来也通过搜索和学习学会了使用

### Debug

可以说debug耗费了很多时间，各种异常，各种报错，其中最让我印象深刻的是MediaPlayer的IllegalStateException，由于缺乏使用的经验，导致这个异常多次抛出，app各种崩溃，最后在学长的帮助下解决了他。而且这还只是其中一个例子，开发中还遇到许许多多的问题。

## 心得体会

深刻的感受到了自己的无知，开发的时候很多东西都没接触过，只能通过各种搜索引擎搜索学习，而且很多东西都无法理解，只能想一些“歪门邪道”来勉强达到效果，项目里面最有代表性的例子是：修改密码的验证码输入框、歌词显示。同时又感受到了自己的懒散，对于学习虽然充满了热情，但很多时候并没有付出行动，所以对很多东西都有心无力，开发中遇到的许多问题也无法解决。

## 写在最后

>感谢在学习和开发中帮助过我的学长和同学。









