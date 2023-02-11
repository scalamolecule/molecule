package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._

class DatomicUpdate(
  elements: List[Element],
  isUpsert: Boolean = false
) extends DatomicUpdateImpl(elements, isUpsert)


