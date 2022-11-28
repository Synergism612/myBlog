-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	5.7.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `icon` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '空' COMMENT '头像',
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `body` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '正文',
  `synopsis` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '空' COMMENT '摘要',
  `views` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `like_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `if_private` int(1) NOT NULL DEFAULT '0' COMMENT '私有  0-公开 1-私有',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'https://s1.328888.xyz/2022/10/13/8V4Ln.jpg','测试标题','https://www.cnblogs.com/keerya/p/7987886.html\r\n\r\n## ansible 简介\r\n\r\n\r\n\r\n### ansible 是什么？\r\n\r\n　　ansible是新出现的自动化运维工具，基于Python开发，集合了众多运维工具（puppet、chef、func、fabric）的优点，实现了批量系统配置、批量程序部署、批量运行命令等功能。\r\n　　ansible是基于 paramiko 开发的,并且基于模块化工作，本身没有批量部署的能力。真正具有批量部署的是ansible所运行的模块，ansible只是提供一种框架。ansible不需要在远程主机上安装client/agents，因为它们是基于ssh来和远\r\n程主机通讯的。ansible目前已经已经被红帽官方收购，是自动化运维工具中大家认可度最高的，并且上手容易，学习简单。是每位运维工程师必须掌握的技能之一。\r\n\r\n\r\n\r\n### ansible 特点\r\n\r\n1. 部署简单，只需在主控端部署Ansible环境，被控端无需做任何操作；\r\n2. 默认使用SSH协议对设备进行管理；\r\n3. 有大量常规运维操作模块，可实现日常绝大部分操作；\r\n4. 配置简单、功能强大、扩展性强；\r\n5. 支持API及自定义模块，可通过Python轻松扩展；\r\n6. 通过Playbooks来定制强大的配置、状态管理；\r\n7. 轻量级，无需在客户端安装agent，更新时，只需在操作机上进行一次更新即可；\r\n8. 提供一个功能强大、操作性强的Web管理界面和REST API接口——AWX平台。\r\n\r\n\r\n\r\n### ansible 架构图\r\n\r\n![img](E:\\Pictures\\typora\\1204916-20171205163000628-69838828.png)\r\n　　上图中我们看到的主要模块如下：\r\n\r\n> `Ansible`：Ansible核心程序。\r\n> `HostInventory`：记录由Ansible管理的主机信息，包括端口、密码、ip等。\r\n> `Playbooks`：“剧本”YAML格式文件，多个任务定义在一个文件中，定义主机需要调用哪些模块来完成的功能。\r\n> `CoreModules`：**核心模块**，主要操作是通过调用核心模块来完成管理任务。\r\n> `CustomModules`：自定义模块，完成核心模块无法完成的功能，支持多种语言。\r\n> `ConnectionPlugins`：连接插件，Ansible和Host通信使用\r\n\r\n[回到顶部](https://www.cnblogs.com/keerya/p/7987886.html#_labelTop)\r\n\r\n## ansible 任务执行\r\n\r\n\r\n\r\n### ansible 任务执行模式\r\n\r\n　　Ansible 系统由控制主机对被管节点的操作方式可分为两类，即`adhoc`和`playbook`：\r\n\r\n- ad-hoc模式(点对点模式)\r\n  　　使用单个模块，支持批量执行单条命令。ad-hoc 命令是一种可以快速输入的命令，而且不需要保存起来的命令。**就相当于bash中的一句话shell。**\r\n- playbook模式(剧本模式)\r\n  　　是Ansible主要管理方式，也是Ansible功能强大的关键所在。**playbook通过多个task集合完成一类功能**，如Web服务的安装部署、数据库服务器的批量备份等。可以简单地把playbook理解为通过组合多条ad-hoc操作的配置文件。\r\n\r\n\r\n\r\n### ansible 执行流程\r\n\r\n![img](E:\\Pictures\\typora\\1204916-20171205162615738-1292598736.png)\r\n　　简单理解就是Ansible在运行时， 首先读取`ansible.cfg`中的配置， 根据规则获取`Inventory`中的管理主机列表， 并行的在这些主机中执行配置的任务， 最后等待执行返回的结果。\r\n\r\n\r\n\r\n### ansible 命令执行过程\r\n\r\n1. 加载自己的配置文件，默认`/etc/ansible/ansible.cfg`；\r\n2. 查找对应的主机配置文件，找到要执行的主机或者组；\r\n3. 加载自己对应的模块文件，如 command；\r\n4. 通过ansible将模块或命令生成对应的临时py文件(python脚本)， 并将该文件传输至远程服务器；\r\n5. 对应执行用户的家目录的`.ansible/tmp/XXX/XXX.PY`文件；\r\n6. 给文件 +x 执行权限；\r\n7. 执行并返回结果；\r\n8. 删除临时py文件，`sleep 0`退出；\r\n\r\n[回到顶部](https://www.cnblogs.com/keerya/p/7987886.html#_labelTop)\r\n\r\n## ansible 配置详解\r\n\r\n\r\n\r\n### ansible 安装方式\r\n\r\n　　ansible安装常用两种方式，`yum安装`和`pip程序安装`。下面我们来详细介绍一下这两种安装方式。\r\n\r\n\r\n\r\n#### 使用 pip（python的包管理模块）安装\r\n\r\n　　首先，我们需要安装一个`python-pip`包，安装完成以后，则直接使用`pip`命令来安装我们的包，具体操作过程如下：\r\n\r\n```\r\n	yum install python-pip\r\n	pip install ansible\r\n```\r\n\r\n\r\n\r\n#### 使用 yum 安装\r\n\r\n　　yum 安装是我们很熟悉的安装方式了。我们需要先安装一个`epel-release`包，然后再安装我们的 ansible 即可。\r\n\r\n```\r\n	yum install epel-release -y\r\n	yum install ansible –y\r\n```\r\n\r\n\r\n\r\n### ansible 程序结构\r\n\r\n安装目录如下(yum安装)：\r\n　　配置文件目录：/etc/ansible/\r\n　　执行文件目录：/usr/bin/\r\n　　Lib库依赖目录：/usr/lib/pythonX.X/site-packages/ansible/\r\n　　Help文档目录：/usr/share/doc/ansible-X.X.X/\r\n　　Man文档目录：/usr/share/man/man1/\r\n\r\n\r\n\r\n### ansible配置文件查找顺序\r\n\r\n　　ansible与我们其他的服务在这一点上有很大不同，这里的配置文件查找是从多个地方找的，顺序如下：\r\n\r\n1. 检查环境变量`ANSIBLE_CONFIG`指向的路径文件(export ANSIBLE_CONFIG=/etc/ansible.cfg)；\r\n2. `~/.ansible.cfg`，检查当前目录下的ansible.cfg配置文件；\r\n3. `/etc/ansible.cfg`检查etc目录的配置文件。\r\n\r\n\r\n\r\n### ansible配置文件\r\n\r\n　　ansible 的配置文件为`/etc/ansible/ansible.cfg`，ansible 有许多参数，下面我们列出一些常见的参数：\r\n\r\n```\r\n	inventory = /etc/ansible/hosts		#这个参数表示资源清单inventory文件的位置\r\n	library = /usr/share/ansible		#指向存放Ansible模块的目录，支持多个目录方式，只要用冒号（：）隔开就可以\r\n	forks = 5		#并发连接数，默认为5\r\n	sudo_user = root		#设置默认执行命令的用户\r\n	remote_port = 22		#指定连接被管节点的管理端口，默认为22端口，建议修改，能够更加安全\r\n	host_key_checking = False		#设置是否检查SSH主机的密钥，值为True/False。关闭后第一次连接不会提示配置实例\r\n	timeout = 60		#设置SSH连接的超时时间，单位为秒\r\n	log_path = /var/log/ansible.log		#指定一个存储ansible日志的文件（默认不记录日志）\r\n```\r\n\r\n\r\n\r\n### ansuble主机清单\r\n\r\n　　在配置文件中，我们提到了资源清单，这个清单就是我们的主机清单，里面保存的是一些 ansible 需要连接管理的主机列表。我们可以来看看他的定义方式：\r\n\r\n```\r\n1、 直接指明主机地址或主机名：\r\n	## green.example.com#\r\n	# blue.example.com#\r\n	# 192.168.100.1\r\n	# 192.168.100.10\r\n2、 定义一个主机组[组名]把地址或主机名加进去\r\n	[mysql_test]\r\n	192.168.253.159\r\n	192.168.253.160\r\n	192.168.253.153\r\n```\r\n\r\n　　需要注意的是，这里的组成员可以使用通配符来匹配，这样对于一些标准化的管理来说就很轻松方便了。\r\n　　我们可以根据实际情况来配置我们的主机列表，具体操作如下：\r\n\r\n```\r\n[root@server ~]# vim /etc/ansible/hosts\r\n	[web]\r\n	192.168.37.122\r\n	192.168.37.133\r\n```\r\n\r\n[回到顶部](https://www.cnblogs.com/keerya/p/7987886.html#_labelTop)\r\n\r\n## ansible 常用命令\r\n\r\n\r\n\r\n### ansible 命令集\r\n\r\n> `/usr/bin/ansible`　　Ansibe AD-Hoc 临时命令执行工具，常用于临时命令的执行\r\n> `/usr/bin/ansible-doc` 　Ansible 模块功能查看工具\r\n> `/usr/bin/ansible-galaxy`　　下载/上传优秀代码或Roles模块 的官网平台，基于网络的\r\n> `/usr/bin/ansible-playbook`　　Ansible 定制自动化的任务集编排工具\r\n> `/usr/bin/ansible-pull`　　Ansible远程执行命令的工具，拉取配置而非推送配置（使用较少，海量机器时使用，对运维的架构能力要求较高）\r\n> `/usr/bin/ansible-vault`　　Ansible 文件加密工具\r\n> `/usr/bin/ansible-console`　　Ansible基于Linux Consoble界面可与用户交互的命令执行工具\r\n\r\n　　其中，我们比较常用的是`/usr/bin/ansible`和`/usr/bin/ansible-playbook`。\r\n\r\n\r\n\r\n### ansible-doc 命令\r\n\r\n　　ansible-doc 命令常用于获取模块信息及其使用帮助，一般用法如下：\r\n\r\n```\r\n	ansible-doc -l				#获取全部模块的信息\r\n	ansible-doc -s MOD_NAME		#获取指定模块的使用帮助\r\n```\r\n\r\n　　我们也可以查看一下ansible-doc的全部用法：\r\n\r\n```\r\n[root@server ~]# ansible-doc\r\nUsage: ansible-doc [options] [module...]\r\n\r\nOptions:\r\n  -h, --help            show this help message and exit　　# 显示命令参数API文档\r\n  -l, --list            List available modules　　#列出可用的模块\r\n  -M MODULE_PATH, --module-path=MODULE_PATH　　#指定模块的路径\r\n                        specify path(s) to module library (default=None)\r\n  -s, --snippet         Show playbook snippet for specified module(s)　　#显示playbook制定模块的用法\r\n  -v, --verbose         verbose mode (-vvv for more, -vvvv to enable　　# 显示ansible-doc的版本号查看模块列表：\r\n                        connection debugging)\r\n  --version             show program\'s version number and exit\r\n```\r\n\r\n　　我们可以来看一下，以mysql相关的为例：\r\n\r\n```\r\n[root@server ~]# ansible-doc -l |grep mysql\r\nmysql_db                           Add or remove MySQL databases from a remote...\r\nmysql_replication                  Manage MySQL replication                   \r\nmysql_user                         Adds or removes a user from a MySQL databas...\r\nmysql_variables                    Manage MySQL global variables      \r\n[root@server ~]# ansible-doc -s mysql_user\r\n```\r\n\r\n![img](E:\\Pictures\\typora\\1204916-20171205163026644-674759103.png)\r\n\r\n\r\n\r\n### ansible 命令详解\r\n\r\n　　命令的具体格式如下：\r\n\r\n```\r\nansible <host-pattern> [-f forks] [-m module_name] [-a args]\r\n```\r\n\r\n　　也可以通过`ansible -h`来查看帮助，下面我们列出一些比较常用的选项，并解释其含义：\r\n\r\n> `-a MODULE_ARGS`　　　#模块的参数，如果执行默认COMMAND的模块，即是命令参数，如： “date”，“pwd”等等\r\n> `-k`，`--ask-pass` #ask for SSH password。登录密码，提示输入SSH密码而不是假设基于密钥的验证\r\n> `--ask-su-pass` #ask for su password。su切换密码\r\n> `-K`，`--ask-sudo-pass` #ask for sudo password。提示密码使用sudo，sudo表示提权操作\r\n> `--ask-vault-pass` #ask for vault password。假设我们设定了加密的密码，则用该选项进行访问\r\n> `-B SECONDS` #后台运行超时时间\r\n> `-C` #模拟运行环境并进行预运行，可以进行查错测试\r\n> `-c CONNECTION` #连接类型使用\r\n> `-f FORKS` #并行任务数，默认为5\r\n> `-i INVENTORY` #指定主机清单的路径，默认为`/etc/ansible/hosts`\r\n> `--list-hosts` #查看有哪些主机组\r\n> `-m MODULE_NAME` #执行模块的名字，默认使用 command 模块，所以如果是只执行单一命令可以不用 -m参数\r\n> `-o` #压缩输出，尝试将所有结果在一行输出，一般针对收集工具使用\r\n> `-S` #用 su 命令\r\n> `-R SU_USER` #指定 su 的用户，默认为 root 用户\r\n> `-s` #用 sudo 命令\r\n> `-U SUDO_USER` #指定 sudo 到哪个用户，默认为 root 用户\r\n> `-T TIMEOUT` #指定 ssh 默认超时时间，默认为10s，也可在配置文件中修改\r\n> `-u REMOTE_USER` #远程用户，默认为 root 用户\r\n> `-v` #查看详细信息，同时支持`-vvv`，`-vvvv`可查看更详细信息\r\n\r\n\r\n\r\n### ansible 配置公私钥\r\n\r\n　　上面我们已经提到过 ansible 是基于 ssh 协议实现的，所以其配置公私钥的方式与 ssh 协议的方式相同，具体操作步骤如下：\r\n\r\n```\r\n#1.生成私钥\r\n[root@server ~]# ssh-keygen \r\n#2.向主机分发私钥\r\n[root@server ~]# ssh-copy-id root@192.168.37.122\r\n[root@server ~]# ssh-copy-id root@192.168.37.133\r\n```\r\n\r\n　　这样的话，就可以实现无密码登录，我们的实验过程也会顺畅很多。\r\n　　注意，如果出现了一下报错：\r\n\r\n```\r\n	-bash: ssh-copy-id: command not found\r\n```\r\n\r\n　　那么就证明我们需要安装一个包：\r\n\r\n```\r\n	yum -y install openssh-clientsansible\r\n```\r\n\r\n　　把包安装上即可。\r\n\r\n\r\n\r\n## ansible 常用模块\r\n\r\n\r\n\r\n### 1）主机连通性测试\r\n\r\n　　我们使用`ansible web -m ping`命令来进行主机连通性测试，效果如下：\r\n\r\n```\r\n[root@server ~]# ansible web -m ping\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": false, \r\n    \"ping\": \"pong\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": false, \r\n    \"ping\": \"pong\"\r\n}\r\n```\r\n\r\n　　这样就说明我们的主机是连通状态的。接下来的操作才可以正常进行。\r\n\r\n\r\n\r\n### 2）command 模块\r\n\r\n　　这个模块可以直接在远程主机上执行命令，并将结果返回本主机。\r\n　　举例如下：\r\n\r\n```\r\n[root@server ~]# ansible web -m command -a \'ss -ntl\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port              \r\nLISTEN     0      128          *:111                      *:*                  \r\nLISTEN     0      5      192.168.122.1:53                       *:*                  \r\nLISTEN     0      128          *:22                       *:*                  \r\nLISTEN     0      128    127.0.0.1:631                      *:*                  \r\nLISTEN     0      128          *:23000                    *:*                  \r\nLISTEN     0      100    127.0.0.1:25                       *:*                  \r\nLISTEN     0      128         :::111                     :::*                  \r\nLISTEN     0      128         :::22                      :::*                  \r\nLISTEN     0      128        ::1:631                     :::*                  \r\nLISTEN     0      100        ::1:25                      :::*                  \r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port              \r\nLISTEN     0      128          *:111                      *:*                  \r\nLISTEN     0      128          *:22                       *:*                  \r\nLISTEN     0      128    127.0.0.1:631                      *:*                  \r\nLISTEN     0      128          *:23000                    *:*                  \r\nLISTEN     0      100    127.0.0.1:25                       *:*                  \r\nLISTEN     0      128         :::111                     :::*                  \r\nLISTEN     0      128         :::22                      :::*                  \r\nLISTEN     0      128        ::1:631                     :::*                  \r\nLISTEN     0      100        ::1:25                      :::*  \r\n```\r\n\r\n　　命令模块接受命令名称，后面是空格分隔的列表参数。给定的命令将在所有选定的节点上执行。它不会通过shell进行处理，比如$HOME和操作如\"<\"，\">\"，\"|\"，\";\"，\"&\" 工作（需要使用（shell）模块实现这些功能）。注意，该命令不支持`| 管道命令`。\r\n　　下面来看一看该模块下常用的几个命令：\r\n\r\n> chdir　　　　　　 # 在执行命令之前，先切换到该目录\r\n> executable # 切换shell来执行命令，需要使用命令的绝对路径\r\n> free_form 　 # 要执行的Linux指令，一般使用Ansible的-a参数代替。\r\n> creates 　# 一个文件名，当这个文件存在，则该命令不执行,可以\r\n> 用来做判断\r\n> removes # 一个文件名，这个文件不存在，则该命令不执行\r\n\r\n　　下面我们来看看这些命令的执行效果：\r\n\r\n```\r\n[root@server ~]# ansible web -m command -a \'chdir=/data/ ls\'	#先切换到/data/ 目录，再执行“ls”命令\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\naaa.jpg\r\nfastdfs\r\nmogdata\r\ntmp\r\nweb\r\nwKgleloeYoCAMLtZAAAWEekAtkc497.jpg\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\naaa.jpg\r\nfastdfs\r\nmogdata\r\ntmp\r\nweb\r\nwKgleloeYoCAMLtZAAAWEekAtkc497.jpg\r\n[root@server ~]# ansible web -m command -a \'creates=/data/aaa.jpg ls\'		#如果/data/aaa.jpg存在，则不执行“ls”命令\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nskipped, since /data/aaa.jpg exists\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nskipped, since /data/aaa.jpg exists\r\n[root@server ~]# ansible web -m command -a \'removes=/data/aaa.jpg cat /data/a\'		#如果/data/aaa.jpg存在，则执行“cat /data/a”命令\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nhello\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nhello\r\n```\r\n\r\n\r\n\r\n### 3）shell 模块\r\n\r\n　　shell模块可以在远程主机上调用shell解释器运行命令，支持shell的各种功能，例如管道等。\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/passwd |grep \"keer\"\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nkeer:x:10001:1000:keer:/home/keer:/bin/sh\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nkeer:x:10001:10001::/home/keer:/bin/sh\r\n```\r\n\r\n　　只要是我们的shell命令，都可以通过这个模块在远程主机上运行，这里就不一一举例了。\r\n\r\n\r\n\r\n### 4）copy 模块\r\n\r\n　　这个模块用于将文件复制到远程主机，同时支持给定内容生成文件和修改权限等。\r\n　　其相关选项如下：\r\n\r\n> `src`　　　　#被复制到远程主机的本地文件。可以是绝对路径，也可以是相对路径。如果路径是一个目录，则会递归复制，用法类似于\"rsync\"\r\n> `content`　　　#用于替换\"src\"，可以直接指定文件的值\r\n> `dest`　　　　#必选项，将源文件复制到的远程主机的**绝对路径**\r\n> `backup`　　　#当文件内容发生改变后，在覆盖之前把源文件备份，备份文件包含时间信息\r\n> `directory_mode`　　　　#递归设定目录的权限，默认为系统默认权限\r\n> `force`　　　　#当目标主机包含该文件，但内容不同时，设为\"yes\"，表示强制覆盖；设为\"no\"，表示目标主机的目标位置不存在该文件才复制。默认为\"yes\"\r\n> `others`　　　　#所有的 file 模块中的选项可以在这里使用\r\n\r\n用法举例如下：\r\n**① 复制文件：**\r\n\r\n```\r\n[root@server ~]# ansible web -m copy -a \'src=~/hello dest=/data/hello\' \r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/hello\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"mode\": \"0644\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437093.55-228281064292921/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/hello\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"mode\": \"0644\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437093.74-44694985235189/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n**② 给定内容生成文件，并制定权限**\r\n\r\n```\r\n[root@server ~]# ansible web -m copy -a \'content=\"I am keer\\n\" dest=/data/name mode=666\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"0421570938940ea784f9d8598dab87f07685b968\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"497fa8386590a5fc89090725b07f175c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 10, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437327.37-199512601767687/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"0421570938940ea784f9d8598dab87f07685b968\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"497fa8386590a5fc89090725b07f175c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 10, \r\n	    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437327.55-218104039503110/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　我们现在可以去查看一下我们生成的文件及其权限：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data/\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\n-rw-rw-rw-   1 root root   12 Dec  6 09:45 name\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 40\r\n-rw-rw-rw- 1 root     root       12 Dec  5 09:45 name\r\n```\r\n\r\n　　可以看出我们的name文件已经生成，并且权限为666。\r\n**③ 关于覆盖**\r\n　　我们把文件的内容修改一下，然后选择覆盖备份：\r\n\r\n```\r\n[root@server ~]# ansible web -m copy -a \'content=\"I am keerya\\n\" backup=yes dest=/data/name mode=666\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"backup_file\": \"/data/name.4394.2017-12-06@09:46:25~\", \r\n    \"changed\": true, \r\n    \"checksum\": \"064a68908ab9971ee85dbc08ea038387598e3778\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"8ca7c11385856155af52e560f608891c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512438383.78-228128616784888/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"backup_file\": \"/data/name.5962.2017-12-05@09:46:24~\", \r\n    \"changed\": true, \r\n    \"checksum\": \"064a68908ab9971ee85dbc08ea038387598e3778\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"8ca7c11385856155af52e560f608891c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512438384.0-170718946740009/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　现在我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data/\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\n-rw-rw-rw-   1 root root   12 Dec  6 09:46 name\r\n-rw-rw-rw-   1 root root   10 Dec  6 09:45 name.4394.2017-12-06@09:46:25~\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 40\r\n-rw-rw-rw- 1 root     root       12 Dec  5 09:46 name\r\n-rw-rw-rw- 1 root     root       10 Dec  5 09:45 name.5962.2017-12-05@09:46:24~\r\n```\r\n\r\n　　可以看出，我们的源文件已经被备份，我们还可以查看一下`name`文件的内容：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /data/name\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nI am keerya\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nI am keerya\r\n```\r\n\r\n　　证明，这正是我们新导入的文件的内容。\r\n\r\n\r\n\r\n### 5）file 模块\r\n\r\n　　该模块主要用于设置文件的属性，比如创建文件、创建链接文件、删除文件等。\r\n　　下面是一些常见的命令：\r\n\r\n> `force`　　#需要在两种情况下强制创建软链接，一种是源文件不存在，但之后会建立的情况下；另一种是目标软链接已存在，需要先取消之前的软链，然后创建新的软链，有两个选项：yes|no\r\n> `group`　　#定义文件/目录的属组。后面可以加上`mode`：定义文件/目录的权限\r\n> `owner`　　#定义文件/目录的属主。后面必须跟上`path`：定义文件/目录的路径\r\n> `recurse`　　#递归设置文件的属性，只对目录有效，后面跟上`src`：被链接的源文件路径，只应用于`state=link`的情况\r\n> `dest`　　#被链接到的路径，只应用于`state=link`的情况\r\n> `state`　　#状态，有以下选项：\r\n>\r\n> > `directory`：如果目录不存在，就创建目录\r\n> > `file`：即使文件不存在，也不会被创建\r\n> > `link`：创建软链接\r\n> > `hard`：创建硬链接\r\n> > `touch`：如果文件不存在，则会创建一个新的文件，如果文件或目录已存在，则更新其最后修改时间\r\n> > `absent`：删除目录、文件或者取消链接文件\r\n\r\n　　用法举例如下：\r\n**① 创建目录：**\r\n\r\n```\r\n[root@server ~]# ansible web -m file -a \'path=/data/app state=directory\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0755\", \r\n    \"owner\": \"root\", \r\n    \"path\": \"/data/app\", \r\n    \"size\": 6, \r\n    \"state\": \"directory\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0755\", \r\n    \"owner\": \"root\", \r\n    \"path\": \"/data/app\", \r\n    \"size\": 4096, \r\n    \"state\": \"directory\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　我们可以查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\ndrwxr-xr-x   2 root root    6 Dec  6 10:21 app\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 44\r\ndrwxr-xr-x 2 root     root     4096 Dec  5 10:21 app\r\n```\r\n\r\n　　可以看出，我们的目录已经创建完成。\r\n**② 创建链接文件**\r\n\r\n```\r\n[root@server ~]# ansible web -m file -a \'path=/data/bbb.jpg src=aaa.jpg state=link\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"dest\": \"/data/bbb.jpg\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0777\", \r\n    \"owner\": \"root\", \r\n    \"size\": 7, \r\n    \"src\": \"aaa.jpg\", \r\n    \"state\": \"link\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"dest\": \"/data/bbb.jpg\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0777\", \r\n    \"owner\": \"root\", \r\n    \"size\": 7, \r\n    \"src\": \"aaa.jpg\", \r\n    \"state\": \"link\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\n-rw-r--r--   1 root root 5649 Dec  5 13:49 aaa.jpg\r\nlrwxrwxrwx   1 root root    7 Dec  6 10:25 bbb.jpg -> aaa.jpg\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 44\r\n-rw-r--r-- 1 root     root     5649 Dec  4 14:44 aaa.jpg\r\nlrwxrwxrwx 1 root     root        7 Dec  5 10:25 bbb.jpg -> aaa.jpg\r\n```\r\n\r\n　　我们的链接文件已经创建成功。\r\n**③ 删除文件**\r\n\r\n```\r\n[root@server ~]# ansible web -m file -a \'path=/data/a state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"path\": \"/data/a\", \r\n    \"state\": \"absent\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"path\": \"/data/a\", \r\n    \"state\": \"absent\"\r\n}\r\n```\r\n\r\n　　我们可以查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls /data/a\'\r\n192.168.37.122 | FAILED | rc=2 >>\r\nls: cannot access /data/a: No such file or directory\r\n\r\n192.168.37.133 | FAILED | rc=2 >>\r\nls: cannot access /data/a: No such file or directory\r\n```\r\n\r\n　　发现已经没有这个文件了。\r\n　　\r\n\r\n\r\n\r\n### 6）fetch 模块\r\n\r\n　　该模块用于从远程某主机获取（复制）文件到本地。\r\n　　有两个选项：\r\n\r\n> `dest`：用来存放文件的目录\r\n> `src`：在远程拉取的文件，并且必须是一个**file**，不能是**目录**\r\n\r\n　　具体举例如下：\r\n\r\n```\r\n[root@server ~]# ansible web -m fetch -a \'src=/data/hello dest=/data\'  \r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/192.168.37.122/data/hello\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"remote_checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"remote_md5sum\": null\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/192.168.37.133/data/hello\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"remote_checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"remote_md5sum\": null\r\n}\r\n```\r\n\r\n　　我们可以在本机上查看一下文件是否复制成功。要注意，文件保存的路径是我们设置的接收目录下的`被管制主机ip`目录下：\r\n\r\n```\r\n[root@server ~]# cd /data/\r\n[root@server data]# ls\r\n1  192.168.37.122  192.168.37.133  fastdfs  web\r\n[root@server data]# cd 192.168.37.122\r\n[root@server 192.168.37.122]# ls\r\ndata\r\n[root@server 192.168.37.122]# cd data/\r\n[root@server data]# ls\r\nhello\r\n[root@server data]# pwd\r\n/data/192.168.37.122/data\r\n```\r\n\r\n\r\n\r\n### 7）cron 模块\r\n\r\n　　该模块适用于管理`cron`计划任务的。\r\n　　其使用的语法跟我们的`crontab`文件中的语法一致，同时，可以指定以下选项：\r\n\r\n> `day=` #日应该运行的工作( 1-31, *, */2, )\r\n> `hour=` # 小时 ( 0-23, *, */2, )\r\n> `minute=` #分钟( 0-59, *, */2, )\r\n> `month=` # 月( 1-12, *, /2, )\r\n> `weekday=` # 周 ( 0-6 for Sunday-Saturday,, )\r\n> `job=` #指明运行的命令是什么\r\n> `name=` #定时任务描述\r\n> `reboot` # 任务在重启时运行，不建议使用，建议使用special_time\r\n> `special_time` #特殊的时间范围，参数：reboot（重启时），annually（每年），monthly（每月），weekly（每周），daily（每天），hourly（每小时）\r\n> `state` #指定状态，present表示添加定时任务，也是默认设置，absent表示删除定时任务\r\n> `user` # 以哪个用户的身份执行\r\n\r\n　　举例如下：\r\n**① 添加计划任务**\r\n\r\n```\r\n[root@server ~]# ansible web -m cron -a \'name=\"ntp update every 5 min\" minute=*/5 job=\"/sbin/ntpdate 172.17.0.1 &> /dev/null\"\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n```\r\n\r\n　　我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'crontab -l\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n```\r\n\r\n　　可以看出，我们的计划任务已经设置成功了。\r\n**② 删除计划任务**\r\n　　如果我们的计划任务添加错误，想要删除的话，则执行以下操作：\r\n　　首先我们查看一下现有的计划任务：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'crontab -l\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n#Ansible: df everyday\r\n* 15 * * * df -lh >> /tmp/disk_total &> /dev/null\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n#Ansible: df everyday\r\n* 15 * * * df -lh >> /tmp/disk_total &> /dev/null\r\n```\r\n\r\n　　然后执行删除操作：\r\n\r\n```\r\n[root@server ~]# ansible web -m cron -a \'name=\"df everyday\" hour=15 job=\"df -lh >> /tmp/disk_total &> /dev/null\" state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n```\r\n\r\n　　删除完成后，我们再查看一下现有的计划任务确认一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'crontab -l\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n```\r\n\r\n　　我们的删除操作已经成功。\r\n\r\n\r\n\r\n### 8）yum 模块\r\n\r\n　　顾名思义，该模块主要用于软件的安装。\r\n　　其选项如下：\r\n\r\n> `name=`　　#所安装的包的名称\r\n> `state=`　　#`present`--->安装， `latest`--->安装最新的, `absent`---> 卸载软件。\r\n> `update_cache`　　#强制更新yum的缓存\r\n> `conf_file`　　#指定远程yum安装时所依赖的配置文件（安装本地已有的包）。\r\n> `disable_pgp_check`　　#是否禁止GPG checking，只用于`present`or `latest`。\r\n> `disablerepo`　　#临时禁止使用yum库。 只用于安装或更新时。\r\n> `enablerepo`　　#临时使用的yum库。只用于安装或更新时。\r\n\r\n　　下面我们就来安装一个包试试看：\r\n\r\n```\r\n[root@server ~]# ansible web -m yum -a \'name=htop state=present\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"msg\": \"\", \r\n    \"rc\": 0, \r\n    \"results\": [\r\n        \"Loaded plugins: fastestmirror, langpacks\\nLoading mirror speeds from cached hostfile\\nResolving Dependencies\\n--> Running transaction check\\n---> Package htop.x86_64 0:2.0.2-1.el7 will be installed\\n--> Finished Dependency Resolution\\n\\nDependencies Resolved\\n\\n================================================================================\\n Package         Arch              Version                Repository       Size\\n================================================================================\\nInstalling:\\n htop            x86_64            2.0.2-1.el7            epel             98 k\\n\\nTransaction Summary\\n================================================================================\\nInstall  1 Package\\n\\nTotal download size: 98 k\\nInstalled size: 207 k\\nDownloading packages:\\nRunning transaction check\\nRunning transaction test\\nTransaction test succeeded\\nRunning transaction\\n  Installing : htop-2.0.2-1.el7.x86_64                                      1/1 \\n  Verifying  : htop-2.0.2-1.el7.x86_64                                      1/1 \\n\\nInstalled:\\n  htop.x86_64 0:2.0.2-1.el7                                                     \\n\\nComplete!\\n\"\r\n    ]\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"msg\": \"Warning: RPMDB altered outside of yum.\\n** Found 3 pre-existing rpmdb problem(s), \'yum check\' output follows:\\nipa-client-4.4.0-12.el7.centos.x86_64 has installed conflicts freeipa-client: ipa-client-4.4.0-12.el7.centos.x86_64\\nipa-client-common-4.4.0-12.el7.centos.noarch has installed conflicts freeipa-client-common: ipa-client-common-4.4.0-12.el7.centos.noarch\\nipa-common-4.4.0-12.el7.centos.noarch has installed conflicts freeipa-common: ipa-common-4.4.0-12.el7.centos.noarch\\n\", \r\n    \"rc\": 0, \r\n    \"results\": [\r\n        \"Loaded plugins: fastestmirror, langpacks\\nLoading mirror speeds from cached hostfile\\nResolving Dependencies\\n--> Running transaction check\\n---> Package htop.x86_64 0:2.0.2-1.el7 will be installed\\n--> Finished Dependency Resolution\\n\\nDependencies Resolved\\n\\n================================================================================\\n Package         Arch              Version                Repository       Size\\n================================================================================\\nInstalling:\\n htop            x86_64            2.0.2-1.el7            epel             98 k\\n\\nTransaction Summary\\n================================================================================\\nInstall  1 Package\\n\\nTotal download size: 98 k\\nInstalled size: 207 k\\nDownloading packages:\\nRunning transaction check\\nRunning transaction test\\nTransaction test succeeded\\nRunning transaction\\n  Installing : htop-2.0.2-1.el7.x86_64                                      1/1 \\n  Verifying  : htop-2.0.2-1.el7.x86_64                                      1/1 \\n\\nInstalled:\\n  htop.x86_64 0:2.0.2-1.el7                                                     \\n\\nComplete!\\n\"\r\n    ]\r\n}\r\n```\r\n\r\n　　安装成功。\r\n\r\n\r\n\r\n### 9）service 模块\r\n\r\n　　该模块用于服务程序的管理。\r\n　　其主要选项如下：\r\n\r\n> `arguments` #命令行提供额外的参数\r\n> `enabled` #设置开机启动。\r\n> `name=` #服务名称\r\n> `runlevel` #开机启动的级别，一般不用指定。\r\n> `sleep` #在重启服务的过程中，是否等待。如在服务关闭以后等待2秒再启动。(定义在剧本中。)\r\n> `state` #有四种状态，分别为：`started`--->启动服务， `stopped`--->停止服务， `restarted`--->重启服务， `reloaded`--->重载配置\r\n\r\n　　下面是一些例子：\r\n**① 开启服务并设置自启动**\r\n\r\n```\r\n[root@server ~]# ansible web -m service -a \'name=nginx state=started enabled=true\' \r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"enabled\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"started\", \r\n    ……\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"enabled\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"started\", \r\n    ……\r\n}\r\n```\r\n\r\n　　我们可以去查看一下端口是否打开：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ss -ntl\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port              \r\nLISTEN     0      128          *:80                       *:*                                  \r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port                    \r\nLISTEN     0      128          *:80                       *:*                  \r\n```\r\n\r\n　　可以看出我们的80端口已经打开。\r\n**② 关闭服务**\r\n　　我们也可以通过该模块来关闭我们的服务：\r\n\r\n```\r\n[root@server ~]# ansible web -m service -a \'name=nginx state=stopped\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"stopped\", \r\n	……\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"stopped\", \r\n	……\r\n}\r\n```\r\n\r\n　　一样的，我们来查看一下端口：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ss -ntl | grep 80\'\r\n192.168.37.122 | FAILED | rc=1 >>\r\n\r\n192.168.37.133 | FAILED | rc=1 >>\r\n```\r\n\r\n　　可以看出，我们已经没有80端口了，说明我们的nginx服务已经关闭了。\r\n\r\n\r\n\r\n### 10）user 模块\r\n\r\n　　该模块主要是用来管理用户账号。\r\n　　其主要选项如下：\r\n\r\n> `comment`　　# 用户的描述信息\r\n> `createhome`　　# 是否创建家目录\r\n> `force`　　# 在使用state=absent时, 行为与userdel –force一致.\r\n> `group`　　# 指定基本组\r\n> `groups`　　# 指定附加组，如果指定为(groups=)表示删除所有组\r\n> `home`　　# 指定用户家目录\r\n> `move_home`　　# 如果设置为home=时, 试图将用户主目录移动到指定的目录\r\n> `name`　　# 指定用户名\r\n> `non_unique`　　# 该选项允许改变非唯一的用户ID值\r\n> `password`　　# 指定用户密码\r\n> `remove`　　# 在使用state=absent时, 行为是与userdel –remove一致\r\n> `shell`　　# 指定默认shell\r\n> `state`　　# 设置帐号状态，不指定为创建，指定值为absent表示删除\r\n> `system`　　# 当创建一个用户，设置这个用户是系统用户。这个设置不能更改现有用户\r\n> `uid`　　# 指定用户的uid\r\n\r\n　　举例如下：\r\n**① 添加一个用户并指定其 uid**\r\n\r\n```\r\n[root@server ~]# ansible web -m user -a \'name=keer uid=11111\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"comment\": \"\", \r\n    \"createhome\": true, \r\n    \"group\": 11111, \r\n    \"home\": \"/home/keer\", \r\n    \"name\": \"keer\", \r\n    \"shell\": \"/bin/bash\", \r\n    \"state\": \"present\", \r\n    \"stderr\": \"useradd: warning: the home directory already exists.\\nNot copying any file from skel directory into it.\\nCreating mailbox file: File exists\\n\", \r\n    \"system\": false, \r\n    \"uid\": 11111\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"comment\": \"\", \r\n    \"createhome\": true, \r\n    \"group\": 11111, \r\n    \"home\": \"/home/keer\", \r\n    \"name\": \"keer\", \r\n    \"shell\": \"/bin/bash\", \r\n    \"state\": \"present\", \r\n    \"stderr\": \"useradd: warning: the home directory already exists.\\nNot copying any file from skel directory into it.\\nCreating mailbox file: File exists\\n\", \r\n    \"system\": false, \r\n    \"uid\": 11111\r\n}\r\n```\r\n\r\n　　添加完成，我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/passwd |grep keer\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nkeer:x:11111:11111::/home/keer:/bin/bash\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nkeer:x:11111:11111::/home/keer:/bin/bash\r\n```\r\n\r\n**② 删除用户**\r\n\r\n```\r\n[root@server ~]# ansible web -m user -a \'name=keer state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"force\": false, \r\n    \"name\": \"keer\", \r\n    \"remove\": false, \r\n    \"state\": \"absent\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"force\": false, \r\n    \"name\": \"keer\", \r\n    \"remove\": false, \r\n    \"state\": \"absent\"\r\n}\r\n```\r\n\r\n　　一样的，删除之后，我们去看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/passwd |grep keer\'\r\n192.168.37.122 | FAILED | rc=1 >>\r\n\r\n192.168.37.133 | FAILED | rc=1 >>\r\n```\r\n\r\n　　发现已经没有这个用户了。\r\n\r\n\r\n\r\n### 11）group 模块\r\n\r\n　　该模块主要用于添加或删除组。\r\n　　常用的选项如下：\r\n\r\n> `gid=`　　#设置组的GID号\r\n> `name=`　　#指定组的名称\r\n> `state=`　　#指定组的状态，默认为创建，设置值为`absent`为删除\r\n> `system=`　　#设置值为`yes`，表示创建为系统组\r\n\r\n　　举例如下：\r\n**① 创建组**\r\n\r\n```\r\n[root@server ~]# ansible web -m group -a \'name=sanguo gid=12222\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 12222, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"present\", \r\n    \"system\": false\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 12222, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"present\", \r\n    \"system\": false\r\n}\r\n```\r\n\r\n　　创建过后，我们来查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/group | grep 12222\' \r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nsanguo:x:12222:\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nsanguo:x:12222:\r\n```\r\n\r\n　　可以看出，我们的组已经创建成功了。\r\n**② 删除组**\r\n\r\n```\r\n[root@server ~]# ansible web -m group -a \'name=sanguo state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"absent\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"absent\"\r\n}\r\n```\r\n\r\n　　照例查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/group | grep 12222\' \r\n192.168.37.122 | FAILED | rc=1 >>\r\n\r\n192.168.37.133 | FAILED | rc=1 >>\r\n```\r\n\r\n　　已经没有这个组的相关信息了。\r\n\r\n\r\n\r\n### 12）script 模块\r\n\r\n　　该模块用于将本机的脚本在被管理端的机器上运行。\r\n　　该模块直接指定脚本的路径即可，我们通过例子来看一看到底如何使用的：\r\n　　首先，我们写一个脚本，并给其加上执行权限：\r\n\r\n```\r\n[root@server ~]# vim /tmp/df.sh\r\n	#!/bin/bash\r\n\r\n	date >> /tmp/disk_total.log\r\n	df -lh >> /tmp/disk_total.log \r\n[root@server ~]# chmod +x /tmp/df.sh \r\n```\r\n\r\n　　然后，我们直接运行命令来实现在被管理端执行该脚本：\r\n\r\n```\r\n[root@server ~]# ansible web -m script -a \'/tmp/df.sh\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"rc\": 0, \r\n    \"stderr\": \"Shared connection to 192.168.37.122 closed.\\r\\n\", \r\n    \"stdout\": \"\", \r\n    \"stdout_lines\": []\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"rc\": 0, \r\n    \"stderr\": \"Shared connection to 192.168.37.133 closed.\\r\\n\", \r\n    \"stdout\": \"\", \r\n    \"stdout_lines\": []\r\n}\r\n```\r\n\r\n　　照例查看一下文件内容：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /tmp/disk_total.log\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nTue Dec  5 15:58:21 CST 2017\r\nFilesystem      Size  Used Avail Use% Mounted on\r\n/dev/sda2        47G  4.4G   43G  10% /\r\ndevtmpfs        978M     0  978M   0% /dev\r\ntmpfs           993M   84K  993M   1% /dev/shm\r\ntmpfs           993M  9.1M  984M   1% /run\r\ntmpfs           993M     0  993M   0% /sys/fs/cgroup\r\n/dev/sda3        47G   33M   47G   1% /app\r\n/dev/sda1       950M  153M  798M  17% /boot\r\ntmpfs           199M   16K  199M   1% /run/user/42\r\ntmpfs           199M     0  199M   0% /run/user/0\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nTue Dec  5 15:58:21 CST 2017\r\nFilesystem      Size  Used Avail Use% Mounted on\r\n/dev/sda2        46G  4.1G   40G  10% /\r\ndevtmpfs        898M     0  898M   0% /dev\r\ntmpfs           912M   84K  912M   1% /dev/shm\r\ntmpfs           912M  9.0M  903M   1% /run\r\ntmpfs           912M     0  912M   0% /sys/fs/cgroup\r\n/dev/sda3       3.7G   15M  3.4G   1% /app\r\n/dev/sda1       1.9G  141M  1.6G   9% /boot\r\ntmpfs           183M   16K  183M   1% /run/user/42\r\ntmpfs           183M     0  183M   0% /run/user/0\r\n```\r\n\r\n　　可以看出已经执行成功了。\r\n\r\n\r\n\r\n### 13）setup 模块\r\n\r\n　　该模块主要用于收集信息，是通过调用facts组件来实现的。\r\n　　facts组件是Ansible用于采集被管机器设备信息的一个功能，我们可以使用setup模块查机器的所有facts信息，可以使用filter来查看指定信息。整个facts信息被包装在一个JSON格式的数据结构中，ansible_facts是最上层的值。\r\n　　facts就是变量，内建变量 。每个主机的各种信息，cpu颗数、内存大小等。会存在facts中的某个变量中。调用后返回很多对应主机的信息，在后面的操作中可以根据不同的信息来做不同的操作。如redhat系列用yum安装，而debian系列用apt来安装软件。\r\n**① 查看信息**\r\n　　我们可以直接用命令获取到变量的值，具体我们来看看例子：\r\n\r\n```\r\n[root@server ~]# ansible web -m setup -a \'filter=\"*mem*\"\'	#查看内存\r\n192.168.37.122 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1116, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1397, \r\n                \"used\": 587\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1116, \r\n                \"total\": 1984, \r\n                \"used\": 868\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1984\r\n    }, \r\n    \"changed\": false\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1203, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1470, \r\n                \"used\": 353\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1203, \r\n                \"total\": 1823, \r\n                \"used\": 620\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1823\r\n    }, \r\n    \"changed\": false\r\n}\r\n```\r\n\r\n　　我们可以通过命令查看一下内存的大小以确认一下是否一致：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'free -m\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n              total        used        free      shared  buff/cache   available\r\nMem:           1984         404        1122           9         457        1346\r\nSwap:          3813           0        3813\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n              total        used        free      shared  buff/cache   available\r\nMem:           1823         292        1207           9         323        1351\r\nSwap:          3813           0        3813\r\n```\r\n\r\n　　可以看出信息是一致的。\r\n**② 保存信息**\r\n　　我们的setup模块还有一个很好用的功能就是可以保存我们所筛选的信息至我们的主机上，同时，文件名为我们被管制的主机的IP，这样方便我们知道是哪台机器出的问题。\r\n　　我们可以看一看例子：\r\n\r\n```\r\n[root@server tmp]# ansible web -m setup -a \'filter=\"*mem*\"\' --tree /tmp/facts\r\n192.168.37.122 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1115, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1396, \r\n                \"used\": 588\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1115, \r\n                \"total\": 1984, \r\n                \"used\": 869\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1984\r\n    }, \r\n    \"changed\": false\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1199, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1467, \r\n                \"used\": 356\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1199, \r\n                \"total\": 1823, \r\n                \"used\": 624\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1823\r\n    }, \r\n    \"changed\": false\r\n}\r\n```\r\n\r\n　　然后我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# cd /tmp/facts/\r\n[root@server facts]# ls\r\n192.168.37.122  192.168.37.133\r\n[root@server facts]# cat 192.168.37.122 \r\n{\"ansible_facts\": {\"ansible_memfree_mb\": 1115, \"ansible_memory_mb\": {\"nocache\": {\"free\": 1396, \"used\": 588}, \"real\": {\"free\": 1115, \"total\": 1984, \"used\": 869}, \"swap\": {\"cached\": 0, \"free\": 3813, \"total\": 3813, \"used\": 0}}, \"ansible_memtotal_mb\": 1984}, \"changed\": false}\r\n```\r\n\r\n### 14）replace模块\r\n\r\nreplace模块的作用为修改文本信息\r\n\r\n```ruby\r\n[root@ansible ansible]# ansible db -m replace -a \'path=/new.txt regexp=\"^i am.*\" replace=\"we are fine\"\'\r\ndb 为修改的主机\r\n-m 指定的模块\r\nreplace 替换模块\r\npath  修改文件的位置\r\nregexp 用正则去匹配要修改的行\r\nreplace 要修改为什么内容\r\n```\r\n\r\n### 15）\r\n\r\n\r\n','测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要',66,1,0,'2022-10-09 10:59:44','2022-11-24 11:02:52'),(2,'空','测试标题1','https://blog.csdn.net/hawava/article/details/119277507\r\n\r\n# Ansible剧本编写\r\n\r\n\r\n\r\n# ansible剧本组成部分\r\n\r\n![剧本组成](E:\\Pictures\\typora\\6fb8ff0a3d2a42bcbb35da84981063bf.png)\r\n\r\n# ansible剧本编写规范\r\n\r\n剧本编写规范：pyyaml\r\n\r\n1. 合理的信息缩进：yaml使用固定的缩进风格表示数据层结构关系，编写ansible-playbook文件一定不能使用tab键进行缩进。\r\n2. 冒号的使用方法：使用冒号时后面一定要有空格信息，以冒号结尾或冒号信息出现在注释说明中，其后不需要加空格。\r\n3. 短横线：表示列表，使用一个短横线加一个空格。多个项使用同样的缩进级别作为同一个列表的一部分。\r\n\r\n# ansible剧本主机规划\r\n\r\n| 外网IP    | 内网IP      | 主机名 | 功能       | 系统版本   |\r\n| --------- | ----------- | ------ | ---------- | ---------- |\r\n| 10.0.0.61 | 172.16.1.61 | m01    | 管理主机   | CentOS 7.x |\r\n| 10.0.0.41 | 172.16.1.41 | backup | 被管理主机 | CentOS 7.x |\r\n| 10.0.0.31 | 172.16.1.31 | nfs01  | 被管理主机 | CentOS 7.x |\r\n| 10.0.0.7  | 172.16.1.7  | web01  | 被管理主机 | CentOS 7.x |\r\n\r\n# ansible剧本主机清单\r\n\r\n主机清单配置文件： /etc/ansible/hosts\r\n\r\n1. 分组配置\r\n\r\n   [web] — ansible web -a … 统一操作web组的主机\r\n   172.16.1.7\r\n   172.16.1.8\r\n   172.16.1.9\r\n\r\n   [data] — ansible data -a … 统一操作data组的主机\r\n   172.16.1.31\r\n   172.16.1.41\r\n\r\n2. 主机名符号匹配配置\r\n\r\n   [web]\r\n\r\n   172.16.1.[7:9] 通过IP地址匹配配置\r\n\r\n   web[01:03] 通过主机名匹配配置（注意：通过主机名匹配需要在/etc/hosts文件中有主机名和IP的映射）\r\n\r\n3. 加上非标准远程端口（如ssh端口变为52113）\r\n\r\n   [web]\r\n   web01:52113\r\n   172.16.1.7:52113\r\n\r\n4. 主机使用特殊的变量\r\n\r\n   [web]\r\n   172.16.1.7 ansible_ssh_port=52113 ansible_ssh_user=root ansible_ssh_pass=123456\r\n\r\n   [web]\r\n   web01 ansible_ssh_host=172.16.1.7 ansible_ssh_port=52113 ansible_ssh_user=root ansible_ssh_pass=123456\r\n\r\n5. 主机组名嵌入配置\r\n\r\n   [rsync:children] — 嵌入子组信息\r\n   rsync_server\r\n   rsync_client\r\n   [rsync_server] — 子组\r\n   172.16.1.41\r\n   [rsync_client] — 子组\r\n   172.16.1.31\r\n   172.16.1.7\r\n\r\n   [web:vars] — 嵌入式变量信息\r\n   ansible_ssh_host=172.16.1.7 — 变量\r\n   ansible_ssh_port=52113 — 变量\r\n   ansible_ssh_user=root — 变量\r\n   ansible_ssh_pass=123456 — 变量\r\n   [web] — 该组调用以上变量\r\n   web01\r\n\r\n[主机清单官方配置方法](https://docs.ansible.com/ansible/latest/user_guide/intro_inventory.html)\r\n\r\n# ansible剧本编写实践\r\n\r\n## ad-hoc部署rsync服务\r\n\r\n服务端部署\r\n\r\n1. 确认软件安装\r\n   ansible 172.16.1.41 -m yum -a “name=rsync state=installed”\r\n2. 编写文件\r\n   ansible 172.16.1.41 -m copy -a “src=/etc/ansible/server_file/rsync_server/rsyncd.conf dest=/etc/”\r\n3. 创建用户\r\n   ansible 172.16.1.41 -m user -a “name=rsync create_home=no shell=/sbin/nologin”\r\n4. 创建目录\r\n   ansible 172.16.1.41 -m file -a “dest=/backup state=directory owner=rsync group=rsync”\r\n5. 创建密码文件\r\n   ansible 172.16.1.41 -m copy -a “content=‘rsync_backup:redhat’ dest=/etc/rsync.password mode=600”\r\n6. 启动服务\r\n   ansible 172.16.1.41 -m service -a “name=rsyncd state=started enabled=yes”\r\n\r\n客户端部署\r\n\r\n1. 确认软件安装\r\n   ansible 172.16.1.41,172.16.1.7 -m yum -a “name=rsync state=installed”\r\n2. 创建密码文件\r\n   ansible 172.16.1.31,172.16.1.7 -m copy -a “content=‘redhat’ dest=/etc/rsync.password mode=600”\r\n3. 测试\r\n   ansible 172.16.1.31,172.16.1.7 -m file -a “dest=/tmp/test.txt state=touch”\r\n   ansible 172.16.1.31,172.16.1.7 -m shell -a \"rsync -avz /tmp/test.txt rsync_backup@172.16.1.41::backup --password-file=/etc/rsync.password\r\n\r\n## playbook部署rsync服务\r\n\r\n创建playbook目录\r\n\r\n```bash\r\n[root@m01 ~]# mkdir /etc/ansible/ansible-playbook\r\n1\r\n```\r\n\r\n进入playbook目录\r\n\r\n```bash\r\n[root@m01 ~]# cd /etc/ansible/ansible-playbook/\r\n1\r\n```\r\n\r\n创建编辑rsync剧本（剧本文件扩展名尽量写为yaml，方便识别文件是一个剧本文件，且文件编写时会有颜色提示）\r\n\r\n```bash\r\n[root@m01 ansible-playbook]# vim rsync_server.yaml\r\n\r\n- hosts: 172.16.1.41\r\n  tasks:\r\n    - name: 01-install rsync\r\n      yum: name=rsync state=installed\r\n    - name: 02-push rsyncd.conf\r\n      copy: src=../server_file/rsync_server/rsyncd.conf dest=/etc/\r\n    - name: 03-create user\r\n      user: name=rsync create_home=no shell=/sbin/nologin\r\n    - name: 04-create backup directory\r\n      file: dest=/backup state=directory owner=rsync group=rsync\r\n    - name: 05-create password file\r\n      copy: content=rsync_backup:redhat dest=/etc/rsync.password mode=600\r\n    - name: 06-start rsync service\r\n      service: name=rsyncd state=started enabled=yes    # 配置文件改变，不重启服务不会生效？\r\n\r\n- hosts: 172.16.1.31,172.16.1.7\r\n  tasks:\r\n    - name: 01-install rsync\r\n      yum: name=rsync state=installed\r\n    - name: 02-create password file\r\n      copy: content=redhat dest=/etc/rsync.password mode=600\r\n    - name: 03-create test file  \r\n      file: dest=/tmp/test.txt state=touch \r\n    - name: 04-test      shell: rsync -avz /tmp/test.txt rsync_backup@172.16.1.41::backup --password-file=/etc/rsync.password\r\n1234567891011121314151617181920212223242526\r\n```\r\n\r\n如何执行剧本？\r\n\r\n1. 检查剧本的语法格式\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# ansible-playbook --syntax-check rsync_server.yaml \r\n   1\r\n   ```\r\n\r\n2. 模拟执行剧本（彩排）\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# ansible-playbook -C rsync_server.yaml \r\n   1\r\n   ```\r\n\r\n3. 正式执行剧本（实干）\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# ansible-playbook rsync_server.yaml \r\n   1\r\n   ```\r\n\r\n# ansible剧本常见错误\r\n\r\n- 剧本语法规范错误（空格、冒号、短横线）；\r\n- 剧本模块使用是否正确；\r\n- 剧本中一个name标识下只能写一个模块任务；\r\n- 剧本中尽量不要大量使用shell模块。\r\n\r\n剧本执行出现错误排查思路/步骤：\r\n1）找到剧本中出现问题关键点；\r\n2）将剧本中的操作转换成单条ad-hoc命令操作；\r\n3）将模块的功能操作转换成linux命令；\r\n4）本地管理主机上执行命令测试；\r\n5）远程被管理主机上执行命令测试。\r\n\r\n# ansible剧本扩展功能\r\n\r\n## 变量\r\n\r\n变量名由字母、数字、下划线组成，变量名需要以字母开头，ansible内置关键字不能作为变量名。\r\n\r\n1. 在剧本文件中编写\r\n\r\n   在剧本中定义变量，借助vars关键字\r\n\r\n   > vars:\r\n   > backupdir: /backup\r\n   > passfile: rsync.password\r\n\r\n   使用{{ 变量名 }}可以引用对应的变量\r\n\r\n2. 在命令行中指定(临时设置)\r\n\r\n   > ansible-playbook -e backupdir=/backup -e passfile=rsync.password rsync_server.yaml\r\n\r\n3. 在主机清单中编写\r\n\r\n   > vim /etc/ansible/hosts\r\n   > [rsync_server:vars]\r\n   > backupdir: /backup\r\n   > passfile: rsync.password\r\n\r\n三种方式优先级：命令行变量设置>剧本变量设置>主机清单变量设置\r\n\r\n## 注册\r\n\r\n注册功能可以在执行剧本时，输出命令结果。\r\n\r\n```bash\r\n- hosts: rsync_server\r\n  tasks:\r\n    - name: check server port\r\n      shell: netstat -lntup \r\n      register: get_server_port\r\n    \r\n    - name: display port info\r\n      debug: msg={{ get_server_port.stdout_lines }}\r\n12345678\r\n```\r\n\r\n## 判断\r\n\r\n指定判断条件\r\n\r\n> (ansible_hostname == “nfs01”)\r\n> (ansible_hostname == “web01”)\r\n\r\n例如\r\n\r\n```bash\r\n- hosts: rsync_server\r\n  remote_user: root\r\n  tasks:\r\n    - name: Check File\r\n      file: path=/tmp/this_is_{{ ansible_hostname }}_file state=touch\r\n      when: (ansible_hostname == \"nfs\") or (ansible_hostname == \"backup\")	\r\n123456\r\n```\r\n\r\nsetup模块显示被管理主机系统的详细信息\r\n\r\n> ansible rsync_server -m setup\r\n\r\nsetup模块获取被管理主机的内置变量信息\r\n\r\n> ansible rsync_server -m setup -a “filter=xxx”\r\n\r\n常见主机信息\r\n\r\n| 参数                               | 作用                               |\r\n| ---------------------------------- | ---------------------------------- |\r\n| ansible_all_ipv4_addresses         | 仅显示ipv4的信息                   |\r\n| ansible_devices                    | 仅显示磁盘设备信息                 |\r\n| ansible_distribution               | 显示是什么系统，例：centos,suse等  |\r\n| ansible_distribution_major_version | 显示是系统主版本                   |\r\n| ansible_distribution_version       | 仅显示系统版本                     |\r\n| ansible_machine                    | 显示系统类型，例：32位，还是64位   |\r\n| ansible_eth0                       | 仅显示eth0的信息                   |\r\n| ansible_hostname                   | 仅显示主机名                       |\r\n| ansible_kernel                     | 仅显示内核版本                     |\r\n| ansible_lvm                        | 显示lvm相关信息                    |\r\n| ansible_memtotal_mb                | 显示系统总内存                     |\r\n| ansible_memfree_mb                 | 显示可用系统内存                   |\r\n| ansible_memory_mb                  | 详细显示内存情况                   |\r\n| ansible_swaptotal_mb               | 显示总的swap内存                   |\r\n| ansible_swapfree_mb                | 显示swap内存的可用内存             |\r\n| ansible_mounts                     | 显示系统磁盘挂载情况               |\r\n| ansible_processor                  | 显示cpu个数(具体显示每个cpu的型号) |\r\n| ansible_processor_vcpus            | 显示cpu个数(只显示总的个数)        |\r\n\r\n## 循环\r\n\r\n一个name下只能执行一个ad-hoc命令，如果想要执行多条，可以使用循环。\r\n\r\n```bash\r\n- hosts: all\r\n  remote_user: root\r\n  tasks:\r\n    - name: Add Users\r\n      user: name={{ item.name }} groups={{ item.groups }} state=present\r\n      with_items: \r\n        - { name: \'testuser1\', groups: \'bin\' }\r\n        - { name: \'testuser2\', groups: \'root\' }\r\n12345678\r\n```\r\n\r\n## 标签\r\n\r\n指定执行标签任务： ansible-playbook --tags=t2 test.yml\r\n跳过指定标签任务： ansible-playbook --skip-tags=t2 test.yml\r\n\r\n```bash\r\n- hosts: all\r\n  ignore_errors: yes\r\n  remote_user: root\r\n  tasks:\r\n    - name: Check File\r\n      file: path=/tmp/this_is_{{ ansible_hostname }}_file state=touch\r\n      when: (ansible_hostname == \"nfs01\") or (ansible_hostname == \"backup\")\r\n      tags: t1\r\n    \r\n    - name: install httpd\r\n      yum: name=httpd state=installed\r\n      when: (ansible_all_ipv4_addresses == [\"172.16.1.7\",\"10.0.0.7\"])\r\n      tags: t2\r\n    \r\n    - name: install httpd2\r\n      yum: name=httpd2 state=installed\r\n      when: (ansible_distribution == \"ubuntu\")\r\n      tags: t3\r\n123456789101112131415161718\r\n```\r\n\r\n## 触发\r\n\r\n```bash\r\n- hosts: backup\r\n  remote_user: root\r\n  tasks:\r\n    - name: 01 Install rsync\r\n      yum: name=rsync state=present\r\n        \r\n    - name: 02 push config file\r\n      copy: src=./file/{{ item.src }} dest=/etc/{{ item.dest }} mode={{ item.mode }} \r\n      with_items:\r\n        - { src: \"rsyncd.conf\", dest: \"rsyncd.conf\", mode: \"0644\" }\r\n        - { src: \"rsync.password\", dest: \"rsync.password\", mode: \"0600\" }\r\n      notify: restart rsync server\r\n\r\n  handlers:    # 当notify发出时，handlers起作用\r\n    - name: restart rsync server\r\n      service: name=rsyncd state=restarted   \r\n12345678910111213141516\r\n```\r\n\r\n## 忽略错误\r\n\r\n默认playbook会检查命令和模块的返回状态，如遇到错误就中断playbook的执行，可以加入ignore_errors: yes忽略错误。\r\n\r\n```bash\r\n- hosts: all\r\n  remote_user: root\r\n  tasks:\r\n    - name: Ignore False\r\n      command: /bin/false\r\n      ignore_errors: yes\r\n    - name: touch new file\r\n      file: path=/tmp/oldboy_ignore state=touch	\r\n12345678\r\n```\r\n\r\n## 整合剧本\r\n\r\n1. include_tasks: playbook.yaml\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# cat main.yml\r\n   - hosts: all(host与include_tasks剧本中的host冲突)\r\n     tasks:\r\n       - include_tasks: rsync-server.yml\r\n       - include_tasks: nfs-server.yml\r\n   12345\r\n   ```\r\n\r\n2. include: playbook.yml(设置gather_facts: no可提高执行速度)\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# cat main.yml\r\n   - include：rsync-server.yml	\r\n   - include：nfs-server.yml\r\n   123\r\n   ```\r\n\r\n3. \\- import_playbook（主要使用该方法进行汇总）\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# vim main.yml \r\n   - import_playbook: rsync.yml    \r\n   - import_playbook: nfs.yml      \r\n   123\r\n   ```\r\n\r\n# ansible剧本角色信息\r\n\r\n待解决问题：\r\n\r\n1. 目录结构不够规范？\r\n2. 编写好的任务如何重复调用？\r\n3. 服务端配置文件改动,客户端参数信息如何自动变化？\r\n4. 汇总剧本中如何显示主机角色信息？\r\n5. 一个剧本内容信息过多,不容易进行阅读,如何进行拆分？\r\n\r\n## 规范目录结构\r\n\r\n创建相应角色目录\r\n\r\n```bash\r\n[root@m01 ~]# cd /etc/ansible/roles/\r\n[root@m01 roles]# mkdir {rsync,nfs-server,nfs-client}\r\n12\r\n```\r\n\r\n创建角色子目录\r\n\r\n```bash\r\n[root@m01 roles]# mkdir {rsync,nfs-server,nfs-client}/{vars,tasks,templates,handlers,files}\r\n1\r\n```\r\n\r\n查看目录结构\r\n\r\n```bash\r\n[root@m01 roles]# tree /etc/ansible/roles/\r\n/etc/ansible/roles/\r\n|-- nfs-server\r\n|   |-- files    -- 保存需要分发的文件\r\n|   |-- handlers    -- 保存触发器配置文件\r\n|   |-- tasks    -- 保存要执行的动作信息文件\r\n|   |-- templates    -- 保存需要分发的模板文件，模板文件中可以设置变量（调取var目录中的变量值）\r\n|   `-- vars    -- 保存变量信息文件\r\n......\r\n123456789\r\n```\r\n\r\n## roles目录下创建文件\r\n\r\n以部署NFS服务端为例：\r\n\r\n1. 编写tasks目录中main.yml文件\r\n\r\n   ```bash\r\n   [root@m01 tasks]# vim main.yml\r\n   - name: 01-copy nfs conf file    \r\n     copy: src=exports dest=/etc/ -- 自动去往file目录寻找exports文件\r\n     - name: 02-create data dir    \r\n     file: path={{ Data_dir }} state=directory owner=nfsnobody group=nfsnobody\r\n     notify: restart nfs server \r\n   - name: 03-start server\r\n     service: name={{ item }} state=started enabled=yes\r\n     with_items:    \r\n       - rpcbind\r\n       - nfs\r\n   12345678910\r\n   ```\r\n\r\n2. 编写vars目录中main.yml文件\r\n\r\n   ```bash\r\n   [root@m01 tasks]# cd ../vars\r\n   [root@m01 vars]# vim main.yml\r\n   Data_dir: /data\r\n   123\r\n   ```\r\n\r\n3. 编写files目录中需要分发的文件\r\n\r\n   ```bash\r\n   [root@m01 vars]# cd ../files/\r\n   [root@m01 files]# echo \'/data172.16.1.0/24(rw,sync)\' > exports\r\n   12\r\n   ```\r\n\r\n4. 编写handlers目录中main.yml文件\r\n\r\n   ```bash\r\n   [root@m01 tasks]# cd ../handlers/\r\n   [root@m01 handlers]# vim main.yml\r\n   [root@m01 handlers]# cat main.yml \r\n   - name: restart nfs server\r\n     service: name=nfs state=restarted\r\n   12345\r\n   ```\r\n\r\n5. 编写好的目录结构\r\n\r\n   ```bash\r\n   [root@m01 nfs-server]# tree\r\n   .\r\n   |-- files\r\n   |   `-- exports\r\n   |-- handlers\r\n   |   `-- main.yml\r\n   |-- tasks\r\n   |   `-- main.yml\r\n   |-- templates\r\n   `-- vars\r\n       `-- main.yml\r\n   \r\n   5 directories, 4 files\r\n   12345678910111213\r\n   ```\r\n\r\n## 编写主剧本文件\r\n\r\n```bash\r\n- hosts: nfs_server\r\n  roles:\r\n    - nfs-server\r\n\r\n- hosts: nfs_client\r\n  roles:\r\n    - nfs-client\r\n1234567\r\n```\r\n\r\n\r\n','',24,1,0,'2022-10-09 10:59:44','2022-11-24 09:23:27'),(3,'http://localhost:8000\\111@qq.com\\blog\\icon\\icon.jpg','大数据笔记','# 一、大数据发展趋势与鲲鹏大数据\n导读\n\n\n大数据从什么地方来？这些数据有哪些特点？\n\n大数据可以应用在哪些社会领域？\n\n大数据面临哪些挑战？\n\n大数据时代的机遇与挑战 \n大数据的定义\n\n在3V的基础上，业界对4V的定义加上了价值密度低（Value），而IBM对4V的定义加上了\n真实准确（Veracity）。\n\n目前对大数据尚未有一个公认的定义，不同的定义基本上是从特征出发，试图给出大数据\n的定义\n\n大数据处理与传统数据处理的差异\n\n企业级大数据平台应用场景\n• 社交网络和物联网技术拓展了数据采集技术渠道。\n• 分布式存储和计算技术夯实了大数据处理的技术基础。\n• 深度神经网络等新兴技术开辟大数据分析技术的新时代。\n\n\n\n大数据应用的主要计算模式\n\nHadoop大数据生态圈\nHadoop成为大数据批量处理的基础，但无法提供实时分析。\n\n\n\n传统数据处理遭遇天花板\n\n\n面临的挑战\n▫ 业务部门无清晰的大数据需求\n▫ 企业内数据孤岛严重\n▫ 数据可用性低,质量差\n▫ 数据相关管理技术和结构\n▫ 数据安全问题\n▫ 大数据人才的缺乏\n▫ 数据开放与隐私的权衡\n\n带来的机遇\n▫ 大数据挖掘成为商业分析的核心\n▫ 大数据成为信息技术应用的支撑点\n▫ 大数据成为信息产业持续增长的新引擎\n\n华为鲲鹏解决方案\n基于鲲鹏处理器,构建整机计算功能\n\n第一代TaiShan 100服务器是基于鲲鹏916处理器，2016年推出市场。2019年推出TaiShan\n200服务器基于最新的鲲鹏920处理器，是市场的主打产品。\n\n鲲鹏生态兼容的操作系统\n\n\n华为云鲲鹏云服务支持丰富场景\n\n\n华为大数据解决方案优势\n　高安全：\n　　▫ 服务器及大数据平台自主可控\n　　▫ 芯片级数据加密，数据不失密\n　　\n　高性能：\n　　▫ 比同档通用服务器性能提升30%\n　　▫ 支持5000+节点大数据集群\n　　\n　高效能：\n　　▫ 比通用服务器能耗降低30%\n　　▫ 同等算力需求下，机架空间省30%\n\n华为云大数据服务\n\n\nDAYU寓意“大禹”治水， 围绕着企业数据湖，提供一站式数据资产管理、开发、探索和\n共享能力。\n\n华为云MRS服务综述\n\n华为云MRS服务的优势\n\nCarbonData是一种新型的Apache Hadoop本地文件格式，使用先进的列式存储、索引、压\n缩和编码技术，以提高计算效率，有助于加速超过PB数量级的数据查询，可用于更快的交\n互查询。同时，CarbonData也是一种将数据源与Spark集成的高性能分析引擎。\n\n\n\n\n华为云MRS服务应用场景\n\n\n• 实时数据处理通常用于异常检测、欺诈识别、基于规则告警、业务流程监控等场景，在数\n据输入系统的过程中，对数据进行处理。\n• 例如在梯联网行业，智能电梯的数据，实时传入到MRS的流式集群中进行实时告警\n\n\n# 二、HDFS分布式文件管理系统和ZooKeeper\n导读\n\n\n大数据平台提供的最基本的两个功能是什么？\n存储和计算能力\n\nHDFS主要包括哪些角色？\nNameNode，DataNode，Client\n\n大数据生态圈组件为什么需要Zookeeper去提供分布式协调？\nHDFS分布式文件管理系统\n特性\n高容错性：认为硬件总是不可靠的；\n高吞吐量：对大量数据访问的应用提供吞吐量支持；\n大文件存储：支持存储TB-PB级别的数据。\n擅长：大文件存储与访问、流式数据访问\n不擅长：大量小文件存储、随机写入、低延迟读取\n\n基本系统架构\n\nHDFS架构包含三个部分：NameNode，DataNode，Client。\n　　▫ NameNode：NameNode用于存储、生成文件系统的元数据。运行一个实例。\n　　▫ DataNode：DataNode用于存储实际的数据，将自己管理的数据块上报给NameNode ，运行多个实例。\n　　▫ Client：支持业务访问HDFS，从NameNode ,DataNode获取数据返回给业务。多个实例，和业务一起运行。\n\nBlock 块\nHDFS默认一个块128MB,一个文件被分成多个块,以块做存储单位。\n抽象的块概念可以带来一下几个明显的好处：\n　　• 支持大规模文件存储：文件以块为单位进行存储，一个大规模文件可以被分拆成若干个文件块，不同的文件块可以被分发到不同的节点上，因此，一个文件的大小不会受到单个节点的存储容量的限制，可以远远大于网络中任意节点的存储容量\n　　• 简化系统设计：首先，大大简化了存储管理，因为文件块大小是固定的，这样就可以很容易计算出一个节点可以存储多少文件块；其次，方便了元数据的管理，元数据不需要和文件块一起存储，可以由其他系统负责管理元数据\n　　• 适合数据备份：每个文件块都可以冗余存储到多个节点上，大大提高了系统的容错性和可用性\n\nNameNode 和 DataNodes\n\nNameNode	DataNodes\n存储元数据	存储文件内容\n元数据保存在内存中	文件内容保存在磁盘中\n保存文件 block,datanode之间的映射关系	维护了block id 到datanode本地文件的映射关系\n\n1、在HDFS中，名称节点（NameNode）负责管理分布式文件系统的命名空间（Namespace），保存了两个核心的数据结构，即FsImage和EditLog\n　　▫ FsImage用于维护文件系统树以及文件树中所有的文件和文件夹的元数据\n　　▫ 操作日志文件EditLog中记录了所有针对文件的创建、删除、重命名等操作\n\n名称节点（NameNode）记录了每个文件中各个块所在的数据节点的位置信息\n\n2、数据节点（DataNode）是分布式文件管理系统HDFS的工作节点，负责数据的存储和读取，会根据客户端或者是名称节点的调度来进行数据的存储和检索，并向名称节点自己所存储的块的列表。\n\n每个数据节点的数据会被保存在各自节点的本地Linux文件系统中\n\nHDFS体系结构\n\n\nHDFS通信协议\n\n\nHDFS高可用性（HA）\n\nHDFS的高可靠性（HA）主要体现在利用zookeeper实现主备NameNode，以解决单点\nNameNode故障问题。\n\n\nZKFC(ZooKeeper Failover Controller)　用于监控NameNode节点的主备状态。\nZKFC控制NameNode主备仲裁\n　　▫ ZKFC作为一个精简的仲裁代理，其利用zookeeper的分布式锁功能，实现主备仲裁，再通过命令通道，控制NameNode的主备状态。ZKFC与NN部署在一起，两者个数相\n同。\nJN(JournalNode)　用于存储Active NameNode生成的Editlog。Standby NameNode加载JN\n上Editlog，同步元数据。\n\n元数据的持久化\n\nFSImage.ckpt: 在内存中对fsimage文件和EditLog文件合并（merge）后产生新的fsimage，写到磁盘上，这个过程叫checkpoint.。\nEditLog.new: NameNode每隔1小时或Editlog满64MB就触发合并,合并时,将数据传到Standby NameNode时,因数据读写不能同步进行,此时NameNode产生一个新的日志文件Editlog.new用来存放这段时间的操作日志。\n\nHDFS联邦（Federation）\n\n\n各NameNode间元数据不共享，每个NameNode都有对应的standby，两两之间并不互相通信，一个失效也不会影响其他NameNode\n\n数据副本机制\n\n副本距离计算公式：\n▫ Distance(Rack1/D1, Rack1/D1)=0，\n▫ 同一台服务器的距离为0。\n▫ Distance(Rack1/D1, Rack1/D3)=2，\n▫ 同一机架不同的服务器距离为2。\n▫ Distance(Rack1/D1, Rack2/D1)=4，\n▫ 不同机架的服务器距离为4。\n▫ 不同数据中心的节点距离为6。\n副本放置策略：\n　　第一个副本：放置在上传文件的数据节点；如果是集群外提交，则随机挑选一台磁盘不太满、CPU不太忙的节点\n　　第二个副本：放置在与第一个副本不同的机架的节点上\n　　第三个副本：与第一个副本相同机架的其他节点上\n　　更多副本：随机节点\n\nHDFS常用shell命令\n\n\nHDFS数据写入流程\n\n\nHDFS数据读取流程\n\n## ZooKeeper\nZooKeeper体系架构\n\n\n2x+1个节点与2x+2个节点的容灾能力相同（3个与4个相同，5个与6个相同…），考虑到选举以及完成写操作的速度与节点数的相关性，ZooKeeper应部署奇数个节点。\n\nZooKeeper关键特性\n\n\nZooKeeper读特性\n\n\nZooKeeper写特性\n\n１、同读请求一样，客户端可以向任一server提出写请求，server将这一请求发送给leader。\n2、leader获取写请求后，会向所有节点发送这条写请求信息，询问是否能够执行这次写\n操作。\n3、 follower节点根据自身情况给出反馈信息ACK应答消息，leader根据反馈信息，若获\n取到的可以执行写操作的数量大于实例总数的一半，则认为本次写操作可执行。\n4、 leader将结果反馈给各follower，并完成写操作，各follower节点同步leader的数据，\n本次写操作完成。\n\nZooKeeper客户端常用命令\n\n\n课后习题\n思考题:\n1.ZooKeeper为什么建议基数部署?\n\n容灾能力相同，但部署成本低\n\n2.HDFS数据块为什么一般比磁盘块大?\n\n块比磁盘大，目的是为了最小化寻址开销。块足够大，那么从磁盘传输数据的时间会明显大于定位这个块开始位置所需的时间。但也不能太大，因为map通常只处理一个块中的数据。如果Map数太少，则作业运行速度会比较慢\n\n3.HDFS在数据写入时,能读取到吗?\n\n当数据在写入的时候，写入数据不能立即可见，在命令空间是立即可见的。当写入超过一个块或者结束的时候，对一个新的reader就是可见的。当前正在写入的块，对其他reader是不可见的\n\n\n1.Namespace（命名空间）的限制\n由于Namenode在内存中存储所有的元数据（metadata），因此单个Namenode所能存储的对象（文件+块）数目收到Namenode所在JVM的heap size的限制。\n2.性能的瓶颈（吞吐量）\n由于是单个NameNode的HDFS架构，因此整个HDFS文件系统的吞吐量受限于单个Namenode的吞吐量。\n3.隔离问题\n由于HDFS仅有一个Namenode，无法隔离各个程序，因此HDFS上的一个实验程序就很有可能影响整个HDFS上运行的程序。\n4.集群的可用性\n在只有一个Namenode的HDFS中，此Namenode的宕机无疑会导致整个集群不可用。\n\n\n\nLeader： 通过选举算法确定，zk中的选举是精华，本人能力有限，怕说不清楚，所有不做讨论,作用发起投票，下达决议，更新系统\nFollower: 接受客户连接，读写请求，发给leader，参与投票，返回客户端结果\nObserver:同步leader\n\n返回目录\n\n# 三、Hive分布式数据仓库\n导读\n\n\n能够通过写SQL语句就可以进行大数据的统计分析？\n通过HQL（类似SQL）的语句可以实现\n\nHive中写HQL语句最终转换成了什么程序？\nMapReduce\n\nHive提供了哪些客户端接口供用户使用？\nHive CLI（Hive Command Line，Hive命令行），客户端可以直接在命令行模式下进行操作。\nhwi（Hive Web Interface，Hive Web接口），Hive提供了更直观的Web界面。\nhiveserver，Hive提供了Thrift服务，Thrift客户端目前支持C++/Java/PHP/Python/Ruby。\n\nHive概述\nHive是基于Hadoop的数据仓库软件,可以查询和管理PB级别的分布式数据\n\nHive特性\n灵活的ETL\n支持Tez,Spark等多种计算引擎\n可直接访问HDFS文件以及HBase\n易用易编程\nHive的使用场景\n\n•Hive 构建在基于静态批处理的Hadoop 之上，Hadoop 通常都有较高的延迟并且在作业提\n交和调度的时候需要大量的开销。\n\n• Hive 并不能够在大规模数据集上实现低延迟快速的查询，例如，Hive 在几百MB 的数据集\n上执行查询一般有分钟级的时间延迟。因此，Hive 并不适合那些需要低延迟的应用，例如，\n联机事务处理（OLTP）。\n\n• Hive 并非为联机事务处理而设计，Hive 并不提供实时的查询和基于行级的数据更新操作。\nHive 的最佳使用场合是大数据集的批处理作业，例如，网络日志分析。\n\nHive的优点\n\n• HiveServer Hive对外提供SQL服务的主要进程。\n• MetaStore Hive提供元数据信息的进程，可供HiveServer，SparkSQL，Oozie等组件调用。\n• Beeline hive的命令行客户端。\n• JDBC java统一数据库接口。\n• Thrift 一种序列化、通信协议。\n• ODBC 基于C/C++的数据库标准接口。\n\nHive功能与架构\nHive的架构\n\n• MetaStore : 存储表、列和Partition等元数据。\n• Driver : 管理HiveQL执行的生命周期，并贯穿Hive任务整个执行期间。\n• Compiler : 编译HiveQL并将其转化为一系列相互依赖的Map/Reduce任务。\n• Optimizer : 优化器，分为逻辑优化器和物理优化器，分别对HiveQL生成的执行计划和MapReduce任务进行优化。\n• Executor : 按照任务的依赖关系分别执行Map/Reduce任务。\n• ThriftServer : 提供thrift接口，作为JDBC和ODBC的服务端，并将Hive和其他应用程序集成\n起来。\n• Clients : 包含命令行接口Beeline 和JDBC/ODBC 接口，为用户访问提供接口。\n\nHive运行流程\n\n\nHive数据存储模型\n\n\n• 数据库：创建表时如果不指定数据库，则默认为default数据库。\n• 表：物理概念，实际对应HDFS上的一个目录。\n• 分区：对应所在表所在目录下的一个子目录。\n• 桶：对应表或分区所在路径的一个文件。\n• 倾斜数据：数据集中于个别字段值的场景，比如按照城市分区时，80%的数据都来自某个大城市。\n• 正常数据：不存在倾斜的数据。\n\n\n\n• 分区表：CREATE TABLE invites (foo INT, bar STRING) PARTITIONED BY (ds STRING);\n• 分桶表：\n　▫ create table stu_buck(sno int,sname string,sex string,sage int,sdept string) clustered by(sno) sorted by(sno DESC) into 4 buckets row format delimited fields terminated by ‘,’;\n▫ #设置变量,设置分桶为true, 设置reduce数量是分桶的数量个数\n▫ set hive.enforce.bucketing = true;\n▫ set mapreduce.job.reduces=4;\n▫ #开会往创建的分通表插入数据(插入数据需要是已分桶, 且排序的)\n▫ #可以使用distribute by(sno) sort by(sno asc) 或是排序和分桶的字段相同的时候使用Cluster by(字段)\n▫ #注意使用cluster by 就等同于分桶+排序(sort)\n▫ insert into table stu_buck select sno,sname,sex,sage,sdept from student distribute by(sno) sort by(sno asc);\n\nHive支持的函数\n\n\n• Round 四舍五入，Floor向下取整，abs 绝对值，rand 随机数\n• To_data 返回日期 month 返回日期中月份 day 返回日期中的具体天\n• hive> select day(‘2011-12-08 10:03:01’) from dual;\n8\n• 如果内置函数不能满足用户需求时，Hive可支持自定义函数。\n• UDF用来解决 一行输入一行输出(One-to-One maping) 的需求。\n\nHive基本操作\n运行Hive服务\n\nHive SQL介绍\n\nHive SQL介绍-DDL操作\nDDL即数据定义语言，DDL操作都是对元数据的操作。主要包含如下操作：\n▫ Create/Drop/Alter Database；\n▫ Create/Drop/Truncate Table；\n▫ Alter Table/Partition/Column；\n▫ Create/Drop/Alter View；\n▫ Create/Drop Index；\n▫ Create/Drop Function；\n▫ Show；\n▫ Describe。\n\n\n\nHive SQL介绍-DML操作\n\n\nHive SQL介绍-DQL操作\n\n# 四、HBase技术原理\n导读\n\nHBase能否应用于实时响应查询计算的应用场景？\nHBase的优势在于实时计算，所有实时数据都直接存入HBase中，客户端通过API直接访问HBase，实现实时计算。由于它使用的是nosql，或者说是列式结构，从而提高了查找性能，使其能运用于大数据场景，这是它跟MapReduce的区别。\n\n为什么说HBase是键值类型数据库？\nKV将简单的键映射到（可能）更复杂的值，就像一个巨大的哈希表。\n\nHBase的主要角色有哪些？分别提供什么作用？\nHMaster\n监控RegionServer\n处理RegionServer故障转移\n处理元数据变更\n处理region的分配或移除\n空闲时对数据进行负载均衡\n通过zookeeper发布自己的位置给客户端\nRegionServer\n负责存储Hbase的实际数据\n处理分配给它的region\n刷新缓存到HDFS上\n维护HLog\n执行压缩\n负责处理Region分片\n\nZookeeper对HBase提供了什么服务支持？\nZookeeper 作用有三点：\n　　▫ 1、分布式锁\n　　▫ 2、事件监控\n　　▫ 3、存储HBase的Region Server数据，充当微型数据库\n\nHBase基本介绍\n简介\nHBase ：兼容结构化/非结构化数据，容量大，高并发，低时延，低成本的数据库\n\n\n\n• 大表：bigtable结构\n　　BigTable 是一个疏松的分布式的持久的多维排序的map，这个map有行健、列键和时间戳索引，每一个值都是连续的byte数组\n• Zookeeper 作用有三点：\n　　▫ 1、分布式锁\n　　▫ 2、事件监控\n　　▫ 3、存储HBase的Region Server数据，充当微型数据库\n\nHBase与RDB的对比\nHBase与传统的关系数据库的区别主要体现在以下几个方面：\n　　▫ 数据索引：关系数据库通常可以针对不同列构建复杂的多个索引，以提高数据访问性能。HBase只有一个索引——行键，通过巧妙的设计，HBase中的所有访问方法，或者通过行键访问，或者通过行键扫描，从而使得整个系统不会慢下来\n　　▫ 数据维护：在关系数据库中，更新操作会用最新的当前值去替换记录中原来的旧值，旧值被覆盖后就不会存在。而在HBase中执行更新操作时，并不会删除数据旧的版本，而是生成一个新的版本，旧有的版本仍然保留\n　　▫ 可伸缩性：关系数据库很难实现横向扩展，纵向扩展的空间也比较有限。相反，HBase和BigTable这些分布式数据库就是为了实现灵活的水平扩展而开发的，能够轻易地通过在集群中增加或者减少硬件数量来实现性能的伸缩\n\nHBase应用场景\n\n\n• 对象存储：1B~100M 对象存储（图片、网页、文本、新闻） —— 海量存储\n• 时序数据：时间序列数据（传感器、监控数据、股票K线）—— 高并发/海量存储\n• 气象数据：卫星轨道、气象数据 —— 高并发/海量存储\n• Cube分析：实时报表 —— 高并发/海量存储\n• NewSQL：元数据库、索引查询 —— SQL、二级索引、动态列\n• Feeds流：朋友圈 —— 高并发请求\n• 消息/订单存储：聊天信息、订单/保存存储 —— 强同步 海量数据\n• 用户画像：用户特征存储 —— 万列稀疏矩阵\n• 兼容结构化/非结构化数据，数据存储容量大，高并发，低时延，低成本的数据库\n\nHBase相关概念\n数据模型\n\n\nHBase表结构\n\n\n\n数据存储概念视图\n\n\n数据存储物理视图\n\n\n行存储\n\n\n列存储\n\n\nHBase架构\n\n\n\n\n• Client使用HBase的RPC机制与Master、RegionServer进行通信。Client与Master进行管理类通信，与RegionServer进行数据操作类通信。\n• RegionServer负责提供表数据读写等服务，是HBase的数据处理和计算单元。RegionServer一般与HDFS集群的DataNode部署在一起，实现数据的存储功能。\n• HMaster，在HA模式下，包含主用Master和备用Master。\n　　▫ 主用Master：负责HBase中RegionServer的管理，包括表的增删改查；RegionServer的负载均衡，Region分布调整；Region分裂以及分裂后的Region分配；RegionServer失效后的Region迁移等。\n　　▫ 备用Master：当主用Master故障时，备用Master将取代主用Master对外提供服务。故障恢复后，原主用Master降为备用。\n• HDFS为HBase提供高可靠的文件存储服务，HBase的数据全部存储在HDFS中。\n• MemStore：当RegionServer中的MemStore大小达到配置的容量上限时，RegionServer会\n将MemStore中的数据“flush”到HDFS中。\n• StoreFile：随着数据的插入，一个Store会产生多个StoreFile，当StoreFile的个数达到配置\n的最大值时，RegionServer会将多个StoreFile合并为一个大的StoreFile。\n\n\n\n\n表和Region\n\nRegion的定位\n\n\n• hbase0.96之后,hbase就废弃了ROOT表**,仅保留hbase:meta\n\n\nHMaster高可用\n\nHBase关键流程\n用户读写数据过程\n\n缓存的刷新\n\nStoreFile的合并\n\nStore工作原理\n\nHLog工作原理\n\n\nHBase突出特点\n多HFile的影响\n\nCompaction\n\n• Compaction都会首先对该Store中所有HFile进行一一排查，排除不满足条件的部分文件：\n　　▫ 排除当前正在执行compact的文件及其比这些文件更新的所有文件(SequenceId更大)。\n　　▫ 排除某些过大的单个文件，如果文件大小大于hbase.hzstore.compaction.max.size( 默认Long最大值 )，则被排除，否则会产生大量IO消耗。\n• 经过排除的文件称为候选文件，HBase接下来会再判断是否满足major compaction条件，如果满足，就会选择全部文件进行合并。判断条件有下面三条，只要满足其中一条就会执行major compaction：\n　　▫ 用户强制执行major compaction。\n　　▫ 长时间没有进行compact(CompactionChecker的判断条件2)且候选文件数小于\nhbase.hstore.compaction.max(默认10)。\n　　▫ Store中含有Reference文件，Reference文件是split region产生的临时文件，只是简单的引用文件，一般必须在compact过程中删除。\n• 如果不满足major compaction条件，就必然为minor compaction。\n\n\n\nOpenScanner\n\nBloomFilter\n\nHBase性能优化\n行键(Row Key)\n\n构建HBase二级索引\n\n\nHBase常用Shell命令\n\n# 五、MapReduce和Yarn技术原理\n导读\n\n\nMapReduce适用于数据密集型任务，还是计算密集型任务？\n数据密集型任务\n\nMapReduce 1.x主要包括哪些角色？主要功能是什么？\nClient：用户编写的MapReduce程序通过Client提交到JobTracker端\nJobTracker：负责资源控制和作业调度；负责监控所有TaskTracker与Job的健康状况，一旦出现失败，就把相应的任务转移到其他节点；JobTracker会跟踪任务的执行进度、资源使用量等信息，并把这些信息告诉任务调度器（TaskTracker），而调度器会在资源出现空闲的时候，选择合适的任务去使用这些资源。\nTaskTracker：会周期地通过“心跳”将本节点上的资源使用情况和任务运行进度汇报给JobTracker，同时接收jobTracker发送过来的命令并执行相应的操作（如启动新任务、杀死任务等）\nTaskTracker使用”slot”（槽）等量划分本节点上的资源量（CPU、内存等）。一个Task获取到一个Slot后才有机会运行。而Hadoop调度器的作用就是将各个TaskTracker上的空闲slot分配给Task使用。slot 分为MapSlot和ReduceSlot，分别提供给mapTask和reduceTask使用。\nTask：分为MapTask和ReduceTask两种，均有TaskTracker启动。\n\nYarn主要分担了MapReduce 1.x中的哪些功能？\nMRv1中资源管理和作业管理均是由JobTracker实现的，集两个功能于一身，而在MRv2中，将这两部分分开了，其中，作业管理由ApplicationMaster实现，资源管理由新增系统YARN完成\n\nYarn默认包含哪三种三种资源调度器？\nFIFO调度器\n先进先出，但不适合资源公平性\n容量调度器\n独立的专门队列保证小作业也可以提交后就启动，队列容量是专门保留的以整个集群的利用率为代价，与FIFO比，大作业执行的时间要长\n公平调度器\n不需要预留资源，调度器可以在运行的作业之间动态平衡资源，大作业启动时，因为是唯一运行的，所以获得集群的所有资源，之后小作业启动时，被分配到集群的一半的资源，这样每个作业都能公平共享资源\n\nMapReduce和Yarn基本介绍\nMapReduce概述\n\n资源调度与分配\n\nYarn概述\n\n\nYarn是轻量级弹性计算平台，除了MapReduce框架，还可以支持其他框架，比如Spark、Storm等\n• 多种框架统一管理，共享集群资源：\n　　▫ 资源利用率高\n　　▫ 运维成本低\n　　▫ 数据共享方便\n\nMapReduce和Yarn功能与架构\nMapReduce工作流程\n\n• 不同的Map任务之间不会进行通信\n• 不同的Reduce任务之间也不会发生任何信息交换\n• 用户不能显式地从一台机器向另一台机器发送消息\n• 所有的数据交换都是通过MapReduce框架自身去实现的\n\nMap阶段详解\n\n▫ 分区 (Partition)—默认采用Hash算法进行分区，MR框架根据Reduce Task个数来确定分区个数。具备相同Key值的记录最终被送到相同的Reduce Task来处理。\n▫ 排序 (Sort) —将Map输出的记录排序，例如将(‘Hi’,’1’),(‘Hello’,’1’)重新排序为(‘Hello’,’1’), (’Hi’,’1’)。\n▫ 组合 (Combine) —这个动作MR框架默认是可选的。例如将 (’Hi’,’1’), (’Hi’,’1’),(‘Hello’,’1’), (Hello’,’1’)进行合并操作为 (’Hi’,’2’), (‘Hello’,’2’)。\n▫ 合并 (Spill) —Map Task在处理后会产生很多的溢出文件(spill file)，这时需将多个溢出文件进行合并处理，生成一个经过分区和排序的Spill File (MOF:MapOutFile)。为减少写入磁盘的数据量，MR支持对MOF进行压缩后再写入。\n\nReduce阶段详解\n\n\n通常在Map Task任务完成MOF输出进度到3%时启动Reduce，从各个Map Task获取MOF文件。前面提到Reduce Task个数由客户端决定，Reduce Task个数决定MOF文件分区数。因此Map Task输出的MOF文件都能找到相对应的Reduce Task来处理。\n\nShuffle过程详解\n\n\n每个Map任务分配一个缓存；MapReduce默认100MB缓存；设置溢写比例0.8；排序是默认的操作；排序后可以合并（Combine）。\n• 在Map任务全部结束之前进行归并，归并得到一个大的文件，放在本地磁盘。\n• 文件归并时，如果溢写文件数量大于预定值（默认是3）则可以再次启动Combiner，少于3不需要。\n• JobTracker会一直监测Map任务的执行，并通知Reduce任务来领取数据。\n• Reduce任务通过RPC向JobTracker询问Map任务是否已经完成，若完成，则领取数据。\n• Reduce领取数据先放入缓存，来自不同Map机器，先归并，再合并，写入磁盘。\n• 多个溢写文件归并成一个或多个大文件，文件中的键值对是排序的。\n\n经典程序WordCount举例\n\n▫ 第一步：待处理的大文件A已经存放在HDFS上，大文件A被切分的数据块A.1、A.2、A.3分别\n存放在Data Node #1、#2、#3上。\n▫ 第二步：WordCount分析处理程序实现了用户自定义的Map函数和Reduce函数。WordCount\n将分析应用提交给RM，RM根据请求创建对应的Job，并根据文件块个数按文件块分片，创建\n3个 MapTask 和 3个Reduce Task，这些Task运行在Container中。\n▫ 第三步：Map Task 1、2、3的输出是一个经分区与排序（假设没做Combine）的MOF文件，\n记录形如表所示。\n▫ 第四步：Reduce Task从 Map Task获取MOF文件，经过合并、排序，最后根据用户自定义的\nReduce逻辑，输出如表所示的统计结果。\n\nWordCount的Map过程\n\nWordCount的Reduce过程\n\nYarn的组件架构\n\n• 在图中有两个客户端向Yarn提交任务，蓝色表示一个任务流程，棕色表示另一个任务流程。\n• 首先client提交任务，ResourceManager接收到任务，然后启动并监控起来的第一个\nContainer,也就是App Mstr。\n• App Mstr通知nodemanager管理资源并启动其他container。\n• 任务最终是运行在Container当中。\n\nMapReduce On Yarn任务调度流程\n\n• 步骤1：用户向YARN 中提交应用程序， 其中包括ApplicationMaster 程序、启动ApplicationMaster 的命令、用户程序等。\n• 步骤2：ResourceManager 为该应用程序分配第一个Container， 并与对应的NodeManager 通信，要求它在这个Container 中启动应用程序的ApplicationMaster 。\n• 步骤3：ApplicationMaster 首先向ResourceManager 注册， 这样用户可以直接通过ResourceManage 查看应用程序的运行状态，然后它将为各个任务申请资源，并监控它的运行状态，直到运行结束，即重复步骤4~7。\n• 步骤4：ApplicationMaster 采用轮询的方式通过RPC 协议向ResourceManager 申请和领取\n资源。\n• 步骤5：一旦ApplicationMaster 申请到资源后，便与对应的NodeManager 通信，要求它\n启动任务。\n• 步骤6：NodeManager 为任务设置好运行环境（包括环境变量、JAR 包、二进制程序等）后，将任务启动命令写到一个脚本中，并通过运行该脚本启动任务。\n• 步骤7：各个任务通过某个RPC 协议向ApplicationMaster 汇报自己的状态和进度，以让ApplicationMaster 随时掌握各个任务的运行状态，从而可以在任务失败时重新启动任务。在应用程序运行过程中，用户可随时通过RPC 向ApplicationMaster 查询应用程序的当前运行状态。\n• 步骤8 应用程序运行完成后，ApplicationMaster 向ResourceManager 注销并关闭自己。\n\nYarn HA方案\n\n\n• ResourceManager的高可用性方案是通过设置一组Active/Standby的ResourceManager节点来实现的。与HDFS的高可用性方案类似，任何时间点上都只能有一个ResourceManager处于Active状态。当Active状态的ResourceManager发生故障时，可通过自动或手动的方式触发故障转移，进Active/Standby状态切换。\n• 在未开启自动故障转移时，Yarn集群启动后，管理员需要在命令行中使用yarn rmadmin命令手动将其中一个ResourceManager切换为Active状态。当需要执行计划性维护或故障发生时，则需要先手动将Active状态的ResourceManager切换为Standby状态，再将另一个ResourceManager切换为Active状态。\n• 开启自动故障转移后，ResourceManager会通过内置的基于ZooKeeper实现的ActiveStandbyElector来决定哪一个ResouceManager应该成为Active节点。当Active状态的ResourceManager发生故障时，另一个ResourceManager将自动被选举为Active状态以接替故障节点。\n• 当集群的ResourceManager以HA方式部署时，客户端使用的“yarn-site.xml”需要配置所有ResourceManager地址。客户端（包括ApplicationMaster和NodeManager）会以轮询的方式寻找Active状态的ResourceManager。如果当前Active状态的ResourceManager无法连接，那么会继续使用轮询的方式找到新的ResourceManager。\n\nYarn AppMaster容错机制\n\n• 在YARN中，ApplicationMaster(AM)与其他Container类似也运行在NodeManager上（忽\n略未管理的AM）。AM可能会由于多种原因崩溃、退出或关闭。如果AM停止运行，\nResourceManager(RM)会关闭ApplicationAttempt中管理的所有Container，包括当前任\n务在NodeManager(NM)上正在运行的所有Container。RM会在另一计算节点上启动新的\nApplicationAttempt。\n• 不同类型的应用希望以多种方式处理AM重新启动的事件。MapReduce类应用目标是不丢\n失任务状态，但也能允许一部分的状态损失。但是对于长周期的服务而言，用户并不希望\n仅仅由于AM的故障而导致整个服务停止运行。\n• YARN支持在新的ApplicationAttempt启动时，保留之前Container的状态，因此运行中的\n作业可以继续无故障的运行。\n\nYarn的资源管理和任务调度\n资源管理\n\n\nYarn的三种资源调度器\n\n\n增强特性\nYarn动态内存管理\n\n• 动态内存管理可用来优化NodeManager中Containers的内存利用率。任务在运行过程中可能产生多个Container。\n• 当前，当单个节点上的Container超过Container运行内存大小时，即使节点总的配置内存利用还很低，NodeManager也会终止这些Containers。这样就会经常使用户作业失败。\n• 动态内存管理特性在当前是一个改进，只有当NodeManager中的所有Containers的总内存使用超过了已确定的阈值，NM总内存阈值的计算方法是\nyarn.nodemanager.resource.memory-mb10241024*yarn.nodemanager.dynamic.memory.usage.threshold，单位GB，那么那些内存使用过多的Containers才会被终止。\n• 举例，假如某些Containers的物理内存利用率超过了配置的内存阈值，但所有Containers的总内存利用率并没有超过设置的NodeManager内存阈值，那么那些内存使用过多的Containers仍可以继续运行。\n\nYarn基于标签调度\n\n\nClient：用户编写的MapReduce程序通过Client提交到JobTracker端\nJobTracker：负责资源控制和作业调度；负责监控所有TaskTracker与Job的健康状况，一旦出现失败，就把相应的任务转移到其他节点；JobTracker会跟踪任务的执行进度、资源使用量等信息，并把这些信息告诉任务调度器（TaskTracker），而调度器会在资源出现空闲的时候，选择合适的任务去使用这些资源。\nTaskTracker：会周期地通过“心跳”将本节点上的资源使用情况和任务运行进度汇报给JobTracker，同时接收jobTracker发送过来的命令并执行相应的操作（如启动新任务、杀死任务等）\nTaskTracker使用”slot”（槽）等量划分本节点上的资源量（CPU、内存等）。一个Task获取到一个Slot后才有机会运行。而Hadoop调度器的作用就是将各个TaskTracker上的空闲slot分配给Task使用。slot 分为MapSlot和ReduceSlot，分别提供给mapTask和reduceTask使用。\nTask：分为MapTask和ReduceTask两种，均有TaskTracker启动。\n\n# 六、Spark基于内存的分布式计算\n导读\n\n为什么说Spark是基于内存的分布式计算引擎？\nRDD宽依赖和窄依赖的主要区别是什么？\nRDD有哪些主要操作？\nSpark支持流计算吗？\nSpark概述\n简介\n\n\nSpark应用场景\n\n\nSpark的特点\n轻：Spark核心代码有3万行。\n　　Scala语言的简洁和丰富表达力。\n　　巧妙利用了Hadoop和Mesos的基础设施。\n快：Spark对小数据集可达到亚秒级的延迟。\n　　对大数据集的迭代机器学习即席查询、图计算等应用，Spark 版本比基于MapReduce、Hive和Pregel的实现快。\n　　内存计算、数据本地性和传输优化、调度优化。\n灵：Spark提供了不同层面的灵活性。\n　　Scala语言trait动态混入策略(如可更换的集群调度器、序列化库)。\n　　允许扩展新的数据算子、新的数据源、新的language bindings 。\n　　Spark支持内存计算、多迭代批量处理、即席查询、流处理和图计算等多种范式。\n巧：巧妙借力现有大数据组件。\n　　 Spark借Hadoop之势，与Hadoop无缝结合。\n　　图计算借用Pregel和PowerGraph的API以及PowerGraph的点分割思想。\n\nSpark vs MapReduce\n\n\nSpark用十分之一的资源，获得3倍与Mapreduce的性能。\nCores：集群总核数。\nRate：集群读取数据速度。\nRate/node：平均每节点读取数据速度。\nDaytona Gray：Sort Benchmark的一个通用排序竞赛项目。\n\nSpark数据结构\nSpark核心概念RDD\n\nRDD是Spark对基础数据的抽象。\nRDD的生成：从Hadoop文件系统（或与Hadoop兼容的其它存储系统）输入创建（如HDFS）；从父RDD转换得到新的RDD。\nRDD的存储：用户可以选择不同的存储级别存储RDD以便重用（11种）；RDD默认存储于内存，但当内存不足时，RDD会溢出到磁盘中。\nRDD的分区：为减少网络传输代价，和进行分布式计算，需对RDD进行分区。在需要进行分区时会根据每条记录Key进行分区，以此保证两个数据集能高效进行Join操作。\nRDD的优点：RDD是只读的，静态的。因此可提供更高的容错能力；可以实现推测式执行。\n\nRDD的依赖关系\n\nDependency（依赖）\n窄依赖是指父RDD的每个分区最多被一个子RDD的一个分区所用。\n宽依赖是指父RDD的每个分区对应一个子RDD的多个分区，是stage划分的依据。\nLineage（血统）：依赖的链条\nRDD数据集通过Lineage记住了它是如何从其他RDD中演变过来的。\n\n宽窄依赖的区别 - 算子\n\n\n宽窄依赖的区别 - 容错性\n\n\n宽窄依赖的区别 - 传输\n\n\nRDD的Stage划分\n\n\nRDD操作类型\n\n\n创建操作\n\n\n控制操作\n\n\n转换操作\n\n\n行动操作\n\n\nDataFrame概念\n\n\nDataSet概念\n\n\nRDD，DataSet，DataFrame的区别\n\nDataFrame与DataSet的区别\nDataFrame\nDataFrame每一行的类型固定为Row，只有通过解析才能获取各个字段的值，每一列的值没法直接访问。\nDataFrame编译器缺少类型安全检查。\nDataSet\n每一行是什么类型是不一定的，可以是Person，也可以是Row。\nDataSet类型安全。\n\nRDD与DataFrame/DataSet的区别\nRDD\n用于Spark1.X各模块的API\n不支持SparkSQL操作\n不支持代码自动优化\nDataFrame与DataSet\n用于Spark2.X各模块的API\n支持SparkSQL操作，还能注册临时表，进行sql语句操作\n支持一些方便的保存方式，比如保存成csv、json等格式\n基于SparkSql引擎构建，支持代码自动优化\n\nRDD与DataFrame、DataSet三者的共性\n三者都是分布式弹性数据集，支持相互转化。\n三者有许多共同的函数，如filter，排序等。\n三者都是Lazy的，在进行创建、转换时，不会立即执行。只有在遇到Action算子时，才会开始遍历运算。\n\nSpark原理与架构\nSpark体系架构\n\n\nSparkCore：类似于MR的分布式内存计算框架，最大的特点是将中间计算结果直接放在内存中，提升计算性能。自带了Standalone模式的资源管理框架，同时，也支持YARN、 MESOS的资源管理统。FI集成的是Spark On Yarn的模式。其它模式暂不支持。\nSparkSQL：Spark SQL是一个用于处理结构化数据的Spark组件，作为Apache Spark大数据框架的一部分，主要用于结构化数据处理和对数据执行类SQL查询。通过Spark SQL，可以针对不同数据格式（如：JSON，Parquet， ORC等）和数据源执行ETL操作（如：HDFS、数据库等），完成特定的查询操作。\nSparkStreaming：微批处理的流处理引擎，将流数据分片以后用SparkCore的计算引擎中进行处理。相对于Storm，实时性稍差，优势体现在吞吐量上。\nMllib和GraphX主要一些算法库。\nStructured Streaming为2.0版本之后的spark独有。\n\n典型案例 - WordCount\n\n\nSpark SQL概述\n\n\nSpark SQL对SQL语句的处理和关系型数据库采用了类似的方法，SparkSQL先会将\nSQL语句进行解析（Parse）形成一个Tree， 然后使用Rule对Tree进行绑定、 优化等\n处理过程。\n词法和语法解析(Parse)： 对读入的SQL语句进行词法和语法解析（Parse），分辨出\nSQL语句中那些词是关键词（如SELECT、FROM、WHERE），哪些是表达式，哪些\n是Projection，哪些是Data Source等，判断SQL语句是否规范，并形成逻辑计划。\n绑定(Bind)：将SQL语句和数据库的数据字典（列、 表和视图等）进行绑定(Bind)，如果\n相关的Projection和Data Source等都存在的话，则表示这个SQL语句是可以执行的。\n优化(Optimize)：Spark SQL会提供几个执行计划，返回从数据库查询的数据集。\n执行(Execute)：执行前面步骤获取的最优执行计划，返回从数据库查询的数据集\n\nSpark SQL vs Hive\n\nSpark SQL和Hive的语法除了桶表操作外，基本一样。\nSpark SQL完美兼容Hive的函数。\n\nStructured Streaming概述\n\n\nStructured Streaming的核心是将流式的数据看成一张数据不断增加的数据库表，这种流式的数据处理模型类似于数据块处理模型，可以把静态数据库表的一些查询操作应用在流式计算中，Spark执行标准的SQL查询，从无边界表中获取数据。\n无边界表：新数据不断到来，旧数据不断丢弃，实际上是一个连续不断的结构化数据流。\n\nStructured Streaming计算模型示例\n\n\n第一个lines DataFrame对象是一张数据输入的Input Table，最后的WordCounts\nDataFrame是一个结果集Result Table。在lines DataFrame数据流之上的查询产生了\nwordCounts的表示方式和在静态的Static DataFrame上的使用方式相同。然而，\nSpark会监控socket连接，获取新的持续不断产生的数据。当新的数据产生时，Spark将\n会在新数据上运行一个增量的counts查询，并且整合新的counts和之前已经计算出来的\ncounts，获取更新后的counts\n\nSpark Streaming概述\n\n\nSpark Streaming基本原理：把输入数据以秒（毫秒）为单位切分，再定时提交这些切分后的数据。\n\n窗口间隔和滑动间隔\n\n\nSpark Streaming vs Storm\n\n返回目录\n\n# 七、Flink流批一体分布式实时处理引擎\n导读\n\nFlink看待数据的观点是流，为什么又可以作批处理计算？\nFlink中三种时间是什么时间？每种时间作用是什么？\nFlink如何使用Watermark来解决数据乱序和延迟问题的？\nFlink原理及架构\nFlink简介\n\nFlink关键机制\n四个机制：状态，时间，检查点，窗口\nFlink核心理念\n\n什么是状态？例如开发一套流计算的系统或者任务做数据处理，可能经常要对数据\n进行统计，如Sum、Count、Min、Max,这些值是需要存储的。因为要不断更新，\n这些值或者变量就可以理解为一种状态。如果数据源是在读取Kafka、RocketMQ，\n可能要记录读取到什么位置，并记录Offset，这些Offset 变量都是要计算的状态。\n\n如果通过外部去访问，如Redis，HBase，它一定是通过网络及RPC。如果通过\nFlink 内部去访问，它只通过自身的进程去访问这些变量。同时Flink 会定期将这些\n状态做Checkpoint 持久化，把Checkpoint 存储到一个分布式的持久化系统中，\n比如HDFS。这样的话，当Flink 的任务出现任何故障时，它都会从最近的一次\nCheckpoint 将整个流的状态进行恢复，然后继续运行它的流处理。对用户没有任\n何数据上的影响。\n\nFlink Runtime整体架构\n\nFlink是一个分层架构的系统，主要分为三层，每一层所包含的组件都提供了特定的抽象，用来服务上层组件。部署层面上，可以单机，集群，云上部署，一般YARN集群部署比较多；核心层面上，有一个分布式的流式数据处理引擎；API层面上，有流式处理API，批处理API。流式处理支持事件处理，表操作。批处理支持机器学习，图计算，也支持表操作。\n\nFlink提供了三种部署方案local，Cluster，Cloud即：本地部署，集群部署和云部署。\nRuntime层是Flink流处理以及批处理时共用的一个引擎，以JobGraph形式接收程序。JobGraph即为一个一般化的并行数据流图（data flow），它拥有任意数量的Task来接收和产生data stream。\n\nDataStream API和DataSet API都会使用单独编译的处理方式生成JobGraph。\nDataSet API使用optimizer来决定针对程序的优化方法，而DataStream API则\n使用stream builder来完成该任务。\n\nTable API:对结构化数据进行查询操作，将结构化数据抽象成关系表，并通过SQL\n的DSL对关系表进行各种查询操作，支持Java和Scala。\n\nLibraries层对应的是Flink不同的API对应的一些功能：处理逻辑表查询的Table，\n机器学习的FlinkML，图像处理的Gelly，复杂事件处理的CEP。\n\nFlink 核心概念 -DataStream\n\n\nDataStream之间的算子操作\n含有Window的是窗口操作，与后面的窗口操作相关连，之间的关系可以通过\nreduce，fold，sum，max函数进行管关联。\nconnect：进行Stream之间的连接，可以通过flatmap，map函数进行操作。\nJoinedStream ：进行Stream之间的join操作，类似于数据库中的join，可\n以通过join函数等进行关联。\nCoGroupedStream：Stream之间的联合，类似于关系数据库中的group\n操作，可以通过coGroup函数进行关联。\nKeyedStream：主要是对数据流依据key进行处理，可以通过keyBy函数进\n行处理。\n\nFlink核心概念 - DataSet\n\n\nFlink程序\n\n\n\n一个程序的基本构成：\n获取execution environment：执行环境StreamExecutionEnvironment是所有Flink程序的基础\n创建执行环境有三种方式，分别为：\nStreamExecutionEnvironment.getExecutionEnvironment\nStreamExecutionEnvironment.createLocalEnvironment\nStreamExecutionEnvironment.createRemoteEnvironment\n\nFlink数据源\n\nFlink程序运行图\n\nFlink是一个基于Master-Slave风格的架构，Flink集群启动时，会启动一个JobManager进程、至少一个TaskManager进程。当Flink程序提交后，会创建一个Client来进行预处理，将程序转换为一个表示完整Job的DAG，并提交到JobManager，最后JobManager将Job中的各个Task分配给TaskManager。Flink 中的计算资源通过Task Slot来定义。每个task slot 代表了TaskManager的一个固定大小的资源池。例如，一个拥有3个slot的TaskManager会将其管理的内存平均分成三分分给各个slot。将资源slot 化意味着来自不同job的task不会出现内存竞争。slot目前仅支持内存的隔离，不支持CPU隔离\nFlink程序在执行的时候，会先被转化为一个Streaming Dataflows，一个Streaming Dataflow是由一组Stream和Transformation Operator组成的DAG。\n\nFlink作业运行流程\n\n\nClient：Flink Client主要给用户提供向Flink系统提交用户任务（流式作业）的能力。\nTaskManager：Flink系统的业务执行节点，执行具体的用户任务。TaskManager可以有多个，各个TaskManager都平等。\nJobManager：Flink系统的管理节点，管理所有的TaskManager，并决策用户任务在哪些Taskmanager执行。JobManager在HA模式下可以有多个，但只有一个主JobManager。\nTaskSlot（任务槽）类似yarn中的container用于资源隔离，但是该组件只包含内存资源，不包含cpu资源。每一个TaskManager当中包含3个Task Slot，TaskManager最多能同时并发执行的任务是可以控制的，那就是3个,因为不能超过slot的数量。 slot有独占的内存空间，这样在一个TaskManager中可以运行多个不同的作业，作业之间不受影响。slot之间可以共享JVM资源, 可以共享Dataset和数据结构，也可以通过多路复用（Multiplexing） 共享TCP连接和心跳消息（Heatbeat Message）。\nTask任务执行的单元。\n\n\n\n一个完整的Flink程序 - java\n\n```java\nimport org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;\nimport org.apache.flink.streaming.api.datastream.DataStream;\nimport org.apache.flink.api.common.functions.FilterFunction;\npublic class Example {\npublic static void main(String[] args) throws Exception {\nfinal StreamExecutionEnvironment env =\nStreamExecutionEnvironment.getExecutionEnvironment();\nDataStream<Person> flintstones = env.fromElements(\nnew Person(\"Fred\", 35),new Person(\"Wilma\", 35),new Person(\"Pebbles\", 2));\nDataStream<Person> adults = flintstones.filter(new FilterFunction<Person>() {\n@Override\npublic boolean filter(Person person) throws Exception {\nreturn person.age >= 18;}});\nadults.print();\nenv.execute();}\npublic static class Person {\npublic String name;public Integer age;public Person() {};\npublic Person(String name, Integer age) {this.name = name;this.age = age;};\npublic String toString() {return this.name.toString() + \": age \" + this.age.toStri();};}}\n```\n\n本示例将有关人的记录流作为输入，并对其进行过滤以仅包括成年人。env在此示例中表示执行环境，每个Flink应用程序都需要一个执行环境。流应用程序应使用StreamExecutionEnvironment。在应用程序中进行的DataStream API调用会生成一个作业图，该作业图附加到StreamExecutionEnvironment。当env.execute()被称为这个图打包，然后发送到任务管理器，这样就可以并行工作并且分片分发到任务管理器中执行。作业的每个并行切片都将在任务槽中执行。\n\nFlink的数据处理\n无状态计算：无状态计算会观察每个独立的事件，并且会在最后一个时间出结果，\n例如一些报警和监控，一直观察每个事件，当触发警报的事件来临就会触发警告。\n有状态计算：有状态的计算就会基于多个事件来输出结果，比如说计算过去一个小\n时的平均温度等等。\nApache Flink 擅长处理无界和有界数据集：精确的时间控制和状态化使得 Flink\n的运行时(runtime)能够运行任何处理无界流的应用。有界流则由一些专为固定大小\n数据集特殊设计的算法和数据结构进行内部处理，产生了出色的性能。\n\n有界流与无界流\n\n\n流与批处理机制\n\n\n\nFlink的Time与Window\n流处理中的时间分类\n\n\n\n三种时间的区别\n\n\nWindow概述\n\n\nWindow类型\n\n\nTimeWindow分类\n\n\n滚动窗口\n\n\n\n滑动窗口\n\n\n\n\n会话窗口\n\n\n代码定义\n\nFlink的Watermark\n乱序问题\n\n\nWatermark原理\n\n\n\n\n\n\n当Flink接收到每一条数据时，都会产生一条Watermark，这条Watermark就等于\n当前所有到达数据中的maxEventTime - 延迟时长，也就是说，Watermark是由\n数据携带的，一旦数据携带的Watermark比当前未触发的窗口的停止时间要晚，那\n么就会触发相应窗口的执行。由于Watermark是由数据携带的，因此，如果运行过\n程中无法获取新的数据，那么没有被触发的窗口将永远都不被触发。\n\n上图中，我们设置的允许最大延迟到达时间为2s，所以时间戳为7s的事件对应的\nWatermark是5s，时间戳为12s的事件的Watermark是10s，如果我们的窗口1是\n1s5s，窗口2是6s10s，那么时间戳为7s的事件到达时的Watermarker恰好触\n发窗口1，时间戳为12s的事件到达时的Watermark恰好触发窗口2。\n\n延迟数据处理机制\n\nSide Output机制\n\n\n\n需要注意的是，设置了 allowedLateness 之后，延迟的数据也可能触发窗口，对于 Session window 来说，可能会对窗口进行合并，产生预期外的行为。\n\nAllowed Lateness机制\n\nFlink的容错\nFlink容错机','# 一、大数据发展趋势与鲲鹏大数据\n导读',20,1,0,'2022-11-22 03:29:03','2022-11-24 11:13:27');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_classify`
