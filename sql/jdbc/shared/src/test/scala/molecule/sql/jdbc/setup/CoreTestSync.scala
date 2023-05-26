package molecule.sql.jdbc.setup

import molecule.sql.jdbc.spi.JdbcSpiSync

trait CoreTestSync extends JdbcTestSuite with JdbcSpiSync
