package controllers

import play.api.mvc._
import akka.actor.{ActorRef, ActorSystem}
import actors.SimManager
import actors.Den

class Application extends Controller {


  def index = Action {

    val system = ActorSystem("life-cycle-sim-system")
    val simMan = system.actorOf(SimManager.props, "SimManager")

//    simMan ! SimManager.CreateDen
    simMan ! SimManager.CreateWarren
    simMan ! SimManager.Multiply
  

    Ok(views.html.index())
  }

}
