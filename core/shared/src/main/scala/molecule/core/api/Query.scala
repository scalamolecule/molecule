package molecule.core.api

import java.util
import molecule.boilerplate.markers.NamespaceMarkers.Molecule_02
import molecule.core.api.MoleculeApi.MoleculeApi_02
import molecule.boilerplate.ast.MoleculeModel._
import zio.{Chunk, ZIO}

trait Query[Tpl] {
  def take(n: Int): Query[Tpl]
  def drop(n: Int): Query[Tpl]
  def from(cursor: String): Query[Tpl]

  def run(implicit conn: Connection): List[Tpl]

  def get(implicit conn: Connection): List[Tpl] = run
//  def run: ZIO[DataSource, Throwable, Chunk[Tpl]]
}
