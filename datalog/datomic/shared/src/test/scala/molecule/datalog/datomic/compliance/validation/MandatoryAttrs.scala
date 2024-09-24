package molecule.datalog.datomic.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.datalog.datomic.setup.Test_datomic_async

object Test_MandatoryAttrs extends MandatoryAttrs with Test_datomic_async
