package codegen


object DatomicGenerator extends App {

  // Coordinate overrides in multiple files --------------

  //  core.api._Insert.generate
//    db.datomic.api._DatomicInsert.generate


  //  core.api._MoleculeApi.generate
//    db.datomic.api._DatomicMoleculeApi.generate


  // jvm specific only -----------------------------------

//    db.datomic._DatomicMoleculeImplicits.generate

//    db.datomic.query._CastFlat.generate
//    db.datomic.query._CastNestedBranch.generate
//    db.datomic.query._CastNestedLeaf.generate
//    db.datomic.query._CastNestedOptBranch.generate
//    db.datomic.query._CastNestedOptLeaf.generate
//    db.datomic.query._NestOpt.generate

    db.datomic.query._Sort.generate

}
