package molecule.core.dataModel

sealed trait Mode
trait Mandatory extends Mode
trait Optional extends Mode
trait Tacit extends Mode
