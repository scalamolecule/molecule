package molecule.sql.postgres.compliance.transaction

import molecule.core.api.{Api_sync, Api_sync_transact}
import molecule.coreTests.setup.MUnitSuite
import molecule.coreTests.spi.action.Transactions_sync
import molecule.sql.postgres.setup.DbProviders_postgres
import molecule.sql.postgres.spi.Spi_postgres_sync


class Transactions_sync extends MUnitSuite {
  Transactions_sync(this,
    new Api_sync with Api_sync_transact with Spi_postgres_sync with DbProviders_postgres {}
  )
}
