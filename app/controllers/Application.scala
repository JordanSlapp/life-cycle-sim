package controllers

import play.api.mvc._
import akka.actor.{ActorRef, ActorSystem}
import actors.SimManager

class Application extends Controller {


  def index = Action {

    val system = ActorSystem("life-cycle-sim-system")
    val simMan = system.actorOf(SimManager.props, "SimManager")

    simMan ! SimManager.CreateFox
    simMan ! SimManager.CreateRabbit

    Ok(views.html.index())
  }

}
