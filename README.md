# Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup
A Big Data project that using modern big data tools and stacks,I have used Hadoop cluster for data storage, and connect to big data environment including flume, kafka, hbase, hive,hue, spark, etc... to implement a real-time world cup data showing.  
<p></p>
This is a very classic big data project structure. Following is my experience and product show, hope you enjoy!  
<p></p>
<img align="left" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/0.png" width="auto" height="auto"/>
<p></p>

✨Tech Environment：  
- Cluster: Hadoop, Zookeeper, Flume
- Real-time: Kafka, Spark Streaming, Mysql, Spring MVC
- Off-line: Hbase, Hive, Hue, Spark Sql
- Tools: VMware, SecureCRT

🍻Product Show

**<h2>Data Set</h2>**  
This data set contains 12 columns attributes for each soccer athlete from different countries(Data Source: Kaggle):  

uid, name,age, height_cm, weight_kg, nationality, overall, team_position, pace, shooting, defending, physic  

We can use those data to do analysis by different dimensions. Let's Map-Reduce! 

<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/1.png" width="auto" height="auto"/>

<p></p>

<h2>Java Map reduce Implement</h2> 
First Step, we use the original way to implement the map-reduce in java file, and then upload them on hadoop to excute, for this step we just want to test the basic map-reduce function on hadoop works good, and verify the data set is clear.  
<p></p>
This round is **Count the players of each country:**
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/2.png" width="100%" height="auto"/>

Run jar file on hadoop, result looks good, format: *Country - Player Numbers*
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/3.png" width="auto" height="auto"/>

Second round is **Find the best overall player of each country:** For this round we used **secondary sorting** which means we will order data set before reducing to improve the speed of analysis. To attain this goal we need customize more java files.

<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/4.png" width="auto" height="auto"/>

Following the result, format: *Country - player - power*.

<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/5.png" width="auto" height="auto"/>

Besides the test above,I have 3 more map-reduce tests, details please see my project folder.

**<h2>Hadoop environment architect</h2>** 
Totally used 3 virtual nodes (hp1,hp2,hp3) for the distribute system environment.  
<p></p>
This project has two topics: **the offline data analyse, the live streaming analyse**.
<img align="left" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/0.png" width="auto" height="auto"/>
<p></p>
Flume: to collect data from log files and transfer data to hbase for storing.
<p></p>
Hbase: store the data.
<p></p>
Hive: data warehouse, do the data analyse by writing sql.
<p></p>
Mysql : the metadata database for hive.
<p></p>
Hue: data visualization.
<p></p>
Spark sql : The huge faster map-reduce in memory. 
<p></p>
Hadoop + Zookeeper: Manage the whole data platform and do map reduce.
<p></p>
For the whole system has a good efficient of data analysis. All software would be divided to different nodes to balance the work pressure.
<p></p>
Hp1 (master):Hadoop, Zookeeper, Hbase 
<p></p>
Hp2 (data collect): Flume, jar  
<p></p>
Hp3 (data analysis and present): Hive, Hue
<p></p>

*Hp1 runnning works:
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/6.png" width="auto" height="auto"/>

<h2>The Data Handling Process</h2>
Java program for reading data row by row and run it on linux, flume will monitor this target file and do data collect.
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/7.png" width="auto" height="auto"/>

For off-line process:
Flume monitor this log file and transfer data to Hbase  
Flumes configs:  
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/8.png" width="auto" height="auto"/>  

Worldcup table (Hbase, Present in Hue):
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/9.png" width="auto" height="auto"/>  

The system connect the hive with hbase by external table, so we could do data search or analysis by hive using SQL.  
Following is the demos:  

1. Sample sql: Top 10 counties with the most number of players
     ```sql
    select nationality,count(*) as num from worldcup3 group by nationality order by num desc limit 10;
    ```
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/10.png" width="auto" height="auto"/>   
Job tracker monitor:
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/11.png" width="auto" height="auto"/>  
Also we can check on yarn web:
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/12.png" width="auto" height="auto"/>  
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/13.png" width="auto" height="auto"/>  

