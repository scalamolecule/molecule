package molecule.db.common.util

import molecule.db.common.api.MetaDb

trait MetaModelUtils {

  def getHasMandatoryRefs(metaDb: MetaDb): Boolean = metaDb.mandatoryRefs.exists(_._2.nonEmpty)
}
