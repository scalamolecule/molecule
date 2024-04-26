package molecule.sql.postgres.compliance.crud.update.relations.oneOwned

import molecule.coreTests.spi.crud.update2.relations.oneOwned._
import molecule.sql.postgres.setup.TestAsync_postgres

object OneOwned_Map extends OneOwned_Map with TestAsync_postgres
object OneOwned_Map_add extends OneOwned_Map_add with TestAsync_postgres
object OneOwned_Map_remove extends OneOwned_Map_remove with TestAsync_postgres
object OneOwned_One extends OneOwned_One with TestAsync_postgres
object OneOwned_Seq extends OneOwned_Seq with TestAsync_postgres
object OneOwned_Seq_add extends OneOwned_Seq_add with TestAsync_postgres
object OneOwned_Seq_remove extends OneOwned_Seq_remove with TestAsync_postgres
object OneOwned_Set extends OneOwned_Set with TestAsync_postgres
object OneOwned_Set_add extends OneOwned_Set_add with TestAsync_postgres
object OneOwned_Set_remove extends OneOwned_Set_remove with TestAsync_postgres
