package molecule.db.core.util

import molecule.base.metaModel.MetaEntity

trait MetaModelUtils {

  def getHasMandatoryRefs(entityMap: Map[String, MetaEntity]): Boolean = entityMap.exists {
    case (_, ent) if ent.mandatoryRefs.nonEmpty => true
    case _                                      => false
  }
}
