package molecule.sql.mariadb.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp.decimal._
import molecule.sql.mariadb.setup.Test_mariadb_async


object Test_AttrOpSpecial_Float_ extends AttrOpDecimal_Float_ with Test_mariadb_async
object Test_AttrOpSpecial_Double_ extends AttrOpDecimal_Double with Test_mariadb_async
object Test_AttrOpSpecial_BigDecimal_ extends AttrOpDecimal_BigDecimal_ with Test_mariadb_async

