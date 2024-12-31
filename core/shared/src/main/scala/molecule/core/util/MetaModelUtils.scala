package molecule.core.util

import molecule.base.ast._


trait MetaModelUtils {

  def getHasMandatoryRefs(entityMap: Map[String, MetaEntity]): Boolean = entityMap.exists {
    case (_, ns) if ns.mandatoryRefs.nonEmpty => true
    case _                                    => false
  }
}
