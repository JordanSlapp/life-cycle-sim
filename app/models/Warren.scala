package models

case class Warren(
                   population: Int,
                   breedRate: Double,
                   DeathRate: Double) {

  def this() = this(0,0,0)

}
object EmptyWarren extends Warren