package com.spark.test.Streaming

import java.sql.{Connection, DriverManager, ResultSet, Statement}

import org.apache.spark.sql.{ForeachWriter, Row}

class JDBCSink(url: String, user: String, pwd: String) extends ForeachWriter[Row] {
  var connection: Connection = _
  var statement: Statement = _
  var resultSet: ResultSet = _

  override def open(partitionId: Long, version: Long): Boolean = {
    Class.forName("com.mysql.jdbc.Driver").newInstance()
    connection = DriverManager.getConnection(url, user, pwd)  // connection = new MySql Pool (url, user, pwd) getJdbcConn () ;
    statement = connection.createStatement()
    true
  }
  override def process(value: Row): Unit = {
    val nationality = value.getAs[String]("nationality")
    val count = value.getAs[Long]("count")

    val querySql = "select * from worldcup where nationality ='"+nationality+"'"
    val updateSql = "update worldcup set count = "+count+" where nationality = '"+nationality+"'"
    val insertSql = "insert into worldcup(nationality,count) values('"+nationality+"',"+count+")"


    var resultSet = statement.executeQuery(querySql)

    if(resultSet.next()){
      statement. executeUpdate(updateSql)
    }else{
      statement. execute(insertSql)
    }

  }

  override def close(errorOrNull: Throwable): Unit = {

    connection.close()
  }
}
