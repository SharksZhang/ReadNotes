#### ip命令学习笔记
#### ip link
```
ip link show 
ip link show [$dev_name]
ip link set eth0 up
ip link set eth0 down 
ip link set eth0 promisc on 
ip link set eth0 promisc off
ip link set eth0 exqueuelen 1200

ip link add veth-a type veth peer name veth-b



```

##### ip addr

```
ip addr show 
ip addr add 192.168.0.1/24 dev eth0
ip addr add 192.168.0.1/24 dev eth0
```