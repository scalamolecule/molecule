package molecule.db.compliance.setup

import java.nio.file.{Files, Path}
import molecule.base.util.BaseHelpers

trait DbConnection extends BaseHelpers{

  def tempDbPath: Path = {
    // Create temp db file for each test suite
    Files.createTempFile(null, ".db").toAbsolutePath
  }
}
