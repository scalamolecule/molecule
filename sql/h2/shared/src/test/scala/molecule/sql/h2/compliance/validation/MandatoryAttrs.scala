package molecule.sql.h2.compliance.validation

import molecule.coreTests.spi.validation.MandatoryAttrs
import molecule.sql.h2.setup.Test_h2_async

object Test_MandatoryAttrs extends MandatoryAttrs with Test_h2_async
