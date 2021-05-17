[TOC]

<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->

<!-- code_chunk_output -->

    - [Aws Regions and AZs](#aws-regions-and-azs)
    - [IAM(Identity and Access Management)](#iamidentity-and-access-management)
      - [IAM Introduction](#iam-introduction)
      - [IAM Federation](#iam-federation)
    - [IAM 101 Brain Dump](#iam-101-brain-dump)
    - [EC2](#ec2)
      - [What is EC2](#what-is-ec2)
      - [Introduction to Security Groups](#introduction-to-security-groups)
      - [Security Groups Deeper Dive](#security-groups-deeper-dive)
      - [Security Groups Good to know](#security-groups-good-to-know)
      - [Elastic Ip](#elastic-ip)
      - [EC2 User Data](#ec2-user-data)
      - [EC2 Instance Lauch Types](#ec2-instance-lauch-types)
      - [EC2 On Demand](#ec2-on-demand)
      - [EC2 Reserved Instances](#ec2-reserved-instances)
      - [EC2 Spot Instances](#ec2-spot-instances)
      - [EC2 Dedicated Hosts](#ec2-dedicated-hosts)
      - [EC2 Dedicated Instances](#ec2-dedicated-instances)
      - [Which host is right for me?](#which-host-is-right-for-me)
- [lambda](#lambda)
  - [serverless introduction](#serverless-introduction)
    - [what is serverless](#what-is-serverless)
    - [Serverless in AWS](#serverless-in-aws)
  - [AWS Lambda overview](#aws-lambda-overview)
    - [Why AWS Lambda](#why-aws-lambda)
    - [Benefits of AWS Lambda](#benefits-of-aws-lambda)
)
<!-- /code_chunk_output -->

#### Aws Regions and AZs

Each region has Many availability zones (usually 3, min si 2, max is 6)

Each AZ is one or more discrete data centers with redundant power,networking,and connectivity
They're separate from each other, so that they're ioslated from disasters.

They're connected with high bandwidth, ultra-low latency networking


#### IAM(Identity and Access Management)

- Your whole AWS security is there:
  - Users
  - Groups
  - Roles

- Root account should never be used(and shared)
- Users must be created with proper permissions
- IAM is at the center of AWS
- Policies are written in JSON

##### IAM Introduction 

Users is for a person

Roles is for a machine


- IAM has a global view 
- Persmissions are governed by Policies(JSON)
- MFA （Multi Factor Authentication） can be setup
- IAM has predefined "Managed policies"

![Screen Shot 2021-03-10 at 9.32.15 PM](Ultimate%20AWS%20Certified%20Developer%20Associate%202021%20-%20NEW!.assets/Screen%20Shot%202021-03-10%20at%209.32.15%20PM.png)

##### IAM Federation

- Big enterprises usually integrate their own repository of users with IAM
- This way, one can login int oAws using their company credential.
- Identity Federation uses the SAML standard.

#### IAM 101 Brain Dump

- One IAM USer per PHYSICAL PERSON
- One IAM Role per Application
- IAM credentials should NEVER BE SHARED
- Never, ever,ever,ever write IAM credential in code.
- Never use the ROOT account except for initial setup.
- Never use ROOT IAM Credentails

#### EC2

##### What is EC2

- EX2 is one of most popular of aws offering
- It mainly consists in the capability of :
  - Renting virtual machines(EC2)
  - Storing data on virtual drives(EBS)
  - Distributing load across machines(ELB)
  - Scaling the Services using an auto-scaling group(ASG)

##### Introduction to Security Groups

- Security Groups are the fundamental of network security in AWS
- They control how traffic is allowed into or out of our EC2 Machines
- It is the most fundamental skill to learn to troubleshoot networking issues.


##### Security Groups Deeper Dive
- Security groups are acting as a firewall on EC2 instances
- The regulate:
  - Access  to Ports
  - Authorised IP ranges - IPv4 and IPv6
  - Control of inbound network(from other to the instance)
  - Control of outbound network(from the instance to other)


##### Security Groups Good to know

- Can be attached to multiple instances
- Locked down to a region / VPC combination
- Does live “outside” the EC2 – if traffic is blocked the EC2 instance won’t see
it
- It’s good to maintain one separate security group for SSH access
- If your application is not accessible (time out), then it’s a security group issue
- If your application gives a “connection refused“ error, then it’s an application
error or it’s not launched
- All inbound traffic is blocked by default
- All outbound traffic is authorised by default

##### Elastic Ip

- When you stop and then start an EC2 instance, it can change its public
IP.
- If you need to have a fixed public IP for your instance, you need an
Elastic IP
- An Elastic IP is a public IPv4 IP you own as long as you don’t delete it
- You can attach it to one instance at a time 

- With an Elastic IP address, you can mask the failure of an instance or software by rapidly remapping the address to another instance in your account.
- You can only have 5 Elastic IP in your account (you can ask AWS to increase that).
- Overall, try to avoid using Elastic IP:
  - They often reflect poor architectural decisions
  - Instead, use a random public IP and register a DNS name to it
  - Or, as we’ll see later, use a Load Balancer and don’t use a public IP

##### EC2 User Data

-  It is possible to bootstrap our instances using an EC2 User data script.
- bootstrapping means launching commands when a machine starts
- That script is only run once at the instance first start
- EC2 user data is used to automate boot tasks such as:
  - Installing updates
  - Installing software
  - Downloading common files from the internet
  - Anything you can think of
- The EC2 User Data Script runs with the root user


##### EC2 Instance Lauch Types

- On Demand Instances: short workload, predictable pricing
- Reserved: (MINIMUM 1 year)
  - Reserved Instances: long workloads
  - Convertible Reserved Instances: long workloads with flexible instances
  - Scheduled Reserved Instances: example – every Thursday between 3 and 6 pm
- Spot Instances: short workloads, for cheap, can lose instances (less reliable)
- Dedicated Instances: no other customers will share your hardware
- Dedicated Hosts: book an entire physical server, control instance placement

##### EC2 On Demand

Pay for what you use (billing per second, after the first minute)

- Has the highest cost but no upfront payment
- No long term commitment
- Recommended for short-term and un-interrupted workloads, where you can't predict how the application will behave

##### EC2 Reserved Instances

- Up to 75% discount compared to On-demand
- Pay upfront for what you use with long term commitment
- Reservation period can be 1 or 3 years
- Reserve a specific instance type
- Recommended for steady state usage applications (think database)
- Convertible Reserved Instance
  - can change the EC2 instance type
  - Up to 54% discount
- Scheduled Reserved Instances
  - launch within time window you reserve
  - When you require a fraction of day / week / month


##### EC2 Spot Instances

- Can get a discount of up to 90% compared to On-demand
- Instances that you can “lose” at any point of time if your max price is less than the
current spot price
- The MOST cost-efficient instances in AWS
- Useful for workloads that are resilient to failure
  - Batch jobs
  - Data analysis
  - Image processing
  - …
- Not great for critical jobs or databases
- Great combo: Reserved Instances for baseline + On-Demand & Spot for peaks


##### EC2 Dedicated Hosts

- Physical dedicated EC2 server for your use
- Full control of EC2 Instance placement
- Visibility into the underlying sockets / physical cores of the hardware
- Allocated for your account for a 3 year period reservation
- More expensive
- Useful for software that have complicated licensing model (BYOL –
Bring Your Own License)
- Or for companies that have strong regulatory or compliance needs


##### EC2 Dedicated Instances

- Instances running on hardware that’s dedicated to you
- May share hardware with other instances in same account
- No control over instance placement (can move hardware after Stop / Start)

##### Which host is right for me?

- On demand: coming and staying in resort whenever we like, we pay the full price
- Reserved: like planning ahead and if we plan to stay for a long time, we may get a good discount.
- Spot instances: the hotel allows people to bid for the empty rooms and the highest bidder keeps the rooms. You can get kicked out at any time
- Dedicated Hosts: We book an entire buildin- of the resort

## lambda

### serverless introduction

#### what is serverless

- Serverless is a new paradigm in which the developers don’t have to
manage servers anymore…
- They just deploy code
- They just deploy… functions !
- Initially... Serverless == FaaS (Function as a Service)
- Serverless was pioneered by AWS Lambda but now also includes anything that’s managed: “databases, messaging, storage, etc.”
- Serverless does not mean there are no servers…= 
  it means you just don’t manage / provision / see them

#### Serverless in AWS

- AWS Lambda
- DynamoDB
- AWS Cognito
- AWS API Gateway
- Amazon S3
- AWS SNS & SQS
- AWS Kinesis Data Firehose
- Aurora Serverless
- Step Functions
- Fargate

### AWS Lambda overview

#### Why AWS Lambda

Amazon EC2

- Virtual Servers in the Cloud
- Limited by RAM and CPU
- Continuously running
- Scaling means intervention to add / remove servers

Amazon Lambda

- Limited by time - short executions
- Virtual functions – no servers to manage!
- Run on-demand
- Scaling is automated!

#### Benefits of AWS Lambda

Easy Pricing:

- Pay per request and compute time
- Free tier of 1,000,000 AWS Lambda requests and 400,000 GBs of compute time
- Integrated with the whole AWS suite of services
- Integrated with many programming languages
- Easy monitoring through AWS CloudWatch
- Easy to get more resources per functions (up to 3GB of RAM!)
- Increasing RAM will also improve CPU and network!

