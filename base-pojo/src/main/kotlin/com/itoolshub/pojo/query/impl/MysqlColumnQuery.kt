package com.itoolshub.pojo.query.impl

import com.itoolshub.easy.template.AbstractDbutilTemblate
import com.itoolshub.pojo.model.table.ColumnModel
import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.query.QueryTableFromDb
import org.apache.commons.dbutils.handlers.MapListHandler
import org.springframework.util.CollectionUtils
import java.sql.Connection
import java.util.stream.Collectors

class MysqlColumnQuery : AbstractDbutilTemblate(), QueryTableFromDb {

  /**
   * 结果转换器
   */
  private val mapListHandler = MapListHandler()


  override fun queryDetailOfTable(conn: Connection, database: String, table: String): TableModel? {
    val tableDetail: List<Map<String, Any>> = this.queryRunner.query(conn, "SHOW FULL COLUMNS" +
        " FROM $database.$table", mapListHandler)
    if (CollectionUtils.isEmpty(tableDetail)) {
      return null
    }
    // 转换为自定义Model
    val columns = tableDetail.stream().map { columnMap ->
      val columnModel = ColumnModel(
          columnMap.get("Field") as String,
          columnMap.get("Type") as String,
          columnMap.get("Collation") as? String,
          columnMap.get("Null") as String == "YES",
          columnMap.get("Key") as? String,
          columnMap.get("Default") as? String,
          columnMap.get("Extra") as? String,
          columnMap.get("Comment") as? String
      )
      return@map columnModel
    }.collect(Collectors.toList())

    return TableModel(table, columns)
  }

}