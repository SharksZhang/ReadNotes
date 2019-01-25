#!/bin/bash

function set_up()
{
	ovs-vsctl add-br br0
	ip link add veth-vm1-br type veth peer name veth-vm1-ns
	ip link add veth-vm2-br type veth peer name veth-vm2-ns
	
	ip netns add vm1
	ip netns add vm2
	
	ovs-vsctl add-port  br0 veth-vm1-br
	ip link set veth-vm1-ns netns vm1
	ovs-vsctl add-port br0 veth-vm2-br
	ip link set veth-vm2-ns netns vm2

	ip link set veth-vm1-br up
	ip link set veth-vm2-br up
	
	ip netns exec vm1 ip addr add 192.168.1.2/24 dev veth-vm1-ns
	ip netns exec vm1 ip link set veth-vm1-ns up
	
	ip netns exec vm2 ip addr add 192.168.1.3/24 dev veth-vm2-ns
	ip netns exec vm2 ip link set veth-vm2-ns up
	
	echo "Try to ping vm1 ..."
	ip netns exec vm2 ping -c 5 192.168.1.2

}

function tear_down()
{
	ip netns del vm1
	ip netns del vm2
	ovs-vsctl del-br br0
}

if [ "$1" == "set_up" ];then
    set_up
elif [ "$1" == "tear_down" ];then
    tear_down
else
    echo "Nothing to do ..."
fi
