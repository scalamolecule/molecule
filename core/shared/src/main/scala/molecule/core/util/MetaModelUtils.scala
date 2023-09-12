package molecule.core.util

import molecule.base.ast._


trait MetaModelUtils {

  def getHasMandatoryRefs(nsMap: Map[String, MetaNs]): Boolean = nsMap.exists {
    case (_, ns) if ns.mandatoryRefs.nonEmpty => true
    case _                                    => false
  }
}
