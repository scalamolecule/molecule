package molecule.sql.sqlite.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp.decimal._
import molecule.sql.sqlite.setup.Test_sqlite_async


object Test_AttrOpSpecial_Float_ extends AttrOpDecimal_Float_ with Test_sqlite_async
object Test_AttrOpSpecial_Double_ extends AttrOpDecimal_Double with Test_sqlite_async
object Test_AttrOpSpecial_BigDecimal_ extends AttrOpDecimal_BigDecimal with Test_sqlite_async

