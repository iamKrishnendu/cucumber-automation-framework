package scenario;

import io.cucumber.java.Scenario;

public class ScenarioFactory {

    public static final ScenarioFactory scenarioFactory = new ScenarioFactory();

    public ScenarioFactory(){}

    public static ScenarioFactory getInstance(){
        return scenarioFactory;
    }

    private ThreadLocal<Scenario> scenarioThreadLocal = new ThreadLocal<>();

    public synchronized Scenario getScenarioThreadLocal(){
        return scenarioThreadLocal.get();
    }
    public synchronized void setScenarioThreadLocal(Scenario scenario){
         scenarioThreadLocal.set(scenario);
    }
    public synchronized void closeScenario(){
        scenarioThreadLocal.remove();
    }
}
