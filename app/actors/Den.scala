package actors

import scala.util.Random

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

object Den {

//  case object CreateFox
  case class KillFox(fox: Fox)
  case object Breed
  case class ChangeBreedRate(rate: Double)
  case class ChangeDeathRate(rate: Double)
  
  def props: Props = {
    Props(new Den)
  }

}

class Den(val population: Int = 2, var breedRate: Double = 0.5 + Random.nextDouble(), var deathRate: Double = 0.4 + Random.nextDouble()) extends Actor with ActorLogging{
  
  import Den._
  
    log.info("Den created")
    println("pop=" + population + ",brate=" + breedRate)
    private val pair: Int = 2
    
//    private val foxes = createFoxes 
     
 
    
    override def receive: Receive = {
//      case CreateFox => 
//        createFox()
      case KillFox(fox) => 
        killFox(fox)
      case Breed => 
        breed
      case ChangeBreedRate(rate) =>
        changeBreedRate(rate)
      case ChangeDeathRate(rate) =>
        changeDeathRate(rate)
      case _ => println("TODO")
    }
  
 
    
    protected def addFoxes(pop: Int): Unit = {
      for(_ <- 1 to pop) { 
      createFox
        }
      log.info("Foxes " + pop)
    }
         
//    protected def createFoxes: List[ActorRef] = 
//      List.empty[ActorRef]
    
      protected def createFox(): ActorRef = 
        context.actorOf(Fox.props)
      
      protected def killFox(fox: Fox): ActorRef = ???
      
      protected def breed: Unit = {
        addFoxes(population * (breedRate + 0.5).toInt * Random.nextInt(15)/pair)
      }
      
    
      protected def changeBreedRate(rate: Double) = {
        breedRate = rate
        log.info("breedrate adjusted to " + breedRate)
      }

      protected def changeDeathRate(rate: Double) = {
        deathRate = rate
        log.info("deathrate adjusted to " + deathRate)
      }
        
}
