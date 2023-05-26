package molecule.sql.jdbc.setup

import molecule.sql.jdbc.spi.JdbcSpiAsync

trait CoreTestAsync extends JdbcTestSuite with JdbcSpiAsync
