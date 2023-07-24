package molecule.core.spi

import molecule.boilerplate.ast.Model._

trait PrintInspect {

  protected def printInspect(label: String, elements: List[Element], stmts: String = ""): Unit = {
//    val stmtsStr = if (stmts.isEmpty) "" else if (elements.isEmpty) stmts else s"\n\n${stmts}"
    val stmtsStr = if (stmts.isEmpty) "" else if (elements.isEmpty) stmts else s"${stmts}"
    println(
      s"""========================================
         |$label:
         |$stmtsStr
         |""".stripMargin
//         |${elements.mkString("\n").trim}$stmtsStr
    )
  }
}
