package molecule.db.core.util

import molecule.db.core.api.MetaDb

trait MetaModelUtils {

  def getHasMandatoryRefs(metaDb: MetaDb): Boolean = metaDb.mandatoryRefs.exists(_._2.nonEmpty)
}
