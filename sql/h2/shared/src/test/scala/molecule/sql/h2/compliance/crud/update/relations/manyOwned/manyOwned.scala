package molecule.sql.h2.compliance.crud.update.relations.manyOwned

import molecule.coreTests.spi.crud.update2.relations.manyOwned._
import molecule.sql.h2.setup.TestAsync_h2

object ManyOwned_Map extends ManyOwned_Map with TestAsync_h2
object ManyOwned_Map_add extends ManyOwned_Map_add with TestAsync_h2
object ManyOwned_Map_remove extends ManyOwned_Map_remove with TestAsync_h2
object ManyOwned_One extends ManyOwned_One with TestAsync_h2
object ManyOwned_Seq extends ManyOwned_Seq with TestAsync_h2
object ManyOwned_Seq_add extends ManyOwned_Seq_add with TestAsync_h2
object ManyOwned_Seq_remove extends ManyOwned_Seq_remove with TestAsync_h2
object ManyOwned_Set extends ManyOwned_Set with TestAsync_h2
object ManyOwned_Set_add extends ManyOwned_Set_add with TestAsync_h2
object ManyOwned_Set_remove extends ManyOwned_Set_remove with TestAsync_h2
