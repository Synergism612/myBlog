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
  `synopsis` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '摘要',
  `views` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `like_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '点赞数',
  `if_private` int(1) NOT NULL DEFAULT '0' COMMENT '私有  0-公开 1-私有',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'https://s1.328888.xyz/2022/10/13/8V4Ln.jpg','测试标题','https://www.cnblogs.com/keerya/p/7987886.html\r\n\r\n## ansible 简介\r\n\r\n\r\n\r\n### ansible 是什么？\r\n\r\n　　ansible是新出现的自动化运维工具，基于Python开发，集合了众多运维工具（puppet、chef、func、fabric）的优点，实现了批量系统配置、批量程序部署、批量运行命令等功能。\r\n　　ansible是基于 paramiko 开发的,并且基于模块化工作，本身没有批量部署的能力。真正具有批量部署的是ansible所运行的模块，ansible只是提供一种框架。ansible不需要在远程主机上安装client/agents，因为它们是基于ssh来和远\r\n程主机通讯的。ansible目前已经已经被红帽官方收购，是自动化运维工具中大家认可度最高的，并且上手容易，学习简单。是每位运维工程师必须掌握的技能之一。\r\n\r\n\r\n\r\n### ansible 特点\r\n\r\n1. 部署简单，只需在主控端部署Ansible环境，被控端无需做任何操作；\r\n2. 默认使用SSH协议对设备进行管理；\r\n3. 有大量常规运维操作模块，可实现日常绝大部分操作；\r\n4. 配置简单、功能强大、扩展性强；\r\n5. 支持API及自定义模块，可通过Python轻松扩展；\r\n6. 通过Playbooks来定制强大的配置、状态管理；\r\n7. 轻量级，无需在客户端安装agent，更新时，只需在操作机上进行一次更新即可；\r\n8. 提供一个功能强大、操作性强的Web管理界面和REST API接口——AWX平台。\r\n\r\n\r\n\r\n### ansible 架构图\r\n\r\n![img](E:\\Pictures\\typora\\1204916-20171205163000628-69838828.png)\r\n　　上图中我们看到的主要模块如下：\r\n\r\n> `Ansible`：Ansible核心程序。\r\n> `HostInventory`：记录由Ansible管理的主机信息，包括端口、密码、ip等。\r\n> `Playbooks`：“剧本”YAML格式文件，多个任务定义在一个文件中，定义主机需要调用哪些模块来完成的功能。\r\n> `CoreModules`：**核心模块**，主要操作是通过调用核心模块来完成管理任务。\r\n> `CustomModules`：自定义模块，完成核心模块无法完成的功能，支持多种语言。\r\n> `ConnectionPlugins`：连接插件，Ansible和Host通信使用\r\n\r\n[回到顶部](https://www.cnblogs.com/keerya/p/7987886.html#_labelTop)\r\n\r\n## ansible 任务执行\r\n\r\n\r\n\r\n### ansible 任务执行模式\r\n\r\n　　Ansible 系统由控制主机对被管节点的操作方式可分为两类，即`adhoc`和`playbook`：\r\n\r\n- ad-hoc模式(点对点模式)\r\n  　　使用单个模块，支持批量执行单条命令。ad-hoc 命令是一种可以快速输入的命令，而且不需要保存起来的命令。**就相当于bash中的一句话shell。**\r\n- playbook模式(剧本模式)\r\n  　　是Ansible主要管理方式，也是Ansible功能强大的关键所在。**playbook通过多个task集合完成一类功能**，如Web服务的安装部署、数据库服务器的批量备份等。可以简单地把playbook理解为通过组合多条ad-hoc操作的配置文件。\r\n\r\n\r\n\r\n### ansible 执行流程\r\n\r\n![img](E:\\Pictures\\typora\\1204916-20171205162615738-1292598736.png)\r\n　　简单理解就是Ansible在运行时， 首先读取`ansible.cfg`中的配置， 根据规则获取`Inventory`中的管理主机列表， 并行的在这些主机中执行配置的任务， 最后等待执行返回的结果。\r\n\r\n\r\n\r\n### ansible 命令执行过程\r\n\r\n1. 加载自己的配置文件，默认`/etc/ansible/ansible.cfg`；\r\n2. 查找对应的主机配置文件，找到要执行的主机或者组；\r\n3. 加载自己对应的模块文件，如 command；\r\n4. 通过ansible将模块或命令生成对应的临时py文件(python脚本)， 并将该文件传输至远程服务器；\r\n5. 对应执行用户的家目录的`.ansible/tmp/XXX/XXX.PY`文件；\r\n6. 给文件 +x 执行权限；\r\n7. 执行并返回结果；\r\n8. 删除临时py文件，`sleep 0`退出；\r\n\r\n[回到顶部](https://www.cnblogs.com/keerya/p/7987886.html#_labelTop)\r\n\r\n## ansible 配置详解\r\n\r\n\r\n\r\n### ansible 安装方式\r\n\r\n　　ansible安装常用两种方式，`yum安装`和`pip程序安装`。下面我们来详细介绍一下这两种安装方式。\r\n\r\n\r\n\r\n#### 使用 pip（python的包管理模块）安装\r\n\r\n　　首先，我们需要安装一个`python-pip`包，安装完成以后，则直接使用`pip`命令来安装我们的包，具体操作过程如下：\r\n\r\n```\r\n	yum install python-pip\r\n	pip install ansible\r\n```\r\n\r\n\r\n\r\n#### 使用 yum 安装\r\n\r\n　　yum 安装是我们很熟悉的安装方式了。我们需要先安装一个`epel-release`包，然后再安装我们的 ansible 即可。\r\n\r\n```\r\n	yum install epel-release -y\r\n	yum install ansible –y\r\n```\r\n\r\n\r\n\r\n### ansible 程序结构\r\n\r\n安装目录如下(yum安装)：\r\n　　配置文件目录：/etc/ansible/\r\n　　执行文件目录：/usr/bin/\r\n　　Lib库依赖目录：/usr/lib/pythonX.X/site-packages/ansible/\r\n　　Help文档目录：/usr/share/doc/ansible-X.X.X/\r\n　　Man文档目录：/usr/share/man/man1/\r\n\r\n\r\n\r\n### ansible配置文件查找顺序\r\n\r\n　　ansible与我们其他的服务在这一点上有很大不同，这里的配置文件查找是从多个地方找的，顺序如下：\r\n\r\n1. 检查环境变量`ANSIBLE_CONFIG`指向的路径文件(export ANSIBLE_CONFIG=/etc/ansible.cfg)；\r\n2. `~/.ansible.cfg`，检查当前目录下的ansible.cfg配置文件；\r\n3. `/etc/ansible.cfg`检查etc目录的配置文件。\r\n\r\n\r\n\r\n### ansible配置文件\r\n\r\n　　ansible 的配置文件为`/etc/ansible/ansible.cfg`，ansible 有许多参数，下面我们列出一些常见的参数：\r\n\r\n```\r\n	inventory = /etc/ansible/hosts		#这个参数表示资源清单inventory文件的位置\r\n	library = /usr/share/ansible		#指向存放Ansible模块的目录，支持多个目录方式，只要用冒号（：）隔开就可以\r\n	forks = 5		#并发连接数，默认为5\r\n	sudo_user = root		#设置默认执行命令的用户\r\n	remote_port = 22		#指定连接被管节点的管理端口，默认为22端口，建议修改，能够更加安全\r\n	host_key_checking = False		#设置是否检查SSH主机的密钥，值为True/False。关闭后第一次连接不会提示配置实例\r\n	timeout = 60		#设置SSH连接的超时时间，单位为秒\r\n	log_path = /var/log/ansible.log		#指定一个存储ansible日志的文件（默认不记录日志）\r\n```\r\n\r\n\r\n\r\n### ansuble主机清单\r\n\r\n　　在配置文件中，我们提到了资源清单，这个清单就是我们的主机清单，里面保存的是一些 ansible 需要连接管理的主机列表。我们可以来看看他的定义方式：\r\n\r\n```\r\n1、 直接指明主机地址或主机名：\r\n	## green.example.com#\r\n	# blue.example.com#\r\n	# 192.168.100.1\r\n	# 192.168.100.10\r\n2、 定义一个主机组[组名]把地址或主机名加进去\r\n	[mysql_test]\r\n	192.168.253.159\r\n	192.168.253.160\r\n	192.168.253.153\r\n```\r\n\r\n　　需要注意的是，这里的组成员可以使用通配符来匹配，这样对于一些标准化的管理来说就很轻松方便了。\r\n　　我们可以根据实际情况来配置我们的主机列表，具体操作如下：\r\n\r\n```\r\n[root@server ~]# vim /etc/ansible/hosts\r\n	[web]\r\n	192.168.37.122\r\n	192.168.37.133\r\n```\r\n\r\n[回到顶部](https://www.cnblogs.com/keerya/p/7987886.html#_labelTop)\r\n\r\n## ansible 常用命令\r\n\r\n\r\n\r\n### ansible 命令集\r\n\r\n> `/usr/bin/ansible`　　Ansibe AD-Hoc 临时命令执行工具，常用于临时命令的执行\r\n> `/usr/bin/ansible-doc` 　Ansible 模块功能查看工具\r\n> `/usr/bin/ansible-galaxy`　　下载/上传优秀代码或Roles模块 的官网平台，基于网络的\r\n> `/usr/bin/ansible-playbook`　　Ansible 定制自动化的任务集编排工具\r\n> `/usr/bin/ansible-pull`　　Ansible远程执行命令的工具，拉取配置而非推送配置（使用较少，海量机器时使用，对运维的架构能力要求较高）\r\n> `/usr/bin/ansible-vault`　　Ansible 文件加密工具\r\n> `/usr/bin/ansible-console`　　Ansible基于Linux Consoble界面可与用户交互的命令执行工具\r\n\r\n　　其中，我们比较常用的是`/usr/bin/ansible`和`/usr/bin/ansible-playbook`。\r\n\r\n\r\n\r\n### ansible-doc 命令\r\n\r\n　　ansible-doc 命令常用于获取模块信息及其使用帮助，一般用法如下：\r\n\r\n```\r\n	ansible-doc -l				#获取全部模块的信息\r\n	ansible-doc -s MOD_NAME		#获取指定模块的使用帮助\r\n```\r\n\r\n　　我们也可以查看一下ansible-doc的全部用法：\r\n\r\n```\r\n[root@server ~]# ansible-doc\r\nUsage: ansible-doc [options] [module...]\r\n\r\nOptions:\r\n  -h, --help            show this help message and exit　　# 显示命令参数API文档\r\n  -l, --list            List available modules　　#列出可用的模块\r\n  -M MODULE_PATH, --module-path=MODULE_PATH　　#指定模块的路径\r\n                        specify path(s) to module library (default=None)\r\n  -s, --snippet         Show playbook snippet for specified module(s)　　#显示playbook制定模块的用法\r\n  -v, --verbose         verbose mode (-vvv for more, -vvvv to enable　　# 显示ansible-doc的版本号查看模块列表：\r\n                        connection debugging)\r\n  --version             show program\'s version number and exit\r\n```\r\n\r\n　　我们可以来看一下，以mysql相关的为例：\r\n\r\n```\r\n[root@server ~]# ansible-doc -l |grep mysql\r\nmysql_db                           Add or remove MySQL databases from a remote...\r\nmysql_replication                  Manage MySQL replication                   \r\nmysql_user                         Adds or removes a user from a MySQL databas...\r\nmysql_variables                    Manage MySQL global variables      \r\n[root@server ~]# ansible-doc -s mysql_user\r\n```\r\n\r\n![img](E:\\Pictures\\typora\\1204916-20171205163026644-674759103.png)\r\n\r\n\r\n\r\n### ansible 命令详解\r\n\r\n　　命令的具体格式如下：\r\n\r\n```\r\nansible <host-pattern> [-f forks] [-m module_name] [-a args]\r\n```\r\n\r\n　　也可以通过`ansible -h`来查看帮助，下面我们列出一些比较常用的选项，并解释其含义：\r\n\r\n> `-a MODULE_ARGS`　　　#模块的参数，如果执行默认COMMAND的模块，即是命令参数，如： “date”，“pwd”等等\r\n> `-k`，`--ask-pass` #ask for SSH password。登录密码，提示输入SSH密码而不是假设基于密钥的验证\r\n> `--ask-su-pass` #ask for su password。su切换密码\r\n> `-K`，`--ask-sudo-pass` #ask for sudo password。提示密码使用sudo，sudo表示提权操作\r\n> `--ask-vault-pass` #ask for vault password。假设我们设定了加密的密码，则用该选项进行访问\r\n> `-B SECONDS` #后台运行超时时间\r\n> `-C` #模拟运行环境并进行预运行，可以进行查错测试\r\n> `-c CONNECTION` #连接类型使用\r\n> `-f FORKS` #并行任务数，默认为5\r\n> `-i INVENTORY` #指定主机清单的路径，默认为`/etc/ansible/hosts`\r\n> `--list-hosts` #查看有哪些主机组\r\n> `-m MODULE_NAME` #执行模块的名字，默认使用 command 模块，所以如果是只执行单一命令可以不用 -m参数\r\n> `-o` #压缩输出，尝试将所有结果在一行输出，一般针对收集工具使用\r\n> `-S` #用 su 命令\r\n> `-R SU_USER` #指定 su 的用户，默认为 root 用户\r\n> `-s` #用 sudo 命令\r\n> `-U SUDO_USER` #指定 sudo 到哪个用户，默认为 root 用户\r\n> `-T TIMEOUT` #指定 ssh 默认超时时间，默认为10s，也可在配置文件中修改\r\n> `-u REMOTE_USER` #远程用户，默认为 root 用户\r\n> `-v` #查看详细信息，同时支持`-vvv`，`-vvvv`可查看更详细信息\r\n\r\n\r\n\r\n### ansible 配置公私钥\r\n\r\n　　上面我们已经提到过 ansible 是基于 ssh 协议实现的，所以其配置公私钥的方式与 ssh 协议的方式相同，具体操作步骤如下：\r\n\r\n```\r\n#1.生成私钥\r\n[root@server ~]# ssh-keygen \r\n#2.向主机分发私钥\r\n[root@server ~]# ssh-copy-id root@192.168.37.122\r\n[root@server ~]# ssh-copy-id root@192.168.37.133\r\n```\r\n\r\n　　这样的话，就可以实现无密码登录，我们的实验过程也会顺畅很多。\r\n　　注意，如果出现了一下报错：\r\n\r\n```\r\n	-bash: ssh-copy-id: command not found\r\n```\r\n\r\n　　那么就证明我们需要安装一个包：\r\n\r\n```\r\n	yum -y install openssh-clientsansible\r\n```\r\n\r\n　　把包安装上即可。\r\n\r\n\r\n\r\n## ansible 常用模块\r\n\r\n\r\n\r\n### 1）主机连通性测试\r\n\r\n　　我们使用`ansible web -m ping`命令来进行主机连通性测试，效果如下：\r\n\r\n```\r\n[root@server ~]# ansible web -m ping\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": false, \r\n    \"ping\": \"pong\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": false, \r\n    \"ping\": \"pong\"\r\n}\r\n```\r\n\r\n　　这样就说明我们的主机是连通状态的。接下来的操作才可以正常进行。\r\n\r\n\r\n\r\n### 2）command 模块\r\n\r\n　　这个模块可以直接在远程主机上执行命令，并将结果返回本主机。\r\n　　举例如下：\r\n\r\n```\r\n[root@server ~]# ansible web -m command -a \'ss -ntl\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port              \r\nLISTEN     0      128          *:111                      *:*                  \r\nLISTEN     0      5      192.168.122.1:53                       *:*                  \r\nLISTEN     0      128          *:22                       *:*                  \r\nLISTEN     0      128    127.0.0.1:631                      *:*                  \r\nLISTEN     0      128          *:23000                    *:*                  \r\nLISTEN     0      100    127.0.0.1:25                       *:*                  \r\nLISTEN     0      128         :::111                     :::*                  \r\nLISTEN     0      128         :::22                      :::*                  \r\nLISTEN     0      128        ::1:631                     :::*                  \r\nLISTEN     0      100        ::1:25                      :::*                  \r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port              \r\nLISTEN     0      128          *:111                      *:*                  \r\nLISTEN     0      128          *:22                       *:*                  \r\nLISTEN     0      128    127.0.0.1:631                      *:*                  \r\nLISTEN     0      128          *:23000                    *:*                  \r\nLISTEN     0      100    127.0.0.1:25                       *:*                  \r\nLISTEN     0      128         :::111                     :::*                  \r\nLISTEN     0      128         :::22                      :::*                  \r\nLISTEN     0      128        ::1:631                     :::*                  \r\nLISTEN     0      100        ::1:25                      :::*  \r\n```\r\n\r\n　　命令模块接受命令名称，后面是空格分隔的列表参数。给定的命令将在所有选定的节点上执行。它不会通过shell进行处理，比如$HOME和操作如\"<\"，\">\"，\"|\"，\";\"，\"&\" 工作（需要使用（shell）模块实现这些功能）。注意，该命令不支持`| 管道命令`。\r\n　　下面来看一看该模块下常用的几个命令：\r\n\r\n> chdir　　　　　　 # 在执行命令之前，先切换到该目录\r\n> executable # 切换shell来执行命令，需要使用命令的绝对路径\r\n> free_form 　 # 要执行的Linux指令，一般使用Ansible的-a参数代替。\r\n> creates 　# 一个文件名，当这个文件存在，则该命令不执行,可以\r\n> 用来做判断\r\n> removes # 一个文件名，这个文件不存在，则该命令不执行\r\n\r\n　　下面我们来看看这些命令的执行效果：\r\n\r\n```\r\n[root@server ~]# ansible web -m command -a \'chdir=/data/ ls\'	#先切换到/data/ 目录，再执行“ls”命令\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\naaa.jpg\r\nfastdfs\r\nmogdata\r\ntmp\r\nweb\r\nwKgleloeYoCAMLtZAAAWEekAtkc497.jpg\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\naaa.jpg\r\nfastdfs\r\nmogdata\r\ntmp\r\nweb\r\nwKgleloeYoCAMLtZAAAWEekAtkc497.jpg\r\n[root@server ~]# ansible web -m command -a \'creates=/data/aaa.jpg ls\'		#如果/data/aaa.jpg存在，则不执行“ls”命令\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nskipped, since /data/aaa.jpg exists\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nskipped, since /data/aaa.jpg exists\r\n[root@server ~]# ansible web -m command -a \'removes=/data/aaa.jpg cat /data/a\'		#如果/data/aaa.jpg存在，则执行“cat /data/a”命令\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nhello\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nhello\r\n```\r\n\r\n\r\n\r\n### 3）shell 模块\r\n\r\n　　shell模块可以在远程主机上调用shell解释器运行命令，支持shell的各种功能，例如管道等。\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/passwd |grep \"keer\"\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nkeer:x:10001:1000:keer:/home/keer:/bin/sh\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nkeer:x:10001:10001::/home/keer:/bin/sh\r\n```\r\n\r\n　　只要是我们的shell命令，都可以通过这个模块在远程主机上运行，这里就不一一举例了。\r\n\r\n\r\n\r\n### 4）copy 模块\r\n\r\n　　这个模块用于将文件复制到远程主机，同时支持给定内容生成文件和修改权限等。\r\n　　其相关选项如下：\r\n\r\n> `src`　　　　#被复制到远程主机的本地文件。可以是绝对路径，也可以是相对路径。如果路径是一个目录，则会递归复制，用法类似于\"rsync\"\r\n> `content`　　　#用于替换\"src\"，可以直接指定文件的值\r\n> `dest`　　　　#必选项，将源文件复制到的远程主机的**绝对路径**\r\n> `backup`　　　#当文件内容发生改变后，在覆盖之前把源文件备份，备份文件包含时间信息\r\n> `directory_mode`　　　　#递归设定目录的权限，默认为系统默认权限\r\n> `force`　　　　#当目标主机包含该文件，但内容不同时，设为\"yes\"，表示强制覆盖；设为\"no\"，表示目标主机的目标位置不存在该文件才复制。默认为\"yes\"\r\n> `others`　　　　#所有的 file 模块中的选项可以在这里使用\r\n\r\n用法举例如下：\r\n**① 复制文件：**\r\n\r\n```\r\n[root@server ~]# ansible web -m copy -a \'src=~/hello dest=/data/hello\' \r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/hello\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"mode\": \"0644\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437093.55-228281064292921/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/hello\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"mode\": \"0644\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437093.74-44694985235189/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n**② 给定内容生成文件，并制定权限**\r\n\r\n```\r\n[root@server ~]# ansible web -m copy -a \'content=\"I am keer\\n\" dest=/data/name mode=666\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"0421570938940ea784f9d8598dab87f07685b968\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"497fa8386590a5fc89090725b07f175c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 10, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437327.37-199512601767687/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"0421570938940ea784f9d8598dab87f07685b968\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"497fa8386590a5fc89090725b07f175c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 10, \r\n	    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512437327.55-218104039503110/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　我们现在可以去查看一下我们生成的文件及其权限：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data/\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\n-rw-rw-rw-   1 root root   12 Dec  6 09:45 name\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 40\r\n-rw-rw-rw- 1 root     root       12 Dec  5 09:45 name\r\n```\r\n\r\n　　可以看出我们的name文件已经生成，并且权限为666。\r\n**③ 关于覆盖**\r\n　　我们把文件的内容修改一下，然后选择覆盖备份：\r\n\r\n```\r\n[root@server ~]# ansible web -m copy -a \'content=\"I am keerya\\n\" backup=yes dest=/data/name mode=666\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"backup_file\": \"/data/name.4394.2017-12-06@09:46:25~\", \r\n    \"changed\": true, \r\n    \"checksum\": \"064a68908ab9971ee85dbc08ea038387598e3778\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"8ca7c11385856155af52e560f608891c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512438383.78-228128616784888/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"backup_file\": \"/data/name.5962.2017-12-05@09:46:24~\", \r\n    \"changed\": true, \r\n    \"checksum\": \"064a68908ab9971ee85dbc08ea038387598e3778\", \r\n    \"dest\": \"/data/name\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"md5sum\": \"8ca7c11385856155af52e560f608891c\", \r\n    \"mode\": \"0666\", \r\n    \"owner\": \"root\", \r\n    \"size\": 12, \r\n    \"src\": \"/root/.ansible/tmp/ansible-tmp-1512438384.0-170718946740009/source\", \r\n    \"state\": \"file\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　现在我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data/\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\n-rw-rw-rw-   1 root root   12 Dec  6 09:46 name\r\n-rw-rw-rw-   1 root root   10 Dec  6 09:45 name.4394.2017-12-06@09:46:25~\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 40\r\n-rw-rw-rw- 1 root     root       12 Dec  5 09:46 name\r\n-rw-rw-rw- 1 root     root       10 Dec  5 09:45 name.5962.2017-12-05@09:46:24~\r\n```\r\n\r\n　　可以看出，我们的源文件已经被备份，我们还可以查看一下`name`文件的内容：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /data/name\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nI am keerya\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nI am keerya\r\n```\r\n\r\n　　证明，这正是我们新导入的文件的内容。\r\n\r\n\r\n\r\n### 5）file 模块\r\n\r\n　　该模块主要用于设置文件的属性，比如创建文件、创建链接文件、删除文件等。\r\n　　下面是一些常见的命令：\r\n\r\n> `force`　　#需要在两种情况下强制创建软链接，一种是源文件不存在，但之后会建立的情况下；另一种是目标软链接已存在，需要先取消之前的软链，然后创建新的软链，有两个选项：yes|no\r\n> `group`　　#定义文件/目录的属组。后面可以加上`mode`：定义文件/目录的权限\r\n> `owner`　　#定义文件/目录的属主。后面必须跟上`path`：定义文件/目录的路径\r\n> `recurse`　　#递归设置文件的属性，只对目录有效，后面跟上`src`：被链接的源文件路径，只应用于`state=link`的情况\r\n> `dest`　　#被链接到的路径，只应用于`state=link`的情况\r\n> `state`　　#状态，有以下选项：\r\n>\r\n> > `directory`：如果目录不存在，就创建目录\r\n> > `file`：即使文件不存在，也不会被创建\r\n> > `link`：创建软链接\r\n> > `hard`：创建硬链接\r\n> > `touch`：如果文件不存在，则会创建一个新的文件，如果文件或目录已存在，则更新其最后修改时间\r\n> > `absent`：删除目录、文件或者取消链接文件\r\n\r\n　　用法举例如下：\r\n**① 创建目录：**\r\n\r\n```\r\n[root@server ~]# ansible web -m file -a \'path=/data/app state=directory\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0755\", \r\n    \"owner\": \"root\", \r\n    \"path\": \"/data/app\", \r\n    \"size\": 6, \r\n    \"state\": \"directory\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0755\", \r\n    \"owner\": \"root\", \r\n    \"path\": \"/data/app\", \r\n    \"size\": 4096, \r\n    \"state\": \"directory\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　我们可以查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\ndrwxr-xr-x   2 root root    6 Dec  6 10:21 app\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 44\r\ndrwxr-xr-x 2 root     root     4096 Dec  5 10:21 app\r\n```\r\n\r\n　　可以看出，我们的目录已经创建完成。\r\n**② 创建链接文件**\r\n\r\n```\r\n[root@server ~]# ansible web -m file -a \'path=/data/bbb.jpg src=aaa.jpg state=link\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"dest\": \"/data/bbb.jpg\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0777\", \r\n    \"owner\": \"root\", \r\n    \"size\": 7, \r\n    \"src\": \"aaa.jpg\", \r\n    \"state\": \"link\", \r\n    \"uid\": 0\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"dest\": \"/data/bbb.jpg\", \r\n    \"gid\": 0, \r\n    \"group\": \"root\", \r\n    \"mode\": \"0777\", \r\n    \"owner\": \"root\", \r\n    \"size\": 7, \r\n    \"src\": \"aaa.jpg\", \r\n    \"state\": \"link\", \r\n    \"uid\": 0\r\n}\r\n```\r\n\r\n　　我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls -l /data\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\ntotal 28\r\n-rw-r--r--   1 root root 5649 Dec  5 13:49 aaa.jpg\r\nlrwxrwxrwx   1 root root    7 Dec  6 10:25 bbb.jpg -> aaa.jpg\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\ntotal 44\r\n-rw-r--r-- 1 root     root     5649 Dec  4 14:44 aaa.jpg\r\nlrwxrwxrwx 1 root     root        7 Dec  5 10:25 bbb.jpg -> aaa.jpg\r\n```\r\n\r\n　　我们的链接文件已经创建成功。\r\n**③ 删除文件**\r\n\r\n```\r\n[root@server ~]# ansible web -m file -a \'path=/data/a state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"path\": \"/data/a\", \r\n    \"state\": \"absent\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"path\": \"/data/a\", \r\n    \"state\": \"absent\"\r\n}\r\n```\r\n\r\n　　我们可以查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ls /data/a\'\r\n192.168.37.122 | FAILED | rc=2 >>\r\nls: cannot access /data/a: No such file or directory\r\n\r\n192.168.37.133 | FAILED | rc=2 >>\r\nls: cannot access /data/a: No such file or directory\r\n```\r\n\r\n　　发现已经没有这个文件了。\r\n　　\r\n\r\n\r\n\r\n### 6）fetch 模块\r\n\r\n　　该模块用于从远程某主机获取（复制）文件到本地。\r\n　　有两个选项：\r\n\r\n> `dest`：用来存放文件的目录\r\n> `src`：在远程拉取的文件，并且必须是一个**file**，不能是**目录**\r\n\r\n　　具体举例如下：\r\n\r\n```\r\n[root@server ~]# ansible web -m fetch -a \'src=/data/hello dest=/data\'  \r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/192.168.37.122/data/hello\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"remote_checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"remote_md5sum\": null\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"dest\": \"/data/192.168.37.133/data/hello\", \r\n    \"md5sum\": \"6f5902ac237024bdd0c176cb93063dc4\", \r\n    \"remote_checksum\": \"22596363b3de40b06f981fb85d82312e8c0ed511\", \r\n    \"remote_md5sum\": null\r\n}\r\n```\r\n\r\n　　我们可以在本机上查看一下文件是否复制成功。要注意，文件保存的路径是我们设置的接收目录下的`被管制主机ip`目录下：\r\n\r\n```\r\n[root@server ~]# cd /data/\r\n[root@server data]# ls\r\n1  192.168.37.122  192.168.37.133  fastdfs  web\r\n[root@server data]# cd 192.168.37.122\r\n[root@server 192.168.37.122]# ls\r\ndata\r\n[root@server 192.168.37.122]# cd data/\r\n[root@server data]# ls\r\nhello\r\n[root@server data]# pwd\r\n/data/192.168.37.122/data\r\n```\r\n\r\n\r\n\r\n### 7）cron 模块\r\n\r\n　　该模块适用于管理`cron`计划任务的。\r\n　　其使用的语法跟我们的`crontab`文件中的语法一致，同时，可以指定以下选项：\r\n\r\n> `day=` #日应该运行的工作( 1-31, *, */2, )\r\n> `hour=` # 小时 ( 0-23, *, */2, )\r\n> `minute=` #分钟( 0-59, *, */2, )\r\n> `month=` # 月( 1-12, *, /2, )\r\n> `weekday=` # 周 ( 0-6 for Sunday-Saturday,, )\r\n> `job=` #指明运行的命令是什么\r\n> `name=` #定时任务描述\r\n> `reboot` # 任务在重启时运行，不建议使用，建议使用special_time\r\n> `special_time` #特殊的时间范围，参数：reboot（重启时），annually（每年），monthly（每月），weekly（每周），daily（每天），hourly（每小时）\r\n> `state` #指定状态，present表示添加定时任务，也是默认设置，absent表示删除定时任务\r\n> `user` # 以哪个用户的身份执行\r\n\r\n　　举例如下：\r\n**① 添加计划任务**\r\n\r\n```\r\n[root@server ~]# ansible web -m cron -a \'name=\"ntp update every 5 min\" minute=*/5 job=\"/sbin/ntpdate 172.17.0.1 &> /dev/null\"\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n```\r\n\r\n　　我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'crontab -l\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n```\r\n\r\n　　可以看出，我们的计划任务已经设置成功了。\r\n**② 删除计划任务**\r\n　　如果我们的计划任务添加错误，想要删除的话，则执行以下操作：\r\n　　首先我们查看一下现有的计划任务：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'crontab -l\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n#Ansible: df everyday\r\n* 15 * * * df -lh >> /tmp/disk_total &> /dev/null\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n#Ansible: df everyday\r\n* 15 * * * df -lh >> /tmp/disk_total &> /dev/null\r\n```\r\n\r\n　　然后执行删除操作：\r\n\r\n```\r\n[root@server ~]# ansible web -m cron -a \'name=\"df everyday\" hour=15 job=\"df -lh >> /tmp/disk_total &> /dev/null\" state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"envs\": [], \r\n    \"jobs\": [\r\n        \"ntp update every 5 min\"\r\n    ]\r\n}\r\n```\r\n\r\n　　删除完成后，我们再查看一下现有的计划任务确认一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'crontab -l\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n#Ansible: ntp update every 5 min\r\n*/5 * * * * /sbin/ntpdate 172.17.0.1 &> /dev/null\r\n```\r\n\r\n　　我们的删除操作已经成功。\r\n\r\n\r\n\r\n### 8）yum 模块\r\n\r\n　　顾名思义，该模块主要用于软件的安装。\r\n　　其选项如下：\r\n\r\n> `name=`　　#所安装的包的名称\r\n> `state=`　　#`present`--->安装， `latest`--->安装最新的, `absent`---> 卸载软件。\r\n> `update_cache`　　#强制更新yum的缓存\r\n> `conf_file`　　#指定远程yum安装时所依赖的配置文件（安装本地已有的包）。\r\n> `disable_pgp_check`　　#是否禁止GPG checking，只用于`present`or `latest`。\r\n> `disablerepo`　　#临时禁止使用yum库。 只用于安装或更新时。\r\n> `enablerepo`　　#临时使用的yum库。只用于安装或更新时。\r\n\r\n　　下面我们就来安装一个包试试看：\r\n\r\n```\r\n[root@server ~]# ansible web -m yum -a \'name=htop state=present\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"msg\": \"\", \r\n    \"rc\": 0, \r\n    \"results\": [\r\n        \"Loaded plugins: fastestmirror, langpacks\\nLoading mirror speeds from cached hostfile\\nResolving Dependencies\\n--> Running transaction check\\n---> Package htop.x86_64 0:2.0.2-1.el7 will be installed\\n--> Finished Dependency Resolution\\n\\nDependencies Resolved\\n\\n================================================================================\\n Package         Arch              Version                Repository       Size\\n================================================================================\\nInstalling:\\n htop            x86_64            2.0.2-1.el7            epel             98 k\\n\\nTransaction Summary\\n================================================================================\\nInstall  1 Package\\n\\nTotal download size: 98 k\\nInstalled size: 207 k\\nDownloading packages:\\nRunning transaction check\\nRunning transaction test\\nTransaction test succeeded\\nRunning transaction\\n  Installing : htop-2.0.2-1.el7.x86_64                                      1/1 \\n  Verifying  : htop-2.0.2-1.el7.x86_64                                      1/1 \\n\\nInstalled:\\n  htop.x86_64 0:2.0.2-1.el7                                                     \\n\\nComplete!\\n\"\r\n    ]\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"msg\": \"Warning: RPMDB altered outside of yum.\\n** Found 3 pre-existing rpmdb problem(s), \'yum check\' output follows:\\nipa-client-4.4.0-12.el7.centos.x86_64 has installed conflicts freeipa-client: ipa-client-4.4.0-12.el7.centos.x86_64\\nipa-client-common-4.4.0-12.el7.centos.noarch has installed conflicts freeipa-client-common: ipa-client-common-4.4.0-12.el7.centos.noarch\\nipa-common-4.4.0-12.el7.centos.noarch has installed conflicts freeipa-common: ipa-common-4.4.0-12.el7.centos.noarch\\n\", \r\n    \"rc\": 0, \r\n    \"results\": [\r\n        \"Loaded plugins: fastestmirror, langpacks\\nLoading mirror speeds from cached hostfile\\nResolving Dependencies\\n--> Running transaction check\\n---> Package htop.x86_64 0:2.0.2-1.el7 will be installed\\n--> Finished Dependency Resolution\\n\\nDependencies Resolved\\n\\n================================================================================\\n Package         Arch              Version                Repository       Size\\n================================================================================\\nInstalling:\\n htop            x86_64            2.0.2-1.el7            epel             98 k\\n\\nTransaction Summary\\n================================================================================\\nInstall  1 Package\\n\\nTotal download size: 98 k\\nInstalled size: 207 k\\nDownloading packages:\\nRunning transaction check\\nRunning transaction test\\nTransaction test succeeded\\nRunning transaction\\n  Installing : htop-2.0.2-1.el7.x86_64                                      1/1 \\n  Verifying  : htop-2.0.2-1.el7.x86_64                                      1/1 \\n\\nInstalled:\\n  htop.x86_64 0:2.0.2-1.el7                                                     \\n\\nComplete!\\n\"\r\n    ]\r\n}\r\n```\r\n\r\n　　安装成功。\r\n\r\n\r\n\r\n### 9）service 模块\r\n\r\n　　该模块用于服务程序的管理。\r\n　　其主要选项如下：\r\n\r\n> `arguments` #命令行提供额外的参数\r\n> `enabled` #设置开机启动。\r\n> `name=` #服务名称\r\n> `runlevel` #开机启动的级别，一般不用指定。\r\n> `sleep` #在重启服务的过程中，是否等待。如在服务关闭以后等待2秒再启动。(定义在剧本中。)\r\n> `state` #有四种状态，分别为：`started`--->启动服务， `stopped`--->停止服务， `restarted`--->重启服务， `reloaded`--->重载配置\r\n\r\n　　下面是一些例子：\r\n**① 开启服务并设置自启动**\r\n\r\n```\r\n[root@server ~]# ansible web -m service -a \'name=nginx state=started enabled=true\' \r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"enabled\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"started\", \r\n    ……\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"enabled\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"started\", \r\n    ……\r\n}\r\n```\r\n\r\n　　我们可以去查看一下端口是否打开：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ss -ntl\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port              \r\nLISTEN     0      128          *:80                       *:*                                  \r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nState      Recv-Q Send-Q Local Address:Port               Peer Address:Port                    \r\nLISTEN     0      128          *:80                       *:*                  \r\n```\r\n\r\n　　可以看出我们的80端口已经打开。\r\n**② 关闭服务**\r\n　　我们也可以通过该模块来关闭我们的服务：\r\n\r\n```\r\n[root@server ~]# ansible web -m service -a \'name=nginx state=stopped\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"stopped\", \r\n	……\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"nginx\", \r\n    \"state\": \"stopped\", \r\n	……\r\n}\r\n```\r\n\r\n　　一样的，我们来查看一下端口：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'ss -ntl | grep 80\'\r\n192.168.37.122 | FAILED | rc=1 >>\r\n\r\n192.168.37.133 | FAILED | rc=1 >>\r\n```\r\n\r\n　　可以看出，我们已经没有80端口了，说明我们的nginx服务已经关闭了。\r\n\r\n\r\n\r\n### 10）user 模块\r\n\r\n　　该模块主要是用来管理用户账号。\r\n　　其主要选项如下：\r\n\r\n> `comment`　　# 用户的描述信息\r\n> `createhome`　　# 是否创建家目录\r\n> `force`　　# 在使用state=absent时, 行为与userdel –force一致.\r\n> `group`　　# 指定基本组\r\n> `groups`　　# 指定附加组，如果指定为(groups=)表示删除所有组\r\n> `home`　　# 指定用户家目录\r\n> `move_home`　　# 如果设置为home=时, 试图将用户主目录移动到指定的目录\r\n> `name`　　# 指定用户名\r\n> `non_unique`　　# 该选项允许改变非唯一的用户ID值\r\n> `password`　　# 指定用户密码\r\n> `remove`　　# 在使用state=absent时, 行为是与userdel –remove一致\r\n> `shell`　　# 指定默认shell\r\n> `state`　　# 设置帐号状态，不指定为创建，指定值为absent表示删除\r\n> `system`　　# 当创建一个用户，设置这个用户是系统用户。这个设置不能更改现有用户\r\n> `uid`　　# 指定用户的uid\r\n\r\n　　举例如下：\r\n**① 添加一个用户并指定其 uid**\r\n\r\n```\r\n[root@server ~]# ansible web -m user -a \'name=keer uid=11111\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"comment\": \"\", \r\n    \"createhome\": true, \r\n    \"group\": 11111, \r\n    \"home\": \"/home/keer\", \r\n    \"name\": \"keer\", \r\n    \"shell\": \"/bin/bash\", \r\n    \"state\": \"present\", \r\n    \"stderr\": \"useradd: warning: the home directory already exists.\\nNot copying any file from skel directory into it.\\nCreating mailbox file: File exists\\n\", \r\n    \"system\": false, \r\n    \"uid\": 11111\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"comment\": \"\", \r\n    \"createhome\": true, \r\n    \"group\": 11111, \r\n    \"home\": \"/home/keer\", \r\n    \"name\": \"keer\", \r\n    \"shell\": \"/bin/bash\", \r\n    \"state\": \"present\", \r\n    \"stderr\": \"useradd: warning: the home directory already exists.\\nNot copying any file from skel directory into it.\\nCreating mailbox file: File exists\\n\", \r\n    \"system\": false, \r\n    \"uid\": 11111\r\n}\r\n```\r\n\r\n　　添加完成，我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/passwd |grep keer\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nkeer:x:11111:11111::/home/keer:/bin/bash\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nkeer:x:11111:11111::/home/keer:/bin/bash\r\n```\r\n\r\n**② 删除用户**\r\n\r\n```\r\n[root@server ~]# ansible web -m user -a \'name=keer state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"force\": false, \r\n    \"name\": \"keer\", \r\n    \"remove\": false, \r\n    \"state\": \"absent\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"force\": false, \r\n    \"name\": \"keer\", \r\n    \"remove\": false, \r\n    \"state\": \"absent\"\r\n}\r\n```\r\n\r\n　　一样的，删除之后，我们去看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/passwd |grep keer\'\r\n192.168.37.122 | FAILED | rc=1 >>\r\n\r\n192.168.37.133 | FAILED | rc=1 >>\r\n```\r\n\r\n　　发现已经没有这个用户了。\r\n\r\n\r\n\r\n### 11）group 模块\r\n\r\n　　该模块主要用于添加或删除组。\r\n　　常用的选项如下：\r\n\r\n> `gid=`　　#设置组的GID号\r\n> `name=`　　#指定组的名称\r\n> `state=`　　#指定组的状态，默认为创建，设置值为`absent`为删除\r\n> `system=`　　#设置值为`yes`，表示创建为系统组\r\n\r\n　　举例如下：\r\n**① 创建组**\r\n\r\n```\r\n[root@server ~]# ansible web -m group -a \'name=sanguo gid=12222\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 12222, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"present\", \r\n    \"system\": false\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"gid\": 12222, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"present\", \r\n    \"system\": false\r\n}\r\n```\r\n\r\n　　创建过后，我们来查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/group | grep 12222\' \r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nsanguo:x:12222:\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nsanguo:x:12222:\r\n```\r\n\r\n　　可以看出，我们的组已经创建成功了。\r\n**② 删除组**\r\n\r\n```\r\n[root@server ~]# ansible web -m group -a \'name=sanguo state=absent\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"absent\"\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"name\": \"sanguo\", \r\n    \"state\": \"absent\"\r\n}\r\n```\r\n\r\n　　照例查看一下：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /etc/group | grep 12222\' \r\n192.168.37.122 | FAILED | rc=1 >>\r\n\r\n192.168.37.133 | FAILED | rc=1 >>\r\n```\r\n\r\n　　已经没有这个组的相关信息了。\r\n\r\n\r\n\r\n### 12）script 模块\r\n\r\n　　该模块用于将本机的脚本在被管理端的机器上运行。\r\n　　该模块直接指定脚本的路径即可，我们通过例子来看一看到底如何使用的：\r\n　　首先，我们写一个脚本，并给其加上执行权限：\r\n\r\n```\r\n[root@server ~]# vim /tmp/df.sh\r\n	#!/bin/bash\r\n\r\n	date >> /tmp/disk_total.log\r\n	df -lh >> /tmp/disk_total.log \r\n[root@server ~]# chmod +x /tmp/df.sh \r\n```\r\n\r\n　　然后，我们直接运行命令来实现在被管理端执行该脚本：\r\n\r\n```\r\n[root@server ~]# ansible web -m script -a \'/tmp/df.sh\'\r\n192.168.37.122 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"rc\": 0, \r\n    \"stderr\": \"Shared connection to 192.168.37.122 closed.\\r\\n\", \r\n    \"stdout\": \"\", \r\n    \"stdout_lines\": []\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"changed\": true, \r\n    \"rc\": 0, \r\n    \"stderr\": \"Shared connection to 192.168.37.133 closed.\\r\\n\", \r\n    \"stdout\": \"\", \r\n    \"stdout_lines\": []\r\n}\r\n```\r\n\r\n　　照例查看一下文件内容：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'cat /tmp/disk_total.log\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\nTue Dec  5 15:58:21 CST 2017\r\nFilesystem      Size  Used Avail Use% Mounted on\r\n/dev/sda2        47G  4.4G   43G  10% /\r\ndevtmpfs        978M     0  978M   0% /dev\r\ntmpfs           993M   84K  993M   1% /dev/shm\r\ntmpfs           993M  9.1M  984M   1% /run\r\ntmpfs           993M     0  993M   0% /sys/fs/cgroup\r\n/dev/sda3        47G   33M   47G   1% /app\r\n/dev/sda1       950M  153M  798M  17% /boot\r\ntmpfs           199M   16K  199M   1% /run/user/42\r\ntmpfs           199M     0  199M   0% /run/user/0\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\nTue Dec  5 15:58:21 CST 2017\r\nFilesystem      Size  Used Avail Use% Mounted on\r\n/dev/sda2        46G  4.1G   40G  10% /\r\ndevtmpfs        898M     0  898M   0% /dev\r\ntmpfs           912M   84K  912M   1% /dev/shm\r\ntmpfs           912M  9.0M  903M   1% /run\r\ntmpfs           912M     0  912M   0% /sys/fs/cgroup\r\n/dev/sda3       3.7G   15M  3.4G   1% /app\r\n/dev/sda1       1.9G  141M  1.6G   9% /boot\r\ntmpfs           183M   16K  183M   1% /run/user/42\r\ntmpfs           183M     0  183M   0% /run/user/0\r\n```\r\n\r\n　　可以看出已经执行成功了。\r\n\r\n\r\n\r\n### 13）setup 模块\r\n\r\n　　该模块主要用于收集信息，是通过调用facts组件来实现的。\r\n　　facts组件是Ansible用于采集被管机器设备信息的一个功能，我们可以使用setup模块查机器的所有facts信息，可以使用filter来查看指定信息。整个facts信息被包装在一个JSON格式的数据结构中，ansible_facts是最上层的值。\r\n　　facts就是变量，内建变量 。每个主机的各种信息，cpu颗数、内存大小等。会存在facts中的某个变量中。调用后返回很多对应主机的信息，在后面的操作中可以根据不同的信息来做不同的操作。如redhat系列用yum安装，而debian系列用apt来安装软件。\r\n**① 查看信息**\r\n　　我们可以直接用命令获取到变量的值，具体我们来看看例子：\r\n\r\n```\r\n[root@server ~]# ansible web -m setup -a \'filter=\"*mem*\"\'	#查看内存\r\n192.168.37.122 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1116, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1397, \r\n                \"used\": 587\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1116, \r\n                \"total\": 1984, \r\n                \"used\": 868\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1984\r\n    }, \r\n    \"changed\": false\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1203, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1470, \r\n                \"used\": 353\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1203, \r\n                \"total\": 1823, \r\n                \"used\": 620\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1823\r\n    }, \r\n    \"changed\": false\r\n}\r\n```\r\n\r\n　　我们可以通过命令查看一下内存的大小以确认一下是否一致：\r\n\r\n```\r\n[root@server ~]# ansible web -m shell -a \'free -m\'\r\n192.168.37.122 | SUCCESS | rc=0 >>\r\n              total        used        free      shared  buff/cache   available\r\nMem:           1984         404        1122           9         457        1346\r\nSwap:          3813           0        3813\r\n\r\n192.168.37.133 | SUCCESS | rc=0 >>\r\n              total        used        free      shared  buff/cache   available\r\nMem:           1823         292        1207           9         323        1351\r\nSwap:          3813           0        3813\r\n```\r\n\r\n　　可以看出信息是一致的。\r\n**② 保存信息**\r\n　　我们的setup模块还有一个很好用的功能就是可以保存我们所筛选的信息至我们的主机上，同时，文件名为我们被管制的主机的IP，这样方便我们知道是哪台机器出的问题。\r\n　　我们可以看一看例子：\r\n\r\n```\r\n[root@server tmp]# ansible web -m setup -a \'filter=\"*mem*\"\' --tree /tmp/facts\r\n192.168.37.122 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1115, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1396, \r\n                \"used\": 588\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1115, \r\n                \"total\": 1984, \r\n                \"used\": 869\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1984\r\n    }, \r\n    \"changed\": false\r\n}\r\n192.168.37.133 | SUCCESS => {\r\n    \"ansible_facts\": {\r\n        \"ansible_memfree_mb\": 1199, \r\n        \"ansible_memory_mb\": {\r\n            \"nocache\": {\r\n                \"free\": 1467, \r\n                \"used\": 356\r\n            }, \r\n            \"real\": {\r\n                \"free\": 1199, \r\n                \"total\": 1823, \r\n                \"used\": 624\r\n            }, \r\n            \"swap\": {\r\n                \"cached\": 0, \r\n                \"free\": 3813, \r\n                \"total\": 3813, \r\n                \"used\": 0\r\n            }\r\n        }, \r\n        \"ansible_memtotal_mb\": 1823\r\n    }, \r\n    \"changed\": false\r\n}\r\n```\r\n\r\n　　然后我们可以去查看一下：\r\n\r\n```\r\n[root@server ~]# cd /tmp/facts/\r\n[root@server facts]# ls\r\n192.168.37.122  192.168.37.133\r\n[root@server facts]# cat 192.168.37.122 \r\n{\"ansible_facts\": {\"ansible_memfree_mb\": 1115, \"ansible_memory_mb\": {\"nocache\": {\"free\": 1396, \"used\": 588}, \"real\": {\"free\": 1115, \"total\": 1984, \"used\": 869}, \"swap\": {\"cached\": 0, \"free\": 3813, \"total\": 3813, \"used\": 0}}, \"ansible_memtotal_mb\": 1984}, \"changed\": false}\r\n```\r\n\r\n### 14）replace模块\r\n\r\nreplace模块的作用为修改文本信息\r\n\r\n```ruby\r\n[root@ansible ansible]# ansible db -m replace -a \'path=/new.txt regexp=\"^i am.*\" replace=\"we are fine\"\'\r\ndb 为修改的主机\r\n-m 指定的模块\r\nreplace 替换模块\r\npath  修改文件的位置\r\nregexp 用正则去匹配要修改的行\r\nreplace 要修改为什么内容\r\n```\r\n\r\n### 15）\r\n\r\n\r\n','测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要',10,100,0,'2022-10-09 10:59:44','2022-10-26 11:03:05'),(2,'空','测试标题1','https://blog.csdn.net/hawava/article/details/119277507\r\n\r\n# Ansible剧本编写\r\n\r\n\r\n\r\n# ansible剧本组成部分\r\n\r\n![剧本组成](E:\\Pictures\\typora\\6fb8ff0a3d2a42bcbb35da84981063bf.png)\r\n\r\n# ansible剧本编写规范\r\n\r\n剧本编写规范：pyyaml\r\n\r\n1. 合理的信息缩进：yaml使用固定的缩进风格表示数据层结构关系，编写ansible-playbook文件一定不能使用tab键进行缩进。\r\n2. 冒号的使用方法：使用冒号时后面一定要有空格信息，以冒号结尾或冒号信息出现在注释说明中，其后不需要加空格。\r\n3. 短横线：表示列表，使用一个短横线加一个空格。多个项使用同样的缩进级别作为同一个列表的一部分。\r\n\r\n# ansible剧本主机规划\r\n\r\n| 外网IP    | 内网IP      | 主机名 | 功能       | 系统版本   |\r\n| --------- | ----------- | ------ | ---------- | ---------- |\r\n| 10.0.0.61 | 172.16.1.61 | m01    | 管理主机   | CentOS 7.x |\r\n| 10.0.0.41 | 172.16.1.41 | backup | 被管理主机 | CentOS 7.x |\r\n| 10.0.0.31 | 172.16.1.31 | nfs01  | 被管理主机 | CentOS 7.x |\r\n| 10.0.0.7  | 172.16.1.7  | web01  | 被管理主机 | CentOS 7.x |\r\n\r\n# ansible剧本主机清单\r\n\r\n主机清单配置文件： /etc/ansible/hosts\r\n\r\n1. 分组配置\r\n\r\n   [web] — ansible web -a … 统一操作web组的主机\r\n   172.16.1.7\r\n   172.16.1.8\r\n   172.16.1.9\r\n\r\n   [data] — ansible data -a … 统一操作data组的主机\r\n   172.16.1.31\r\n   172.16.1.41\r\n\r\n2. 主机名符号匹配配置\r\n\r\n   [web]\r\n\r\n   172.16.1.[7:9] 通过IP地址匹配配置\r\n\r\n   web[01:03] 通过主机名匹配配置（注意：通过主机名匹配需要在/etc/hosts文件中有主机名和IP的映射）\r\n\r\n3. 加上非标准远程端口（如ssh端口变为52113）\r\n\r\n   [web]\r\n   web01:52113\r\n   172.16.1.7:52113\r\n\r\n4. 主机使用特殊的变量\r\n\r\n   [web]\r\n   172.16.1.7 ansible_ssh_port=52113 ansible_ssh_user=root ansible_ssh_pass=123456\r\n\r\n   [web]\r\n   web01 ansible_ssh_host=172.16.1.7 ansible_ssh_port=52113 ansible_ssh_user=root ansible_ssh_pass=123456\r\n\r\n5. 主机组名嵌入配置\r\n\r\n   [rsync:children] — 嵌入子组信息\r\n   rsync_server\r\n   rsync_client\r\n   [rsync_server] — 子组\r\n   172.16.1.41\r\n   [rsync_client] — 子组\r\n   172.16.1.31\r\n   172.16.1.7\r\n\r\n   [web:vars] — 嵌入式变量信息\r\n   ansible_ssh_host=172.16.1.7 — 变量\r\n   ansible_ssh_port=52113 — 变量\r\n   ansible_ssh_user=root — 变量\r\n   ansible_ssh_pass=123456 — 变量\r\n   [web] — 该组调用以上变量\r\n   web01\r\n\r\n[主机清单官方配置方法](https://docs.ansible.com/ansible/latest/user_guide/intro_inventory.html)\r\n\r\n# ansible剧本编写实践\r\n\r\n## ad-hoc部署rsync服务\r\n\r\n服务端部署\r\n\r\n1. 确认软件安装\r\n   ansible 172.16.1.41 -m yum -a “name=rsync state=installed”\r\n2. 编写文件\r\n   ansible 172.16.1.41 -m copy -a “src=/etc/ansible/server_file/rsync_server/rsyncd.conf dest=/etc/”\r\n3. 创建用户\r\n   ansible 172.16.1.41 -m user -a “name=rsync create_home=no shell=/sbin/nologin”\r\n4. 创建目录\r\n   ansible 172.16.1.41 -m file -a “dest=/backup state=directory owner=rsync group=rsync”\r\n5. 创建密码文件\r\n   ansible 172.16.1.41 -m copy -a “content=‘rsync_backup:redhat’ dest=/etc/rsync.password mode=600”\r\n6. 启动服务\r\n   ansible 172.16.1.41 -m service -a “name=rsyncd state=started enabled=yes”\r\n\r\n客户端部署\r\n\r\n1. 确认软件安装\r\n   ansible 172.16.1.41,172.16.1.7 -m yum -a “name=rsync state=installed”\r\n2. 创建密码文件\r\n   ansible 172.16.1.31,172.16.1.7 -m copy -a “content=‘redhat’ dest=/etc/rsync.password mode=600”\r\n3. 测试\r\n   ansible 172.16.1.31,172.16.1.7 -m file -a “dest=/tmp/test.txt state=touch”\r\n   ansible 172.16.1.31,172.16.1.7 -m shell -a \"rsync -avz /tmp/test.txt rsync_backup@172.16.1.41::backup --password-file=/etc/rsync.password\r\n\r\n## playbook部署rsync服务\r\n\r\n创建playbook目录\r\n\r\n```bash\r\n[root@m01 ~]# mkdir /etc/ansible/ansible-playbook\r\n1\r\n```\r\n\r\n进入playbook目录\r\n\r\n```bash\r\n[root@m01 ~]# cd /etc/ansible/ansible-playbook/\r\n1\r\n```\r\n\r\n创建编辑rsync剧本（剧本文件扩展名尽量写为yaml，方便识别文件是一个剧本文件，且文件编写时会有颜色提示）\r\n\r\n```bash\r\n[root@m01 ansible-playbook]# vim rsync_server.yaml\r\n\r\n- hosts: 172.16.1.41\r\n  tasks:\r\n    - name: 01-install rsync\r\n      yum: name=rsync state=installed\r\n    - name: 02-push rsyncd.conf\r\n      copy: src=../server_file/rsync_server/rsyncd.conf dest=/etc/\r\n    - name: 03-create user\r\n      user: name=rsync create_home=no shell=/sbin/nologin\r\n    - name: 04-create backup directory\r\n      file: dest=/backup state=directory owner=rsync group=rsync\r\n    - name: 05-create password file\r\n      copy: content=rsync_backup:redhat dest=/etc/rsync.password mode=600\r\n    - name: 06-start rsync service\r\n      service: name=rsyncd state=started enabled=yes    # 配置文件改变，不重启服务不会生效？\r\n\r\n- hosts: 172.16.1.31,172.16.1.7\r\n  tasks:\r\n    - name: 01-install rsync\r\n      yum: name=rsync state=installed\r\n    - name: 02-create password file\r\n      copy: content=redhat dest=/etc/rsync.password mode=600\r\n    - name: 03-create test file  \r\n      file: dest=/tmp/test.txt state=touch \r\n    - name: 04-test      shell: rsync -avz /tmp/test.txt rsync_backup@172.16.1.41::backup --password-file=/etc/rsync.password\r\n1234567891011121314151617181920212223242526\r\n```\r\n\r\n如何执行剧本？\r\n\r\n1. 检查剧本的语法格式\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# ansible-playbook --syntax-check rsync_server.yaml \r\n   1\r\n   ```\r\n\r\n2. 模拟执行剧本（彩排）\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# ansible-playbook -C rsync_server.yaml \r\n   1\r\n   ```\r\n\r\n3. 正式执行剧本（实干）\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# ansible-playbook rsync_server.yaml \r\n   1\r\n   ```\r\n\r\n# ansible剧本常见错误\r\n\r\n- 剧本语法规范错误（空格、冒号、短横线）；\r\n- 剧本模块使用是否正确；\r\n- 剧本中一个name标识下只能写一个模块任务；\r\n- 剧本中尽量不要大量使用shell模块。\r\n\r\n剧本执行出现错误排查思路/步骤：\r\n1）找到剧本中出现问题关键点；\r\n2）将剧本中的操作转换成单条ad-hoc命令操作；\r\n3）将模块的功能操作转换成linux命令；\r\n4）本地管理主机上执行命令测试；\r\n5）远程被管理主机上执行命令测试。\r\n\r\n# ansible剧本扩展功能\r\n\r\n## 变量\r\n\r\n变量名由字母、数字、下划线组成，变量名需要以字母开头，ansible内置关键字不能作为变量名。\r\n\r\n1. 在剧本文件中编写\r\n\r\n   在剧本中定义变量，借助vars关键字\r\n\r\n   > vars:\r\n   > backupdir: /backup\r\n   > passfile: rsync.password\r\n\r\n   使用{{ 变量名 }}可以引用对应的变量\r\n\r\n2. 在命令行中指定(临时设置)\r\n\r\n   > ansible-playbook -e backupdir=/backup -e passfile=rsync.password rsync_server.yaml\r\n\r\n3. 在主机清单中编写\r\n\r\n   > vim /etc/ansible/hosts\r\n   > [rsync_server:vars]\r\n   > backupdir: /backup\r\n   > passfile: rsync.password\r\n\r\n三种方式优先级：命令行变量设置>剧本变量设置>主机清单变量设置\r\n\r\n## 注册\r\n\r\n注册功能可以在执行剧本时，输出命令结果。\r\n\r\n```bash\r\n- hosts: rsync_server\r\n  tasks:\r\n    - name: check server port\r\n      shell: netstat -lntup \r\n      register: get_server_port\r\n    \r\n    - name: display port info\r\n      debug: msg={{ get_server_port.stdout_lines }}\r\n12345678\r\n```\r\n\r\n## 判断\r\n\r\n指定判断条件\r\n\r\n> (ansible_hostname == “nfs01”)\r\n> (ansible_hostname == “web01”)\r\n\r\n例如\r\n\r\n```bash\r\n- hosts: rsync_server\r\n  remote_user: root\r\n  tasks:\r\n    - name: Check File\r\n      file: path=/tmp/this_is_{{ ansible_hostname }}_file state=touch\r\n      when: (ansible_hostname == \"nfs\") or (ansible_hostname == \"backup\")	\r\n123456\r\n```\r\n\r\nsetup模块显示被管理主机系统的详细信息\r\n\r\n> ansible rsync_server -m setup\r\n\r\nsetup模块获取被管理主机的内置变量信息\r\n\r\n> ansible rsync_server -m setup -a “filter=xxx”\r\n\r\n常见主机信息\r\n\r\n| 参数                               | 作用                               |\r\n| ---------------------------------- | ---------------------------------- |\r\n| ansible_all_ipv4_addresses         | 仅显示ipv4的信息                   |\r\n| ansible_devices                    | 仅显示磁盘设备信息                 |\r\n| ansible_distribution               | 显示是什么系统，例：centos,suse等  |\r\n| ansible_distribution_major_version | 显示是系统主版本                   |\r\n| ansible_distribution_version       | 仅显示系统版本                     |\r\n| ansible_machine                    | 显示系统类型，例：32位，还是64位   |\r\n| ansible_eth0                       | 仅显示eth0的信息                   |\r\n| ansible_hostname                   | 仅显示主机名                       |\r\n| ansible_kernel                     | 仅显示内核版本                     |\r\n| ansible_lvm                        | 显示lvm相关信息                    |\r\n| ansible_memtotal_mb                | 显示系统总内存                     |\r\n| ansible_memfree_mb                 | 显示可用系统内存                   |\r\n| ansible_memory_mb                  | 详细显示内存情况                   |\r\n| ansible_swaptotal_mb               | 显示总的swap内存                   |\r\n| ansible_swapfree_mb                | 显示swap内存的可用内存             |\r\n| ansible_mounts                     | 显示系统磁盘挂载情况               |\r\n| ansible_processor                  | 显示cpu个数(具体显示每个cpu的型号) |\r\n| ansible_processor_vcpus            | 显示cpu个数(只显示总的个数)        |\r\n\r\n## 循环\r\n\r\n一个name下只能执行一个ad-hoc命令，如果想要执行多条，可以使用循环。\r\n\r\n```bash\r\n- hosts: all\r\n  remote_user: root\r\n  tasks:\r\n    - name: Add Users\r\n      user: name={{ item.name }} groups={{ item.groups }} state=present\r\n      with_items: \r\n        - { name: \'testuser1\', groups: \'bin\' }\r\n        - { name: \'testuser2\', groups: \'root\' }\r\n12345678\r\n```\r\n\r\n## 标签\r\n\r\n指定执行标签任务： ansible-playbook --tags=t2 test.yml\r\n跳过指定标签任务： ansible-playbook --skip-tags=t2 test.yml\r\n\r\n```bash\r\n- hosts: all\r\n  ignore_errors: yes\r\n  remote_user: root\r\n  tasks:\r\n    - name: Check File\r\n      file: path=/tmp/this_is_{{ ansible_hostname }}_file state=touch\r\n      when: (ansible_hostname == \"nfs01\") or (ansible_hostname == \"backup\")\r\n      tags: t1\r\n    \r\n    - name: install httpd\r\n      yum: name=httpd state=installed\r\n      when: (ansible_all_ipv4_addresses == [\"172.16.1.7\",\"10.0.0.7\"])\r\n      tags: t2\r\n    \r\n    - name: install httpd2\r\n      yum: name=httpd2 state=installed\r\n      when: (ansible_distribution == \"ubuntu\")\r\n      tags: t3\r\n123456789101112131415161718\r\n```\r\n\r\n## 触发\r\n\r\n```bash\r\n- hosts: backup\r\n  remote_user: root\r\n  tasks:\r\n    - name: 01 Install rsync\r\n      yum: name=rsync state=present\r\n        \r\n    - name: 02 push config file\r\n      copy: src=./file/{{ item.src }} dest=/etc/{{ item.dest }} mode={{ item.mode }} \r\n      with_items:\r\n        - { src: \"rsyncd.conf\", dest: \"rsyncd.conf\", mode: \"0644\" }\r\n        - { src: \"rsync.password\", dest: \"rsync.password\", mode: \"0600\" }\r\n      notify: restart rsync server\r\n\r\n  handlers:    # 当notify发出时，handlers起作用\r\n    - name: restart rsync server\r\n      service: name=rsyncd state=restarted   \r\n12345678910111213141516\r\n```\r\n\r\n## 忽略错误\r\n\r\n默认playbook会检查命令和模块的返回状态，如遇到错误就中断playbook的执行，可以加入ignore_errors: yes忽略错误。\r\n\r\n```bash\r\n- hosts: all\r\n  remote_user: root\r\n  tasks:\r\n    - name: Ignore False\r\n      command: /bin/false\r\n      ignore_errors: yes\r\n    - name: touch new file\r\n      file: path=/tmp/oldboy_ignore state=touch	\r\n12345678\r\n```\r\n\r\n## 整合剧本\r\n\r\n1. include_tasks: playbook.yaml\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# cat main.yml\r\n   - hosts: all(host与include_tasks剧本中的host冲突)\r\n     tasks:\r\n       - include_tasks: rsync-server.yml\r\n       - include_tasks: nfs-server.yml\r\n   12345\r\n   ```\r\n\r\n2. include: playbook.yml(设置gather_facts: no可提高执行速度)\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# cat main.yml\r\n   - include：rsync-server.yml	\r\n   - include：nfs-server.yml\r\n   123\r\n   ```\r\n\r\n3. \\- import_playbook（主要使用该方法进行汇总）\r\n\r\n   ```bash\r\n   [root@m01 ansible-playbook]# vim main.yml \r\n   - import_playbook: rsync.yml    \r\n   - import_playbook: nfs.yml      \r\n   123\r\n   ```\r\n\r\n# ansible剧本角色信息\r\n\r\n待解决问题：\r\n\r\n1. 目录结构不够规范？\r\n2. 编写好的任务如何重复调用？\r\n3. 服务端配置文件改动,客户端参数信息如何自动变化？\r\n4. 汇总剧本中如何显示主机角色信息？\r\n5. 一个剧本内容信息过多,不容易进行阅读,如何进行拆分？\r\n\r\n## 规范目录结构\r\n\r\n创建相应角色目录\r\n\r\n```bash\r\n[root@m01 ~]# cd /etc/ansible/roles/\r\n[root@m01 roles]# mkdir {rsync,nfs-server,nfs-client}\r\n12\r\n```\r\n\r\n创建角色子目录\r\n\r\n```bash\r\n[root@m01 roles]# mkdir {rsync,nfs-server,nfs-client}/{vars,tasks,templates,handlers,files}\r\n1\r\n```\r\n\r\n查看目录结构\r\n\r\n```bash\r\n[root@m01 roles]# tree /etc/ansible/roles/\r\n/etc/ansible/roles/\r\n|-- nfs-server\r\n|   |-- files    -- 保存需要分发的文件\r\n|   |-- handlers    -- 保存触发器配置文件\r\n|   |-- tasks    -- 保存要执行的动作信息文件\r\n|   |-- templates    -- 保存需要分发的模板文件，模板文件中可以设置变量（调取var目录中的变量值）\r\n|   `-- vars    -- 保存变量信息文件\r\n......\r\n123456789\r\n```\r\n\r\n## roles目录下创建文件\r\n\r\n以部署NFS服务端为例：\r\n\r\n1. 编写tasks目录中main.yml文件\r\n\r\n   ```bash\r\n   [root@m01 tasks]# vim main.yml\r\n   - name: 01-copy nfs conf file    \r\n     copy: src=exports dest=/etc/ -- 自动去往file目录寻找exports文件\r\n     - name: 02-create data dir    \r\n     file: path={{ Data_dir }} state=directory owner=nfsnobody group=nfsnobody\r\n     notify: restart nfs server \r\n   - name: 03-start server\r\n     service: name={{ item }} state=started enabled=yes\r\n     with_items:    \r\n       - rpcbind\r\n       - nfs\r\n   12345678910\r\n   ```\r\n\r\n2. 编写vars目录中main.yml文件\r\n\r\n   ```bash\r\n   [root@m01 tasks]# cd ../vars\r\n   [root@m01 vars]# vim main.yml\r\n   Data_dir: /data\r\n   123\r\n   ```\r\n\r\n3. 编写files目录中需要分发的文件\r\n\r\n   ```bash\r\n   [root@m01 vars]# cd ../files/\r\n   [root@m01 files]# echo \'/data172.16.1.0/24(rw,sync)\' > exports\r\n   12\r\n   ```\r\n\r\n4. 编写handlers目录中main.yml文件\r\n\r\n   ```bash\r\n   [root@m01 tasks]# cd ../handlers/\r\n   [root@m01 handlers]# vim main.yml\r\n   [root@m01 handlers]# cat main.yml \r\n   - name: restart nfs server\r\n     service: name=nfs state=restarted\r\n   12345\r\n   ```\r\n\r\n5. 编写好的目录结构\r\n\r\n   ```bash\r\n   [root@m01 nfs-server]# tree\r\n   .\r\n   |-- files\r\n   |   `-- exports\r\n   |-- handlers\r\n   |   `-- main.yml\r\n   |-- tasks\r\n   |   `-- main.yml\r\n   |-- templates\r\n   `-- vars\r\n       `-- main.yml\r\n   \r\n   5 directories, 4 files\r\n   12345678910111213\r\n   ```\r\n\r\n## 编写主剧本文件\r\n\r\n```bash\r\n- hosts: nfs_server\r\n  roles:\r\n    - nfs-server\r\n\r\n- hosts: nfs_client\r\n  roles:\r\n    - nfs-client\r\n1234567\r\n```\r\n\r\n\r\n','',20,200,0,'2022-10-09 10:59:44','2022-10-23 03:08:46');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分类-文章对照表：文章的分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_classify`
