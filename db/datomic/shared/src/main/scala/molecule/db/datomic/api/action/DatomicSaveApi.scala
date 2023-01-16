package molecule.db.datomic.api.action

import molecule.boilerplate.ast.Model._
import molecule.core.api.action._

class DatomicSaveApi(elements: List[Element])
  extends DatomicSaveApiImpl(elements) with SaveApi


