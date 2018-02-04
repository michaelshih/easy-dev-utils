package com.itoolshub.pojo.template.javabean

import com.itoolshub.pojo.model.table.TableModel
import com.itoolshub.pojo.template.Template
import com.itoolshub.pojo.util.FreeMarkerUtil
import org.junit.Test
import java.util.*

class JavaBeanTemplateTest {

  @Test
  fun testParse() {
    val tableModel = TableModel("user",Collections.emptyList())
    // 定义文件,渲染
    var template: Template = JavaBeanTemplate(null, "Downloads/quding", tableModel)
    FreeMarkerUtil.parseTemplate(template.mapRootData(), template.templateName(), template.targetOutFile())
  }
}