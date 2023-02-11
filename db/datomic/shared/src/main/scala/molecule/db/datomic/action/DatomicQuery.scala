package molecule.db.datomic.action

import molecule.boilerplate.ast.Model._

// Implemented on jvm and js platforms
class DatomicQuery[Tpl](elements: List[Element])
  extends DatomicQueryImpl[Tpl](elements)
