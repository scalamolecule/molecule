package codegen.core.api

import codegen.CoreGenBase

object _Insert extends CoreGenBase( "Insert", "/api") {

  val content = {
    val traits = (2 to 22).map(arity => Trait(arity).body).mkString("\n")
    s"""// GENERATED CODE ********************************
       |package molecule.core.api
       |
       |trait Insert {
       |  // Implemented for each db and JS/JVM platform
       |  private[core] def _insertOp(tpls: Seq[Product]): InsertOps
       |}
       |
       |object Insert  {
       |
       |  trait Insert_1[A] extends Insert {
       |    def apply(a: A, as: A*): InsertOps = _insertOp((a +: as).map(a => Tuple1(a)))
       |    def apply(tpls: Seq[A]): InsertOps = _insertOp(tpls.map(a => Tuple1(a)))
       |  }
       |$traits
       |}""".stripMargin
  }

  case class Trait(arity: Int) extends TemplateVals(arity) {
    val pad = "   " * (arity - 2)
    val body =
      s"""
         |  trait Insert_$arity[${`A..V`}] extends Insert {
         |    def apply(${`a:A..v:V`})                : InsertOps = _insertOp(Seq((${`a..v`})))
         |    def apply(tpl: ${`(A..V)`}, more: ${`(A..V)`}*): InsertOps = _insertOp(tpl +: more)
         |    def apply(tpls: Seq[${`(A..V)`}])$pad         : InsertOps = _insertOp(tpls)
         |  }""".stripMargin
  }
}
