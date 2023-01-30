package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action._

class DatomicQueryApi[Tpl](elements: List[Element])
  extends DatomicQueryApiImpl[Tpl](elements) //with QueryApi[Tpl]
