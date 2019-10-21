package com.ln.sparkstream.util

import java.sql.{Connection, DriverManager, ResultSet, Statement}

import org.apache.spark.sql.{ForeachWriter, Row}

/**
 * 封装一个jdbc操作
 *
 * @param url
 * @param username
 * @param password
 */
class JDBCSink(url: String, username: String, password: String) extends ForeachWriter[Row] {

  val driver = "com.mysql.jdbc.Driver"
  var connection: Connection = _
  var statement: Statement = _
  var resultSet: ResultSet = _

  override def open(partitionId: Long, version: Long): Boolean = {
    Class.forName(driver)
    connection = DriverManager.getConnection(url, username, password)
    statement = connection.createStatement
    true
  }


  override def process(value: Row): Unit = {
    val titleName = value.getAs[String]("titleName").toString.replaceAll("[\\[\\]]", "")
    val count = value.getAs[Long]("count")

    val querySql = "select titleName from webCount where titleName='" + titleName + "'"
    val updateSql = "update webCount set count=" + count + " where titleName='" + titleName + "'"
    val insertSql = "insert into webCount(titleName,count) values('" + titleName + "'," + count + ")"
    resultSet = statement.executeQuery(querySql)
    if (resultSet.next()) {
      statement.executeUpdate(updateSql)
    } else {
      statement.executeLargeUpdate(insertSql)
    }
  }

  override def close(errorOrNull: Throwable): Unit = {
    if (resultSet != null) {
      resultSet.close()
    }
    if (statement != null) {
      statement.close()
    }
    if (connection != null) {
      connection.close()
    }
  }
}
