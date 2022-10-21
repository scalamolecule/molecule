/*
* AUTO-GENERATED Molecule DSL for namespace `Schema`
*
* To change:
* 1. Edit data model in molecule.boilerplate.api.generic.dsl.dataModel.Schema
* 2. `sbt clean compile -Dmolecule=true`
*/
package molecule.boilerplate.api.generic.dsl.Schema

import java.util.Date
import molecule.boilerplate.api.expression._
import molecule.boilerplate.api.generic.dsl.Schema.Schema_._
import molecule.boilerplate.markers.namespaceMarkers._
import scala.language.higherKinds


trait Schema_0 extends Molecule_00 {
  final lazy val t            : Schema_1_OneM[t           , Long   , Long   ] = ???
  final lazy val tx           : Schema_1_OneM[tx          , Long   , Long   ] = ???
  final lazy val txInstant    : Schema_1_OneM[txInstant   , Date   , Date   ] = ???
  final lazy val attrId       : Schema_1_OneM[attrId      , Long   , Long   ] = ???
  final lazy val a            : Schema_1_OneM[a           , String , String ] = ???
  final lazy val part         : Schema_1_OneM[part        , String , String ] = ???
  final lazy val nsFull       : Schema_1_OneM[nsFull      , String , String ] = ???
  final lazy val ns           : Schema_1_OneM[ns          , String , String ] = ???
  final lazy val attr         : Schema_1_OneM[attr        , String , String ] = ???
  final lazy val ident        : Schema_1_OneM[ident       , String , String ] = ???
  final lazy val valueType    : Schema_1_OneM[valueType   , String , String ] = ???
  final lazy val cardinality  : Schema_1_OneM[cardinality , String , String ] = ???
  final lazy val doc          : Schema_1_OneM[doc         , String , String ] = ???
  final lazy val unique       : Schema_1_OneM[unique      , String , String ] = ???
  final lazy val isComponent  : Schema_1_OneM[isComponent , Boolean, Boolean] = ???
  final lazy val noHistory    : Schema_1_OneM[noHistory   , Boolean, Boolean] = ???
  final lazy val index        : Schema_1_OneM[index       , Boolean, Boolean] = ???
  final lazy val fulltext     : Schema_1_OneM[fulltext    , Boolean, Boolean] = ???
  
  final lazy val ident$       : Schema_1_OneO[ident$      , String , Option[String ]] = ???
  final lazy val valueType$   : Schema_1_OneO[valueType$  , String , Option[String ]] = ???
  final lazy val cardinality$ : Schema_1_OneO[cardinality$, String , Option[String ]] = ???
  final lazy val doc$         : Schema_1_OneO[doc$        , String , Option[String ]] = ???
  final lazy val unique$      : Schema_1_OneO[unique$     , String , Option[String ]] = ???
  final lazy val isComponent$ : Schema_1_OneO[isComponent$, Boolean, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_1_OneO[noHistory$  , Boolean, Option[Boolean]] = ???
  final lazy val index$       : Schema_1_OneO[index$      , Boolean, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_1_OneO[fulltext$   , Boolean, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_0_OneT[t_          , Long   ] = ???
  final lazy val tx_          : Schema_0_OneT[tx_         , Long   ] = ???
  final lazy val txInstant_   : Schema_0_OneT[txInstant_  , Date   ] = ???
  final lazy val attrId_      : Schema_0_OneT[attrId_     , Long   ] = ???
  final lazy val a_           : Schema_0_OneT[a_          , String ] = ???
  final lazy val part_        : Schema_0_OneT[part_       , String ] = ???
  final lazy val nsFull_      : Schema_0_OneT[nsFull_     , String ] = ???
  final lazy val ns_          : Schema_0_OneT[ns_         , String ] = ???
  final lazy val attr_        : Schema_0_OneT[attr_       , String ] = ???
  final lazy val ident_       : Schema_0_OneT[ident_      , String ] = ???
  final lazy val valueType_   : Schema_0_OneT[valueType_  , String ] = ???
  final lazy val cardinality_ : Schema_0_OneT[cardinality_, String ] = ???
  final lazy val doc_         : Schema_0_OneT[doc_        , String ] = ???
  final lazy val unique_      : Schema_0_OneT[unique_     , String ] = ???
  final lazy val isComponent_ : Schema_0_OneT[isComponent_, Boolean] = ???
  final lazy val noHistory_   : Schema_0_OneT[noHistory_  , Boolean] = ???
  final lazy val index_       : Schema_0_OneT[index_      , Boolean] = ???
  final lazy val fulltext_    : Schema_0_OneT[fulltext_   , Boolean] = ???
}

trait Schema_0_OneT[Attr, t] extends Schema_0 with ExprOneT_0[Attr, t, Schema_0_OneT]

