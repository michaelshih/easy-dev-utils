package com.itoolshub.pojo.template.javabean

import com.itoolshub.pojo.model.table.ColumnModel
import com.itoolshub.pojo.model.table.TableModel
import org.junit.Test

class JavaBeanTemplateTest {

  @Test
  fun testParse() {
    val filed1 = ColumnModel("name", "varchar", true)
    val filed2 = ColumnModel("pwd", "datetime", false)
    val filed3 = ColumnModel("age", "int(5)", false)
    filed3.default = "0"
    val tableModel = TableModel("user", listOf(filed1, filed2, filed3))

    // 定义文件,渲染
    val template = JavaBeanTemplate(tableModel, "Downloads/quding")
    assert(template.className().equals("User"))
    assert(template.fileName().equals("User.java"))
    template.renderTemplate()
  }


}