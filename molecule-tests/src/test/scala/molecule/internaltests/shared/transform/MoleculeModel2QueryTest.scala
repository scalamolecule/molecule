package molecule.internaltests.shared.transform

import molecule.core.ast.elements._
import molecule.datomic.base.ast.query._
import molecule.datomic.base.transform.Model2Query
import molecule.setup.TestSpec

class Model2QueryTest extends TestSpec {

  "Multiple aggregates share attribute value" in new CoreSetup {

    Model2Query(Model(List(
      Atom("Obj", "meanRadius", "Double", 1, Fn("sum", None), None, Seq(), Seq()),
      Atom("Obj", "meanRadius", "Double", 1, Fn("avg", None), None, Seq(), Seq())
    )))._1.toString ===
      Query(
        Find(List(
          AggrExpr("sum", Seq(), Var("b")),
          AggrExpr("avg", Seq(), Var("b")))),
        With(List("a")),
        In(Nil, Nil, Nil),
        Where(List(
          DataClause(ImplDS, Var("a"), KW("Obj", "meanRadius", ""), Var("b"), Empty, NoBinding)
        ))).toString


    Model2Query(Model(List(
      Atom("Obj", "meanRadius", "Double", 2, Fn("sum", None), None, Seq(), Seq()),
      Atom("Obj", "meanRadius", "Double", 2, Fn("avg", None), None, Seq(), Seq())
    )))._1.toString ===
      Query(
        Find(List(
          AggrExpr("sum", Seq(), Var("b")),
          AggrExpr("avg", Seq(), Var("b")))),
        With(List("a")),
        In(Nil, Nil, Nil),
        Where(List(
          DataClause(ImplDS, Var("a"), KW("Obj", "meanRadius", ""), Var("b"), Empty, NoBinding)
        ))).toString
  }
}
