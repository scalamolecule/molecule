package molecule.core.dataModel



sealed trait Relationship

sealed trait CardOneRelationship extends Relationship

case object OneToOne extends CardOneRelationship
case object OneToMany extends CardOneRelationship


sealed trait CardManyRelationship extends Relationship

case object ManyToOne extends CardManyRelationship

// Simple join table with just two foreign keys
case object ManyToMany extends CardManyRelationship
