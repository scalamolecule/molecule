package molecule.db.compliance.setup

import java.nio.file.{Files, Path}

trait DbConnection {

  def tempDbPath: Path = {
    // Create temp db file for each test suite
    Files.createTempFile(null, ".db").toAbsolutePath
  }
}
