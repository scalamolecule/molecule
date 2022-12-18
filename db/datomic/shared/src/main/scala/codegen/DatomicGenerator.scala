package codegen

import codegen.db._


object DatomicGenerator extends App {


//    core.api._MoleculeApi.generate
  //  core.api._Insert.generate
//    datomic.api._DatomicMoleculeApi.generate
//    datomic.api._DatomicInsert.generate


    datomic.query.casting._CastIt2Tpl.generate
    datomic.query.casting._CastNestedBranch.generate
    datomic.query.casting._CastNestedOptBranch.generate
    datomic.query.casting._CastNestedOptLeaf.generate
    datomic.query.casting._CastRow2Tpl.generate
    datomic.query.casting._NestOpt.generate

//    datomic.query._SortOne.generate
//    datomic.query._SortOneOpt.generate
//    datomic.query._SortOneOptFlat.generate
//
//    codegen.core._MoleculeImplicits.generate
//    datomic._DatomicMoleculeImplicits.generate
}
