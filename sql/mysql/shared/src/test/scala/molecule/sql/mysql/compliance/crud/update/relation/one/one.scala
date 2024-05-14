package molecule.sql.mysql.compliance.crud.update.relation.one

import molecule.coreTests.spi.crud.update2.relation.one._
import molecule.sql.mysql.setup.TestAsync_mysql

object One_Map extends One_Map with TestAsync_mysql
object One_Map_add extends One_Map_add with TestAsync_mysql
object One_Map_remove extends One_Map_remove with TestAsync_mysql
object One_One extends One_One with TestAsync_mysql
object One_Seq extends One_Seq with TestAsync_mysql
object One_Seq_add extends One_Seq_add with TestAsync_mysql
object One_Seq_remove extends One_Seq_remove with TestAsync_mysql
object One_Set extends One_Set with TestAsync_mysql
object One_Set_add extends One_Set_add with TestAsync_mysql
object One_Set_remove extends One_Set_remove with TestAsync_mysql
