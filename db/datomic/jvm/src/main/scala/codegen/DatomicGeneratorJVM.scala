package codegen


object DatomicGeneratorJVM extends App {

//  db.datomic._DatomicMoleculeImplicits.generate
//  db.datomic.api._DatomicInsert.generate
  db.datomic.transaction.insert._InsertResolvers.generate

}
