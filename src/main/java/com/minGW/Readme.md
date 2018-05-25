### ActiveMQ集群基础知识

[jovezhao/nest](https://github.com/jovezhao/nest.git)


1. 防止单点故障
2. 负载均衡

----------------------

集群方式：
1. 客户端集群：让多个消费者消费同一个队列
2. Broker clusters：多个Broker之间同步消息,实现负载均衡
3. Master Slave： 实现高可用

------------
客户端配置：
ActiveMQ失效转移(failover)

允许当其中一台消息服务器宕机时，客户端在传输层上重新连接到其他消息服务器

语法：failover:(uri1,...uriN)?transportOptions

Broker Cluster集群配置:

NetworkConnector(网络连接器)
网络连接器主要用于配置ActiveMQ服务器与服务器之间的网络通讯方式，用于服务器透传消息

网络连接器分为静态连接器和动态连接器

服务器节点A和服务器节点B 相互同步消息
节点A可以消费B的消息，B可以消费A的消息


-------
Master/Slave 集群配置

1. 共享存储(节点通过获取持久化资源的排它锁成为Master)
2. 基于复制的LevelDB Store(基于ZK(ZK也需要3台来保持自己的高可用)来选举Master)