--

LOCK TABLES `article_classify` WRITE;
/*!40000 ALTER TABLE `article_classify` DISABLE KEYS */;
INSERT INTO `article_classify` VALUES (1,1,1),(7,2,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章-评论对照表：文章的评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comment`
--

LOCK TABLES `article_comment` WRITE;
/*!40000 ALTER TABLE `article_comment` DISABLE KEYS */;
INSERT INTO `article_comment` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(6,1,10),(7,1,11),(8,1,12),(9,1,13),(10,1,14),(11,1,15),(12,1,16),(13,1,17),(14,1,18),(15,1,19),(16,1,20),(17,2,21),(18,2,22),(19,1,23),(20,1,24),(21,1,25),(22,1,26),(23,1,27),(24,1,28),(25,1,29),(26,1,30),(27,1,31),(28,1,32),(29,1,33),(30,1,34),(31,1,35),(32,1,36),(33,1,37),(34,1,38),(35,1,39),(36,1,40),(37,1,41);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='文章-标签对照表：文章的标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag`
--

LOCK TABLES `article_tag` WRITE;
/*!40000 ALTER TABLE `article_tag` DISABLE KEYS */;
INSERT INTO `article_tag` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,2);
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
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `auth_name_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth`
--

LOCK TABLES `auth` WRITE;
/*!40000 ALTER TABLE `auth` DISABLE KEYS */;
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
  UNIQUE KEY `auth_uri_auth_id_un` (`auth_id`),
  KEY `auth_uri_uri_id` (`uri_id`),
  CONSTRAINT `auth_uri_auth_id` FOREIGN KEY (`auth_id`) REFERENCES `auth` (`id`),
  CONSTRAINT `auth_uri_uri_id` FOREIGN KEY (`uri_id`) REFERENCES `uri` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='权限-链接对照表：有权进入的链接';
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classify`
--

LOCK TABLES `classify` WRITE;
/*!40000 ALTER TABLE `classify` DISABLE KEYS */;
INSERT INTO `classify` VALUES (1,'测试分类','这是一个测试分类','2022-10-11 09:54:04','2022-10-11 09:54:04'),(2,'测试分类1','这是一个测试分类1','2022-10-12 01:45:46','2022-10-12 01:45:46');
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='收藏信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `collection`
--

LOCK TABLES `collection` WRITE;
/*!40000 ALTER TABLE `collection` DISABLE KEYS */;
INSERT INTO `collection` VALUES (41,'测试标题','http://localhost:8080/blog/content/1','测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要'),(42,'测试标题','http://localhost:8080/blog/content/1','测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要测试摘要');
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
  `body` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '内容',
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
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,'测试评论',10,NULL,NULL,'2022-10-10 01:54:53','2022-10-10 01:54:53'),(2,'测试回评',20,1,1,'2022-10-10 01:55:09','2022-10-23 11:31:19'),(3,'回复你哦',30,1,2,'2022-10-10 01:55:09','2022-10-23 11:31:19'),(4,'啊对对对，你说得对',40,1,3,'2022-10-10 01:55:09','2022-10-23 11:31:19'),(10,'笨比',0,NULL,NULL,'2022-10-29 02:59:45','2022-10-29 02:59:45'),(11,'笨比',0,NULL,NULL,'2022-10-29 03:00:06','2022-10-29 03:00:06'),(12,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:23','2022-10-29 03:58:23'),(13,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:26','2022-10-29 03:58:26'),(14,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:27','2022-10-29 03:58:27'),(15,'笨比笨比',0,NULL,NULL,'2022-10-29 03:58:28','2022-10-29 03:58:28'),(16,'你好',0,NULL,NULL,'2022-10-29 04:45:32','2022-10-29 04:45:32'),(17,'你好啊',0,NULL,NULL,'2022-10-29 04:58:08','2022-10-29 04:58:08'),(18,'你好',0,NULL,NULL,'2022-10-29 05:04:09','2022-10-29 05:04:09'),(19,'。。。。。。。。。。。。。。',0,NULL,NULL,'2022-10-29 05:04:18','2022-10-29 05:04:18'),(20,'铸币',0,1,NULL,'2022-10-29 05:39:41','2022-10-29 05:39:41'),(21,'铸币',0,NULL,NULL,'2022-10-29 05:43:56','2022-10-29 05:43:56'),(22,'zhubi',0,NULL,NULL,'2022-10-29 05:44:37','2022-10-29 05:44:37'),(23,'zhubizsdasdf',0,NULL,NULL,'2022-11-05 05:22:47','2022-11-05 05:22:47'),(24,'qqq',0,NULL,NULL,'2022-11-05 05:25:20','2022-11-05 05:25:20'),(25,'qwqwed',0,NULL,NULL,'2022-11-05 05:26:51','2022-11-05 05:26:51'),(26,'qqqqqqqqqqqqqqqqqqqqq',0,NULL,NULL,'2022-11-05 05:28:43','2022-11-05 05:28:43'),(27,'你好',0,NULL,NULL,'2022-11-05 05:30:32','2022-11-05 05:30:32'),(28,'双方的DSFC我D是',0,NULL,NULL,'2022-11-05 05:31:29','2022-11-05 05:31:29'),(29,'啊手动阀手动阀手动阀',0,NULL,NULL,'2022-11-05 05:32:10','2022-11-05 05:32:10'),(30,'阿斯顿发射点',0,NULL,NULL,'2022-11-05 05:33:11','2022-11-05 05:33:11'),(31,'你妈的',0,NULL,NULL,'2022-11-05 05:34:58','2022-11-05 05:34:58'),(32,'狗叫？',0,NULL,NULL,'2022-11-05 05:35:30','2022-11-05 05:35:30'),(33,'去死去死去死',0,NULL,NULL,'2022-11-05 05:36:30','2022-11-05 05:36:30'),(34,'1111',0,NULL,NULL,'2022-11-05 05:39:46','2022-11-05 05:39:46'),(35,'1111111',0,NULL,NULL,'2022-11-05 05:40:13','2022-11-05 05:40:13'),(36,'踩踩踩',0,NULL,NULL,'2022-11-05 05:40:46','2022-11-05 05:40:46'),(37,'你好啊',0,NULL,NULL,'2022-11-05 05:41:35','2022-11-05 05:41:35'),(38,'111',0,NULL,NULL,'2022-11-05 05:44:24','2022-11-05 05:44:24'),(39,'所谓的',0,NULL,NULL,'2022-11-05 05:45:55','2022-11-05 05:45:55'),(40,'的味道',0,NULL,NULL,'2022-11-05 05:46:17','2022-11-05 05:46:17'),(41,'你好啊废物',0,NULL,NULL,'2022-11-05 06:09:59','2022-11-05 06:09:59');
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
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户组名',
  `father_id` bigint(20) DEFAULT NULL COMMENT '父用户组主键',
  `annotation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '注释',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_name_un` (`name`),
  KEY `group_father_id` (`father_id`),
  CONSTRAINT `group_father_id` FOREIGN KEY (`father_id`) REFERENCES `group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_role`
--

DROP TABLE IF EXISTS `group_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `group_id` bigint(20) NOT NULL COMMENT '用户组主键',
  `role_id` bigint(20) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `group_role_group_id_un` (`group_id`),
  KEY `group_role_role_id` (`role_id`),
  CONSTRAINT `group_role_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `group_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户组-角色对照表：用户组对应的角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_role`
--

LOCK TABLES `group_role` WRITE;
/*!40000 ALTER TABLE `group_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `group_role` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'root',1,'根管理员','2022-09-26 11:51:34','2022-09-26 11:51:34');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='标签表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'测试标签','这是一个测试标签','2022-10-11 09:53:46','2022-10-11 09:53:46'),(2,'测试标签1','这是一个测试标签1','2022-10-12 01:40:40','2022-10-12 01:40:40');
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
  UNIQUE KEY `uri_value_un` (`value`),
  KEY `uri_father_id` (`father_id`),
  CONSTRAINT `uri_father_id` FOREIGN KEY (`father_id`) REFERENCES `uri` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='链接表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uri`
