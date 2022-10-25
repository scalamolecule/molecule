package molecule.db.datomic.base

import molecule.boilerplate.ast.MoleculeModel._


abstract class StmtsBuilderN(elements: Seq[Element]) {

  protected def resolve(
    elements: Seq[Element],
    acc: List[Product => Unit],
    n: Int = 0
  ): List[Product => Unit]

  def getResolver: Product => Unit = {
    val resolvers: List[Product => Unit] = resolve(elements, Nil)
    resolvers.length match {
      case 2 => resolve2(resolvers)
      case 3 => resolve3(resolvers)
    }
  }

  def resolve2(resolvers: List[Product => Unit]): Product => Unit = {
    val List(r1, r2) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
    }
  }
  def resolve3(resolvers: List[Product => Unit]): Product => Unit = {
    val List(r1, r2, r3) = resolvers
    (tpl: Product) => {
      r1(tpl)
      r2(tpl)
      r3(tpl)
    }
  }
  // todo: generate code for 100

}