# Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup
A Big Data project that using modern big data tools and stacks,I have used Hadoop cluster for data storage, and connect to big data environment including flume, kafka, hbase, hive,hue, spark, etc... to implement a real-time world cup data showing.  
<p></p>
This is a very classic big data project structure. Following is my experience and product show, hope you enjoy!  
<p></p>
<img align="left" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/0.png" width="auto" height="auto"/>
<p></p>

✨Tech Environment：  
- Cluster: Hadoop, Zookeeper, Flume
- Real-time: Kafka, Spark, Mysql, Spring MVC
- Off-line: Hbase, Hive, Hue
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

This round is **Count the players of each country:**
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/2.png" width="100%" height="auto"/>

Run jar file on hadoop, result looks good, format: Country - Player Numbers
<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/3.png" width="auto" height="auto"/>

Second round is **Find the best overall player of each country:**, For this round we used secondary sorting which means we will order data set before reducing to improve the speed of analysis. To attain this goal we need customize more java files.

<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/4.png" width="auto" height="auto"/>

Following the result, format: Country - player - power.

<img align="center" alt="img" src="https://github.com/XingyuHuang23/Real-time-Big-Data-Analysis-System-for-the-FIFA-World-Cup/blob/main/imgs/5.png" width="auto" height="auto"/>

Besides the test above,I have 3 more map-reduce tests, details please see my project folder.

**<h2>Hadoop environment architect</h2>** 


