package molecule.boilerplate.ast

import molecule.base.ast.SchemaAST._
import scala.util.Random

/** AST for molecule Model representation. <br><br> Molecule transforms custom
 * boilerplate DSL constructs to Datomic queries in 3 steps: <br><br> Custom
 * DSL molecule --> MoleculeModel --> Query --> Datomic query string
 */
object MoleculeModel
  extends AtomOneMan_
  with AtomOneOpt_
  with AtomOneTac_
  with ModelBase
