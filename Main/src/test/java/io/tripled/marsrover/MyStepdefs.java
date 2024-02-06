package io.tripled.marsrover;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.tripled.marsrover.api.command.ApplicationService;
import io.tripled.marsrover.api.command.CreateSimulationCommand;
import io.tripled.marsrover.api.command.DriveCommand;
import io.tripled.marsrover.api.command.LandCommand;
import io.tripled.marsrover.api.message.RoverDrivingMessage;
import io.tripled.marsrover.api.presenter.RoverDrivingPresenter;
import io.tripled.marsrover.api.presenter.RoverLandingPresenter;
import io.tripled.marsrover.api.presenter.SimCreationPresenter;
import io.tripled.marsrover.api.presenter.StatePresenter;
import io.tripled.marsrover.businesslogic.simulation.SimulationRepository;
import io.tripled.marsrover.vocabulary.rover.Coordinate;
import io.tripled.marsrover.vocabulary.rover.Direction;
import io.tripled.marsrover.vocabulary.rover.Move;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

@CucumberContextConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MarsRoverApplication.class})
@SpringBootTest
public class MyStepdefs {

    private SimulationRepository simulationRepository;
    private ApplicationService applicationService;

    public MyStepdefs(SimulationRepository simulationRepository, ApplicationService applicationService){
        this.simulationRepository = simulationRepository;
        this.applicationService = applicationService;
    }

    @Given("A simulation of size {int}")
    public void aSimulationOfSize(int simulationSize){
        applicationService.handleCommand(new CreateSimulationCommand(simulationSize), new SimCreationPresenterImpl() );
    }

    @And("We land a rover on coordinates {int} {int}")
    public void weLandARoverOnCoordinatesXY(int x, int y) {
        applicationService.handleCommand(new LandCommand(new Coordinate(x,y)), new RoverLandingPresenterImpl() );
    }

    @When("We give the Rover \"R1\" the Instruction {string} {int}")
    public void weGiveTheRoverTheInstructionAmount(String direction, int steps) {
        applicationService.handleCommand(new DriveCommand(List.of(new Move(Direction.DIRECTION.parseDirection(direction), steps))), new RoverDrivingPresenterImpl());
    }

    @Then("The Rover \"R1\" is at {int} {int} with orientation {string}")
    public void theRoverIsAtWithOrientation(int x, int y, String heading) {
        new StatePresenterImpl().stateMessage(applicationService.getSimulationSize(), applicationService.getRoverSate(0));
    }

    @Then("No rover should be present in the simulation")
    public void noRoverShouldBePresentInTheSimulation() {
        new RoverLandingPresenterImpl().roverLandingErrorOutOfBounds(applicationService.getRoverSate(0).roverCoordinate().x(),applicationService.getRoverSate(0).roverCoordinate().y() ,applicationService.getSimulationSize());
    }

    @When("We give the Rover \"R1\" the Instruction")
    public void weGiveTheRoverTheInstruction(DataTable dataTable) {

        List<List<String>> cellsInListOfList = dataTable.cells();
        List<String> cellsInList = cellsInListOfList.getFirst();

        for(int i = 1; i < cellsInListOfList.size(); i++){
            weGiveTheRoverTheInstructionAmount(cellsInListOfList.get(i).get(0), Integer.parseInt(cellsInListOfList.get(i).get(1)));
        }

        System.out.println(cellsInList);
    }
}
