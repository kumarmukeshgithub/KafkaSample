Kafka Configuration in Mac M3 Pro

Steps for Installation:

1. Open the terminal in Mac 
2. Paste the command given below then press enter-
   <br>brew install kafka
3. To install ZooKeeper use the command -
    <br>brew install zookeeper


 Steps to Work with kafka:
 1. First start the zookeeper server -
    <br>brew services start zookeeper
 2. Then start the kafka server -
    <br>brew services start kafka
 3. To create a topic use command like -
    <br>kafka-topics --create --topic test-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
 4. Then start the Producer -
    <br>kafka-console-producer --topic test-topic --bootstrap-server localhost:9092
 5. To start the consumer -
    <br>kafka-console-consumer --topic test-topic --bootstrap-server localhost:9092 --from-beginning
 6. Stop the Kafka server -
    <br>brew services stop kafka
 7. Stop the Zookeeper server-
    <br>brew services stop zookeeper



