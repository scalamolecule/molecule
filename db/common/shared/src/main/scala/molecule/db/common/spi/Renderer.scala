package molecule.db.common.spi

import molecule.core.dataModel.DataModel

trait Renderer {

  protected def renderInspection(
    action: String,
    dataModel: DataModel,
    dbString: String = "",
    dataString: String = ""
  ): String = {
    val render = List(
      if (dataModel.elements.isEmpty) None else Some(dataModel),
      if (dbString.isEmpty) None else Some(dbString),
      if (dataString.isEmpty) None else Some(dataString),
    ).flatten.mkString("\n\n")
    val inspection = s"""=== $action =====================================
       |$render
       |----------------------------------------
       |""".stripMargin
    // Also print out
    println(inspection)
    inspection
  }
}