Result:
Hue could extract the result from hdfs after map reduce finished. And provided the chart component to present the data analysis graph.
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/14.png" width="auto" height="auto"/>  

Analysis Graph:  
Bars:
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/15.png" width="auto" height="auto"/> 

Pie:  
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/16.png" width="auto" height="auto"/>  

There are some queries we could do, and see the results
1. Top 10 counties with the most number of players: 
    ```sql
    select nationality,count(*) as num from worldcup3 group by nationality order by num desc limit 10;
    ```
2. Top 20 countries with the oldest average age
    ```sql
    select nationality,avg(age) as avg_age from worldcup3 group by nationality order by avg_age desc limit 20;
    ```
3. Top 30 countries with the highest player
   ```sql
   select nationality,max(height_cm) as max_height from worldcup3 group by nationality order by max_height desc limit 30;
    ``` 
4. Top player of each country by overall: 
   ```sql
   select nationality,first_value(name) as name,max(overall) as max_overall from worldcup_spark group by nationality order by max_overall desc limit 10;
    ``` 
5. Top player of each position come from which country by overall:
   ```sql
   select team_position,first_value(nationality) as nationality,first_value(name) as name,max(overall) as max_overall from worldcup_spark group by team_position order by max_overall desc limit 10;
    ```  
<h2>Exploration on Spark Sql</h2>
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/17.png" width="auto" height="auto"/>  
A experiment of using spark sql connect to hive for presenting data  
<p></p>
Operation process:  
<p></p>
1. Start metastore of hive:  
bin/hive --service metastore 
<p></p>
2. Start the spark sql and do sql commands:  
bin/spark-sql  

<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/18.png" width="auto" height="auto"/> 
We can see the second time of doing the same sql was huge faster then first time, because spark has load the data to cache by first time. Used the same data set to do map-reduce work: 

Top 10 counties with the most number of players age < 18:  
    ```
    select nationality,first_value(name) as name,max(overall) as max_overall from worldcup_spark group by nationality order by max_overall desc limit 10;
    ```
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/19.png" width="auto" height="auto"/> 

Time taken just 2s!!!  
Rocket speed!!!  
Same query by hive on yarn will be 82s to handle the query....  

Hue also connected with the mysql and zookeeper, we could check their status very easily:
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/20.png" width="auto" height="auto"/> 

<h2>live streaming data analysis</h2>
The tools used for offline data: Flume, Kafka, Spark Streaming, Mysql  
Structure Flow:  
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/21.png" width="auto" height="auto"/> 

Used the same data set to do the real time analysis.  

Flume: Collect the data from log file and push data to kafka.

Kafka: As the message queue to transfer data flow, flume as the provider, spark as the consumer 

Spark Streaming: Live map-reduce analyse for data streaming and push data to mysql for storing.  

Mysql: Stored the real time changed data for later use.  

For this structure, I also set 3 nodes to implement data transfer.  

Hp1 (master): Flume, Kafka, Mysql
Hp2 (data collect): Flume, Jar
Localhost (real time analyse): Spark streaming.    

Note: We could set the spark streaming part on the hp2 node and used spark shell to background
running, the reason I set the spark streaming on localhost now is just for presenting code and
effect easily.

**Operation process:**  
1. start the kafka cluster (hp1,hp2,hp3):  
  bin/kafka-server-start.sh config/server.properties  

       Note: we should create the topics “worldcup” and register in zookeeper before starting the kafka cluster.   

2. start the flume nodes (hp1,hp2): ./start.sh  

3. Start the data collect jar(hp2): ./wp.shell  

4. start the spark streaming ( localhost || hp2 ):  
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/22.png" width="auto" height="auto"/> 

5. Check the streaming in mysql:  
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/23.png" width="auto" height="auto"/> 

Spark Streaming will continuously push the real-time analysis data result in mysql by 3S a round

**Guides:**  

https://spark.apache.org/docs/latest/structured-streaming-programming-guide.html  

https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html
