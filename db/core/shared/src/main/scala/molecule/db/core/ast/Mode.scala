package molecule.db.core.ast

sealed trait Mode
trait Mandatory extends Mode
trait Optional extends Mode
trait Tacit extends Mode
