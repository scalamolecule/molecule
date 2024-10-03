package molecule.datalog.datomic.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_Adjacent extends Adjacent with Test_datomic_async
object Test_CrossNs extends CrossNs with Test_datomic_async
object Test_FilterAttr_id extends FilterAttr_id with Test_datomic_async
object Test_FilterAttrNested extends FilterAttrNested with Test_datomic_async
object Test_FilterAttrRef extends FilterAttrRef with Test_datomic_async
object Test_Semantics extends Semantics with Test_datomic_async
object Test_Sorting extends Sorting with Test_datomic_async
object Test_Types extends Types with Test_datomic_async

