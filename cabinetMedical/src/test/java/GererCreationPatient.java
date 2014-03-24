package test.java;
import java.util.Arrays;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import org.junit.runner.RunWith;

import com.iut.cabinet.application.GererPatientCtrl;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;


public class GererCreationPatient extends JUnitStories{
	public GererCreationPatient()
	{
		super();
	}
	// Cette m�thode permet d'avoir le rapport dans la console
	@Override
	  public Configuration configuration() {

        Configuration configuration = new MostUsefulConfiguration();
       
        StoryReporterBuilder storyReporterBuilder;
        storyReporterBuilder = new StoryReporterBuilder();
        storyReporterBuilder.withDefaultFormats();
        storyReporterBuilder.withFormats(org.jbehave.core.reporters.Format.CONSOLE); 
        configuration.useStoryReporterBuilder(storyReporterBuilder);
        configuration.usePendingStepStrategy(new FailingUponPendingStep());  
        
        return configuration;
    }

	
	@Override
    public InjectableStepsFactory stepsFactory() {
    return new InstanceStepsFactory(configuration(), new GererPatientCtrl());
    }
	@Override
	protected List<String> storyPaths() {
		String codeLocation = CodeLocations.codeLocationFromClass(this.getClass()).getFile();
		List<String> storyPaths =  new StoryFinder().findPaths(codeLocation,Arrays.asList("**/*.story"), Arrays.asList(""));
    return storyPaths; 
	}
}
