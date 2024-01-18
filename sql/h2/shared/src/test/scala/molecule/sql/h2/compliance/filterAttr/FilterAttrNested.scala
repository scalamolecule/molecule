package molecule.sql.h2.compliance.filterAttr

import molecule.coreTests.spi.filterAttr.{FilterAttrNested, FilterAttrRef, FilterAttr_id}
import molecule.sql.h2.setup.TestAsync_h2

object FilterAttr_id extends FilterAttr_id with TestAsync_h2
object FilterAttrNested extends FilterAttrNested with TestAsync_h2
object FilterAttrRef extends FilterAttrRef with TestAsync_h2
