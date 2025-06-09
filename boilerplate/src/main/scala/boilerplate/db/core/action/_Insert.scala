package boilerplate.db.core.action

import boilerplate.db.core.CoreBase

object _Insert extends CoreBase( "Insert", "/action") {

  val content = {
    val traits = (2 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.db.core.action
       |
       |import molecule.db.core.ast.DataModel
       |
       |case class ${fileName}_1[A](dataModel: DataModel) {
       |  final def apply(a: A, as: A*) = Insert(dataModel, (a +: as).map(a => Tuple1(a)))
       |  final def apply(tpls: Seq[A]) = Insert(dataModel, tpls.map(a => Tuple1(a)))
       |}
       |$traits
       |""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad = "   " * (arity - 2)
    val body =
      s"""
         |case class $fileName_$arity[${`A..V`}](dataModel: DataModel) {
         |  final def apply(${`a:A..v:V`})                 = Insert(dataModel, Seq((${`a..v`})))
         |  final def apply(tpl: ${`(A..V)`}, more: ${`(A..V)`}*) = Insert(dataModel, tpl +: more)
         |  final def apply(tpls: Seq[${`(A..V)`}])$pad          = Insert(dataModel, tpls)
         |}""".stripMargin
  }
}
