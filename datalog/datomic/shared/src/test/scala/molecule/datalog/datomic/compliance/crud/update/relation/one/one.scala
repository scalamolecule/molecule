package molecule.datalog.datomic.compliance.crud.update.relation.one

import molecule.coreTests.spi.crud.update.relation.one._
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_One_Map extends One_Map with Test_datomic_async
object Test_One_Map_add extends One_Map_add with Test_datomic_async
object Test_One_Map_remove extends One_Map_remove with Test_datomic_async
object Test_One_One extends One_One with Test_datomic_async
object Test_One_Seq extends One_Seq with Test_datomic_async
object Test_One_Seq_add extends One_Seq_add with Test_datomic_async
object Test_One_Seq_remove extends One_Seq_remove with Test_datomic_async
object Test_One_Set extends One_Set with Test_datomic_async
object Test_One_Set_add extends One_Set_add with Test_datomic_async
object Test_One_Set_remove extends One_Set_remove with Test_datomic_async