--

DROP TABLE IF EXISTS `article_classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classify_id` bigint(20) NOT NULL,
  `article_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `class_article_article_id` (`article_id`),
  KEY `class_article_class_id` (`classify_id`),
  CONSTRAINT `class_article_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `class_article_class_id` FOREIGN KEY (`classify_id`) REFERENCES `classify` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分类-文章对照表：文章的分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_classify`
--

LOCK TABLES `article_classify` WRITE;
/*!40000 ALTER TABLE `article_classify` DISABLE KEYS */;
INSERT INTO `article_classify` VALUES (1,1,1),(7,2,2),(8,8,3);
/*!40000 ALTER TABLE `article_classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_comment`
--

DROP TABLE IF EXISTS `article_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(20) NOT NULL COMMENT '文章主键',
  `comment_id` bigint(20) NOT NULL COMMENT '评论主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `article_comment_comment_id_un` (`comment_id`),
  KEY `article_comment_article_id` (`article_id`),
  CONSTRAINT `article_comment_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `article_comment_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章-评论对照表：文章的评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comment`
--

LOCK TABLES `article_comment` WRITE;
/*!40000 ALTER TABLE `article_comment` DISABLE KEYS */;
INSERT INTO `article_comment` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(6,1,10),(7,1,11),(8,1,12),(9,1,13),(10,1,14),(11,1,15),(12,1,16),(13,1,17),(14,1,18),(15,1,19),(16,1,20),(17,2,21),(18,2,22),(19,1,23),(20,1,24),(21,1,25),(22,1,26),(23,1,27),(24,1,28),(25,1,29),(26,1,30),(27,1,31),(28,1,32),(29,1,33),(30,1,34),(31,1,35),(32,1,36),(33,1,37),(34,1,38),(35,1,39),(36,1,40),(37,1,41),(38,1,42),(39,1,43);
/*!40000 ALTER TABLE `article_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_tag`
--

DROP TABLE IF EXISTS `article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_tag_article_id` (`article_id`),
  KEY `article_tag_tag_id` (`tag_id`),
  CONSTRAINT `article_tag_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `article_tag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章-标签对照表：文章的标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag`
