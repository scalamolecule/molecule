package molecule.datalog.datomic.compliance.filterAttr.one

import molecule.coreTests.spi.filterAttr.one._
import molecule.datalog.datomic.setup.TestAsync_datomic

object Test_Adjacent extends Adjacent with TestAsync_datomic
object Test_CrossNs extends CrossNs with TestAsync_datomic
object Test_CrossNsOwned extends CrossNsOwned with TestAsync_datomic
object Test_FilterAttr_id extends FilterAttr_id with TestAsync_datomic
object Test_FilterAttrNested extends FilterAttrNested with TestAsync_datomic
object Test_FilterAttrRef extends FilterAttrRef with TestAsync_datomic
object Test_Semantics extends Semantics with TestAsync_datomic
object Test_Sorting extends Sorting with TestAsync_datomic
object Test_Types extends Types with TestAsync_datomic

