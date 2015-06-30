package actors

import akka.actor._
import scala.concurrent.duration._
import scala.util.Random


object Warren {

  case object CreateRabbit
  case object RabbitsBreed

  case object RabbitDie

  def props(population: Int): Props = {
    Props(new Warren(population, breedRate = 1, deathRate = 1))

  }
}

class Warren(population: Int, breedRate: Double, deathRate: Double) extends Actor with ActorLogging {

  import context.dispatcher
  import actors.Warren._

  println("Warren Created")

  // un comment to have rabbits breed

  //val schedulerBreed = context.system.scheduler.schedule(1 seconds, 10 seconds, self, Warren.RabbitsBreed)
  //val schedulerDeath = context.system.scheduler.schedule(10 seconds, 15 seconds, self, Warren.RabbitDie)


  private var rabbits: List[ActorRef] = List.empty[ActorRef]
  addRabbits(population)

  override def receive: Receive = {
    case RabbitsBreed =>
      rabbitsMate()
    case RabbitDie => removeRabbit()
  }

  protected def addRabbits(rabbitsPop: Int): Unit = {
    for (rab <- 1 to rabbitsPop) {
      rabbits = rabbits :+ context.actorOf(Rabbit.props)
    }
  }

  protected def rabbitsMate(): Unit = {
    val amountOfRabbitsMated: Int = rabbits.length / 2
    println("nom of rabbits: "+ amountOfRabbitsMated)
    for (rab <- 1 to amountOfRabbitsMated) {
      val numOfRabbits: Int = Random.nextInt(8)
      addRabbits(numOfRabbits)
      println("--------------------------------------------")
      println(s" $numOfRabbits rabbits produced!")
      println(s"Population of rabbits: ${rabbits.length}")
    }
  }

  protected def removeRabbit(): Unit = {
    println("------------[Rabbits Die :(]------------------")

  }


  override def postStop(): Unit = {
    schedulerBreed.cancel()
    schedulerDeath.cancel()
  }

}