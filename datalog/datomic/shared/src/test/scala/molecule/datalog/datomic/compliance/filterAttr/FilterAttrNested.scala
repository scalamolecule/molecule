package molecule.datalog.datomic.compliance.filterAttr

import molecule.coreTests.spi.filterAttr.{FilterAttrNested, FilterAttrRef, FilterAttr_id}
import molecule.datalog.datomic.setup.TestAsync_datomic

object FilterAttr_id extends FilterAttr_id with TestAsync_datomic
object FilterAttrNested extends FilterAttrNested with TestAsync_datomic
object FilterAttrRef extends FilterAttrRef with TestAsync_datomic
