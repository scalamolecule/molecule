package molecule.core.spi

import molecule.boilerplate.ast.DataModel._

trait Renderer {

  protected def printRaw(
    action: String,
    elements: List[Element],
    dbString: String = "",
    dataString: String = ""
  ): Unit = {
    val render = List(
      if (elements.isEmpty) None else Some(elements.mkString("\n").trim),
      if (dbString.isEmpty) None else Some(dbString),
      if (dataString.isEmpty) None else Some(dataString),
    ).flatten.mkString("\n\n")
    println(
      s"""========================================
         |$action:
         |$render
         |----------------------------------------
         |""".stripMargin
    )
  }
}
