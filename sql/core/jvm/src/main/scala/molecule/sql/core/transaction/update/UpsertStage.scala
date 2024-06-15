package molecule.sql.core.transaction.update

import molecule.boilerplate.ast.Model._


sealed trait UpsertStage

case class FindAllIds(refPath: List[String], elements: List[Element]) extends UpsertStage {
  override def toString: String = {
    s"""FindAllIds(
       |  refPath : $refPath
       |  elements: ${elements.mkString("\n    ", "\n    ", "")}
       |)""".stripMargin
  }
}

case class FindKnownIds(refPath: List[String],
                        //                          isCardOne: Boolean,
                        elements: List[Element]) extends UpsertStage {
  override def toString: String = {
    s"""FindKnownIds(
       |  refPath  : $refPath
       |  elements : ${elements.mkString("\n    ", "\n    ", "")}
       |)""".stripMargin
    //         |  isCardOne: $isCardOne
  }
}

case class CompleteCurRef(refPath: List[String], idsResolver: List[String] => List[Element]) extends UpsertStage {
  override def toString: String = {
    s"""CompleteCurRef(
       |  refPath    : $refPath
       |  idsResolver: List(<ids>) => List(
       |    ${idsResolver(List("<ids>")).mkString("\n    ")}
       |  )
       |)""".stripMargin
  }
}

case class UpdateNsData(refPath: List[String], elements: List[Element]) extends UpsertStage {
  override def toString: String = {
    s"""UpdateNsData(
       |  refPath : $refPath
       |  elements: ${elements.mkString("\n    ", "\n    ", "")}
       |)""".stripMargin
  }
}