package molecule.core.dataModel


sealed trait Relationship
case object OneToMany extends Relationship
case object ManyToOne extends Relationship
