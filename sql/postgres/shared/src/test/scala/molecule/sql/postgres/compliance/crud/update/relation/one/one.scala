package molecule.sql.postgres.compliance.crud.update.relation.one

import molecule.coreTests.spi.crud.update.relation.one._
import molecule.sql.postgres.setup.TestAsync_postgres

object Test_One_Map extends One_Map with TestAsync_postgres
object Test_One_Map_add extends One_Map_add with TestAsync_postgres
object Test_One_Map_remove extends One_Map_remove with TestAsync_postgres
object Test_One_One extends One_One with TestAsync_postgres
object Test_One_Seq extends One_Seq with TestAsync_postgres
object Test_One_Seq_add extends One_Seq_add with TestAsync_postgres
object Test_One_Seq_remove extends One_Seq_remove with TestAsync_postgres
object Test_One_Set extends One_Set with TestAsync_postgres
object Test_One_Set_add extends One_Set_add with TestAsync_postgres
object Test_One_Set_remove extends One_Set_remove with TestAsync_postgres
