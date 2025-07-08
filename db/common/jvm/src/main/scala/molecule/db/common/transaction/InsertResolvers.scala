package molecule.db.common.transaction

import molecule.core.dataModel.*

trait InsertResolvers {

  protected def resolve(
    elements: List[Element],
    resolvers: List[Product => Unit],
    tplIndex: Int,
    prevRefs: List[String]
  ): List[Product => Unit]

  def getResolver(elements: List[Element]): Product => Unit = {
    val resolvers: List[Product => Unit] = resolve(elements, Nil, 0, Nil)
    (tpl: Product) => resolvers.foreach(_(tpl))
  }
}