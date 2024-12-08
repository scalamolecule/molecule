package molecule.sql.h2.compliance.crud.update.attrOp

import molecule.coreTests.spi.crud.update.attrOp.decimal._
import molecule.sql.h2.setup.Test_h2_async


object Test_AttrOpSpecial_Float_ extends AttrOpDecimal_Float_ with Test_h2_async
object Test_AttrOpSpecial_Double_ extends AttrOpDecimal_Double with Test_h2_async
object Test_AttrOpSpecial_BigDecimal_ extends AttrOpDecimal_BigDecimal with Test_h2_async

