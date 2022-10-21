package molecule.core.api

import java.util
import molecule.boilerplate.markers.namespaceMarkers.Molecule_02
import molecule.core.api.MoleculeApi.MoleculeApi_2
import molecule.boilerplate.ast.moleculeModel._
import zio.{Chunk, ZIO}

trait Query[Tpl] {
  def take(n: Int): Query[Tpl]
  def drop(n: Int): Query[Tpl]
  def from(cursor: String): Query[Tpl]

  def run(implicit conn: Connection): List[Tpl]
//  def run: ZIO[DataSource, Throwable, Chunk[Tpl]]
}
