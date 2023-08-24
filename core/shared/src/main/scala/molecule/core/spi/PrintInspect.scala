package molecule.core.spi

import molecule.boilerplate.ast.Model._

trait PrintInspect {

  protected def printInspect(label: String, elements: List[Element], stmts0: String = ""): Unit = {
    val stmts = if(stmts0.isBlank) "" else s"\n\n$stmts0"
    println(
      s"""========================================
         |$label:
         |${elements.mkString("\n").trim}$stmts
         |----------------------------------------
         |""".stripMargin
    )
  }
}