--

LOCK TABLES `uri` WRITE;
/*!40000 ALTER TABLE `uri` DISABLE KEYS */;
INSERT INTO `uri` VALUES (1,'/api','',NULL,'api接口','2022-09-25 00:02:59','2022-09-25 00:04:02'),(2,'/api/public',NULL,1,'共有接口','2022-09-25 00:04:02','2022-09-25 00:06:07'),(3,'/api/public/key','get',2,'公钥接口','2022-09-25 00:04:56','2022-09-25 00:34:45'),(4,'/api/public/key/error','get',3,'公钥错误接口','2022-09-25 00:07:05','2022-09-25 00:34:48'),(5,'/api/mail',NULL,1,'邮箱接口','2022-09-25 00:08:15','2022-09-25 00:09:06'),(6,'/api/mail/code','get',5,'邮箱验证码接口','2022-09-25 00:09:06','2022-09-25 00:34:52');
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
INSERT INTO `user` VALUES (1,'','一二三四五六七八','1273059247@qq.com','vnpY8K8gHl/+5vBwsYFaZg==',NULL,0,'我是测试用户','2022-10-20 01:17:19','2022-10-21 13:13:42'),(2,'空','神和五律','111@qq.com','vnpY8K8gHl/+5vBwsYFaZg==','2017-01-11 16:00:00',1,'我叫神和五律','2022-10-20 01:19:02','2022-11-05 10:59:20');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-文章对照表：用户的文章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_article`
--

LOCK TABLES `user_article` WRITE;
/*!40000 ALTER TABLE `user_article` DISABLE KEYS */;
INSERT INTO `user_article` VALUES (3,1,1),(4,2,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-分类对照表：用户创建的分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_classify`
--

