package molecule.sql.jdbc.setup

import molecule.sql.jdbc.spi.JdbcSpiZio

trait CoreTestZio extends JdbcZioSpec with JdbcSpiZio
