package molecule.core.spi

import molecule.boilerplate.ast.Model._

trait Renderer {

  protected def printRaw(
    action: String,
    elements: List[Element],
    dbString0: String = "",
    dataString0: String = ""
  ): Unit = {
    val dbString   = if (dbString0.isBlank) "" else s"\n\n$dbString0"
    val dataString = if (dataString0.isBlank) "" else s"\n\n$dataString0"
    println(
      s"""========================================
         |$action:
         |${elements.mkString("\n").trim}$dbString$dataString
         |----------------------------------------
         |""".stripMargin
    )
  }
}