LOCK TABLES `user_classify` WRITE;
/*!40000 ALTER TABLE `user_classify` DISABLE KEYS */;
INSERT INTO `user_classify` VALUES (2,1,1),(3,2,2);
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-评论对照表：用户的评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_comment`
--

LOCK TABLES `user_comment` WRITE;
/*!40000 ALTER TABLE `user_comment` DISABLE KEYS */;
INSERT INTO `user_comment` VALUES (3,1,1),(4,2,2),(6,1,3),(7,2,4),(9,2,10),(10,2,11),(11,2,12),(12,2,13),(13,2,14),(14,2,15),(15,2,16),(16,2,17),(17,2,18),(18,2,19),(19,2,20),(20,2,21),(21,2,22),(22,2,23),(23,2,24),(24,2,25),(25,2,26),(26,2,27),(27,2,28),(28,2,29),(29,2,30),(30,2,31),(31,2,32),(32,2,33),(33,2,34),(34,2,35),(35,2,36),(36,2,37),(37,2,38),(38,2,39),(39,2,40),(40,2,41);
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
-- Table structure for table `user_friends`
--

DROP TABLE IF EXISTS `user_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_friends` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `my_id` bigint(20) NOT NULL COMMENT '我的主键',
  `friend_id` bigint(20) NOT NULL COMMENT '好友的主键',
  `notes` varchar(100) COLLATE utf8_unicode_ci NOT NULL COMMENT '备注',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0-未通过 1-通过',
  `creation_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_friends_my_id_un` (`my_id`),
  KEY `user_friends_friend_id` (`friend_id`),
  CONSTRAINT `user_friends_friend_id` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_friends_my_id` FOREIGN KEY (`my_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-用户对照表：好友列表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_friends`
--

LOCK TABLES `user_friends` WRITE;
/*!40000 ALTER TABLE `user_friends` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键',
  `group_id` bigint(20) NOT NULL COMMENT '用户组主键',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_group_user_idun` (`user_id`),
  KEY `user_group_group_id` (`group_id`),
  CONSTRAINT `user_group_group_id` FOREIGN KEY (`group_id`) REFERENCES `group` (`id`),
  CONSTRAINT `user_group_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-用户组对照表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='用户-标签对照表：用户创建的标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tag`
--

LOCK TABLES `user_tag` WRITE;
/*!40000 ALTER TABLE `user_tag` DISABLE KEYS */;
INSERT INTO `user_tag` VALUES (2,1,1),(3,2,2);
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

-- Dump completed on 2022-11-09 10:13:13
