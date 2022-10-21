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


trait Schema_1[A] extends Molecule_01[A] {
  final lazy val t            : Schema_2_OneM[t           , Long   , A, Long   ] = ???
  final lazy val tx           : Schema_2_OneM[tx          , Long   , A, Long   ] = ???
  final lazy val txInstant    : Schema_2_OneM[txInstant   , Date   , A, Date   ] = ???
  final lazy val attrId       : Schema_2_OneM[attrId      , Long   , A, Long   ] = ???
  final lazy val a            : Schema_2_OneM[a           , String , A, String ] = ???
  final lazy val part         : Schema_2_OneM[part        , String , A, String ] = ???
  final lazy val nsFull       : Schema_2_OneM[nsFull      , String , A, String ] = ???
  final lazy val ns           : Schema_2_OneM[ns          , String , A, String ] = ???
  final lazy val attr         : Schema_2_OneM[attr        , String , A, String ] = ???
  final lazy val ident        : Schema_2_OneM[ident       , String , A, String ] = ???
  final lazy val valueType    : Schema_2_OneM[valueType   , String , A, String ] = ???
  final lazy val cardinality  : Schema_2_OneM[cardinality , String , A, String ] = ???
  final lazy val doc          : Schema_2_OneM[doc         , String , A, String ] = ???
  final lazy val unique       : Schema_2_OneM[unique      , String , A, String ] = ???
  final lazy val isComponent  : Schema_2_OneM[isComponent , Boolean, A, Boolean] = ???
  final lazy val noHistory    : Schema_2_OneM[noHistory   , Boolean, A, Boolean] = ???
  final lazy val index        : Schema_2_OneM[index       , Boolean, A, Boolean] = ???
  final lazy val fulltext     : Schema_2_OneM[fulltext    , Boolean, A, Boolean] = ???
  
  final lazy val ident$       : Schema_2_OneO[ident$      , String , A, Option[String ]] = ???
  final lazy val valueType$   : Schema_2_OneO[valueType$  , String , A, Option[String ]] = ???
  final lazy val cardinality$ : Schema_2_OneO[cardinality$, String , A, Option[String ]] = ???
  final lazy val doc$         : Schema_2_OneO[doc$        , String , A, Option[String ]] = ???
  final lazy val unique$      : Schema_2_OneO[unique$     , String , A, Option[String ]] = ???
  final lazy val isComponent$ : Schema_2_OneO[isComponent$, Boolean, A, Option[Boolean]] = ???
  final lazy val noHistory$   : Schema_2_OneO[noHistory$  , Boolean, A, Option[Boolean]] = ???
  final lazy val index$       : Schema_2_OneO[index$      , Boolean, A, Option[Boolean]] = ???
  final lazy val fulltext$    : Schema_2_OneO[fulltext$   , Boolean, A, Option[Boolean]] = ???
  
  final lazy val t_           : Schema_1_OneT[t_          , Long   , A] = ???
  final lazy val tx_          : Schema_1_OneT[tx_         , Long   , A] = ???
  final lazy val txInstant_   : Schema_1_OneT[txInstant_  , Date   , A] = ???
  final lazy val attrId_      : Schema_1_OneT[attrId_     , Long   , A] = ???
  final lazy val a_           : Schema_1_OneT[a_          , String , A] = ???
  final lazy val part_        : Schema_1_OneT[part_       , String , A] = ???
  final lazy val nsFull_      : Schema_1_OneT[nsFull_     , String , A] = ???
  final lazy val ns_          : Schema_1_OneT[ns_         , String , A] = ???
  final lazy val attr_        : Schema_1_OneT[attr_       , String , A] = ???
  final lazy val ident_       : Schema_1_OneT[ident_      , String , A] = ???
  final lazy val valueType_   : Schema_1_OneT[valueType_  , String , A] = ???
  final lazy val cardinality_ : Schema_1_OneT[cardinality_, String , A] = ???
  final lazy val doc_         : Schema_1_OneT[doc_        , String , A] = ???
  final lazy val unique_      : Schema_1_OneT[unique_     , String , A] = ???
  final lazy val isComponent_ : Schema_1_OneT[isComponent_, Boolean, A] = ???
  final lazy val noHistory_   : Schema_1_OneT[noHistory_  , Boolean, A] = ???
  final lazy val index_       : Schema_1_OneT[index_      , Boolean, A] = ???
  final lazy val fulltext_    : Schema_1_OneT[fulltext_   , Boolean, A] = ???
}

trait Schema_1_OneM[Attr, t, A] extends Schema_1[A] with ExprOneM_1[Attr, t, A, Schema_1_OneM]
trait Schema_1_OneO[Attr, t, A] extends Schema_1[A] with ExprOneO_1[Attr, t, A, Schema_1_OneO]
trait Schema_1_OneT[Attr, t, A] extends Schema_1[A] with ExprOneT_1[Attr, t, A, Schema_1_OneT]

