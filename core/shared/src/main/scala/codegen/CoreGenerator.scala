package codegen

object CoreGenerator extends App {

//  _MoleculeImplicits.generate
//  core.api._Insert.generate
//  core.api._Save.generate


  // Use instead in tandem with db.datomic.api._DatomicMoleculeApi.generate to coordinate correct overrides
  core.api._MoleculeApi.generate
}