--

LOCK TABLES `article_tag` WRITE;
/*!40000 ALTER TABLE `article_tag` DISABLE KEYS */;
INSERT INTO `article_tag` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2),(5,3,3);
/*!40000 ALTER TABLE `article_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth`
--

DROP TABLE IF EXISTS `auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '权限名',
  `annotation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注释',
  `check` int(1) DEFAULT NULL COMMENT '检查程度 0-不需要 1-用户检查 2-权限检查',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_name_un` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
INSERT INTO `auth` VALUES (1,'public','公有权限',0,'2022-11-16 10:54:52','2022-11-16 11:18:35'),(2,'base','基本权限',0,'2022-11-16 10:57:16','2022-11-16 11:18:35'),(3,'browse','浏览权限',0,'2022-11-16 11:13:12','2022-11-16 11:18:35'),(4,'my','浏览我的权限',1,'2022-11-16 11:14:22','2022-11-16 11:18:35'),(5,'add','基本添加权限',1,'2022-11-16 11:15:44','2022-11-16 11:18:35'),(6,'remove','基本删除权限',1,'2022-11-16 11:18:35','2022-11-16 11:23:06');
/*!40000 ALTER TABLE `auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auth_uri`
--

DROP TABLE IF EXISTS `auth_uri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auth_uri` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `auth_id` bigint(20) NOT NULL COMMENT '权限主键',
  `uri_id` bigint(20) NOT NULL COMMENT '链接主键',
  PRIMARY KEY (`id`),
  KEY `auth_uri_auth_id` (`auth_id`),
  KEY `auth_uri_uri_id` (`uri_id`),
  CONSTRAINT `auth_uri_auth_id` FOREIGN KEY (`auth_id`) REFERENCES `auth` (`id`),
  CONSTRAINT `auth_uri_uri_id` FOREIGN KEY (`uri_id`) REFERENCES `uri` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限-链接对照表：权限详细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_uri`
--

LOCK TABLES `auth_uri` WRITE;
/*!40000 ALTER TABLE `auth_uri` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_uri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classify`
--

DROP TABLE IF EXISTS `classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '分类名',
  `annotation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注释',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_name_un` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classify`
--

LOCK TABLES `classify` WRITE;
/*!40000 ALTER TABLE `classify` DISABLE KEYS */;
INSERT INTO `classify` VALUES (1,'测试分类','这是一个测试分类','2022-10-11 09:54:04','2022-10-11 09:54:04'),(2,'测试分类1','这是一个测试分类1','2022-10-12 01:45:46','2022-10-12 01:45:46'),(3,'新的分类','这是一个新的分类','2022-11-14 10:37:25','2022-11-14 10:37:25'),(7,'新的分类2','这是一个新的分类2','2022-11-14 10:38:22','2022-11-14 10:38:22'),(8,'大数据','大数据','2022-11-22 03:11:08','2022-11-22 03:11:08');
/*!40000 ALTER TABLE `classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `collection`
--

DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '标题',
  `href` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '网址',
  `synopsis` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '空空如也' COMMENT '摘要',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收藏信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (42,'测试标题','http://localhost:8080/blog/content/1','测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要'),(43,'的萨芬','阿斯蒂芬','阿斯蒂芬');
/*!40000 ALTER TABLE `collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `body` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
  `like_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `root_id` bigint(20) DEFAULT NULL COMMENT '根评论主键',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父评论主键',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `comment_parent_id` (`parent_id`),
  KEY `comment_root_id` (`root_id`),
  CONSTRAINT `comment_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `comment_root_id` FOREIGN KEY (`root_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'测试评论',1,NULL,NULL,'2022-10-10 01:54:53','2022-11-24 07:11:43'),(2,'测试回评',1,1,1,'2022-10-10 01:55:09','2022-11-24 07:11:43'),(3,'回复你哦',0,1,2,'2022-10-10 01:55:09','2022-11-24 07:12:42'),(4,'啊对对对，你说得对',0,1,3,'2022-10-10 01:55:09','2022-11-24 07:12:42'),(10,'笨比',0,NULL,NULL,'2022-10-29 02:59:45','2022-10-29 02:59:45'),(11,'笨比',0,NULL,NULL,'2022-10-29 03:00:06','2022-10-29 03:00:06'),(12,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:23','2022-10-29 03:58:23'),(13,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:26','2022-10-29 03:58:26'),(14,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:27','2022-10-29 03:58:27'),(15,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:28','2022-10-29 03:58:28'),(16,'你好',0,NULL,NULL,'2022-10-29 04:45:32','2022-10-29 04:45:32'),(17,'你好啊',0,NULL,NULL,'2022-10-29 04:58:08','2022-10-29 04:58:08'),(18,'你好',0,NULL,NULL,'2022-10-29 05:04:09','2022-10-29 05:04:09'),(19,'。。。。。。。。。。。。。。',0,NULL,NULL,'2022-10-29 05:04:18','2022-10-29 05:04:18'),(20,'铸币',0,1,NULL,'2022-10-29 05:39:41','2022-10-29 05:39:41'),(21,'铸币',0,NULL,NULL,'2022-10-29 05:43:56','2022-10-29 05:43:56'),(22,'zhubi',0,NULL,NULL,'2022-10-29 05:44:37','2022-10-29 05:44:37'),(23,'zhubizsdasdf',0,NULL,NULL,'2022-11-05 05:22:47','2022-11-05 05:22:47'),(24,'qqq',0,NULL,NULL,'2022-11-05 05:25:20','2022-11-05 05:25:20'),(25,'qwqwed',0,NULL,NULL,'2022-11-05 05:26:51','2022-11-05 05:26:51'),(26,'qqqqqqqqqqqqqqqqqqqqq',0,NULL,NULL,'2022-11-05 05:28:43','2022-11-05 05:28:43'),(27,'你好',0,NULL,NULL,'2022-11-05 05:30:32','2022-11-05 05:30:32'),(28,'双方的DSFC我D是',0,NULL,NULL,'2022-11-05 05:31:29','2022-11-05 05:31:29'),(29,'啊手动阀手动阀手动阀',0,NULL,NULL,'2022-11-05 05:32:10','2022-11-05 05:32:10'),(30,'阿斯顿发射点',0,NULL,NULL,'2022-11-05 05:33:11','2022-11-05 05:33:11'),(31,'你妈的',0,NULL,NULL,'2022-11-05 05:34:58','2022-11-05 05:34:58'),(32,'狗叫？',0,NULL,NULL,'2022-11-05 05:35:30','2022-11-05 05:35:30'),(33,'去死去死去死',0,NULL,NULL,'2022-11-05 05:36:30','2022-11-05 05:36:30'),(34,'1111',0,NULL,NULL,'2022-11-05 05:39:46','2022-11-05 05:39:46'),(35,'1111111',0,NULL,NULL,'2022-11-05 05:40:13','2022-11-05 05:40:13'),(36,'踩踩踩',0,NULL,NULL,'2022-11-05 05:40:46','2022-11-05 05:40:46'),(37,'你好啊',0,NULL,NULL,'2022-11-05 05:41:35','2022-11-05 05:41:35'),(38,'111',0,NULL,NULL,'2022-11-05 05:44:24','2022-11-05 05:44:24'),(39,'所谓的',0,NULL,NULL,'2022-11-05 05:45:55','2022-11-05 05:45:55'),(40,'的味道',0,NULL,NULL,'2022-11-05 05:46:17','2022-11-05 05:46:17'),(41,'你好啊废物',0,NULL,NULL,'2022-11-05 06:09:59','2022-11-05 06:09:59'),(42,'啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀手动是啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀手动是啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀手动是啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀手动是啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀啊手动阀手动阀手动阀手动阀手动阀手动阀手动是asfd ',0,NULL,NULL,'2022-11-13 08:12:35','2022-11-13 08:12:35'),(43,'你好',0,41,NULL,'2022-11-17 03:13:43','2022-11-17 03:13:43');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '分组名称',
  `annotation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注释',
  `if_private` int(1) NOT NULL DEFAULT '0' COMMENT '私有  0-公开 1-私有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收藏夹表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1,'默认收藏夹','这是一个默认的收藏夹',0);
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite_collection`
--

DROP TABLE IF EXISTS `favorite_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite_collection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `favorite_id` bigint(20) NOT NULL COMMENT '收藏夹主键',
  `collection_id` bigint(20) NOT NULL COMMENT '收藏主键',
  PRIMARY KEY (`id`),
  KEY `favorite_collection_favorite_id` (`favorite_id`),
  KEY `favorite_collection_collection_id` (`collection_id`),
  CONSTRAINT `favorite_collection_collection_id` FOREIGN KEY (`collection_id`) REFERENCES `collection` (`id`),
  CONSTRAINT `favorite_collection_favorite_id` FOREIGN KEY (`favorite_id`) REFERENCES `favorite` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收藏夹_收藏对照表：收藏夹中的收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite_collection`
--

LOCK TABLES `favorite_collection` WRITE;
/*!40000 ALTER TABLE `favorite_collection` DISABLE KEYS */;
INSERT INTO `favorite_collection` VALUES (51,1,42);
/*!40000 ALTER TABLE `favorite_collection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件名',
  `suffix` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件后缀(有点)',
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件类型',
  `size` double NOT NULL COMMENT '文件大小(MB)',
  `path` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '存储路径',
  `href` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件链接',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `file_path_un` (`path`),
  UNIQUE KEY `file_href_un` (`href`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文件表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '路径',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '文件夹名',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父文件夹id',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `folder_path_un` (`path`),
  KEY `folder_parent_id` (`parent_id`),
  CONSTRAINT `folder_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文件夹表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_file`
--

DROP TABLE IF EXISTS `folder_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `folder_id` bigint(20) NOT NULL COMMENT '文件夹id',
  `file_id` bigint(20) NOT NULL COMMENT '文件id',
  PRIMARY KEY (`id`),
  KEY `folder_file_folder_id` (`folder_id`),
  KEY `folder_file_file_id` (`file_id`),
  CONSTRAINT `folder_file_file_id` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`),
  CONSTRAINT `folder_file_folder_id` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文件夹-文件对照表：文件夹下的文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_file`
--

LOCK TABLES `folder_file` WRITE;
/*!40000 ALTER TABLE `folder_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `folder_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repository`
--

DROP TABLE IF EXISTS `repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repository` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `path` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '路径',
  `size` bigint(20) NOT NULL COMMENT '容量(MB)',
  `used` double NOT NULL COMMENT '已使用(MB)',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `repository_path_un` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文件仓库表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repository`
--

LOCK TABLES `repository` WRITE;
/*!40000 ALTER TABLE `repository` DISABLE KEYS */;
INSERT INTO `repository` VALUES (1,'111@qq.com\\',20,0,'2022-11-19 11:31:51','2022-11-19 11:31:51');
/*!40000 ALTER TABLE `repository` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repository_file`
--

DROP TABLE IF EXISTS `repository_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repository_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `repository_id` bigint(20) NOT NULL COMMENT '文件仓库主键',
  `file_id` bigint(20) NOT NULL COMMENT '文件主键',
  PRIMARY KEY (`id`),
  KEY `repository_folder_folder_id` (`file_id`) USING BTREE,
  KEY `repository_folder_repository_id` (`repository_id`) USING BTREE,
  CONSTRAINT `repository_file_file_id` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`),
  CONSTRAINT `repository_file_repository_id` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文件仓库-文件对照表：文件仓库下文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repository_file`
--

LOCK TABLES `repository_file` WRITE;
/*!40000 ALTER TABLE `repository_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `repository_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repository_folder`
--

DROP TABLE IF EXISTS `repository_folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `repository_folder` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `repository_id` bigint(20) NOT NULL COMMENT '文件仓库主键',
  `folder_id` bigint(20) NOT NULL COMMENT '文件夹主键',
  PRIMARY KEY (`id`),
  KEY `repository_folder_repository_id` (`repository_id`),
  KEY `repository_folder_folder_id` (`folder_id`),
  CONSTRAINT `repository_folder_folder_id` FOREIGN KEY (`folder_id`) REFERENCES `folder` (`id`),
  CONSTRAINT `repository_folder_repository_id` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文件仓库-文件夹对照表：文件仓库下的文件夹';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repository_folder`
--

LOCK TABLES `repository_folder` WRITE;
/*!40000 ALTER TABLE `repository_folder` DISABLE KEYS */;
/*!40000 ALTER TABLE `repository_folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '角色名',
  `code` int(10) NOT NULL COMMENT '角色代码',
  `annotation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注释',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name_un` (`name`),
  UNIQUE KEY `role_code_un` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'root',1,'根管理员','2022-09-26 11:51:34','2022-09-26 11:51:34'),(2,'visitor',2,'游客','2022-11-16 10:49:56','2022-11-16 10:49:56'),(3,'ordinary',3,'普通用户','2022-11-16 10:50:20','2022-11-16 10:50:20'),(5,'admin',5,'管理员','2022-11-16 11:00:44','2022-11-16 11:00:44');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_auth`
--

DROP TABLE IF EXISTS `role_auth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_auth` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  `auth_id` bigint(20) NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_auth_role_idun` (`role_id`),
  KEY `role_auth_auth_id` (`auth_id`),
  CONSTRAINT `role_auth_auth_id` FOREIGN KEY (`auth_id`) REFERENCES `auth` (`id`),
  CONSTRAINT `role_auth_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色-权限对照表：角色拥有的权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_auth`
--

LOCK TABLES `role_auth` WRITE;
/*!40000 ALTER TABLE `role_auth` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_auth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '标签名',
  `annotation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注释',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tag_name_un` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'测试标签','这是一个测试标签','2022-10-11 09:53:46','2022-10-11 09:53:46'),(2,'测试标签1','这是一个测试标签1','2022-10-12 01:40:40','2022-10-12 01:40:40'),(3,'笔记','笔记','2022-11-22 03:11:40','2022-11-22 03:11:40');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uri`
--

DROP TABLE IF EXISTS `uri`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `uri` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `value` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '链接值',
  `method` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '请求方式',
  `father_id` bigint(20) DEFAULT NULL COMMENT '父链接主键',
  `annotation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注释',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `uri_father_id` (`father_id`),
  CONSTRAINT `uri_father_id` FOREIGN KEY (`father_id`) REFERENCES `uri` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='链接表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uri`
--

LOCK TABLES `uri` WRITE;
/*!40000 ALTER TABLE `uri` DISABLE KEYS */;
INSERT INTO `uri` VALUES (1,'/api',NULL,NULL,'主接口','2022-11-16 09:56:09','2022-11-16 09:56:09'),(2,'/api/public',NULL,1,'公共接口','2022-11-16 09:56:45','2022-11-16 09:56:45'),(3,'/api/public/key','get',2,'公钥获取接口','2022-11-16 09:57:28','2022-11-16 10:04:00'),(4,'/api/public/key/error','get',2,'公钥异常接口','2022-11-16 09:58:04','2022-11-16 10:04:00'),(5,'/api/blog',NULL,1,'博客接口','2022-11-16 09:59:06','2022-11-16 09:59:06'),(6,'/api/blog/index',NULL,5,'博客主页接口','2022-11-16 09:59:24','2022-11-16 09:59:24'),(7,'/api/blog/index/userInfo','get',6,'博客主页默认用户信息接口','2022-11-16 09:59:24','2022-11-16 09:59:24'),(8,'/api/blog/index/article','get',6,'博客主页分页文章接口','2022-11-16 09:59:24','2022-11-16 09:59:24'),(9,'/api/blog/index/tag','get',6,'博客主页标签云接口','2022-11-16 09:59:24','2022-11-16 09:59:24'),(10,'/api/blog/index/classify','get',6,'博客主页分类云接口','2022-11-16 09:59:24','2022-11-16 09:59:24'),(11,'/api/blog/index/article','delete',6,'博客主页删除文章接口','2022-11-16 10:05:51','2022-11-16 10:09:39'),(12,'api/blog/user',NULL,5,'博客用户接口','2022-11-16 10:08:07','2022-11-16 10:09:39'),(13,'api/blog/user/login','post',12,'博客用户登录接口','2022-11-16 10:09:26','2022-11-16 10:09:45'),(14,'api/blog/user/register','post',12,'博客用户注册接口','2022-11-16 10:10:35','2022-11-16 10:10:35'),(15,'api/blog/user/logout','post',12,'博客用户登出接口','2022-11-16 10:11:10','2022-11-16 10:11:10'),(16,'api/mail',NULL,1,'邮箱接口','2022-11-16 10:12:01','2022-11-16 10:12:01'),(17,'api/mail/register/code','get',16,'邮箱注册验证码接口','2022-11-16 10:12:38','2022-11-16 10:12:38'),(18,'api/blog/content',NULL,5,'博客内容页接口','2022-11-16 10:14:18','2022-11-16 10:14:18'),(19,'api/blog/content/article','get',18,'博客内容页文章接口','2022-11-16 10:14:54','2022-11-16 10:14:54'),(20,'api/blog/content/author','get',18,'博客内容页作者接口','2022-11-16 10:15:47','2022-11-16 10:15:47'),(21,'api/blog/content/classify','get',18,'博客内容页分类接口','2022-11-16 10:16:17','2022-11-16 10:16:47'),(22,'api/blog/content/tag','get',18,'博客内容页标签接口','2022-11-16 10:16:47','2022-11-16 10:16:47'),(23,'api/blog/content/comment','get',18,'博客内容页评论接口','2022-11-16 10:19:24','2022-11-16 10:19:24'),(24,'api/blog/content/nominate',NULL,18,'博客内容页推荐接口','2022-11-16 10:20:03','2022-11-16 10:20:03'),(25,'api/blog/content/nominate/classify','get',24,'博客内容页推荐同分类接口','2022-11-16 10:20:53','2022-11-16 10:20:53'),(26,'api/blog/content/nominate/tag','get',24,'博客内容页推荐同标签接口','2022-11-16 10:21:28','2022-11-16 10:21:28'),(27,'api/blog/content/comment','post',18,'博客内容页新增评论接口','2022-11-16 10:22:26','2022-11-16 10:22:26'),(28,'/api/blog/enshrine',NULL,5,'博客收藏窗口接口','2022-11-16 10:24:54','2022-11-16 10:24:54'),(29,'/api/blog/enshrine/favorite','get',28,'博客收藏窗口收藏夹接口','2022-11-16 10:25:37','2022-11-16 10:25:37'),(30,'/api/blog/enshrine/collection','post',28,'博客收藏窗口新增收藏接口','2022-11-16 10:26:12','2022-11-16 10:26:12'),(31,'/api/blog/pandect',NULL,5,'博客专栏接口','2022-11-16 10:29:17','2022-11-16 10:29:17'),(32,'/api/blog/pandect/article','get',31,'博客专栏分页文章接口','2022-11-16 10:29:46','2022-11-16 10:29:46'),(33,'/api/blog/pandect/classify','get',31,'博客专栏分类接口','2022-11-16 10:30:23','2022-11-16 10:30:23'),(34,'/api/blog/pandect/tag','get',31,'博客专栏标签接口','2022-11-16 10:30:48','2022-11-16 10:30:48'),(35,'/api/blog/pigeonhole',NULL,5,'博客归档接口','2022-11-16 10:32:03','2022-11-16 10:32:03'),(36,'/api/blog/pigeonhole/archive','get',35,'博客归档档案接口','2022-11-16 10:32:37','2022-11-16 10:32:37'),(37,'/api/blog/homepage',NULL,5,'博客个人页接口','2022-11-16 10:37:40','2022-11-16 10:37:40'),(38,'/api/blog/homepage/author','get',37,'博客个人页作者接口','2022-11-16 10:38:13','2022-11-16 10:38:13'),(39,'/api/blog/homepage/collection','delete',37,'博客个人页删除收藏接口','2022-11-16 10:38:48','2022-11-16 10:38:48'),(40,'/api/blog/homepage/favorite','get',37,'博客个人页收藏夹接口','2022-11-16 10:39:33','2022-11-16 10:39:33'),(41,'/api/blog/homepage/favorite','post',37,'博客个人页新增收藏夹接口','2022-11-16 10:40:04','2022-11-16 10:40:04'),(42,'/api/blog/homepage/favorite','delete',37,'博客个人页删除收藏夹接口','2022-11-16 10:40:40','2022-11-16 10:40:40'),(43,'/api/blog/homepage/favorite','put',37,'博客个人页更新收藏夹接口','2022-11-16 10:41:30','2022-11-16 10:41:30'),(44,'/api/blog/homepage/user','put',37,'博客个人页更新用户接口','2022-11-16 10:43:05','2022-11-16 10:43:05'),(45,'/api/blog/write',NULL,5,'博客创作页接口','2022-11-16 10:45:52','2022-11-16 10:45:52'),(46,'/api/blog/write/classify','get',45,'博客创作页分类接口','2022-11-16 10:46:23','2022-11-16 10:46:23'),(47,'/api/blog/write/classify','post',45,'博客创作页新建分类接口','2022-11-16 10:46:51','2022-11-16 10:46:51'),(48,'/api/blog/write/tag','get',45,'博客创作页标签接口','2022-11-16 10:47:24','2022-11-16 10:47:24'),(49,'/api/blog/write/tag','post',45,'博客创作页新增标签接口','2022-11-16 10:47:51','2022-11-16 10:47:51'),(50,'/api/blog/write/article','get',45,'博客创作页文章接口','2022-11-16 10:48:24','2022-11-16 10:48:24'),(51,'/api/blog/write/article','post',45,'博客创作页新增文章接口','2022-11-16 10:48:45','2022-11-16 10:48:45'),(52,'/api/blog/write/article','put',45,'博客创作页更新文章接口','2022-11-16 10:49:08','2022-11-16 10:49:08');
/*!40000 ALTER TABLE `uri` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `icon` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '空' COMMENT '头像',
  `nickname` varchar(8) COLLATE utf8_unicode_ci NOT NULL COMMENT '昵称',
  `username` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  `sex` int(1) NOT NULL DEFAULT '0' COMMENT '性别 0-未知 1-男 2-女',
  `intro` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '简介',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_username_un` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'','一二三四五六七八','1273059247@qq.com','vnpY8K8gHl/+5vBwsYFaZg==',NULL,0,'我是测试用户','2022-10-20 01:17:19','2022-10-21 13:13:42'),(2,'http://localhost:8000\\111@qq.com\\user\\icon\\icon.jpg','神和五律','111@qq.com','vnpY8K8gHl/+5vBwsYFaZg==','2017-01-04 16:00:00',1,'我叫神和五律','2022-10-20 01:19:02','2022-11-24 10:07:13');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_article`
--

DROP TABLE IF EXISTS `user_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `article_id` bigint(20) NOT NULL COMMENT '文章主键',
  PRIMARY KEY (`id`),
  KEY `user_article_user_id` (`user_id`),
  KEY `user_article_article_id` (`article_id`),
  CONSTRAINT `user_article_article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `user_article_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-文章对照表：用户的文章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_article`
--

LOCK TABLES `user_article` WRITE;
/*!40000 ALTER TABLE `user_article` DISABLE KEYS */;
INSERT INTO `user_article` VALUES (3,1,1),(4,2,2),(5,2,3);
/*!40000 ALTER TABLE `user_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_classify`
--

DROP TABLE IF EXISTS `user_classify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_classify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `classify_id` bigint(20) NOT NULL COMMENT '标签主键',
  PRIMARY KEY (`id`),
  KEY `user_tag_tag_id` (`classify_id`) USING BTREE,
  KEY `user_tag_user_id` (`user_id`) USING BTREE,
  CONSTRAINT `user_classify_classify_id` FOREIGN KEY (`classify_id`) REFERENCES `classify` (`id`),
  CONSTRAINT `user_classify_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-分类对照表：用户创建的分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_classify`
--

LOCK TABLES `user_classify` WRITE;
/*!40000 ALTER TABLE `user_classify` DISABLE KEYS */;
INSERT INTO `user_classify` VALUES (2,1,1),(3,2,2),(4,2,3),(5,2,7),(6,2,8);
/*!40000 ALTER TABLE `user_classify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_comment`
--

DROP TABLE IF EXISTS `user_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `comment_id` bigint(20) NOT NULL COMMENT '评论主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_comment_comment_id_un` (`comment_id`),
  KEY `user_comment_user_id` (`user_id`),
  CONSTRAINT `user_comment_comment_id` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`),
  CONSTRAINT `user_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-评论对照表：用户的评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comment`
--

LOCK TABLES `user_comment` WRITE;
/*!40000 ALTER TABLE `user_comment` DISABLE KEYS */;
INSERT INTO `user_comment` VALUES (3,1,1),(4,2,2),(6,1,3),(7,2,4),(9,2,10),(10,2,11),(11,2,12),(12,2,13),(13,2,14),(14,2,15),(15,2,16),(16,2,17),(17,2,18),(18,2,19),(19,2,20),(20,2,21),(21,2,22),(22,2,23),(23,2,24),(24,2,25),(25,2,26),(26,2,27),(27,2,28),(28,2,29),(29,2,30),(30,2,31),(31,2,32),(32,2,33),(33,2,34),(34,2,35),(35,2,36),(36,2,37),(37,2,38),(38,2,39),(39,2,40),(40,2,41),(41,2,42),(42,2,43);
/*!40000 ALTER TABLE `user_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_favorite`
--

DROP TABLE IF EXISTS `user_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `favorite_id` bigint(20) NOT NULL COMMENT '收藏夹主键',
  PRIMARY KEY (`id`),
  KEY `user_favorite_user_id` (`user_id`),
  KEY `user_favorite_favorite_id` (`favorite_id`),
  CONSTRAINT `user_favorite_favorite_id` FOREIGN KEY (`favorite_id`) REFERENCES `favorite` (`id`),
  CONSTRAINT `user_favorite_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-收藏夹对照表：用户的收藏夹';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_favorite`
--

LOCK TABLES `user_favorite` WRITE;
/*!40000 ALTER TABLE `user_favorite` DISABLE KEYS */;
INSERT INTO `user_favorite` VALUES (1,2,1);
/*!40000 ALTER TABLE `user_favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_notable`
--

DROP TABLE IF EXISTS `user_notable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_notable` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `my_id` bigint(20) NOT NULL COMMENT '我的主键',
  `notable_id` bigint(20) NOT NULL COMMENT '被关注的主键',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `user_notable_notable_id` (`notable_id`),
  KEY `user_notable_my_id` (`my_id`),
  CONSTRAINT `user_notable_my_id` FOREIGN KEY (`my_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_notable_notable_id` FOREIGN KEY (`notable_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-用户对照表：关注列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_notable`
--

LOCK TABLES `user_notable` WRITE;
/*!40000 ALTER TABLE `user_notable` DISABLE KEYS */;
INSERT INTO `user_notable` VALUES (5,1,2,'2022-10-24 12:59:26','2022-10-24 12:59:26'),(6,2,1,'2022-10-24 12:59:26','2022-10-24 12:59:26');
/*!40000 ALTER TABLE `user_notable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_repository`
--

DROP TABLE IF EXISTS `user_repository`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_repository` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `repository_id` bigint(20) NOT NULL COMMENT '文件仓库主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_repository_user_id_un` (`user_id`),
  UNIQUE KEY `user_repository_repository_id_un` (`repository_id`),
  CONSTRAINT `user_repository_repository_id` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`id`),
  CONSTRAINT `user_repository_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-仓库对照表：用户的文件仓库\r\n一个用户一个仓库，双唯一性约束';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_repository`
--

LOCK TABLES `user_repository` WRITE;
/*!40000 ALTER TABLE `user_repository` DISABLE KEYS */;
INSERT INTO `user_repository` VALUES (1,2,1);
/*!40000 ALTER TABLE `user_repository` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_role_user_id_un` (`user_id`),
  KEY `user_role_role_id` (`role_id`),
  CONSTRAINT `user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-角色对照表：用户对应的角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tag`
--

DROP TABLE IF EXISTS `user_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `tag_id` bigint(20) NOT NULL COMMENT '标签主键',
  PRIMARY KEY (`id`),
  KEY `user_tag_user_id` (`user_id`),
  KEY `user_tag_tag_id` (`tag_id`),
  CONSTRAINT `user_tag_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`),
  CONSTRAINT `user_tag_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-标签对照表：用户创建的标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tag`
--

LOCK TABLES `user_tag` WRITE;
/*!40000 ALTER TABLE `user_tag` DISABLE KEYS */;
INSERT INTO `user_tag` VALUES (2,1,1),(3,2,2),(4,2,3);
/*!40000 ALTER TABLE `user_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web`
--

DROP TABLE IF EXISTS `web`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `web` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `inform` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '没有通知哦' COMMENT '主页通知',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web`
--

LOCK TABLES `web` WRITE;
/*!40000 ALTER TABLE `web` DISABLE KEYS */;
/*!40000 ALTER TABLE `web` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'blog'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-26 11:02:32
